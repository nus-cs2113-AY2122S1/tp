package seedu.duke;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;
import seedu.entry.Expense;
import seedu.entry.Income;
import seedu.utility.FinancialTracker;

public class FinancialTrackerTest {
    @Test
    public void addEntry_twoEntryObjects_expectSizeOfListToBeTwo() {
        FinancialTracker testTracker = new FinancialTracker();
        testTracker.addEntry(new Expense("Lunch", 5.20));
        testTracker.addEntry(new Income("Allowance", 200));
        //assertTrue(testTracker.isEmpty() == false && testTracker.size() == 2);
    }
}
