
package ru.outofmemoryguru.deal.service;

import jakarta.persistence.EntityNotFoundException;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import ru.outofmemoryguru.deal.AbstractContainerPostgres;
import ru.outofmemoryguru.deal.model.Statement;
import ru.outofmemoryguru.deal.repository.ClientRepository;
import ru.outofmemoryguru.deal.repository.CreditRepository;
import ru.outofmemoryguru.deal.repository.StatementRepository;
import ru.outofmemoryguru.deal.service.to.LoanOfferServiceModel;

import javax.sql.DataSource;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static ru.outofmemoryguru.deal.testdata.ServiceModelTestData.*;



class DealServiceTest extends AbstractContainerPostgres {

    @Autowired
    private DealService dealService;
    @Autowired
    private CreditRepository creditRepository;
    @Autowired
    private ClientRepository clientRepository;
    @Autowired
    private StatementRepository statementRepository;


    @Autowired
    private DataSource dataSource;


    @Test
    @Disabled
    void creditPreCalculation() {
        List<LoanOfferServiceModel> loanOfferList = dealService
                .creditPreCalculation(loanStatementServiceModelFromStatementAa11);

        assertThat(loanOfferList)
                .usingElementComparatorIgnoringFields("statementId")
                .containsExactlyInAnyOrderElementsOf(expected4OffersModel);
    }

    @Test
    @Disabled
    void selectOffer() {
        Statement sourceStatement = statementRepository.findById(loanOffer2.getStatementId())
                .orElseThrow(() -> new EntityNotFoundException("Statement id "
                        + loanOffer2.getStatementId() + " not found"));
        System.out.println(sourceStatement.getAppliedOffer());

        dealService.selectOffer(loanOffer2);

        Statement newStatement = statementRepository.findById(loanOffer2.getStatementId())
                .orElseThrow(() -> new EntityNotFoundException("Statement id "
                        + loanOffer2.getStatementId() + " not found"));
        System.out.println(newStatement.getAppliedOffer());
    }

    @Test
    @Disabled
    void creditFinalCalculation() {
    }

/*    @BeforeEach
    @Disabled
    void fillData() throws Exception {
        try (Connection conn = dataSource.getConnection()) {
            DatabaseConnection dbUnitConn = new DatabaseConnection(conn);

}
            IDataSet dataSet = new FlatXmlDataSetBuilder().build(
                    getClass().getClassLoader().getResourceAsStream("test-dataset.xml")
            );
            DatabaseOperation.CLEAN_INSERT.execute(dbUnitConn, dataSet);
        }
    }*/

}
