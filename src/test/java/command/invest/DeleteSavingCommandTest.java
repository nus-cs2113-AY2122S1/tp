package command.invest;

import command.CommandLineFactory;
import entity.income.Income;
import entity.income.IncomeList;
import entity.invest.Saving;
import entity.invest.SavingList;
import org.junit.jupiter.api.Test;
import picocli.CommandLine;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class DeleteSavingCommandTest {

    @Test
    public void testDeleteSavingById() {
        Saving saving = new Saving("main", 1000.00);
        SavingList.addSavings(saving);

        CommandLine cmd = CommandLineFactory.getCmd();
        int exitCode = cmd.execute("invest", "delete", "savings", "-i=1");
        assertEquals(0, exitCode);
    }
}
