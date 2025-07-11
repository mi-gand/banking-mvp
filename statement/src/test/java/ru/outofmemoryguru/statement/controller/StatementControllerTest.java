package ru.outofmemoryguru.statement.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import ru.outofmemoryguru.statement.AbstractContainerPostgres;
import ru.outofmemoryguru.statement.controller.dto.LoanOfferDto;
import ru.outofmemoryguru.statement.model.Statement;
import ru.outofmemoryguru.statement.model.enumdata.ApplicationStatus;
import ru.outofmemoryguru.statement.model.jsonb.StatusHistory;
import ru.outofmemoryguru.statement.repository.StatementRepository;

import java.util.List;

import static com.github.tomakehurst.wiremock.client.WireMock.aResponse;
import static com.github.tomakehurst.wiremock.client.WireMock.urlEqualTo;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static ru.outofmemoryguru.statement.testdata.DtoTestData.*;
import static ru.outofmemoryguru.statement.testdata.JsonTestData.EXPECTED4_OFFERS_JSON;

@SpringBootTest
@AutoConfigureMockMvc
class StatementControllerTest extends AbstractContainerPostgres {

    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private ObjectMapper objectMapper;
    @Autowired
    private StatementRepository statementRepository;

    private final String URI_OFFERS_STATEMENT = "/statement/statement";
    private final String URI_SELECT_OFFER_STATEMENT = "/statement/offer";

    private final String URI_OFFERS_FROM_CALCULATOR = "/calculator/offers";


/*    private final String URI_OFFERS_FROM_DEAL = "/deal/statement";
    private final String URI_OFFER_SELECT_FROM_DEAL = "/deal/offer/select";*/

    @Test
    void forwardToDealMsLoanOffers() throws Exception {
        //todo перепроверить с deal
        List<StatusHistory> previousStatusHistory = statementForDtoTest.getStatusHistory();

        statementRepository.save(statementForDtoTest);

        mockMvc.perform(post(URI_SELECT_OFFER_STATEMENT)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(loanOfferDto1)))
                .andExpect(status().isOk());

        Statement updatedStatement = statementRepository.findById(statementForDtoTest.getStatementId()).orElseThrow();

        assertEquals(ApplicationStatus.APPROVED, updatedStatement.getStatus());
        assertThat(updatedStatement)
                .usingRecursiveComparison()
                .ignoringFields("statementId", "status", "appliedOffer", "statusHistory")
                .isEqualTo(statementForDtoTest);
        assertTrue(previousStatusHistory.size() == 1 && updatedStatement.getStatusHistory().size() == 2);
    }

    @Test
    void selectOffer() throws Exception {
        setupWireMockOffers();
        String responseJson = mockMvc.perform(org.springframework.test.web.servlet.request.MockMvcRequestBuilders
                        .post(URI_OFFERS_STATEMENT)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(loanStatementDtoFromStatementAa11)))
                .andExpect(status().isOk())
                .andReturn()
                .getResponse()
                .getContentAsString();

        List<LoanOfferDto> actualOffers = objectMapper.readValue(
                responseJson,
                new com.fasterxml.jackson.core.type.TypeReference<>() {
                }
        );
        assertThat(actualOffers)
                .usingElementComparatorIgnoringFields("statementId")
                .containsExactlyInAnyOrderElementsOf(expected4OffersDto);
    }

    void setupWireMockOffers() {
        wireMockServer.resetAll();
        wireMockServer.stubFor(com.github.tomakehurst.wiremock.client.WireMock
                .post(urlEqualTo(URI_OFFERS_FROM_CALCULATOR))
                .willReturn(aResponse()
                        .withStatus(200)
                        .withHeader("Content-Type", "application/json")
                        .withBody(EXPECTED4_OFFERS_JSON)));
    }


}