package ru.outofmemoryguru.calculator.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import ru.outofmemoryguru.calculator.dto.CreditDto;
import ru.outofmemoryguru.calculator.dto.LoanOfferDto;
import ru.outofmemoryguru.calculator.dto.LoanStatementRequestDto;
import ru.outofmemoryguru.calculator.dto.ScoringDataDto;

import java.util.List;

@RestController
@RequestMapping(value = LoanController.REST_URL, produces = MediaType.APPLICATION_JSON_VALUE)
@Tag(name = "Scoring", description = "Расчет кредитного предложения")
public class LoanController {
    static final String REST_URL = "/calculator";

    @PostMapping("/offers")
    @Operation(summary = "Получить список предложений",
                description = "Возвращает 4 предварительных кредитных предложения")
    @ApiResponse(responseCode = "200", description = "Список счетов успешно получен")
    public List<LoanOfferDto> offerPreCalculation(@RequestBody LoanStatementRequestDto dto){


        return null;
    }

    @PostMapping("/calc")
    public CreditDto validateOffer(ScoringDataDto dto){     //todo change name
        return null;
    }
}