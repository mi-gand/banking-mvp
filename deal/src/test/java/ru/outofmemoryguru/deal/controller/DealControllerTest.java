package ru.outofmemoryguru.deal.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import ru.outofmemoryguru.deal.AbstractContainerPostgres;
import ru.outofmemoryguru.deal.controller.dto.LoanOfferDto;
import ru.outofmemoryguru.deal.model.Statement;
import ru.outofmemoryguru.deal.model.enumdata.ApplicationStatus;
import ru.outofmemoryguru.deal.model.jsonb.AppliedOffer;
import ru.outofmemoryguru.deal.model.jsonb.StatusHistory;
import ru.outofmemoryguru.deal.repository.StatementRepository;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static ru.outofmemoryguru.deal.testdata.DtoTestData.*;


@AutoConfigureMockMvc

public class DealControllerTest extends AbstractContainerPostgres {

    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private ObjectMapper objectMapper;
    @Autowired
    private StatementRepository statementRepository;

    private static final String DEAL_STATEMENT = "/deal/statement";
    private static final String DEAL_OFFER_SELECT = "/deal/offer/select";
    private static final String DEAL_CALCULATE = "/deal/calculate/";

    @Test
    void creditPreCalculation() throws Exception {

        String responseJson = mockMvc.perform(post(DEAL_STATEMENT)
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

    @Test
    void selectOffer() throws Exception {
        List <StatusHistory> previousStatusHistory = statementForDtoTest.getStatusHistory();

        statementRepository.save(statementForDtoTest);

        mockMvc.perform(post(DEAL_OFFER_SELECT)
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

    void creditFinalCalculation() {

    }


}
