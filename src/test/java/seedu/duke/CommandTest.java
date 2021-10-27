package seedu.duke;

import org.junit.jupiter.api.Test;

import seedu.commands.AddExpenseCommand;
import seedu.commands.AddIncomeCommand;
import seedu.commands.ClearAllEntriesCommand;
import seedu.commands.DeleteExpenseCommand;
import seedu.commands.DeleteIncomeCommand;
import seedu.commands.TotalExpenseCommand;
import seedu.commands.TotalIncomeCommand;
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

    @Test
    public void testClearAllEntriesCommand() {
        ClearAllEntriesCommand testCommand = new ClearAllEntriesCommand();
        testCommand.execute(testTracker, testUi, budgetManager);
        assertEquals(0, testTracker.getIncomeSize());
        assertEquals(0, testTracker.getExpenseSize());
    }

    @Test
    public void testTotalExpenseCommand() {
        TotalExpenseCommand testCommand = new TotalExpenseCommand();
        testCommand.execute(testTracker, testUi, budgetManager);
        assertEquals(0, testTracker.getTotalIncome());
        Expense testExpense1 = new Expense("Bubble Tea", 4.80, ExpenseCategory.FOOD);
        Expense testExpense2 = new Expense("Chicken Rice", 3.00, ExpenseCategory.FOOD);
        AddExpenseCommand testCommand2 = new AddExpenseCommand(testExpense1);
        AddExpenseCommand testCommand3 = new AddExpenseCommand(testExpense2);
        testCommand2.execute(testTracker, testUi, budgetManager);
        testCommand3.execute(testTracker, testUi, budgetManager);
        TotalExpenseCommand testCommand4 = new TotalExpenseCommand();
        testCommand4.execute(testTracker, testUi, budgetManager);
        assertEquals(7.80, testTracker.getTotalExpense());
    }

    @Test
    public void testTotalIncomeCommand() {
        TotalIncomeCommand testCommand = new TotalIncomeCommand();
        testCommand.execute(testTracker, testUi, budgetManager);
        assertEquals(0, testTracker.getTotalExpense());
        Income testIncome1 = new Income("Pocket Money", 100, IncomeCategory.ALLOWANCE);
        Income testIncome2 = new Income("Chicken Rice", 2500, IncomeCategory.SALARY);
        AddIncomeCommand testCommand2 = new AddIncomeCommand(testIncome1);
        AddIncomeCommand testCommand3 = new AddIncomeCommand(testIncome2);
        testCommand2.execute(testTracker, testUi, budgetManager);
        testCommand3.execute(testTracker, testUi, budgetManager);
        TotalIncomeCommand testCommand4 = new TotalIncomeCommand();
        testCommand4.execute(testTracker, testUi, budgetManager);
        assertEquals(2600, testTracker.getTotalIncome());
    }

}

