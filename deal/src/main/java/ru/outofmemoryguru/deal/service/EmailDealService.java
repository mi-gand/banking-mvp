package ru.outofmemoryguru.deal.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import ru.outofmemoryguru.commondata.kafka.dto.ClientDto;
import ru.outofmemoryguru.commondata.kafka.dto.EmailMessageDto;
import ru.outofmemoryguru.deal.model.Client;
import ru.outofmemoryguru.deal.model.Statement;
import ru.outofmemoryguru.deal.repository.ClientRepository;
import ru.outofmemoryguru.deal.repository.StatementRepository;

import java.util.UUID;

@Service
@Slf4j
@RequiredArgsConstructor
public class EmailDealService {
    private final ClientRepository clientRepository;
    private final StatementRepository statementRepository;
    private final KafkaTemplate<String, EmailMessageDto> kafkaTemplate;
    private final ObjectMapper objectMapper;

    public void sendToKafka(String statementId, String topic) {
        Statement statement = statementRepository.findById(UUID.fromString(statementId))
                .orElseThrow(() -> new EntityNotFoundException("Statement id " + statementId + " not found"));

        UUID clientId = statement.getClientId();
        Client client = clientRepository.findById(clientId)
                .orElseThrow(() -> new EntityNotFoundException("Client id " + clientId + " not found"));

        EmailMessageDto emailMessageDto = new EmailMessageDto(statementId, topic,
                objectMapper.convertValue(client, ClientDto.class));
        log.info("Топик в кафку: {} \n", topic);
        log.info("Отправляю в кафку: {} \n", emailMessageDto);
        kafkaTemplate.send(topic, emailMessageDto);
    }
}
