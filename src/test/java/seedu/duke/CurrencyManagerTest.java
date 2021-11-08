package seedu.duke;

import org.junit.jupiter.api.Test;
import seedu.commands.Command;
import seedu.commands.currency.CurrencyConversionCommand;
import seedu.commands.currency.CurrencyType;
import seedu.entry.Expense;
import seedu.entry.ExpenseCategory;
import seedu.exceptions.ExpenseOverflowException;
import seedu.utility.BudgetManager;
import seedu.utility.CurrencyManager;
import seedu.utility.FinancialTracker;
import seedu.utility.Ui;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertSame;

public class CurrencyManagerTest {

    @Test
    public void checkCurrency_expectedCurrencyTypeSGD() {
        CurrencyManager currencyManager = new CurrencyManager();
        assertSame(currencyManager.getCurrency(), CurrencyType.SGD);
    }

    @Test
    public void checkCurrency_expectedCurrencyTypeHDK() {
        Ui ui = new Ui();
        CurrencyManager currencyManager = new CurrencyManager();
        BudgetManager budgetManager = new BudgetManager();
        FinancialTracker testTracker = new FinancialTracker(currencyManager);
        assertSame(currencyManager.getCurrency(), CurrencyType.SGD);
        Command testCommand = new CurrencyConversionCommand(CurrencyType.HKD);
        testCommand.execute(testTracker, ui, budgetManager, currencyManager);
        assertSame(currencyManager.getCurrency(), CurrencyType.HKD);
    }

    @Test
    public void convertCurrencyHKD_expectedCurrencyTypeHKD() {
        Ui ui = new Ui();
        CurrencyManager currencyManager = new CurrencyManager();
        BudgetManager budgetManager = new BudgetManager();
        FinancialTracker testTracker = new FinancialTracker(currencyManager);
        Command testCommand = new CurrencyConversionCommand(CurrencyType.HKD);
        testCommand.execute(testTracker, ui, budgetManager, currencyManager);
        assertSame(currencyManager.getCurrency(), CurrencyType.HKD);
    }

    @Test
    public void convertCurrencyHKDTwice_SameTypeExceptionThrown_expenseValueNoChange() {
        Ui ui = new Ui();
        CurrencyManager currencyManager = new CurrencyManager();
        BudgetManager budgetManager = new BudgetManager();
        FinancialTracker testTracker = new FinancialTracker(currencyManager);
        Expense expense = new Expense("food", 10.0, ExpenseCategory.FOOD);
        Command convertCurrencyCommand = new CurrencyConversionCommand(CurrencyType.HKD);
        convertCurrencyCommand.execute(testTracker, ui, budgetManager, currencyManager);
        assertEquals(expense.getValue(), 10.00);
        Command convertCurrencyCommandAgain = new CurrencyConversionCommand(CurrencyType.HKD);
        convertCurrencyCommandAgain.execute(testTracker, ui, budgetManager, currencyManager);
        assertEquals(expense.getValue(), 10.00);
    }

    @Test
    public void convertCurrencyHKD_expectedNewExpenseValue() throws ExpenseOverflowException {
        Ui ui = new Ui();
        CurrencyManager currencyManager = new CurrencyManager();
        BudgetManager budgetManager = new BudgetManager();
        FinancialTracker testTracker = new FinancialTracker(currencyManager);
        Expense expense = new Expense("food", 10.0, ExpenseCategory.FOOD);
        testTracker.addExpense(expense);
        Command testCurrencyConversionToHDK  = new CurrencyConversionCommand(CurrencyType.HKD);
        testCurrencyConversionToHDK.execute(testTracker, ui, budgetManager, currencyManager);
        assertEquals(expense.getValue(), 50.00);
        Command testCurrencyConversionToSGD = new CurrencyConversionCommand(CurrencyType.SGD);
        testCurrencyConversionToSGD.execute(testTracker, ui, budgetManager, currencyManager);
        assertEquals(expense.getValue(), 10.00);
    }
}
