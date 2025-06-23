package ru.outofmemoryguru.deal.model;

import jakarta.persistence.*;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;
import ru.outofmemoryguru.deal.model.enumdata.CreditStatus;
import ru.outofmemoryguru.deal.model.jsonb.PaymentSchedule;

import java.math.BigDecimal;
import java.util.UUID;

@Entity
@Table(name = "credit", schema = "deal")
public class Credit {
    @Id
    private UUID credit_id;

    private BigDecimal amount;

    private Integer term;
    private BigDecimal monthlyPayment;
    private BigDecimal rate;
    private BigDecimal psk;

    @JdbcTypeCode(SqlTypes.JSON)
    @Column(name = "payment_schedule", columnDefinition = "jsonb")
    private PaymentSchedule paymentSchedule;

    private boolean salaryClient;
    @Enumerated(EnumType.STRING)
    private CreditStatus creditStatus;
}
