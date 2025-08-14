package ru.outofmemoryguru.deal.controller.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import ru.outofmemoryguru.deal.model.Client;
import ru.outofmemoryguru.deal.model.Credit;
import ru.outofmemoryguru.deal.model.enumdata.ApplicationStatus;
import ru.outofmemoryguru.deal.model.jsonb.AppliedOffer;
import ru.outofmemoryguru.deal.model.jsonb.StatusHistory;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
public class StatementAdminDto {
    private UUID statementId;
    private Client client;
    private Credit credit;
    private ApplicationStatus status;
    private LocalDate creationDate;
    private AppliedOffer appliedOffer;
    private LocalDate signDate;
    private List<StatusHistory> statusHistory;
    private String sesCode;
}
