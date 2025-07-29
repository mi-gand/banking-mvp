package ru.outofmemoryguru.deal.service;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;
import ru.outofmemoryguru.deal.controller.converter.loanOffer.LoanOfferConverter;
import ru.outofmemoryguru.deal.controller.dto.CreditDto;
import ru.outofmemoryguru.deal.controller.dto.EmploymentDto;
import ru.outofmemoryguru.deal.controller.dto.LoanOfferDto;
import ru.outofmemoryguru.deal.model.Client;
import ru.outofmemoryguru.deal.model.Credit;
import ru.outofmemoryguru.deal.model.Statement;
import ru.outofmemoryguru.deal.model.enumdata.ApplicationStatus;
import ru.outofmemoryguru.deal.model.enumdata.ChangeType;
import ru.outofmemoryguru.deal.model.jsonb.Employment;
import ru.outofmemoryguru.deal.model.jsonb.Passport;
import ru.outofmemoryguru.deal.model.jsonb.StatusHistory;
import ru.outofmemoryguru.deal.repository.ClientRepository;
import ru.outofmemoryguru.deal.repository.CreditRepository;
import ru.outofmemoryguru.deal.repository.StatementRepository;
import ru.outofmemoryguru.deal.service.to.FinishRegistrationServiceModel;
import ru.outofmemoryguru.deal.service.to.LoanOfferServiceModel;
import ru.outofmemoryguru.deal.service.to.LoanStatementServiceModel;
import ru.outofmemoryguru.deal.service.to.ScoringDataServiceModel;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.UUID;

import static ru.outofmemoryguru.commondata.kafka.mappings.ActionToTopicMap.CREATE_DOCUMENTS;
import static ru.outofmemoryguru.commondata.kafka.mappings.ActionToTopicMap.FINISH_REGISTRATION;
import static ru.outofmemoryguru.deal.model.enumdata.ApplicationStatus.APPROVED;
import static ru.outofmemoryguru.deal.model.enumdata.ApplicationStatus.PREAPPROVAL;
import static ru.outofmemoryguru.deal.model.enumdata.ChangeType.AUTOMATIC;
import static ru.outofmemoryguru.deal.model.enumdata.ChangeType.MANUAL;
import static ru.outofmemoryguru.deal.model.enumdata.CreditStatus.CALCULATED;

@Service
@Slf4j
@RequiredArgsConstructor
public class DealService {
    private final ClientRepository clientRepository;
    private final StatementRepository statementRepository;
    private final CreditRepository creditRepository;
    private final RestClient restClient;
    private final EmailDealService emailDealService;
    private final ModelMapper modelMapper;
    private final String URI_OFFERS_FROM_CALCULATOR = "/calculator/offers";
    private final String URI_CALC_FROM_CALCULATOR = "/calculator/draft-calc";
    private final LoanOfferConverter loanOfferConverter = new LoanOfferConverter();

    public List<LoanOfferServiceModel> creditPreCalculation(LoanStatementServiceModel requestDto) {
        Client rawClient = saveRawClient(requestDto);
        Statement statement = saveRawStatement(requestDto, rawClient);

        List<LoanOfferServiceModel> offersMsCalculator = requestLoanOffers(requestDto)
                .stream()
                .map(loanOfferConverter::convertToServiceModel)
                .peek(x -> x.setStatementId(statement.getStatementId()))
                .sorted(Comparator.comparing(LoanOfferServiceModel::getRate))
                .toList();

        statementRepository.save(statement);
        log.info("Pre-calculation done for statementId={}: offers count = {}",
                statement.getStatementId(), offersMsCalculator.size());
        return offersMsCalculator;
    }

    private Client saveRawClient(LoanStatementServiceModel requestDto) {
        Client client = new Client();
        client.setClientId(UUID.randomUUID());
        client.setFirstName(requestDto.getFirstName());
        client.setLastName(requestDto.getLastName());
        client.setMiddleName(requestDto.getMiddleName());
        client.setEmail(requestDto.getEmail());
        client.setGender(null);
        client.setMaritalStatus(null);
        client.setDependentAmount(null);

        Passport passport = new Passport();
        passport.setNumber(requestDto.getPassportNumber());
        passport.setSeries(requestDto.getPassportSeries());
        passport.setIssue_branch(null);
        passport.setIssue_date(null);

        client.setPassportId(passport);

        Employment employment = new Employment();
        employment.setStatus(null);
        employment.setEmployerInn(null);
        employment.setSalary(null);
        employment.setPosition(null);
        employment.setWorkExperienceTotal(null);
        employment.setWorkExperienceCurrent(null);

        client.setEmploymentId(employment);
        client.setAccountNumber(null);

        clientRepository.save(client);
        return client;
    }

