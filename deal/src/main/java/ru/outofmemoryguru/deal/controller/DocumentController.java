package ru.outofmemoryguru.deal.controller;

import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.constraints.Pattern;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import ru.outofmemoryguru.commondata.kafka.mappings.ActionToTopicMap;
import ru.outofmemoryguru.deal.controller.dto.JsonFromUiDto;
import ru.outofmemoryguru.deal.service.EmailDealService;

@RestController
@RequestMapping("/deal/document")
@Tag(name = "Контроллер взаимодействия с Kafka")
@RequiredArgsConstructor
public class DocumentController {

    private final EmailDealService emailDealService;

    @PostMapping("{statementId}/{action}")
    public void sendToKafka(@PathVariable("statementId")
                            @Pattern(
                                    regexp = "^[0-9a-fA-F]{8}-[0-9a-fA-F]{4}-[0-9a-fA-F]" +
                                            "{4}-[0-9a-fA-F]{4}-[0-9a-fA-F]{12}$",
                                    message = "Invalid UUID format"
                            ) String statementId,
                            @PathVariable("action") String action) {
        if (!ActionToTopicMap.getActionsForTopics().containsKey(action)) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "недопустимый путь");
        }
        emailDealService.sendToKafka(statementId, ActionToTopicMap.getTopic(action));
    }

    @PostMapping("{statementId}/{action}/code")
    public void sendToKafkaCode(@PathVariable("statementId")
                                @Pattern(
                                        regexp = "^[0-9a-fA-F]{8}-[0-9a-fA-F]{4}-[0-9a-fA-F]" +
                                                "{4}-[0-9a-fA-F]{4}-[0-9a-fA-F]{12}$",
                                        message = "Invalid UUID format"
                                ) String statementId,
                                @PathVariable("action") String action,
                                @RequestBody(required = false) JsonFromUiDto body) {
        if (!ActionToTopicMap.getActionsForTopics().containsKey(action)) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "недопустимый путь");
        }
        emailDealService.sendToKafka(statementId, ActionToTopicMap.getTopic(action), body);
    }

}
