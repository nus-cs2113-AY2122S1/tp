package command.expense;

import command.CommandLineFactory;
import org.junit.jupiter.api.Test;
import picocli.CommandLine;
import storage.DataManager;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class AddExpenseCommandTest {

    @Test
    public void testAddExpense() {
        try {
            DataManager dataMgr = DataManager.getDataMgr();
            dataMgr.loadAllManagers();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }

        CommandLine cmd = CommandLineFactory.getCmd();
        int exitCode = cmd.execute("expense", "add", "Eat Lunch", "-v=20.99");
        assertEquals(0, exitCode);
    }
}
