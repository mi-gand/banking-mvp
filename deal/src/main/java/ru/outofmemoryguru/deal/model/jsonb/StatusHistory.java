package ru.outofmemoryguru.deal.model.jsonb;

import lombok.Data;
import ru.outofmemoryguru.deal.model.enumdata.ChangeType;

import java.time.LocalDate;

@Data
public class StatusHistory {
    private String status;
    private LocalDate time;
    private ChangeType changeType;
}
