package ru.outofmemoryguru.statement.controller;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.tomakehurst.wiremock.WireMockServer;
import com.github.tomakehurst.wiremock.client.WireMock;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import static com.github.tomakehurst.wiremock.client.WireMock.aResponse;
import static com.github.tomakehurst.wiremock.client.WireMock.urlEqualTo;
import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static ru.outofmemoryguru.statement.testdata.DtoTestData.*;
import static ru.outofmemoryguru.statement.testdata.JsonTestData.EXPECTED4_OFFERS_JSON;

@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
class StatementControllerTestOne {

    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private ObjectMapper objectMapper;

    private static WireMockServer wireMockServer = new WireMockServer(0);

    private static final String URI_OFFERS_MVC = "/statement/statement";
    private static final String URI_SELECT_MVC = "/statement/offer";
    private static final String URI_OFFERS_REJECT_MVC = "/statement/offer/reject";
    private static final String URI_OFFERS_FROM_DEAL = "/deal/statement";
    private static final String URI_SELECT_FROM_DEAL = "/deal/offer/select";

    @BeforeAll
    static void startWireMock() {
        wireMockServer.start();
    }

    @AfterAll
    static void stopWireMock() {
        wireMockServer.stop();
    }

    @DynamicPropertySource
    static void overrideProperties(DynamicPropertyRegistry registry) {
        registry.add("deal.base-url",
                () -> "http://localhost:" + wireMockServer.port());
    }

    @BeforeEach
    void resetStubs() {
        wireMockServer.resetAll();
    }

    @Test
    @DisplayName("POST /statement/statement → 200 и список предложений")
    void forwardToDealMsLoanOffers() throws Exception {
        wireMockServer.stubFor(WireMock.post(urlEqualTo(URI_OFFERS_FROM_DEAL))
                .willReturn(aResponse()
                        .withStatus(200)
                        .withHeader("Content-Type", "application/json")
                        .withBody(EXPECTED4_OFFERS_JSON)));

        mockMvc.perform(post(URI_OFFERS_MVC)
                        .contentType(APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(loanStatementDtoFromStatementAa11)))
                .andExpect(status().isOk())
                .andExpect(content().json(EXPECTED4_OFFERS_JSON));
    }

    @Test
    @DisplayName("POST /statement/offer → 200")
    void selectOfferSuccess() throws Exception {
        wireMockServer.stubFor(WireMock.post(urlEqualTo(URI_SELECT_FROM_DEAL))
                .willReturn(aResponse().withStatus(200)));

        mockMvc.perform(post(URI_SELECT_MVC)
                        .contentType(APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(loanOfferDto1)))
                .andExpect(status().isOk());
    }

    @Test
    @DisplayName("POST /statement/statement → 400 при 12 невалидных параметов")
    void offerPreCalculationValidation12Err() throws Exception {

        MvcResult result = mockMvc.perform(post(URI_OFFERS_MVC)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(loanStatementRequestDtoInvalid12Err)))
                .andExpect(status().isBadRequest())
                .andReturn();

        String responseBody = result.getResponse().getContentAsString();

        JsonNode root = objectMapper.readTree(responseBody);
        JsonNode errorsNode = root.get("errors");

        int actualErrorCount = errorsNode.size();
        assertThat(actualErrorCount).isEqualTo(EXPECTED_VALIDATION_ERR_12_NUMBER);
    }

    @Test
    @DisplayName("POST /statement/offers/reject → 200")
    void clientRejectOffers() throws Exception {
        mockMvc.perform(post(URI_OFFERS_REJECT_MVC)).andExpect(status().isOk());
    }
}