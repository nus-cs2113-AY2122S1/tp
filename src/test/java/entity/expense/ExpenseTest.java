package entity.expense;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

public class ExpenseTest {

    @Test
    public void expenseCreation_expenseWithoutCategory_Success() {
        String expenseDescription = "Lunch";
        double expenseValue = 12.88;
        String expenseDate = "05/11/2021";
        Expense testExpense = new Expense(expenseDescription, expenseValue, expenseDate);

        assertEquals(expenseDescription, testExpense.getDescription());
        assertEquals(expenseValue, testExpense.getValue());
        assertEquals(expenseDate, testExpense.getDate());
        assertEquals("-", testExpense.getCategory());
    }

    @Test
    public void expenseCreation_expenseWithCategory_success() {
        String expenseDescription = "Lunch";
        double expenseValue = 12.88;
        String expenseDate = "05/11/2021";
        String expenseCategory = "Food";
        Expense testExpense = new Expense(expenseDescription, expenseValue, expenseDate, expenseCategory);

        assertEquals(expenseDescription, testExpense.getDescription());
        assertEquals(expenseValue, testExpense.getValue());
        assertEquals(expenseDate, testExpense.getDate());
        assertEquals(expenseCategory, testExpense.getCategory());
    }

    @Test
    public void expenseUpdateValue_valueUpdate_success() {
        String expenseDescription = "Lunch";
        double oldExpenseValue = 12.88;
        String expenseDate = "05/11/2021";
        Expense testExpense = new Expense(expenseDescription, oldExpenseValue, expenseDate);

        double newExpenseValue = 15.30;
        testExpense.updateValue(newExpenseValue);

        assertNotEquals(oldExpenseValue, testExpense.getValue());
        assertEquals(newExpenseValue, testExpense.getValue());
    }

    @Test
    public void expenseUpdateCategory_categoryUpdate_success() {
        String expenseDescription = "Lunch";
        double expenseValue = 12.88;
        String expenseDate = "05/11/2021";
        String oldExpenseCategory = "Food";
        Expense testExpense = new Expense(expenseDescription, expenseValue, expenseDate, oldExpenseCategory);

        String newExpenseCategory = "Snacks";
        testExpense.updateCategory(newExpenseCategory);

        assertNotEquals(oldExpenseCategory, testExpense.getCategory());
        assertEquals(newExpenseCategory, testExpense.getCategory());
    }

    @Test
    public void expenseString_formatOfExpense_success() {
        String expenseDescription = "Lunch";
        double expenseValue = 12.88;
        String expenseDate = "05/11/2021";
        String expenseCategory = "Food";

        String expenseStringFormat = "Lunch                     | 12.88      | 05/11/2021 | Food      ";
        Expense testExpense = new Expense(expenseDescription, expenseValue, expenseDate, expenseCategory);

        assertEquals(expenseStringFormat, testExpense.toString());
    }
}