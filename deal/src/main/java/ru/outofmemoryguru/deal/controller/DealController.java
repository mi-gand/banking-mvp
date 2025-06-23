package ru.outofmemoryguru.deal.controller;

import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Pattern;
import org.springframework.web.bind.annotation.*;
import ru.outofmemoryguru.deal.controller.dto.FinishRegistrationRequestDto;
import ru.outofmemoryguru.deal.controller.dto.LoanOfferDto;
import ru.outofmemoryguru.deal.controller.dto.LoanStatementRequestDto;

import java.util.List;

@RestController
@RequestMapping("/deal")
@Tag(name = "Название для MS-Deal", description = "Описание для MS-Deal")
public class DealController {

    @PostMapping("/statement")
    List<LoanOfferDto> creditPreCalculation(@Valid @RequestBody LoanStatementRequestDto requestDto) {
        return List.of(new LoanOfferDto());
    }

    @PostMapping("/offer/select")
    void selectOffer(@Valid @RequestBody LoanOfferDto requestDto) {

    }

    @PostMapping("/calculate/{statementId}")
    void creditFinalCalculation(@Valid @RequestBody FinishRegistrationRequestDto requestDto,
                                @PathVariable
                                @Pattern(       //по ТЗ String а не UUID, проверяю
                                        regexp = "^[0-9a-fA-F]{8}-[0-9a-fA-F]{4}-[0-9a-fA-F]" +
                                                "{4}-[0-9a-fA-F]{4}-[0-9a-fA-F]{12}$",
                                        message = "Invalid UUID format"
                                )
                                String statementId) {

    }
}
