package ru.outofmemoryguru.statement.model.jsonb;

import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.Data;
import ru.outofmemoryguru.statement.model.enumdata.ApplicationStatus;
import ru.outofmemoryguru.statement.model.enumdata.ChangeType;

import java.time.LocalDateTime;

@Data
public class StatusHistory {
    @Enumerated(EnumType.STRING)
    private ApplicationStatus status;
    private LocalDateTime time;
    private ChangeType changeType;
}
