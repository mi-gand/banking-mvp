package ru.outofmemoryguru.deal.controller;

import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.constraints.Pattern;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;
import ru.outofmemoryguru.deal.service.EmailDealService;

import java.util.Map;

@RestController
@RequestMapping("/deal/document")
@Tag(name = "Контроллер взаимодействия с Kafka")
@RequiredArgsConstructor
public class DocumentController {

    private final EmailDealService emailDealService;
    private static final Map<String, String> ACTION_TO_TOPIC = Map.of(
            "send", "send-documents",
            "sign", "create-documents",
            "code", "credit-issued"
    );

    @PostMapping("{statementId}/{action}")
    public void sendToKafka(@PathVariable("statementId")
                            @Pattern(
                                    regexp = "^[0-9a-fA-F]{8}-[0-9a-fA-F]{4}-[0-9a-fA-F]" +
                                            "{4}-[0-9a-fA-F]{4}-[0-9a-fA-F]{12}$",
                                    message = "Invalid UUID format"
                            ) String statementId,
                            @PathVariable("action") String action) {
        if (!ACTION_TO_TOPIC.containsKey(action)) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "недопустимый путь");
        }
        emailDealService.sendToKafka(statementId, ACTION_TO_TOPIC.get(action));
    }
}
