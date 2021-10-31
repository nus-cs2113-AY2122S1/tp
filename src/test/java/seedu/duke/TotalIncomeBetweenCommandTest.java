package seedu.duke;

import org.junit.jupiter.api.Test;
import seedu.entry.Expense;
import seedu.entry.ExpenseCategory;
import seedu.entry.Income;
import seedu.entry.IncomeCategory;
import seedu.exceptions.DuplicateIncomeException;
import seedu.utility.BudgetManager;
import seedu.utility.FinancialTracker;
import seedu.utility.Ui;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TotalIncomeBetweenCommandTest {
    private FinancialTracker testTracker = new FinancialTracker();

    @Test
    public void execute_twoValidDateInputs_validTotalSum() throws DuplicateIncomeException {
        LocalDate startDate = LocalDate.parse("20/10/2021", DateTimeFormatter.ofPattern("dd/MM/yyyy"));
        LocalDate endDate = LocalDate.parse("29/10/2021", DateTimeFormatter.ofPattern("dd/MM/yyyy"));

        Income testIncome1 = new Income("Salary", 400.00, IncomeCategory.ALLOWANCE);
        Income testIncome2 = new Income("Allowance", 400.00, IncomeCategory.ALLOWANCE);
        testTracker.addIncome(testIncome1);
        testTracker.addIncome(testIncome2);
        assertEquals(800.00, testTracker.getIncomeBetween(startDate,endDate));
        assertEquals(800.00, testTracker.getIncomeBetween(endDate,startDate));
    }

    @Test
    public void execute_twoValidDateInputs_TotalSumZero() throws DuplicateIncomeException {
        LocalDate startDate = LocalDate.parse("30/10/2021", DateTimeFormatter.ofPattern("dd/MM/yyyy"));
        LocalDate endDate = LocalDate.parse("31/10/2021", DateTimeFormatter.ofPattern("dd/MM/yyyy"));

        Income testIncome1 = new Income("Salary", 400.00, IncomeCategory.ALLOWANCE);
        Income testIncome2 = new Income("Allowance", 400.00, IncomeCategory.ALLOWANCE);
        testTracker.addIncome(testIncome1);
        testTracker.addIncome(testIncome2);
        assertEquals(0.00, testTracker.getIncomeBetween(startDate,endDate));
        assertEquals(0.00, testTracker.getIncomeBetween(endDate,startDate));
    }
}
