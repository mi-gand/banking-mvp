package ru.outofmemoryguru.deal.model.jsonb;

import lombok.Data;

import java.time.LocalDate;

@Data
public class Passport {
    String series;
    String number;
    String issue_branch;
    LocalDate issue_date;
}
