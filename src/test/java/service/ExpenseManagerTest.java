package service;

import entity.expense.ExpenseList;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

class ExpenseManagerTest {

    @BeforeAll
    public static void setUp() {
        ExpenseList.clearExpenses();
        ExpenseList.setRunningExpenseValue(0);
    }

    @Test
    public void addExpense_addExpenseWithoutCategory_success() {
        ExpenseManager testExpenseManager = ExpenseManager.getExpenseMgr();
        String expenseDescription = "Lunch";
        double expenseValue = 10;

        testExpenseManager.addExpense(expenseDescription, expenseValue);

        assertEquals(1, ExpenseList.getExpenses().size());
        assertEquals(expenseValue, ExpenseList.getRunningExpenseValue());

        ExpenseList.clearExpenses();
        ExpenseList.setRunningExpenseValue(0);
    }

    @Test
    public void addExpense_addExpenseWithCategory_success() {
        ExpenseManager testExpenseManager = ExpenseManager.getExpenseMgr();
        String expenseDescription = "Lunch";
        double expenseValue = 10;
        String expenseCategory = "Food";

        testExpenseManager.addExpense(expenseDescription, expenseValue, expenseCategory);

        assertEquals(1, ExpenseList.getExpenses().size());
        assertEquals(expenseValue, ExpenseList.getRunningExpenseValue());

        ExpenseList.clearExpenses();
        ExpenseList.setRunningExpenseValue(0);
    }

    @Test
    void deleteExpense_expenseListDeleteByName_success() {
        ExpenseManager testExpenseManager = ExpenseManager.getExpenseMgr();
        String expenseDescription = "Lunch";
        double expenseValue = 10;
        String expenseCategory = "Food";

        testExpenseManager.addExpense(expenseDescription, expenseValue, expenseCategory);
        testExpenseManager.deleteExpense(expenseDescription);

        assertEquals(0, ExpenseList.getExpenses().size());
        assertEquals(0, ExpenseList.getRunningExpenseValue());

        ExpenseList.clearExpenses();
        ExpenseList.setRunningExpenseValue(0);
    }

    @Test
    void deleteExpense_expenseListDeleteByName_fail() {
        ExpenseManager testExpenseManager = ExpenseManager.getExpenseMgr();
        String expenseDescription = "Lunch";
        double expenseValue = 10;
        String expenseCategory = "Food";

        testExpenseManager.addExpense(expenseDescription, expenseValue, expenseCategory);
        testExpenseManager.deleteExpense("Non Existent Expense");

        assertNotEquals(0, ExpenseList.getExpenses().size());
        assertNotEquals(0, ExpenseList.getRunningExpenseValue());

        ExpenseList.clearExpenses();
        ExpenseList.setRunningExpenseValue(0);
    }

    @Test
    void deleteExpense_expenseListDeleteByIndex_success() {
        ExpenseManager testExpenseManager = ExpenseManager.getExpenseMgr();
        String expenseDescription = "Lunch";
        double expenseValue = 10;
        String expenseCategory = "Food";
        int expenseId = 1;

        testExpenseManager.addExpense(expenseDescription, expenseValue, expenseCategory);
        testExpenseManager.deleteExpense(expenseId);

        assertEquals(0, ExpenseList.getExpenses().size());
        assertEquals(0, ExpenseList.getRunningExpenseValue());

        ExpenseList.clearExpenses();
        ExpenseList.setRunningExpenseValue(0);
    }

    @Test
    void deleteExpense_expenseListDeleteByIndex_fail() {
        ExpenseManager testExpenseManager = ExpenseManager.getExpenseMgr();
        String expenseDescription = "Lunch";
        double expenseValue = 10;
        String expenseCategory = "Food";
        int expenseId = 100;

        testExpenseManager.addExpense(expenseDescription, expenseValue, expenseCategory);
        try {
            testExpenseManager.deleteExpense(expenseId);
        } catch (IndexOutOfBoundsException ignored) {
            // Do nothing
        }

        assertNotEquals(0, ExpenseList.getExpenses().size());
        assertNotEquals(0, ExpenseList.getRunningExpenseValue());

        ExpenseList.clearExpenses();
        ExpenseList.setRunningExpenseValue(0);
    }

    @Test
    void updateExpense_updateExpenseValueOnly_success() {
        ExpenseManager testExpenseManager = ExpenseManager.getExpenseMgr();
        String expenseDescription = "Lunch";
        double oldExpenseValue = 10;
        double newExpenseValue = 15;
        String expenseCategory = "Food";

        testExpenseManager.addExpense(expenseDescription, oldExpenseValue, expenseCategory);
        testExpenseManager.updateExpense(expenseDescription, newExpenseValue);

        assertEquals(newExpenseValue, ExpenseList.getExpenses().get(0).getValue());
        assertEquals(newExpenseValue, ExpenseList.getRunningExpenseValue());

        ExpenseList.clearExpenses();
        ExpenseList.setRunningExpenseValue(0);
    }

    @Test
    void updateExpense_updateExpenseValueAndCategory_success() {
        ExpenseManager testExpenseManager = ExpenseManager.getExpenseMgr();
        String expenseDescription = "Lunch";
        double oldExpenseValue = 10;
        double newExpenseValue = 15;
        String oldExpenseCategory = "Food";
        String newExpenseCategory = "Snacks";

        testExpenseManager.addExpense(expenseDescription, oldExpenseValue, oldExpenseCategory);
        testExpenseManager.updateExpense(expenseDescription, newExpenseValue, newExpenseCategory);

        assertEquals(newExpenseValue, ExpenseList.getExpenses().get(0).getValue());
        assertEquals(newExpenseValue, ExpenseList.getRunningExpenseValue());
        assertEquals(newExpenseCategory, ExpenseList.getExpenses().get(0).getCategory());

        ExpenseList.clearExpenses();
        ExpenseList.setRunningExpenseValue(0);
    }
}