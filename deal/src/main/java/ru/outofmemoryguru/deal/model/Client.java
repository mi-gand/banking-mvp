package ru.outofmemoryguru.deal.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;
import ru.outofmemoryguru.deal.model.enumdata.Gender;
import ru.outofmemoryguru.deal.model.enumdata.MaritalStatus;
import ru.outofmemoryguru.deal.model.jsonb.Employment;
import ru.outofmemoryguru.deal.model.jsonb.Passport;

import java.time.LocalDate;
import java.util.UUID;

@Entity
@Getter
@Setter
@Table(name = "client", schema = "deal")
public class Client {

    @Id
    private UUID clientId;

    private String lastName;
    private String firstName;
    private String middleName;
    private LocalDate birthDate;
    private String email;
    private Gender gender;
    private MaritalStatus maritalStatus;
    private Integer dependentAmount;

    @JdbcTypeCode(SqlTypes.JSON)
    @Column(columnDefinition = "jsonb")
    private Passport passportId;

    @JdbcTypeCode(SqlTypes.JSON)
    @Column(columnDefinition = "jsonb")
    private Employment employmentId;

    private String accountNumber;

}