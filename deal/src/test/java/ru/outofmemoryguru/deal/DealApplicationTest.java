package ru.outofmemoryguru.deal;

import jakarta.persistence.EntityManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;


@SpringBootTest
//@DataJpaTest
@Rollback(false)
class DealApplicationTest {
    @Autowired
    EntityManager em;

/*    @Test
    @Transactional
    void createValue(){
        Statement statement = new Statement();
        statement.setStatementId(UUID.randomUUID());
        statement.setCreditId(UUID.randomUUID());
        statement.setClientId(UUID.randomUUID());
        statement.setStatus(ApplicationStatus.CC_APPROVED);
        statement.setCreationDate(LocalDate.now());

        em.persist(statement);

    }*/
}