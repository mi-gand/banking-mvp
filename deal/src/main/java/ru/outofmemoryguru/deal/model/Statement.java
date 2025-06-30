package ru.outofmemoryguru.deal.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;
import ru.outofmemoryguru.deal.model.enumdata.ApplicationStatus;
import ru.outofmemoryguru.deal.model.jsonb.AppliedOffer;
import ru.outofmemoryguru.deal.model.jsonb.StatusHistory;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Entity
@Getter
@Setter
@Table(name = "statement", schema = "deal")
public class Statement {

    @Id
    private UUID statementId;

    private UUID clientId;
    private UUID creditId;

    @Enumerated(EnumType.STRING)
    private ApplicationStatus status;

    private LocalDate creationDate;

    @JdbcTypeCode(value = SqlTypes.JSON)
    @Column(name = "applied_offer", columnDefinition = "jsonb")
    private AppliedOffer appliedOffer;

    private LocalDate signDate;
    @JdbcTypeCode(SqlTypes.JSON)
    @Column(columnDefinition = "jsonb")
    private List<StatusHistory> statusHistory;

}
