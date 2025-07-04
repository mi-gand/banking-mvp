package ru.outofmemoryguru.calculator.controller.converter.loanOffer;

import org.springframework.stereotype.Component;
import ru.outofmemoryguru.calculator.controller.converter.Converter;
import ru.outofmemoryguru.calculator.controller.dto.LoanOfferDTO;
import ru.outofmemoryguru.calculator.service.to.LoanOfferServiceModel;

@Component
public class LoanOfferConverter implements Converter<LoanOfferDTO, LoanOfferServiceModel> {
    @Override
    public LoanOfferDTO convertToDto(LoanOfferServiceModel source) {
        if (source == null) {
            return null;
        }
        LoanOfferDTO dto = new LoanOfferDTO();
        dto.setInsuranceEnabled(source.isInsuranceEnabled());
        dto.setMonthlyPayment(source.getMonthlyPayment());
        dto.setRate(source.getRate());
        dto.setRequestedAmount(source.getRequestedAmount());
        dto.setSalaryClient(source.isSalaryClient());
        dto.setStatementId(source.getStatementId());
        dto.setTerm(source.getTerm());
        dto.setTotalAmount(source.getTotalAmount());
        return dto;
    }

    @Override
    public LoanOfferServiceModel convertToServiceModel(LoanOfferDTO source) {
        if (source == null) {
            return null;
        }
        LoanOfferServiceModel model = new LoanOfferServiceModel();
        model.setInsuranceEnabled(source.isInsuranceEnabled());
        model.setMonthlyPayment(source.getMonthlyPayment());
        model.setRate(source.getRate());
        model.setRequestedAmount(source.getRequestedAmount());
        model.setSalaryClient(source.isSalaryClient());
        model.setStatementId(source.getStatementId());
        model.setTerm(source.getTerm());
        model.setTotalAmount(source.getTotalAmount());
        return model;
    }
}
