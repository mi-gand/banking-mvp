package ru.outofmemoryguru.statement.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.outofmemoryguru.statement.controller.dto.LoanOfferDto;
import ru.outofmemoryguru.statement.controller.dto.LoanStatementRequestDto;
import ru.outofmemoryguru.statement.service.StatementService;
import ru.outofmemoryguru.statement.service.to.LoanOfferServiceModel;
import ru.outofmemoryguru.statement.service.to.LoanStatementServiceModel;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/statement")
public class StatementController {
    private final StatementService statementService;
    private final ModelMapper modelMapper;

    @PostMapping("/statement")
    @Operation(summary = "Получить список предложений",
            description = "Возвращает 4 предварительных кредитных предложения")
    @ApiResponse(responseCode = "200", description = "Список LoanOffer успешно получен")
    List<LoanOfferDto> forwardToDealMsLoanOffers(@Valid @RequestBody LoanStatementRequestDto requestDto) {

        return statementService.forwardToDealMsLoanOffers(modelMapper.map(requestDto, LoanStatementServiceModel.class))
                .stream()
                .map(x -> modelMapper.map(x, LoanOfferDto.class))
                .toList();
    }

    @PostMapping("/offer")
    @Operation(summary = "Клиент выбирает кредитное предложение",
            description = """
                    Обновляется StatusHistory, заявка сохраняется
                    """)
    @ApiResponse(responseCode = "200", description = "Заявка сохранена")
    void selectOffer(@Valid @RequestBody LoanOfferDto requestDto) {
        statementService.selectOffer(modelMapper.map(requestDto, LoanOfferServiceModel.class));
    }
}