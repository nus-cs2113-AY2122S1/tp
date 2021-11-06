package command.budget;

import command.CommandLineFactory;
import entity.budget.Budget;
import entity.budget.BudgetList;
import org.junit.jupiter.api.Test;
import picocli.CommandLine;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DeleteBudgetCommandTest {

    @Test
    public void deleteBudgetCommand_deleteBudget_success() {
        Budget budget = new Budget(1000);
        BudgetList.addBudget(budget);

        CommandLine cmd = CommandLineFactory.getCmd();
        int exitCode = cmd.execute("budget", "delete");
        assertEquals(0, exitCode);
    }
}
