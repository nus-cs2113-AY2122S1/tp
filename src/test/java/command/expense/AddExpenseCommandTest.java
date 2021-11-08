package command.expense;

import command.CommandLineFactory;
import org.junit.jupiter.api.Test;
import picocli.CommandLine;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class AddExpenseCommandTest {

    @Test
    public void addExpenseCommand_addExpense_success() {
        CommandLine cmd = CommandLineFactory.getCmd();
        int exitCode = cmd.execute("expense", "add", "Eat Lunch", "-v=20.99");
        assertEquals(0, exitCode);
    }
}
