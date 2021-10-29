package command.invest;

import command.CommandLineFactory;
import org.junit.jupiter.api.Test;
import picocli.CommandLine;
import storage.DataManager;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class AddSavingCommandTest {

    @Test
    public void testAddSaving() {
        try {
            DataManager dataMgr = DataManager.getDataMgr();
            dataMgr.loadAllManagers();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        CommandLine cmd = CommandLineFactory.getCmd();
        int exitCode = cmd.execute("invest", "add", "savings", "main", "-v 1000");

        assertEquals(0, exitCode);
    }
}
