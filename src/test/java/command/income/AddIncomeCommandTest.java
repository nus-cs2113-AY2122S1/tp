package command.income;

import command.CommandLineFactory;
import org.junit.jupiter.api.Test;
import picocli.CommandLine;
import storage.DataManager;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class AddIncomeCommandTest {

    @Test
    public void testAddIncome() {
        try {
            DataManager dataMgr = DataManager.getDataMgr();
            dataMgr.loadAllManagers();
        } catch (IOException e) {

        }
        CommandLine cmd = CommandLineFactory.getCmd();
        int exitCode = cmd.execute("income", "add", "Salary", "-v 1000");

        assertEquals(0, exitCode);
    }
}
