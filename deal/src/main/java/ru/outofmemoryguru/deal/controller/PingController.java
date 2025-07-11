package ru.outofmemoryguru.deal.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/deal")
@Tag(name = "Контроллер поднятия микросервиса", description = """
        Показывает сторонним микросервисам и ПО готовность к работе
        """)
public class PingController {
    @GetMapping("/ready")
    @Operation(summary = "Микросервис готов к работе",
            description = "Используется Тестконтейнером")
    @ApiResponse(responseCode = "200", description = "Микросервис готов к работе")
    public ResponseEntity<Void> ready() {
        return ResponseEntity.ok().build();
    }
}
