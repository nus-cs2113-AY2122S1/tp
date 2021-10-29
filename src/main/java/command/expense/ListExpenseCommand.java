package command.expense;

import picocli.CommandLine.Command;
import picocli.CommandLine.Option;
import service.ExpenseManager;
import terminal.Ui;

import java.util.concurrent.Callable;

@Command(name = "list",
        description = "Shows a list of all expenses, together with the sum of expenses in the current month. "
                + "The expenses will be automatically sorted in chronological order.")
public class ListExpenseCommand implements Callable<Integer> {

    @Option(names = {"-c", "--category"}, description = "Category of the expense item")
    String category;

    public Integer call() throws Exception {
        Ui ui = Ui.getUi();
        ExpenseManager expenseMgr = ExpenseManager.getExpenseMgr();
        if (category != null) {
            ui.printMessage("Your expenses for category (" + category + "):");
            expenseMgr.listExpenses(category);
        } else {
            ui.printMessage("Your expenses:");
            expenseMgr.listExpenses();
        }

        return 0;
    }
}
