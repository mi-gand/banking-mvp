package ru.outofmemoryguru.deal.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import ru.outofmemoryguru.deal.controller.dto.StatementAdminDto;
import ru.outofmemoryguru.deal.model.Statement;

import java.util.List;
import java.util.UUID;

@Repository
public interface StatementRepository extends JpaRepository<Statement, UUID> {

    @Query("""
            select new ru.outofmemoryguru.deal.controller.dto.StatementAdminDto(
              statement.statementId,
              client,
              credit,
              statement.status,
              statement.creationDate,
              statement.appliedOffer,
              statement.signDate,
              statement.statusHistory,
              statement.sesCode
            )
            from Statement statement
            left join Client client  on client.clientId = statement.clientId
            left join Credit credit on credit.credit_id  = statement.creditId
            where (:id is null or statement.statementId = :id)
            """)
    List<StatementAdminDto> findStatementAdminDtoByIdOrAll(@Param("id") UUID id);

}
