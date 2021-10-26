package seedu.duke;

import org.junit.jupiter.api.Test;
import seedu.entry.Expense;
import seedu.entry.ExpenseCategory;
import seedu.utility.FinancialTracker;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TotalExpenseBetweenCommandTest {
    private FinancialTracker testTracker = new FinancialTracker();
    private LocalDate date = LocalDate.now();
    
    @Test
    public void execute_twoValidDateInputs_validTotalSum() {
        Expense testExpense1 = new Expense("Salary", 400.00, ExpenseCategory.FOOD);
        Expense testExpense2 = new Expense("Allowance", 400.00, ExpenseCategory.MISC);
        testTracker.addExpense(testExpense1);
        testTracker.addExpense(testExpense2);
        assertEquals(800.00, testTracker.getExpenseBetween(date,date));
    }
}
