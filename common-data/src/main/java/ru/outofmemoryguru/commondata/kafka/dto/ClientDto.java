package ru.outofmemoryguru.commondata.kafka.dto;

import lombok.Data;

import java.time.LocalDate;
import java.util.UUID;

@Data
public class ClientDto {        //обрезанный Client
    private UUID clientId;
    private String lastName;
    private String firstName;
    private String middleName;
    private LocalDate birthDate;
    private String email;
    private Integer dependentAmount;
    private String accountNumber;
}