    private Statement saveRawStatement(LoanStatementServiceModel requestDto, Client client) {
        Statement statement = new Statement();
        statement.setStatementId(UUID.randomUUID());
        statement.setClientId(client.getClientId());
        statement.setCreditId(null);
        statement.setStatus(PREAPPROVAL);
        statement.setCreationDate(LocalDate.now());

        statement.setAppliedOffer(null);

        StatusHistory statusHistory = new StatusHistory();
        statusHistory.setStatus(statement.getStatus());
        statusHistory.setTime(LocalDateTime.now());
        statusHistory.setChangeType(ChangeType.AUTOMATIC);
        List<StatusHistory> statusHistoryList = new ArrayList<>();
        statusHistoryList.add(statusHistory);
        statement.setStatusHistory(statusHistoryList);

        statementRepository.save(statement);
        return statement;
    }

    private List<LoanOfferDto> requestLoanOffers(LoanStatementServiceModel requestDto) {
        return restClient
                .post()
                .uri(URI_OFFERS_FROM_CALCULATOR)
                .body(requestDto)
                .retrieve()
                .body(new ParameterizedTypeReference<List<LoanOfferDto>>() {
                });
    }

    public void selectOffer(LoanOfferServiceModel to) {
        Statement statement = statementRepository.findById(to.getStatementId())
                .orElseThrow(() -> new EntityNotFoundException("Statement id " + to.getStatementId() + " not found"));

        statementRepository.save(updateStatusHistoryStatement(statement, APPROVED, MANUAL));
        emailDealService.sendToKafka(statement.getStatementId().toString(), FINISH_REGISTRATION);
    }

    private Statement updateStatusHistoryStatement(Statement statement, ApplicationStatus status, ChangeType changeType) {
        StatusHistory updatedStatusHistory = new StatusHistory();
        statement.setStatus(status);
        updatedStatusHistory.setTime(LocalDateTime.now());
        updatedStatusHistory.setStatus(status);
        updatedStatusHistory.setChangeType(changeType);
        statement.getStatusHistory().add(updatedStatusHistory);
        return statement;
    }

    //@PostMapping("/calculate/{statementId}")
    public void creditFinalCalculation(FinishRegistrationServiceModel to, String statementId) {
        Statement statement = statementRepository.findById(UUID.fromString(statementId))
                .orElseThrow(() -> new EntityNotFoundException("Statement id " + statementId + " not found"));

        ScoringDataServiceModel scoringDataServiceModel = saturateScoringData(to, statement);

        log.info("Request to calculator: {}", scoringDataServiceModel);
        Credit newCredit = calculateCreditFromMsCalculator(scoringDataServiceModel);
        log.info("Response from calculator: {}", newCredit);
        newCredit.setCredit_id(UUID.randomUUID());
        creditRepository.save(newCredit);
        statement.setStatus(APPROVED);

        statementRepository.save(updateStatusHistoryStatement(statement, APPROVED, AUTOMATIC));
        log.info("Statement status set to APPROVED for statementId={}", statementId);
        emailDealService.sendToKafka(statementId, CREATE_DOCUMENTS);
    }

    private ScoringDataServiceModel saturateScoringData(FinishRegistrationServiceModel to, Statement statement) {
        ScoringDataServiceModel scoringDataServiceModel = new ScoringDataServiceModel();
        Client client = clientRepository.findById(statement.getClientId())
                .orElseThrow(() -> new EntityNotFoundException("Client id " + statement.getClientId() + " not found"));

        scoringDataServiceModel.setFirstName(client.getFirstName());
        scoringDataServiceModel.setLastName(client.getLastName());
        scoringDataServiceModel.setMiddleName(client.getMiddleName());
        scoringDataServiceModel.setBirthdate(client.getBirthDate());
        scoringDataServiceModel.setPassportSeries(client.getPassportId().getSeries());
        scoringDataServiceModel.setPassportNumber(client.getPassportId().getNumber());

        scoringDataServiceModel.setGender(to.getGender());
        scoringDataServiceModel.setMaritalStatus(to.getMaritalStatus());
        scoringDataServiceModel.setDependentAmount(to.getDependentAmount());
        scoringDataServiceModel.setPassportIssueDate(to.getPassportIssueDate());
        scoringDataServiceModel.setPassportIssueBranch(to.getPassportIssueBranch());
        scoringDataServiceModel.setEmployment(modelMapper.map(to.getEmployment(), EmploymentDto.class));
        scoringDataServiceModel.setAccountNumber(to.getAccountNumber());

        scoringDataServiceModel.setAmount(statement.getAppliedOffer().getRequestedAmount());
        scoringDataServiceModel.setTerm(statement.getAppliedOffer().getTerm());
        scoringDataServiceModel.setInsuranceEnabled(statement.getAppliedOffer().isInsuranceEnabled());
        scoringDataServiceModel.setSalaryClient(statement.getAppliedOffer().isSalaryClient());

        return scoringDataServiceModel;
    }

    private Credit calculateCreditFromMsCalculator(ScoringDataServiceModel scoringDataServiceModel) {
        CreditDto creditDto = restClient
                .post()
                .uri(URI_CALC_FROM_CALCULATOR)
                .body(scoringDataServiceModel)
                .retrieve()
                .body(new ParameterizedTypeReference<CreditDto>() {
                });
        Credit credit = modelMapper.map(creditDto, Credit.class);
        credit.setCreditStatus(CALCULATED);
        return credit;
    }
}
