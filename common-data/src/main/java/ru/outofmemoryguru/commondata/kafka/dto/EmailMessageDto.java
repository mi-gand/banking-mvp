package ru.outofmemoryguru.commondata.kafka.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class EmailMessageDto {
    private String statementId;
    private String eventType;
    private ClientDto client;
    private String sesCode;
}
