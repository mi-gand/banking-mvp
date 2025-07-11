package ru.outofmemoryguru.statement.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.outofmemoryguru.statement.model.Credit;

import java.util.UUID;

public interface CreditRepository extends JpaRepository<Credit, UUID> {
}
