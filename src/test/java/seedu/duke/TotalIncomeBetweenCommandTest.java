package seedu.duke;

import org.junit.jupiter.api.Test;
import seedu.entry.Income;
import seedu.entry.IncomeCategory;
import seedu.utility.BudgetManager;
import seedu.utility.FinancialTracker;
import seedu.utility.Ui;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TotalIncomeBetweenCommandTest {
    private FinancialTracker testTracker = new FinancialTracker();
    private LocalDate date = LocalDate.now();
    
    @Test
    public void execute_twoValidDateInputs_validTotalSum() {
        Income testIncome1 = new Income("Salary", 400.00, IncomeCategory.SALARY);
        Income testIncome2 = new Income("Allowance", 400.00, IncomeCategory.SALARY);
        testTracker.addIncome(testIncome1);
        testTracker.addIncome(testIncome2);
        assertEquals(800.00, testTracker.getIncomeBetween(date,date));
    }
}
