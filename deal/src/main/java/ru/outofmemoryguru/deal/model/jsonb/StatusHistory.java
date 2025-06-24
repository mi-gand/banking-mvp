package ru.outofmemoryguru.deal.model.jsonb;

import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.Data;
import ru.outofmemoryguru.deal.model.enumdata.ApplicationStatus;
import ru.outofmemoryguru.deal.model.enumdata.ChangeType;

import java.time.LocalDate;

@Data
public class StatusHistory {
    @Enumerated(EnumType.STRING)
    private ApplicationStatus status;
    private LocalDate time;
    private ChangeType changeType;
}
