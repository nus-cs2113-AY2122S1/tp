package seedu.duke;

import org.junit.jupiter.api.Test;
import seedu.commands.AddExpenseCommand;
import seedu.commands.AddIncomeCommand;
import seedu.commands.DeleteExpenseCommand;
import seedu.commands.DeleteIncomeCommand;
import seedu.entry.Expense;
import seedu.entry.ExpenseCategory;
import seedu.entry.Income;
import seedu.entry.IncomeCategory;
import seedu.utility.BudgetManager;
import seedu.utility.FinancialTracker;
import seedu.utility.Ui;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class CommandTest {
    private FinancialTracker testTracker = new FinancialTracker();
    private Ui testUi = new Ui();
    private BudgetManager budgetManager = new BudgetManager();

    @Test
    public void testAddExpenseCommand() {
        Expense testExpense = new Expense("Bubble Tea", 4.80, ExpenseCategory.FOOD);
        AddExpenseCommand testCommand = new AddExpenseCommand(testExpense);
        testCommand.execute(testTracker, testUi, budgetManager);
        assertEquals(1, testTracker.getExpenseSize());
    }

    @Test
    public void testAddIncomeCommand() {
        Income testIncome = new Income("Pocket Money", 100, IncomeCategory.ALLOWANCE);
        AddIncomeCommand testCommand = new AddIncomeCommand(testIncome);
        testCommand.execute(testTracker, testUi, budgetManager);
        assertEquals(1, testTracker.getIncomeSize());
    }

    @Test
    public void testDeleteExpenseCommand() {
        DeleteExpenseCommand testCommand = new DeleteExpenseCommand(1);
        testCommand.execute(testTracker, testUi, budgetManager);
        assertTrue(testTracker.isExpensesEmpty());
    }

    @Test
    public void testDeleteIncomeCommand() {
        DeleteIncomeCommand testCommand = new DeleteIncomeCommand(1);
        testCommand.execute(testTracker, testUi, budgetManager);
        assertTrue(testTracker.isIncomesEmpty());
    }
}