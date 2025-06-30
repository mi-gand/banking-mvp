package ru.outofmemoryguru.deal.model.jsonb;

import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.Data;
import ru.outofmemoryguru.deal.model.enumdata.ApplicationStatus;
import ru.outofmemoryguru.deal.model.enumdata.ChangeType;

import java.time.LocalDateTime;

@Data
public class StatusHistory {
    @Enumerated(EnumType.STRING)
    private ApplicationStatus status;
    private LocalDateTime time;
    private ChangeType changeType;
}
