package ru.outofmemoryguru.dossier.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.mail.MailProperties;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
import ru.outofmemoryguru.commondata.kafka.dto.ClientDto;
import ru.outofmemoryguru.commondata.kafka.dto.EmailMessageDto;

import static ru.outofmemoryguru.commondata.kafka.mappings.ActionToTopicMap.*;

@Slf4j
@Service
@RequiredArgsConstructor
public class EmailFromDealListener {

    private final JavaMailSender mailSender;
    private final MailProperties mailProperties;

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
        ClientDto client = dto.getClient();
        String mail = client.getEmail();

        switch (dto.getEventType()) {


            case FINISH_REGISTRATION -> {
                sendEmail(mail, FINISH_REGISTRATION, "Вы зарегестрировались");
                log.info("я нахожусь в EmailFromDealListener -> FINISH_REGISTRATION. Отловил DTO: {}", dto);
            }
            case CREATE_DOCUMENTS -> {
                sendEmail(mail, CREATE_DOCUMENTS, "Кредитное предложение создано");
                log.info("я нахожусь в EmailFromDealListener -> CREATE_DOCUMENTS. Отловил DTO: {}", dto);
            }
            case SEND_DOCUMENTS -> {
                sendEmail(mail, SEND_DOCUMENTS, "Итоговые документы по кредиту");
                log.info("я нахожусь в EmailFromDealListener -> SEND_DOCUMENTS. Отловил DTO: {}", dto);
            }
            case SEND_SES -> {
                sendEmail(mail, SEND_SES, "Подпишите документы");
                log.info("я нахожусь в EmailFromDealListener -> SEND_SES. Отловил DTO: {}", dto);
            }
            case CREDIT_ISSUED -> {
                sendEmail(mail, CREDIT_ISSUED, "Кредит одобрен");
                log.info("я нахожусь в EmailFromDealListener -> CREDIT_ISSUED. Отловил DTO: {}", dto);
            }
        }
    }

    private void sendEmail(String ClientMail, String topic, String text) {
        SimpleMailMessage msg = new SimpleMailMessage();
        msg.setFrom(mailProperties.getUsername());
        msg.setTo(ClientMail);
        msg.setSubject(topic);
        msg.setText(text);
        mailSender.send(msg);
    }
}