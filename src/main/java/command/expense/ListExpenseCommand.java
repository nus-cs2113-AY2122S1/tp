package command.expense;

import picocli.CommandLine.Command;
import service.ExpenseManager;

import java.util.concurrent.Callable;

@Command(name = "list",
        description = "Shows a list of all expenses, together with the sum of expenses in the current month. "
        + "The expenses will be automatically sorted in reverse chronological order.")
public class ListExpenseCommand implements Callable<Integer> {

    public Integer call() throws Exception {
        System.out.println("Your expenses:");
        ExpenseManager.listExpenses();
        return 0;
    }
}
