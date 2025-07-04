package ru.outofmemoryguru.deal.controller.converter.loanStatement;

import org.springframework.stereotype.Component;
import ru.outofmemoryguru.deal.controller.converter.Converter;
import ru.outofmemoryguru.deal.controller.dto.LoanStatementRequestDto;
import ru.outofmemoryguru.deal.service.to.LoanStatementServiceModel;

@Component
public class LoanStatementConverter implements Converter<LoanStatementRequestDto, LoanStatementServiceModel> {
    @Override
    public LoanStatementRequestDto convertToDto(LoanStatementServiceModel source) {
        if (source == null) {
            return null;
        }
        LoanStatementRequestDto dto = new LoanStatementRequestDto();
        dto.setAmount(source.getAmount());
        dto.setBirthdate(source.getBirthdate());
        dto.setEmail(source.getEmail());
        dto.setFirstName(source.getFirstName());
        dto.setLastName(source.getLastName());
        dto.setMiddleName(source.getMiddleName());
        dto.setPassportNumber(source.getPassportNumber());
        dto.setPassportSeries(source.getPassportSeries());
        dto.setTerm(source.getTerm());
        return dto;
    }

    @Override
    public LoanStatementServiceModel convertToServiceModel(LoanStatementRequestDto source) {
        if (source == null) {
            return null;
        }
        LoanStatementServiceModel model = new LoanStatementServiceModel();
        model.setAmount(source.getAmount());
        model.setBirthdate(source.getBirthdate());
        model.setEmail(source.getEmail());
        model.setFirstName(source.getFirstName());
        model.setLastName(source.getLastName());
        model.setMiddleName(source.getMiddleName());
        model.setPassportNumber(source.getPassportNumber());
        model.setPassportSeries(source.getPassportSeries());
        model.setTerm(source.getTerm());
        return model;
    }
}
