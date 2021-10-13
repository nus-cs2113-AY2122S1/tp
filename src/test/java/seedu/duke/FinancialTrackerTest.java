package seedu.duke;

import org.junit.jupiter.api.Test;
import seedu.entry.Expense;
import seedu.entry.Income;
import seedu.exceptions.ExpenseEntryNotFoundException;
import seedu.exceptions.IncomeEntryNotFoundException;
import seedu.utility.FinancialTracker;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;


public class FinancialTrackerTest {
    
    @Test
    public void addEntry_twoExpenseObjects_expectSizeOfListToBeTwo() {
        FinancialTracker testTracker = new FinancialTracker();
        testTracker.addExpense(new Expense("Lunch", 5.20));
        testTracker.addExpense(new Expense("Dinner", 20));
        assertEquals(2, testTracker.getExpenseSize());
    }

    @Test
    public void addIncome_twoIncomeObjects_expectSizeOfListToBeTwo() {
        FinancialTracker testTracker = new FinancialTracker();
        testTracker.addIncome(new Income("allowance", 5.20));
        testTracker.addIncome(new Income("salary", 20));
        assertEquals(2, testTracker.getIncomeSize());
    }
    
        @Test
        public void removeExpense_emptyExpenseList_expectExpenseEntryNotFoundException() {
            FinancialTracker testTracker = new FinancialTracker();
            testTracker.addExpense(new Expense("Lunch", 5.20));
            assertThrows(ExpenseEntryNotFoundException.class, () -> {
                testTracker.removeExpense(4);
            });
        }

        @Test
        public void removeIncome_emptyIncomeList_expectIncomeEntryNotFoundException() {
            FinancialTracker testTracker = new FinancialTracker();
            testTracker.addIncome(new Income("allowance", 5.20));
            assertThrows(IncomeEntryNotFoundException.class, () -> {
                testTracker.removeIncome(4);
            });
        }
}
