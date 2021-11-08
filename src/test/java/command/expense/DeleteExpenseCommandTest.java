package command.expense;

import command.CommandLineFactory;
import entity.expense.Expense;
import entity.expense.ExpenseList;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import picocli.CommandLine;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DeleteExpenseCommandTest {

    @BeforeAll
    public static void setUp() {
        ExpenseList.clearExpenses();
    }

    @AfterAll
    public static void tearDown() {
        ExpenseList.clearExpenses();
    }

    @Test
    public void deleteExpenseCommand_deleteExpenseByName_success() {
        Expense expense = new Expense("Eat Lunch", 10.99, "21/11/2021");
        ExpenseList.addExpense(expense);
        CommandLine cmd = CommandLineFactory.getCmd();
        int exitCode = cmd.execute("expense", "delete", "-n Eat Lunch");
        assertEquals(0, exitCode);

        ExpenseList.clearExpenses();
    }

    @Test
    public void deleteExpenseCommand_deleteExpenseById_success() {
        Expense expense = new Expense("Eat Lunch", 10.99, "21/11/2021");
        ExpenseList.addExpense(expense);
        CommandLine cmd = CommandLineFactory.getCmd();
        int exitCode = cmd.execute("expense", "delete", "-i=1");
        assertEquals(0, exitCode);

        ExpenseList.clearExpenses();
    }
}
