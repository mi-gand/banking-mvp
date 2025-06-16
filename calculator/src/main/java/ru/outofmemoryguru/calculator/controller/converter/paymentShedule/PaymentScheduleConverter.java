package ru.outofmemoryguru.calculator.controller.converter.paymentShedule;

import org.springframework.stereotype.Component;
import ru.outofmemoryguru.calculator.controller.converter.Converter;
import ru.outofmemoryguru.calculator.controller.dto.PaymentScheduleElementDto;
import ru.outofmemoryguru.calculator.service.to.PaymentScheduleElementServiceModel;

@Component
public class PaymentScheduleConverter implements Converter <PaymentScheduleElementDto, PaymentScheduleElementServiceModel> {
    @Override
    public PaymentScheduleElementDto convertToDto(PaymentScheduleElementServiceModel source) {
        if (source == null) {
            return null;
        }
        PaymentScheduleElementDto dto = new PaymentScheduleElementDto();
        dto.setNumber(source.getNumber());
        dto.setDate(source.getDate());
        dto.setTotalPayment(source.getTotalPayment());
        dto.setInterestPayment(source.getInterestPayment());
        dto.setDebtPayment(source.getDebtPayment());
        dto.setRemainingDebt(source.getRemainingDebt());
        return dto;
    }

    @Override
    public PaymentScheduleElementServiceModel convertToServiceModel(PaymentScheduleElementDto source) {
        if (source == null) {
            return null;
        }
        PaymentScheduleElementServiceModel model = new PaymentScheduleElementServiceModel();
        model.setNumber(source.getNumber());
        model.setDate(source.getDate());
        model.setTotalPayment(source.getTotalPayment());
        model.setInterestPayment(source.getInterestPayment());
        model.setDebtPayment(source.getDebtPayment());
        model.setRemainingDebt(source.getRemainingDebt());
        return model;
    }
}
