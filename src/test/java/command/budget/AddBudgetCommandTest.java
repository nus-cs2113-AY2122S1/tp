package command.budget;

import command.CommandLineFactory;
import org.junit.jupiter.api.Test;
import picocli.CommandLine;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class AddBudgetCommandTest {

    @Test
    public void testAddBudget() {
        CommandLine cmd = CommandLineFactory.getCmd();
        int exitCode = cmd.execute("budget", "add", "-v=1000");
        assertEquals(0, exitCode);
    }
}
