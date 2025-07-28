package ru.outofmemoryguru.dossier.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;
import ru.outofmemoryguru.commondata.dto.EmailMessageDto;

@Slf4j
@Service
public class EmailFromDealListener {

    @KafkaListener(
            topics = {
                    "finish-registration",
                    "create-documents",
                    "send-documents",
                    "send-ses",
                    "credit-issued",
                    "statement-denied"
            },
            groupId = "email"
    )
    public void handleEvent(EmailMessageDto dto) {
        switch (dto.getEventType()) {
            case "send-documents" -> log.info
                    ("я нахожусь в EmailFromDealListener -> send-documents. Отловил DTO: {}", dto);
            case "create-documents" -> log.info
                    ("я нахожусь в EmailFromDealListener -> create-documents. Отловил DTO: {}", dto);
        }
    }
}