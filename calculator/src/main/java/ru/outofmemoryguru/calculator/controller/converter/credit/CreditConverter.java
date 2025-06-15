package ru.outofmemoryguru.calculator.controller.converter.credit;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import ru.outofmemoryguru.calculator.controller.converter.Converter;
import ru.outofmemoryguru.calculator.controller.converter.paymentShedule.PaymentScheduleConverter;
import ru.outofmemoryguru.calculator.controller.dto.CreditDto;
import ru.outofmemoryguru.calculator.service.to.CreditServiceModel;

@Component
@RequiredArgsConstructor
public class CreditConverter implements Converter<CreditDto, CreditServiceModel> {

    private final PaymentScheduleConverter paymentScheduleConverter;

    @Override
    public CreditDto convertToDto(CreditServiceModel source) {
        if (source == null) {
            return null;
        }
        CreditDto dto = new CreditDto();
        dto.setAmount(source.getAmount());
        dto.setTerm(source.getTerm());
        dto.setMonthlyPayment(source.getMonthlyPayment());
        dto.setRate(source.getRate());
        dto.setPsk(source.getPsk());
        dto.setInsuranceEnabled(source.isInsuranceEnabled());
        dto.setSalaryClient(source.isSalaryClient());
        dto.setPaymentSchedule(source.getPaymentSchedule().stream()
                .map(paymentScheduleConverter::convertToDto)
                .toList());
        return dto;
    }

    @Override
    public CreditServiceModel convertToServiceModel(CreditDto source) {
        if (source == null) {
            return null;
        }
        CreditServiceModel model = new CreditServiceModel();
        model.setAmount(source.getAmount());
        model.setTerm(source.getTerm());
        model.setMonthlyPayment(source.getMonthlyPayment());
        model.setRate(source.getRate());
        model.setPsk(source.getPsk());
        model.setInsuranceEnabled(source.isInsuranceEnabled());
        model.setSalaryClient(source.isSalaryClient());
        model.setPaymentSchedule(source.getPaymentSchedule().stream()
                .map(paymentScheduleConverter::convertToServiceModel)
                .toList());
        return model;
    }
}
