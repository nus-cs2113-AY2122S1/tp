package command.budget;

import command.CommandLineFactory;
import org.junit.jupiter.api.Test;
import picocli.CommandLine;
import storage.DataManager;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class AddBudgetCommandTest {

    @Test
    public void testAddBudget() {
        try {
            DataManager dataMgr = DataManager.getDataMgr();
            dataMgr.loadAllManagers();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }

        CommandLine cmd = CommandLineFactory.getCmd();
        int exitCode = cmd.execute("budget", "add", "-v=1000");
        assertEquals(0, exitCode);
    }
}
