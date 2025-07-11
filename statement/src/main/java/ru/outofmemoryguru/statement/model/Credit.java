package ru.outofmemoryguru.statement.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;
import ru.outofmemoryguru.statement.model.enumdata.CreditStatus;
import ru.outofmemoryguru.statement.model.jsonb.PaymentSchedule;

import java.math.BigDecimal;
import java.util.UUID;

@Entity
@Getter
@Setter
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

    @Override
    public String toString() {
        return "Credit{" +
                "credit_id=" + credit_id +
                ", amount=" + amount +
                ", term=" + term +
                ", monthlyPayment=" + monthlyPayment +
                ", rate=" + rate +
                ", psk=" + psk +
                ", paymentSchedule=" + paymentSchedule +
                ", salaryClient=" + salaryClient +
                ", creditStatus=" + creditStatus +
                '}';
    }
}
