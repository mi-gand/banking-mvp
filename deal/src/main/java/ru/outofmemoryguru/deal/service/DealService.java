package ru.outofmemoryguru.deal.service;

import lombok.RequiredArgsConstructor;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;
import ru.outofmemoryguru.deal.controller.converter.loanOffer.LoanOfferConverter;
import ru.outofmemoryguru.deal.controller.dto.LoanOfferDto;
import ru.outofmemoryguru.deal.model.Client;
import ru.outofmemoryguru.deal.model.Statement;
import ru.outofmemoryguru.deal.model.enumdata.ChangeType;
import ru.outofmemoryguru.deal.model.jsonb.AppliedOffer;
import ru.outofmemoryguru.deal.model.jsonb.Employment;
import ru.outofmemoryguru.deal.model.jsonb.Passport;
import ru.outofmemoryguru.deal.model.jsonb.StatusHistory;
import ru.outofmemoryguru.deal.repository.ClientRepository;
import ru.outofmemoryguru.deal.repository.StatementRepository;
import ru.outofmemoryguru.deal.service.to.LoanOfferServiceModel;
import ru.outofmemoryguru.deal.service.to.LoanStatementServiceModel;
import ru.outofmemoryguru.deal.service.to.converter.fromMsCalculator.ConverterFromCalculator;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.UUID;

import static ru.outofmemoryguru.deal.model.enumdata.ApplicationStatus.PREAPPROVAL;

@Service
@RequiredArgsConstructor
public class DealService {
    private final ClientRepository clientRepository;
    private final StatementRepository statementRepository;
    private final RestClient restClient;
    private final String URI_OFFERS = "/calculator/offers";
    private final LoanOfferConverter loanOfferConverter = new LoanOfferConverter();

    public List<LoanOfferServiceModel> creditPreCalculation(LoanStatementServiceModel requestDto) {
        Client rawClient = saveRawClient(requestDto);
        Statement statement = saveRawStatement(requestDto, rawClient);
        List<LoanOfferServiceModel> offersMsCalculator = requestLoanOffers(requestDto)
                .stream()
                .map(loanOfferConverter::convertToServiceModel)
                .sorted(Comparator.comparing(LoanOfferServiceModel::getRate))
                .toList();

        List<AppliedOffer> offersMsCalculatorToAppliedOffersMsDeal = offersMsCalculator
                .stream()
                .map(ConverterFromCalculator::convertToAppliedOffer)
                .peek(x -> x.setStatementId(statement.getStatementId()))
                .toList();


        statement.setAppliedOffer(offersMsCalculatorToAppliedOffersMsDeal);
        statementRepository.save(statement);
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

        statement.setAppliedOffer(new ArrayList<>());

        StatusHistory statusHistory = new StatusHistory();
        statusHistory.setStatus(statement.getStatus());
        statusHistory.setTime(LocalDate.now());
        statusHistory.setChangeType(ChangeType.AUTOMATIC);  //todo по какой логике назначать поле?
        statement.setStatusHistory(statusHistory);

        statementRepository.save(statement);
        return statement;
    }

    private List<LoanOfferDto> requestLoanOffers(LoanStatementServiceModel requestDto) {
        return restClient
                .post()
                .uri(URI_OFFERS)
                .body(requestDto)
                .retrieve()
                .body(new ParameterizedTypeReference<List<LoanOfferDto>>() {
                });
    }

}
