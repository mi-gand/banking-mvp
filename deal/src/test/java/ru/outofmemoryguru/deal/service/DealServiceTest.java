package ru.outofmemoryguru.deal.service;

import jakarta.persistence.EntityNotFoundException;
import org.dbunit.database.DatabaseConnection;
import org.dbunit.dataset.IDataSet;
import org.dbunit.dataset.xml.FlatXmlDataSetBuilder;
import org.dbunit.operation.DatabaseOperation;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import ru.outofmemoryguru.deal.model.Statement;
import ru.outofmemoryguru.deal.repository.ClientRepository;
import ru.outofmemoryguru.deal.repository.CreditRepository;
import ru.outofmemoryguru.deal.repository.StatementRepository;
import ru.outofmemoryguru.deal.service.to.LoanOfferServiceModel;
import org.springframework.cloud.contract.wiremock.AutoConfigureWireMock;

import javax.sql.DataSource;
import java.sql.Connection;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static ru.outofmemoryguru.deal.testdata.ServiceModelTestData.*;


@SpringBootTest
@AutoConfigureWireMock(port = 0)
class DealServiceTest extends AbstractContainerPostgres {

    @Autowired
    private DealService dealService;
    @Autowired
    private CreditRepository creditRepository;
    @Autowired
    private ClientRepository clientRepository;
    @Autowired
    private StatementRepository statementRepository;
    /*    @Autowired
        private ModelMapper modelMapper;*/
    @Autowired
    private DataSource dataSource;


    @Test
    void creditPreCalculation() {
        List<LoanOfferServiceModel> loanOfferList = dealService
                .creditPreCalculation(loanStatementServiceModelFromStatementAa11);

        assertThat(loanOfferList)
                .usingElementComparatorIgnoringFields("statementId")
                .containsExactlyInAnyOrderElementsOf(expected4OffersModel);
    }

    @Test
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
    void creditFinalCalculation() {
    }

    @BeforeEach
    void fillData() throws Exception {
        try (Connection conn = dataSource.getConnection()) {
            DatabaseConnection dbUnitConn = new DatabaseConnection(conn);

            IDataSet dataSet = new FlatXmlDataSetBuilder().build(
                    getClass().getClassLoader().getResourceAsStream("test-dataset.xml")
            );
            DatabaseOperation.CLEAN_INSERT.execute(dbUnitConn, dataSet);
        }
    }



}
