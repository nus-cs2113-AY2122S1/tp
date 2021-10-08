package seedu.duke;

import org.junit.jupiter.api.Test;
import seedu.commands.AddExpenseCommand;
import seedu.commands.DeleteExpenseCommand;
import seedu.entry.Expense;
import seedu.utility.FinancialTracker;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

public class CommandTest {

    @Test
    public void testAddExpenseCommand() {
        FinancialTracker testTracker = new FinancialTracker();
        testTracker.addEntry(new Expense("Bubble Tea", 4.80));
        assertNotNull(testTracker.getEntry(0));
    }

    @Test
    public void testDeleteExpenseCommand() {
        FinancialTracker testTracker = new FinancialTracker();
        testTracker.addEntry(new Expense("Bubble Tea", 4.80));
        testTracker.removeEntry(0);
        assertNull(testTracker.getEntry(0));
    }
}
