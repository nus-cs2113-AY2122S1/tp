package command.expense;

import command.CommandLineFactory;
import entity.Expense;
import entity.ExpenseList;
import org.junit.jupiter.api.Test;
import picocli.CommandLine;
import storage.DataManager;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DeleteExpenseCommandTest {

    @Test
    public void testDeleteExpenseByName() {
        try {
            DataManager dataMgr = DataManager.getDataMgr();
            dataMgr.loadAllManagers();
        } catch (IOException e) {

        }

        Expense expense = new Expense("Eat Lunch", 10.99, "21-Nov-2021");
        ExpenseList.addExpense(expense);
        CommandLine cmd = CommandLineFactory.getCmd();
        int exitCode = cmd.execute("expense", "delete", "-n Eat Lunch");
        assertEquals(0, exitCode);
    }

    @Test
    public void testDeleteExpenseById() {
        try {
            DataManager dataMgr = DataManager.getDataMgr();
            dataMgr.loadAllManagers();
        } catch (IOException e) {

        }
        Expense expense = new Expense("Eat Lunch", 10.99, "21-Nov-2021");
        ExpenseList.addExpense(expense);
        CommandLine cmd = CommandLineFactory.getCmd();
        int exitCode = cmd.execute("expense", "delete", "-i=1");
        assertEquals(0, exitCode);
    }
}
