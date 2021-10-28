package command.budget;

import command.CommandLineFactory;
import entity.Budget;
import entity.BudgetList;
import org.junit.jupiter.api.Test;
import picocli.CommandLine;
import storage.DataManager;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DeleteBudgetCommandTest {

    @Test
    public void testDeleteBudget() {
        try {
            DataManager dataMgr = DataManager.getDataMgr();
            dataMgr.loadAllManagers();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }

        Budget budget = new Budget(1000);
        BudgetList.addBudget(budget);

        CommandLine cmd = CommandLineFactory.getCmd();
        int exitCode = cmd.execute("budget", "delete");
        assertEquals(0, exitCode);
    }
}
