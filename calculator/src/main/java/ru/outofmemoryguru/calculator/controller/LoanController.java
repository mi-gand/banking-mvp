package ru.outofmemoryguru.calculator.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.outofmemoryguru.calculator.controller.converter.credit.CreditConverter;
import ru.outofmemoryguru.calculator.controller.converter.loanOffer.LoanOfferConverter;
import ru.outofmemoryguru.calculator.controller.converter.loanStatement.LoanStatementConverter;
import ru.outofmemoryguru.calculator.controller.converter.scoringData.ScoringDataConverter;
import ru.outofmemoryguru.calculator.controller.dto.CreditDto;
import ru.outofmemoryguru.calculator.controller.dto.LoanOfferDto;
import ru.outofmemoryguru.calculator.controller.dto.LoanStatementRequestDto;
import ru.outofmemoryguru.calculator.controller.dto.ScoringDataDto;
import ru.outofmemoryguru.calculator.service.ScoringService;
import ru.outofmemoryguru.calculator.service.to.CreditServiceModel;
import ru.outofmemoryguru.calculator.service.to.LoanOfferServiceModel;

import java.util.List;

@RestController
@RequestMapping(value = "/calculator")
@Tag(name = "Scoring", description = "Расчет кредитного предложения")
@RequiredArgsConstructor
public class LoanController {
    private final ScoringService scoringService;
    //todo ConverterFacade or Map<ClassPair, Converter>
    private final LoanStatementConverter loanStatementConverter;
    private final ScoringDataConverter scoringDataConverter;
    private final CreditConverter creditConverter;
    private final LoanOfferConverter loanOfferConverter;

    @PostMapping("/offers")
    @Operation(summary = "Получить список предложений",
                description = "Возвращает 4 предварительных кредитных предложения")
    @ApiResponse(responseCode = "200", description = "Список счетов успешно получен")
    public List<LoanOfferDto> offerPreCalculation(@Valid @RequestBody LoanStatementRequestDto dto){

       List<LoanOfferServiceModel> responseServiceModels = scoringService
               .preScoringLoanOffers(loanStatementConverter.convertToServiceModel(dto));

        return responseServiceModels.stream()
                .map(loanOfferConverter::convertToDto)
                .toList();
    }

    @PostMapping("/calc")
    @Operation(summary = "Получить полный расчет кридита",
            description = "Возвращает кредитное предложение, которое проходит через стадии:\n" +
                    "1. валидация присланных данных на уровне Jackson и jakarta.validation DTO\n" +
                    "2. скоринг данных\n" +
                    "3. полный расчет параметров кредита")
    @ApiResponse(responseCode = "200", description = "Список счетов успешно получен")
    public CreditDto calculateCredit(@Valid @RequestBody ScoringDataDto scoringDataDto){
        CreditServiceModel model = scoringService
                .calculateCredit(scoringDataConverter.convertToServiceModel(scoringDataDto));

        return creditConverter.convertToDto(model);
    }
}