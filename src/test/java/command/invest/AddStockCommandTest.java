package command.invest;

import command.CommandLineFactory;
import org.junit.jupiter.api.Test;
import picocli.CommandLine;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class AddStockCommandTest {

    @Test
    public void addStockCommand_addStock_success() {
        CommandLine cmd = CommandLineFactory.getCmd();
        int exitCode = cmd.execute("invest", "add", "stocks", "d05", "-n=1000", "-p=25.00");

        assertEquals(0, exitCode);
    }
}
