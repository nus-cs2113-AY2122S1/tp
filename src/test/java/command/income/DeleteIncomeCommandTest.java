package command.income;

import command.CommandLineFactory;
import entity.income.Income;
import entity.income.IncomeList;
import org.junit.jupiter.api.Test;
import picocli.CommandLine;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class DeleteIncomeCommandTest {

    @Test
    public void deleteIncomeCommand_deleteIncomeByName_success() {
        Income income = new Income("Salary", 1000, "14/11/2011");
        IncomeList.addIncome(income);

        CommandLine cmd = CommandLineFactory.getCmd();
        int exitCode = cmd.execute("income", "delete", "-n Salary");
        assertEquals(0, exitCode);
    }

    @Test
    public void deleteIncomeCommand_deleteIncomeById_success() {
        Income income = new Income("Salary", 1000, "14/11/2011");
        IncomeList.addIncome(income);

        CommandLine cmd = CommandLineFactory.getCmd();
        int exitCode = cmd.execute("income", "delete", "-i=1");
        assertEquals(0, exitCode);
    }
}
