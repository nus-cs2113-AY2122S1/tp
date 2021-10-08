package seedu.duke;

import org.junit.jupiter.api.Test;
import seedu.commands.AddExpenseCommand;
import seedu.commands.DeleteExpenseCommand;
import seedu.entry.Expense;
import seedu.utility.FinancialTracker;

import static org.junit.jupiter.api.Assertions.*;

public class CommandTest {

    @Test
    public void testAddExpenseCommand() {
        FinancialTracker testTracker = new FinancialTracker();
        Expense expense = new Expense("Bubble Tea", 4.80);
        new AddExpenseCommand(expense);
        assertNotNull(testTracker.getEntry(0));
    }

    @Test
    public void testDeleteExpenseCommand() {
        FinancialTracker testTracker = new FinancialTracker();
        new DeleteExpenseCommand(1);
        assertNull(testTracker.getEntry(0));
    }
}
