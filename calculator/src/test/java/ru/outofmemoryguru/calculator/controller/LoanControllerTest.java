package ru.outofmemoryguru.calculator.controller;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import ru.outofmemoryguru.calculator.controller.dto.CreditDto;
import ru.outofmemoryguru.calculator.controller.dto.LoanOfferModelService;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static ru.outofmemoryguru.calculator.testdata.DtoTestData.*;

@SpringBootTest
@AutoConfigureMockMvc
public class LoanControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void offerPreCalculationValidationOk() throws Exception {

        mockMvc.perform(post("/calculator/offers")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(loanStatementRequestDto1)))
                .andExpect(status().isOk());
    }

    @Test
    void offerPreCalculationValidation12Err() throws Exception {

        MvcResult result = mockMvc.perform(post("/calculator/offers")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(loanStatementRequestDtoInvalid12Err)))
                .andExpect(status().isBadRequest())
                .andReturn();

        String responseBody = result.getResponse().getContentAsString();

        JsonNode root = objectMapper.readTree(responseBody);
        JsonNode errorsNode = root.get("errors");

        int actualErrorCount = errorsNode.size();
        assertThat(actualErrorCount).isEqualTo(EXPECTED_VALIDATION_ERR_12);
    }

    @Test
    void offerPreCalculationResponse() throws Exception {
        String responseJson = mockMvc.perform(post("/calculator/offers")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(loanStatementRequestDto1)))
                .andExpect(status().isOk())
                .andReturn()
                .getResponse()
                .getContentAsString();

        List<LoanOfferModelService> actualOffers = objectMapper.readValue(
                responseJson,
                new TypeReference<>() {
                }
        );

        for (int i = 0; i < expectedOffers.size(); i++) {
            LoanOfferModelService expected = expectedOffers.get(i);
            LoanOfferModelService actual = actualOffers.get(i);

            assertEquals(expected.getRequestedAmount(), actual.getRequestedAmount());
            assertEquals(expected.getTotalAmount(), actual.getTotalAmount());
            assertEquals(expected.getTerm(), actual.getTerm());
            assertEquals(expected.getMonthlyPayment(), actual.getMonthlyPayment());
            assertEquals(expected.getRate(), actual.getRate());
            assertEquals(expected.isInsuranceEnabled(), actual.isInsuranceEnabled());
            assertEquals(expected.isSalaryClient(), actual.isSalaryClient());
        }
    }

    @Test
    void calculateCreditValidationOk() throws Exception {

        mockMvc.perform(post("/calculator/calc")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(scoringDataDto1)))
                .andExpect(status().isOk());
    }

    @Test
    void calculateCreditValidationOffers22Err() throws Exception {

        MvcResult result = mockMvc.perform(post("/calculator/calc")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(scoringDataDtoInvalid22Err)))
                .andExpect(status().isBadRequest())
                .andReturn();

        String responseBody = result.getResponse().getContentAsString();

        JsonNode root = objectMapper.readTree(responseBody);
        JsonNode errorsNode = root.get("errors");

        int actualErrorCount = errorsNode.size();
        errorsNode.forEach(error -> System.out.println(error.toString()));
        assertThat(actualErrorCount).isEqualTo(EXPECTED_VALIDATION_ERR_23);
    }

    @Test
    void calculateCreditResponse() throws Exception {
        String responseJson = mockMvc.perform(post("/calculator/calc")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(scoringDataDto1)))
                .andExpect(status().isOk())
                .andReturn()
                .getResponse()
                .getContentAsString();

        CreditDto actualCreditDto = objectMapper.readValue(
                responseJson,
                new TypeReference<>() {
                }
        );
        assertEquals(expectedCreditDto, actualCreditDto);
    }
}
