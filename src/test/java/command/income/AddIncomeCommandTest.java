package command.income;

import command.CommandLineFactory;
import org.junit.jupiter.api.Test;
import picocli.CommandLine;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class AddIncomeCommandTest {

    @Test
    public void addIncomeCommand_addIncome_success() {
        CommandLine cmd = CommandLineFactory.getCmd();
        int exitCode = cmd.execute("income", "add", "Salary", "-v 1000");

        assertEquals(0, exitCode);
    }
}
