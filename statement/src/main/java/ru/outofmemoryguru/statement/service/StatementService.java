package ru.outofmemoryguru.statement.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;
import ru.outofmemoryguru.statement.controller.dto.LoanOfferDto;
import ru.outofmemoryguru.statement.controller.dto.LoanStatementRequestDto;
import ru.outofmemoryguru.statement.service.to.LoanOfferServiceModel;
import ru.outofmemoryguru.statement.service.to.LoanStatementServiceModel;

import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class StatementService {

    private final RestClient restClient;
    private final ModelMapper modelMapper;
    private final String URI_OFFERS_FROM_DEAL = "/deal/statement";
    private final String URI_OFFER_SELECT_FROM_DEAL = "/deal/offer/select";

    public List<LoanOfferServiceModel> forwardToDealMsLoanOffers(LoanStatementServiceModel requestServiceModel) {
        return requestLoanOffers(requestServiceModel)
                .stream()
                .map(x -> modelMapper.map(x, LoanOfferServiceModel.class))
                .toList();
    }

    private List<LoanOfferDto> requestLoanOffers(LoanStatementServiceModel serviceModel) {
        return restClient
                .post()
                .uri(URI_OFFERS_FROM_DEAL)
                .body(modelMapper.map(serviceModel, LoanStatementRequestDto.class))
                .retrieve()
                .body(new ParameterizedTypeReference<List<LoanOfferDto>>() {
                });
    }

    public void selectOffer(LoanOfferServiceModel to) {
        requestLoanOfferSelect(to);
    }

    private void requestLoanOfferSelect(LoanOfferServiceModel serviceModel) {
        ResponseEntity<Void> resp = restClient.post()
                .uri(URI_OFFER_SELECT_FROM_DEAL)
                .body(modelMapper.map(serviceModel, LoanOfferServiceModel.class))
                .retrieve()
                .toBodilessEntity();

        if (!resp.getStatusCode().is2xxSuccessful()){
            throw new UnsupportedOperationException("Ошибка на стороне Deal: " + resp.getStatusCode());
        }
    }

    public void clientRejectOffer(){
        log.info("Клиент отказался от предложения. Заглушка на будущую логику");
    }
}
