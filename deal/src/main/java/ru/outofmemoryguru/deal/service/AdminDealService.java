package ru.outofmemoryguru.deal.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.outofmemoryguru.deal.controller.dto.StatementAdminDto;
import ru.outofmemoryguru.deal.repository.StatementRepository;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class AdminDealService {
    private final StatementRepository statementRepository;

    public List<StatementAdminDto> getStatement(UUID statementId) {
        return statementRepository.findStatementAdminDtoByIdOrAll(statementId);
    }
}
