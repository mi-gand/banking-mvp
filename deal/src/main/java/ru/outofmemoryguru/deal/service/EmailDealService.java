package ru.outofmemoryguru.deal.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import ru.outofmemoryguru.commondata.kafka.dto.ClientDto;
import ru.outofmemoryguru.commondata.kafka.dto.EmailMessageDto;
import ru.outofmemoryguru.deal.controller.dto.JsonFromUiDto;
import ru.outofmemoryguru.deal.model.Client;
import ru.outofmemoryguru.deal.model.Statement;
import ru.outofmemoryguru.deal.model.enumdata.ApplicationStatus;
import ru.outofmemoryguru.deal.model.enumdata.ChangeType;
import ru.outofmemoryguru.deal.model.jsonb.StatusHistory;
import ru.outofmemoryguru.deal.repository.ClientRepository;
import ru.outofmemoryguru.deal.repository.StatementRepository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import static ru.outofmemoryguru.commondata.kafka.mappings.ActionToTopicMap.*;

@Service
@Slf4j
@RequiredArgsConstructor
public class EmailDealService {
    private final ClientRepository clientRepository;
    private final StatementRepository statementRepository;
    private final KafkaTemplate<String, EmailMessageDto> kafkaTemplate;
    private final ObjectMapper objectMapper;
    private static final Map<String, List<ApplicationStatus>> TOPIC_TO_STATEMENT_STATUS = Map.of(
            FINISH_REGISTRATION, List.of(ApplicationStatus.APPROVED),
            CREATE_DOCUMENTS, List.of(ApplicationStatus.CC_APPROVED),
            SEND_DOCUMENTS, List.of(ApplicationStatus.PREPARE_DOCUMENTS),
            CREDIT_ISSUED, List.of(ApplicationStatus.DOCUMENT_SIGNED,
                    ApplicationStatus.CREDIT_ISSUED)
    );

    public void sendToKafka(String statementId, String topic) {
        Statement statement = statementRepository.findById(UUID.fromString(statementId))
                .orElseThrow(() -> new EntityNotFoundException("Statement id " + statementId + " not found"));

        UUID clientId = statement.getClientId();
        Client client = clientRepository.findById(clientId)
                .orElseThrow(() -> new EntityNotFoundException("Client id " + clientId + " not found"));

        String sesCode = null;
        if (topic.equals(SEND_SES)) {
            sesCode = String.valueOf((int) (Math.random() * 9000) + 1000);
            statement.setSesCode(sesCode);
        } else {
            setStatementStatusAndHistory(statement, topic);
        }
        statementRepository.save(statement);

        EmailMessageDto emailMessageDto = new EmailMessageDto(statementId, topic,
                objectMapper.convertValue(client, ClientDto.class), sesCode);
        log.info("Топик в кафку: {} \n", topic);
        log.info("Отправляю в кафку: {} \n", emailMessageDto);
        kafkaTemplate.send(topic, emailMessageDto);
    }

    public void sendToKafka(String statementId, String topic, JsonFromUiDto body) {

        Statement statement = statementRepository.findById(UUID.fromString(statementId))
                .orElseThrow(() -> new EntityNotFoundException("Statement id " + statementId + " not found"));

        UUID clientId = statement.getClientId();
        Client client = clientRepository.findById(clientId)
                .orElseThrow(() -> new EntityNotFoundException("Client id " + clientId + " not found"));


        if (!statement.getSesCode().equals(body.getSesCode())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Неправильный SES code, брутфорьс дальше");
        }

        statementRepository.save(statement);

        EmailMessageDto emailMessageDto = new EmailMessageDto(statementId, topic,
                objectMapper.convertValue(client, ClientDto.class), body.getSesCode());
        log.info("Топик в кафку: {} \n", topic);
        log.info("Отправляю в кафку: {} \n", emailMessageDto);
        kafkaTemplate.send(topic, emailMessageDto);

    }

    private void setStatementStatusAndHistory(Statement statement, String topic) {
        List<ApplicationStatus> statuses = TOPIC_TO_STATEMENT_STATUS.get(topic);
        for (ApplicationStatus status : statuses) {
            statement.setStatus(status);
            statement.setStatusHistory(newStatusHistoryList(statement.getStatusHistory(), status));
            statementRepository.save(statement);
        }
    }

    private List<StatusHistory> newStatusHistoryList(List<StatusHistory> actualStatusHistoryList,
                                                     ApplicationStatus status) {
        StatusHistory newStatusHistory = new StatusHistory();
        newStatusHistory.setStatus(status);
        newStatusHistory.setTime(LocalDateTime.now());
        newStatusHistory.setChangeType(ChangeType.AUTOMATIC);
        actualStatusHistoryList.add(newStatusHistory);
        return actualStatusHistoryList;
    }
}
