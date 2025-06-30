package ru.outofmemoryguru.deal.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.outofmemoryguru.deal.model.Credit;

import java.util.UUID;

public interface CreditRepository extends JpaRepository<Credit, UUID> {
}
