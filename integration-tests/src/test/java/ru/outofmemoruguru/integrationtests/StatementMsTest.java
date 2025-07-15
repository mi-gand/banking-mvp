
package ru.outofmemoruguru.integrationtests;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testcontainers.junit.jupiter.Testcontainers;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static ru.outofmemoruguru.integrationtests.testdata.JsonTestData.*;

@Testcontainers
@TestInstance(TestInstance.Lifecycle.PER_METHOD)
class StatementMsTest extends AbstractTestContainersPrepare {

    private final static Logger log = LoggerFactory.getLogger(StatementMsTest.class);

    private final String URL_OFFERS_STATEMENT;
    private final String URL_SELECT_OFFER_STATEMENT;

    private final String URL_STATEMENT;
    private final String DELETE_STATEMENT_ID = "(?m)\\s*\"statementId\"\\s*:\\s*\"[^\"]*\",?";

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
    @DisplayName("/statement/statement -> List<LoanOfferDto>")
    void forwardToDealMsLoanOffers() throws Exception {
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(URL_OFFERS_STATEMENT))
                .header("Content-Type", "application/json")
                .POST(HttpRequest.BodyPublishers.ofString(LOAN_STATEMENT_JSON))
                .build();

        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

        String actual4LoanOffers = response.body();

        String actual4LoanOffersWithoutId = actual4LoanOffers.replaceAll(DELETE_STATEMENT_ID, "")
                .replaceAll("\\s+", "");
        String expected4OffersJsonWithoutId = EXPECTED4_OFFERS_JSON.replaceAll(DELETE_STATEMENT_ID, "")
                .replaceAll("\\s+", "");
        System.out.println(dealContainer.getLogs());
        System.out.println(statementContainer.getLogs());
        assertEquals(expected4OffersJsonWithoutId, actual4LoanOffersWithoutId);

    }

    @Test
    @DisplayName("/statement/offer -> 200 ")
    void selectOffer() throws Exception {
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(URL_SELECT_OFFER_STATEMENT))
                .header("Content-Type", "application/json")
                .POST(HttpRequest.BodyPublishers.ofString(OFFER1_JSON))
                .build();

        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
        assertEquals(200, response.statusCode());

    }
}

