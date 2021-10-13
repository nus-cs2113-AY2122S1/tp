package seedu.duke;

import org.junit.jupiter.api.Test;
import seedu.commands.AddExpenseCommand;
import seedu.commands.AddIncomeCommand;
import seedu.commands.DeleteExpenseCommand;
import seedu.commands.DeleteIncomeCommand;
import seedu.entry.Expense;
import seedu.entry.Income;
import seedu.utility.FinancialTracker;
import seedu.utility.Ui;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class CommandTest {
    private FinancialTracker testTracker = new FinancialTracker();
    private Ui testUi = new Ui();

    @Test
    public void testAddExpenseCommand() {
        Expense testExpense = new Expense("Bubble Tea", 4.80);
        AddExpenseCommand testCommand = new AddExpenseCommand(testExpense);
        testCommand.execute(testTracker,testUi);
        assertEquals(1, testTracker.getExpenseSize());
    }

    @Test
    public void testAddIncomeCommand() {
        Income testIncome = new Income("Allowance", 100);
        AddIncomeCommand testCommand = new AddIncomeCommand(testIncome);
        testCommand.execute(testTracker, testUi);
        assertEquals(1, testTracker.getIncomeSize());
    }

    @Test
    public void testDeleteExpenseCommand() {
        DeleteExpenseCommand testCommand = new DeleteExpenseCommand(1);
        testCommand.execute(testTracker, testUi);
        assertTrue(testTracker.isExpensesEmpty());
    }

    @Test
    public void testDeleteIncomeCommand() {
        DeleteIncomeCommand testCommand = new DeleteIncomeCommand(1);
        testCommand.execute(testTracker, testUi);
        assertTrue(testTracker.isIncomesEmpty());
    }
}