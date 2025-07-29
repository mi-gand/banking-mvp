package ru.outofmemoryguru.dossier.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;
import ru.outofmemoryguru.commondata.kafka.dto.EmailMessageDto;

import static ru.outofmemoryguru.commondata.kafka.mappings.ActionToTopicMap.*;

@Slf4j
@Service
public class EmailFromDealListener {

    @KafkaListener(
            topics = {
                    FINISH_REGISTRATION,
                    CREATE_DOCUMENTS,
                    SEND_DOCUMENTS,
                    SEND_SES,
                    CREDIT_ISSUED
            },
            groupId = "email"
    )
    public void handleEvent(EmailMessageDto dto) {
        switch (dto.getEventType()) {
            case FINISH_REGISTRATION -> log.info
                    ("я нахожусь в EmailFromDealListener -> FINISH_REGISTRATION. Отловил DTO: {}", dto);
            case CREATE_DOCUMENTS -> log.info
                    ("я нахожусь в EmailFromDealListener -> CREATE_DOCUMENTS. Отловил DTO: {}", dto);
            case SEND_DOCUMENTS -> log.info
                    ("я нахожусь в EmailFromDealListener -> SEND_DOCUMENTS. Отловил DTO: {}", dto);
            case SEND_SES -> log.info
                    ("я нахожусь в EmailFromDealListener -> SEND_SES. Отловил DTO: {}", dto);
            case CREDIT_ISSUED -> log.info
                    ("я нахожусь в EmailFromDealListener -> CREDIT_ISSUED. Отловил DTO: {}", dto);
        }
    }
}