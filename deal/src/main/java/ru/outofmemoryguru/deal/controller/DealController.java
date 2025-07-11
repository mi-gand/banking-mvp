package ru.outofmemoryguru.deal.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Pattern;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.web.bind.annotation.*;
import ru.outofmemoryguru.deal.controller.converter.loanOffer.LoanOfferConverter;
import ru.outofmemoryguru.deal.controller.converter.loanStatement.LoanStatementConverter;
import ru.outofmemoryguru.deal.controller.dto.FinishRegistrationRequestDto;
import ru.outofmemoryguru.deal.controller.dto.LoanOfferDto;
import ru.outofmemoryguru.deal.controller.dto.LoanStatementRequestDto;
import ru.outofmemoryguru.deal.service.DealService;
import ru.outofmemoryguru.deal.service.to.FinishRegistrationServiceModel;

import java.util.List;

@RestController
@RequestMapping("/deal")
@Tag(name = "Микросервис сделка(deal)", description = """
        Последовательно создает необходимые сущности
        в зависимости от действий клиента
        """)
@RequiredArgsConstructor
public class DealController {
    private final DealService dealService;
    private final LoanStatementConverter loanStatementConverter;
    private final LoanOfferConverter loanOfferConverter = new LoanOfferConverter();
    private final ModelMapper modelMapper;

    @PostMapping("/statement")
    @Operation(summary = "Получить список предложений",
            description = "Возвращает 4 предварительных кредитных предложения")
    @ApiResponse(responseCode = "200", description = "Список LoanOffer успешно получен")
    List<LoanOfferDto> creditPreCalculation(@Valid @RequestBody LoanStatementRequestDto requestDto) {

        return dealService.creditPreCalculation(loanStatementConverter.convertToServiceModel(requestDto))
                .stream()
                .map(loanOfferConverter::convertToDto)
                .toList();
    }

    @PostMapping("/offer/select")
    @Operation(summary = "Клиент выбирает кредитное предложение",
            description = """
                    Обновляется StatusHistory, заявка сохраняется
                    """)
    @ApiResponse(responseCode = "200", description = "Заявка сохранена")
    void selectOffer(@Valid @RequestBody LoanOfferDto requestDto) {
        dealService.selectOffer(loanOfferConverter.convertToServiceModel(requestDto));
    }

    @PostMapping("/calculate/{statementId}")
    @Operation(summary = "Точный расчет кредита",
            description = "Обращение в соседний микросервис  /calculator/calc")
    @ApiResponse(responseCode = "200", description = "Заявка сохранена")
    void creditFinalCalculation(@Valid @RequestBody FinishRegistrationRequestDto requestDto,
                                @PathVariable("statementId")
                                @Pattern(       //по ТЗ String а не UUID, проверяю
                                        regexp = "^[0-9a-fA-F]{8}-[0-9a-fA-F]{4}-[0-9a-fA-F]" +
                                                "{4}-[0-9a-fA-F]{4}-[0-9a-fA-F]{12}$",
                                        message = "Invalid UUID format"
                                )String statementId) {
        FinishRegistrationServiceModel model = modelMapper.map(requestDto, FinishRegistrationServiceModel.class);
        dealService.creditFinalCalculation(model, statementId);

    }
}
