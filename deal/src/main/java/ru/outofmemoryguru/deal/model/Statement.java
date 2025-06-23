package ru.outofmemoryguru.deal.model;

import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;
import ru.outofmemoryguru.deal.model.enumdata.ApplicationStatus;
import ru.outofmemoryguru.deal.model.jsonb.AppliedOffer;
import ru.outofmemoryguru.deal.model.jsonb.StatusHistory;

import java.time.LocalDate;
import java.util.UUID;

@Entity
@Data
@Table(name = "statement", schema = "deal")
public class Statement {

    @Id
    private UUID statementId;

    private UUID clientId;
    private UUID creditId;

    @Enumerated(EnumType.STRING)
    private ApplicationStatus status;

    private LocalDate creationDate;

    @JdbcTypeCode(value = SqlTypes.JSON)    //todo Из прошлого МС подтягивать?
    @Column(name = "applied_offer", columnDefinition = "jsonb")
    private AppliedOffer appliedOffer;

    private LocalDate signDate;
    private String sesCode;    //todo WTF?  what is it? what type?
    @JdbcTypeCode(SqlTypes.JSON)
    @Column(columnDefinition = "jsonb")
    private StatusHistory statusHistory;

}
