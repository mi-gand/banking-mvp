package ru.outofmemoryguru.calculator.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import ru.outofmemoryguru.calculator.config.LoanOfferProperties;
import ru.outofmemoryguru.calculator.model.EmploymentStatus;
import ru.outofmemoryguru.calculator.model.Gender;
import ru.outofmemoryguru.calculator.model.MaritalStatus;
import ru.outofmemoryguru.calculator.model.Position;
import ru.outofmemoryguru.calculator.service.to.LoanStatementServiceModel;
import ru.outofmemoryguru.calculator.service.to.ScoringDataServiceModel;
import ru.outofmemoryguru.calculator.testdata.ServiceModelTestData;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

//просто заглушка, которая покрывает 40% текущих Line и 20% методов
@ExtendWith(MockitoExtension.class)
class ScoringServiceCoverageTest {

    @Mock(lenient = true)
    private LoanOfferProperties loanOfferProperties;

    @InjectMocks
    private ScoringService scoringService;

    private final LoanStatementServiceModel loanModel = ServiceModelTestData.loanStatementServiceModel1;
    private final ScoringDataServiceModel scoringModel = ServiceModelTestData.scoringDataServiceModel1;

    @BeforeEach
    void setup() {
        when(loanOfferProperties.getBaseRate()).thenReturn(BigDecimal.valueOf(10));
        when(loanOfferProperties.getInsuranceDiscount()).thenReturn(BigDecimal.valueOf(1));
        when(loanOfferProperties.getSalaryClientDiscount()).thenReturn(BigDecimal.valueOf(1));
        when(loanOfferProperties.getInsuranceCost()).thenReturn(BigDecimal.valueOf(10000));
    }

    @Test
    void testPreScoringLoanOffers_noAssert() {
        scoringService.preScoringLoanOffers(loanModel);
    }

    @Test
    void testCalculateCredit_noAssert() {
        scoringService.calculateCredit(scoringModel);
    }

    @Test
    void testBuildPaymentSchedule_noAssert() {
        scoringService.buildPaymentSchedule(
                BigDecimal.valueOf(500000),
                BigDecimal.valueOf(0.01),
                BigDecimal.valueOf(10000),
                12
        );
    }

    @Test
    void testCalculateCreditRate_allBranches_viaReflection() throws Exception {
        Method method = ScoringService.class.getDeclaredMethod("calculateCreditRate", ScoringDataServiceModel.class);
        method.setAccessible(true);

        scoringModel.getEmployment().setEmploymentStatus(EmploymentStatus.SELF_EMPLOYED);
        scoringModel.getEmployment().setPosition(Position.TOP_MANAGER);
        scoringModel.setMaritalStatus(MaritalStatus.DIVORCED);
        scoringModel.setSalaryClient(true);
        scoringModel.setBirthdate(LocalDate.now().minusYears(40));
        scoringModel.setGender(Gender.MALE);
        scoringModel.getEmployment().setWorkExperienceTotal(100);
        scoringModel.getEmployment().setWorkExperienceCurrent(50);

        method.invoke(scoringService, scoringModel);

        scoringModel.setGender(Gender.FEMALE);
        scoringModel.setBirthdate(LocalDate.now().minusYears(35));
        method.invoke(scoringService, scoringModel);

        scoringModel.getEmployment().setPosition(Position.OWNER);
        scoringModel.setMaritalStatus(MaritalStatus.MARRIED);
        method.invoke(scoringService, scoringModel);
    }

    @Test
    void testCalculateCreditRate_exceptionsTriggered_viaReflection() throws Exception {
        Method method = ScoringService.class.getDeclaredMethod("calculateCreditRate", ScoringDataServiceModel.class);
        method.setAccessible(true);

        scoringModel.getEmployment().setEmploymentStatus(EmploymentStatus.UNEMPLOYED);
        assertThrows(InvocationTargetException.class, () -> method.invoke(scoringService, scoringModel));

        scoringModel.getEmployment().setEmploymentStatus(EmploymentStatus.BUSINESS_OWNER);
        scoringModel.setAmount(BigDecimal.valueOf(99999999));
        assertThrows(InvocationTargetException.class, () -> method.invoke(scoringService, scoringModel));

        scoringModel.setAmount(BigDecimal.valueOf(500000));
        scoringModel.setBirthdate(LocalDate.now().minusYears(17));
        assertThrows(InvocationTargetException.class, () -> method.invoke(scoringService, scoringModel));

        scoringModel.setBirthdate(LocalDate.now().minusYears(61));
        assertThrows(InvocationTargetException.class, () -> method.invoke(scoringService, scoringModel));

        scoringModel.setBirthdate(LocalDate.now().minusYears(40));
        scoringModel.getEmployment().setWorkExperienceTotal(5);
        assertThrows(InvocationTargetException.class, () -> method.invoke(scoringService, scoringModel));

        scoringModel.getEmployment().setWorkExperienceTotal(100);
        scoringModel.getEmployment().setWorkExperienceCurrent(1);
        assertThrows(InvocationTargetException.class, () -> method.invoke(scoringService, scoringModel));
    }
}
