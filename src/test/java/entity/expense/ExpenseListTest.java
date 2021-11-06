package entity.expense;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;

class ExpenseListTest {

    @BeforeAll
    public static void setUp() {
        ExpenseList.clearExpenses();
        ExpenseList.setRunningExpenseValue(0);
    }

    @Test
    public void addExpense_expenseListAdd_success() {
        String expenseDescription = "Lunch";
        double expenseValue = 10;
        String expenseDate = "05/11/2021";
        String expenseCategory = "Food";
        Expense testExpense = new Expense(expenseDescription, expenseValue, expenseDate, expenseCategory);

        ExpenseList.addExpense(testExpense);

        assertEquals(1, ExpenseList.getExpenses().size());
        assertEquals(expenseValue, ExpenseList.getRunningExpenseValue());

        ExpenseList.clearExpenses();
        ExpenseList.setRunningExpenseValue(0);
    }

    @Test
    void deleteExpense_expenseListDeleteByName_success() {
        String expenseDescription = "Lunch";
        double expenseValue = 10;
        String expenseDate = "05/11/2021";
        String expenseCategory = "Food";
        Expense testExpense = new Expense(expenseDescription, expenseValue, expenseDate, expenseCategory);

        ExpenseList.addExpense(testExpense);
        boolean isSuccess = ExpenseList.deleteExpense(expenseDescription);

        assertTrue(isSuccess);
        assertEquals(0, ExpenseList.getExpenses().size());
        assertEquals(0, ExpenseList.getRunningExpenseValue());

        ExpenseList.clearExpenses();
        ExpenseList.setRunningExpenseValue(0);
    }

    @Test
    void deleteExpense_expenseListDeleteByName_fail() {
        String expenseDescription = "Lunch";
        double expenseValue = 10;
        String expenseDate = "05/11/2021";
        String expenseCategory = "Food";
        Expense testExpense = new Expense(expenseDescription, expenseValue, expenseDate, expenseCategory);

        ExpenseList.addExpense(testExpense);
        boolean isSuccess = ExpenseList.deleteExpense("Non Existent Expense");

        assertFalse(isSuccess);
        assertNotEquals(0, ExpenseList.getExpenses().size());
        assertNotEquals(0, ExpenseList.getRunningExpenseValue());

        ExpenseList.clearExpenses();
        ExpenseList.setRunningExpenseValue(0);
    }

    @Test
    void deleteExpense_expenseListDeleteByIndex_success() {
        String expenseDescription = "Lunch";
        double expenseValue = 10;
        String expenseDate = "05/11/2021";
        String expenseCategory = "Food";
        int expenseId = 0;
        Expense testExpense = new Expense(expenseDescription, expenseValue, expenseDate, expenseCategory);

        ExpenseList.addExpense(testExpense);
        ExpenseList.deleteExpense(expenseId);

        assertEquals(0, ExpenseList.getExpenses().size());
        assertEquals(0, ExpenseList.getRunningExpenseValue());

        ExpenseList.clearExpenses();
        ExpenseList.setRunningExpenseValue(0);
    }

    @Test
    void deleteExpense_expenseListDeleteByIndex_fail() {
        String expenseDescription = "Lunch";
        double expenseValue = 10;
        String expenseDate = "05/11/2021";
        String expenseCategory = "Food";
        int expenseId = 100;
        Expense testExpense = new Expense(expenseDescription, expenseValue, expenseDate, expenseCategory);

        ExpenseList.addExpense(testExpense);
        try {
            ExpenseList.deleteExpense(expenseId);
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
        String expenseDescription = "Lunch";
        double oldExpenseValue = 10;
        double newExpenseValue = 15;
        String expenseDate = "05/11/2021";
        String expenseCategory = "Food";
        Expense testExpense = new Expense(expenseDescription, oldExpenseValue, expenseDate, expenseCategory);

        ExpenseList.addExpense(testExpense);
        ExpenseList.updateExpense(expenseDescription, newExpenseValue);

        assertEquals(newExpenseValue, testExpense.getValue());
        assertEquals(newExpenseValue, ExpenseList.getRunningExpenseValue());

        ExpenseList.clearExpenses();
        ExpenseList.setRunningExpenseValue(0);
    }

    @Test
    void updateExpense_updateExpenseValueAndCategory_success() {
        String expenseDescription = "Lunch";
        double oldExpenseValue = 10;
        double newExpenseValue = 15;
        String expenseDate = "05/11/2021";
        String oldExpenseCategory = "Food";
        String newExpenseCategory = "Snacks";
        Expense testExpense = new Expense(expenseDescription, oldExpenseValue, expenseDate, oldExpenseCategory);

        ExpenseList.addExpense(testExpense);
        ExpenseList.updateExpense(expenseDescription, newExpenseValue, newExpenseCategory);

        assertEquals(newExpenseValue, testExpense.getValue());
        assertEquals(newExpenseCategory, testExpense.getCategory());
        assertEquals(newExpenseValue, ExpenseList.getRunningExpenseValue());

        ExpenseList.clearExpenses();
        ExpenseList.setRunningExpenseValue(0);
    }
}