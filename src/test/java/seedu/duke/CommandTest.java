package seedu.duke;

import org.junit.jupiter.api.Test;
import seedu.commands.Command;
import seedu.commands.DeleteExpenseCommand;
import seedu.utility.FinancialTracker;

import java.util.jar.JarFile;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CommandTest {
    @Test
    void testDeleteExpenseCommand() {
        int deleteIndex = 2;
        new DeleteExpenseCommand(deleteIndex);
        assertEquals(financialEntry.getEntry(deleteIndex) == null);

    }

    void testGetEntry() {

    }
}
