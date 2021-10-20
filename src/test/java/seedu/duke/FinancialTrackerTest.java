package seedu.duke;

import org.junit.jupiter.api.Test;
import seedu.entry.Expense;
import seedu.entry.ExpenseCategory;
import seedu.entry.Income;
import seedu.entry.IncomeCategory;
import seedu.exceptions.ExpenseEntryNotFoundException;
import seedu.exceptions.IncomeEntryNotFoundException;
import seedu.utility.FinancialTracker;

import java.time.LocalDate;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;


public class FinancialTrackerTest {

    @Test
    public void addEntry_twoExpenseObjects_expectSizeOfListToBeTwo() {
        FinancialTracker testTracker = new FinancialTracker();
        testTracker.addExpense(new Expense("Lunch", 5.20, ExpenseCategory.FOOD));
        testTracker.addExpense(new Expense("Dinner", 20, ExpenseCategory.FOOD));
        assertEquals(2, testTracker.getExpenseSize());
    }

    @Test
    public void addIncome_twoIncomeObjects_expectSizeOfListToBeTwo() {
        FinancialTracker testTracker = new FinancialTracker();
        testTracker.addIncome(new Income("pocket money", 5.20, IncomeCategory.ADHOC));
        testTracker.addIncome(new Income("salary", 20, IncomeCategory.ADHOC));
        assertEquals(2, testTracker.getIncomeSize());
    }

    @Test
    public void removeExpense_emptyExpenseList_expectExpenseEntryNotFoundException() {
        FinancialTracker testTracker = new FinancialTracker();
        testTracker.addExpense(new Expense("Lunch", 5.20, ExpenseCategory.FOOD));
        assertThrows(ExpenseEntryNotFoundException.class, () -> {
            testTracker.removeExpense(4);
        });
    }

    @Test
    public void removeIncome_emptyIncomeList_expectIncomeEntryNotFoundException() {
        FinancialTracker testTracker = new FinancialTracker();
        testTracker.addIncome(new Income("pocket money", 5.20, IncomeCategory.ADHOC));
        assertThrows(IncomeEntryNotFoundException.class, () -> {
            testTracker.removeIncome(4);
        });
    }
    
    @Test
    public void getMonthlyIncomeBreakdown_IncomeList_expectTotalAccumulatedIncome() {
        FinancialTracker testTracker = new FinancialTracker();
        testTracker.addIncome(new Income("pocket money", 5.20, IncomeCategory.ALLOWANCE));
        testTracker.addIncome(new Income("salary", 100, IncomeCategory.ADHOC));
        ArrayList<Double> totalIncome = testTracker.getMonthlyIncomeBreakdown(2021);
        assertEquals(105.20, totalIncome.get(LocalDate.now().getMonthValue() - 1));
    }

    @Test
    public void getMonthlyExpenseBreakdown_ExpenseList_expectTotalAccumulatedExpense() {
        FinancialTracker testTracker = new FinancialTracker();
        testTracker.addExpense(new Expense("lunch", 5.20, ExpenseCategory.FOOD));
        testTracker.addExpense(new Expense("dinner", 100, ExpenseCategory.FOOD));
        ArrayList<Double> totalExpense = testTracker.getMonthlyExpenseBreakdown(2021);
        System.out.println(totalExpense);
        assertEquals(105.20, totalExpense.get(LocalDate.now().getMonthValue() - 1));
    }
}
