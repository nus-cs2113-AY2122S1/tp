package seedu.duke;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import seedu.entry.Expense;
import seedu.entry.ExpenseCategory;
import seedu.utility.BudgetManager;
import seedu.utility.FinancialTracker;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class BudgetManagerTest {

    /*
    private final PrintStream standardOut = System.out;
    private final ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();
    private final String newLine = System.lineSeparator();

    @BeforeEach
    public void setUp() {
        System.setOut(new PrintStream(outputStreamCaptor));
    }

    @AfterEach
    public void tearDown() {
        System.setOut(standardOut);
    }

    private static final String SEPARATOR_LINE = "-------------------------------------------------------------------"
            + "----------------------------------";
    private static final String currentMonth =
            LocalDate.now().getMonth().toString();
     */
    BudgetManager budgetManager = new BudgetManager();
    FinancialTracker finances = new FinancialTracker();

    @Test
    public void setBudget_validEntry_correctBudget() {
        budgetManager.setBudget(2000.50, ExpenseCategory.TRANSPORT);
        assertEquals(2000.50, budgetManager.getBudget(ExpenseCategory.TRANSPORT));
    }

    @Test
    public void setThreshold_validEntry_correctThreshold() {
        budgetManager.setThreshold(0.15);
        assertEquals(0.15, budgetManager.getThreshold());
    }

    /*
    @Test
    public void handleBudget_validEntries_correctReminders() {
        String expectedOutput = ""

        budgetManager.setBudget(12, ExpenseCategory.FOOD);
        budgetManager.setThreshold(0.1);
        finances.addExpense(new Expense("mcdonalds", 10.70, ExpenseCategory.FOOD));
        finances.addExpense(new Expense("dinner", 5.25, ExpenseCategory.FOOD));
        assertEquals(expectedOutput, outputStreamCaptor.toString().trim());
    }
    */

}
