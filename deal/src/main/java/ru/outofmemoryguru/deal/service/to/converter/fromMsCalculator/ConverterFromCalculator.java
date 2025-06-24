package ru.outofmemoryguru.deal.service.to.converter.fromMsCalculator;

import ru.outofmemoryguru.deal.model.jsonb.AppliedOffer;
import ru.outofmemoryguru.deal.service.to.LoanOfferServiceModel;

public class ConverterFromCalculator {

    public static AppliedOffer convertToAppliedOffer(LoanOfferServiceModel source) {
        if (source == null) {
            return null;
        }
        AppliedOffer to = new AppliedOffer();
        to.setInsuranceEnabled(source.isInsuranceEnabled());
        to.setMonthlyPayment(source.getMonthlyPayment());
        to.setRate(source.getRate());
        to.setRequestedAmount(source.getRequestedAmount());
        to.setSalaryClient(source.isSalaryClient());
        to.setStatementId(source.getStatementId());
        to.setTerm(source.getTerm());
        to.setTotalAmount(source.getTotalAmount());
        return to;
    }


}
