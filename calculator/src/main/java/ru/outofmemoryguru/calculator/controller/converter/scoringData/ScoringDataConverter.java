package ru.outofmemoryguru.calculator.controller.converter.scoringData;

import org.springframework.stereotype.Component;
import ru.outofmemoryguru.calculator.controller.converter.Converter;
import ru.outofmemoryguru.calculator.controller.dto.ScoringDataDto;
import ru.outofmemoryguru.calculator.service.to.ScoringDataServiceModel;

@Component
public class ScoringDataConverter implements Converter<ScoringDataDto, ScoringDataServiceModel> {
    @Override
    public ScoringDataDto convertToDto(ScoringDataServiceModel source) {
        if (source == null) {
            return null;
        }
        ScoringDataDto dto = new ScoringDataDto();
        dto.setAccountNumber(source.getAccountNumber());
        dto.setAmount(source.getAmount());
        dto.setBirthdate(source.getBirthdate());
        dto.setDependentAmount(source.getDependentAmount());
        dto.setEmployment(source.getEmployment());
        dto.setGender(source.getGender());
        dto.setInsuranceEnabled(source.isInsuranceEnabled());
        dto.setLastName(source.getLastName());
        dto.setMaritalStatus(source.getMaritalStatus());
        dto.setMiddleName(source.getMiddleName());
        dto.setPassportIssueBranch(source.getPassportIssueBranch());
        dto.setPassportIssueDate(source.getPassportIssueDate());
        dto.setPassportNumber(source.getPassportNumber());
        dto.setPassportSeries(source.getPassportSeries());
        dto.setSalaryClient(source.isSalaryClient());
        dto.setTerm(source.getTerm());
        dto.setFirstName(source.getFirstName());
        return dto;
    }

    @Override
    public ScoringDataServiceModel convertToServiceModel(ScoringDataDto source) {
        if (source == null) {
            return null;
        }
        ScoringDataServiceModel model = new ScoringDataServiceModel();
        model.setAccountNumber(source.getAccountNumber());
        model.setAmount(source.getAmount());
        model.setBirthdate(source.getBirthdate());
        model.setDependentAmount(source.getDependentAmount());
        model.setEmployment(source.getEmployment());
        model.setGender(source.getGender());
        model.setInsuranceEnabled(source.isInsuranceEnabled());
        model.setLastName(source.getLastName());
        model.setMaritalStatus(source.getMaritalStatus());
        model.setMiddleName(source.getMiddleName());
        model.setPassportIssueBranch(source.getPassportIssueBranch());
        model.setPassportIssueDate(source.getPassportIssueDate());
        model.setPassportNumber(source.getPassportNumber());
        model.setPassportSeries(source.getPassportSeries());
        model.setSalaryClient(source.isSalaryClient());
        model.setTerm(source.getTerm());
        model.setFirstName(source.getFirstName());
        return model;
    }
}
