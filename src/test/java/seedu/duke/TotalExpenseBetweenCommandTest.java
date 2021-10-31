package seedu.duke;

import org.junit.jupiter.api.Test;
import seedu.entry.Expense;
import seedu.entry.ExpenseCategory;
import seedu.utility.FinancialTracker;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TotalExpenseBetweenCommandTest {
    private FinancialTracker testTracker = new FinancialTracker();
    
    @Test
    public void execute_twoValidDateInputs_validTotalSum() {
        LocalDate startDate = LocalDate.parse("20/10/2021", DateTimeFormatter.ofPattern("dd/MM/yyyy"));
        LocalDate endDate = LocalDate.parse("29/10/2021", DateTimeFormatter.ofPattern("dd/MM/yyyy"));
        
        Expense testExpense1 = new Expense("Salary", 400.00, ExpenseCategory.FOOD, startDate);
        Expense testExpense2 = new Expense("Allowance", 400.00, ExpenseCategory.MISC, endDate);
        testTracker.addExpense(testExpense1);
        testTracker.addExpense(testExpense2);
        assertEquals(800.00, testTracker.getExpenseBetween(startDate,endDate));
        assertEquals(800.00, testTracker.getExpenseBetween(endDate,startDate));
    }

    @Test
    public void execute_twoValidDateInputs_TotalSumZero() {
        LocalDate startDate = LocalDate.parse("28/10/2021", DateTimeFormatter.ofPattern("dd/MM/yyyy"));
        LocalDate endDate = LocalDate.parse("29/10/2021", DateTimeFormatter.ofPattern("dd/MM/yyyy"));

        Expense testExpense1 = new Expense("Salary", 400.00, ExpenseCategory.FOOD);
        Expense testExpense2 = new Expense("Allowance", 400.00, ExpenseCategory.MISC);
        testTracker.addExpense(testExpense1);
        testTracker.addExpense(testExpense2);
        assertEquals(0.00, testTracker.getExpenseBetween(startDate,endDate));
        assertEquals(0.00, testTracker.getExpenseBetween(endDate,startDate));
    }

}
