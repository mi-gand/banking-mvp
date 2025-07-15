
package ru.outofmemoruguru.integrationtests;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static ru.outofmemoruguru.integrationtests.testdata.JsonTestData.EXPECTED4_OFFERS_JSON;
import static ru.outofmemoruguru.integrationtests.testdata.JsonTestData.LOAN_STATEMENT_JSON;

@Disabled
class StatementMsTest extends AbstractTestContainersPrepare {

    private final static Logger log = LoggerFactory.getLogger(StatementMsTest.class);

    private final String URL_OFFERS_STATEMENT;
    private final String URL_SELECT_OFFER_STATEMENT;

    //private final ObjectMapper mapper = new ObjectMapper();
    private final String URL_STATEMENT;

    private StatementMsTest() {
        URL_STATEMENT = "http://localhost:" + statementContainer.getMappedPort(8080);
        URL_OFFERS_STATEMENT = URL_STATEMENT + "/statement/statement";
        URL_SELECT_OFFER_STATEMENT = URL_STATEMENT + "/statement/offer";
    }

    @Test
    void containersWorks() {

        assertTrue(postgres.isRunning(), "Postgres контейнер не работает");
        log.debug("Порт базы: {}. {}", postgres.getMappedPort(5432), getTimeFromStart());
        log.debug("JDBC URL: {}", postgres.getJdbcUrl());
        log.debug("Пользователь: {}", postgres.getUsername());
        System.out.println(calculatorContainer.getLogs());
        assertTrue(calculatorContainer.isRunning(), "calculator контейнер не работает");
        log.debug("Запущен МС calculator. {}", getTimeFromStart());
        System.out.println(dealContainer.getLogs());
        assertTrue(dealContainer.isRunning(), "deal контейнер не работает");

        System.out.println(statementContainer.getLogs());
        assertTrue(statementContainer.isRunning(), "statement контейнер не работает");

    }


    @Test
    @DisplayName("POST /statement/statement request LoanStatement, response List<LoanOfferDto>")
    @Disabled
    void forwardToDealMsLoanOffers() throws Exception {
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(URL_OFFERS_STATEMENT))
                .header("Content-Type", "application/json")
                .POST(HttpRequest.BodyPublishers.ofString(LOAN_STATEMENT_JSON))
                .build();

        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

        String actual4LoanOffers = response.body();


        assertEquals(EXPECTED4_OFFERS_JSON, actual4LoanOffers); //совпадать не будет из-за ID



/*        //todo перепроверить с deal
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
        assertTrue(previousStatusHistory.size() == 1 && updatedStatement.getStatusHistory().size() == 2);*/
    }

    @Test
    void selectOffer() throws Exception {
/*        setupWireMockOffers();
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
                .containsExactlyInAnyOrderElementsOf(expected4OffersDto);*/
    }

/*    void setupWireMockOffers() {
        wireMockServer.resetAll();
        wireMockServer.stubFor(com.github.tomakehurst.wiremock.client.WireMock
                .post(urlEqualTo(URI_OFFERS_FROM_CALCULATOR))
                .willReturn(aResponse()
                        .withStatus(200)
                        .withHeader("Content-Type", "application/json")
                        .withBody(EXPECTED4_OFFERS_JSON)));
    }*/



}

