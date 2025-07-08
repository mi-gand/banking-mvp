package ru.outofmemoryguru.deal.controller.converter.finishregistration;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import ru.outofmemoryguru.deal.controller.converter.Converter;
import ru.outofmemoryguru.deal.controller.dto.FinishRegistrationRequestDto;
import ru.outofmemoryguru.deal.service.to.FinishRegistrationServiceModel;

/*
@Component
@RequiredArgsConstructor
public class FinishRegistrationConverter implements Converter<FinishRegistrationRequestDto, FinishRegistrationServiceModel> {

    @Override
    public FinishRegistrationRequestDto convertToDto(FinishRegistrationServiceModel source) {
        if (source == null) {
            return null;
        }

        FinishRegistrationRequestDto dto = new FinishRegistrationRequestDto();
        dto.setGender(source.getGender());
        dto.setMaritalStatus(source.getMaritalStatus());
        dto.setDependentAmount(source.getDependentAmount());
        dto.setPassportIssueDate(source.getPassportIssueDate());
        dto.setPassportIssueBranch(source.getPassportIssueBranch());
        dto.setEmployment(source.getEmployment());
        dto.setAccountNumber(source.getAccountNumber());

        return dto;
    }

    @Override
    public FinishRegistrationServiceModel convertToServiceModel(FinishRegistrationRequestDto source) {
        if (source == null) {
            return null;
        }

        FinishRegistrationServiceModel model = new FinishRegistrationServiceModel();
        model.setGender(source.getGender());
        model.setMaritalStatus(source.getMaritalStatus());
        model.setDependentAmount(source.getDependentAmount());
        model.setPassportIssueDate(source.getPassportIssueDate());
        model.setPassportIssueBranch(source.getPassportIssueBranch());
        model.setEmployment(source.getEmployment());
        model.setAccountNumber(source.getAccountNumber());

        return model;
    }
}*/
