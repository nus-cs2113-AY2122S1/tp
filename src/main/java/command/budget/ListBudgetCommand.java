package command.budget;

import picocli.CommandLine.Command;
import service.BudgetManager;
import terminal.Ui;

import java.util.concurrent.Callable;

@Command(name = "list", description = "Shows the budget for the current month.")
public class ListBudgetCommand implements Callable<Integer> {

    public Integer call() throws Exception {
        Ui ui = Ui.getUi();
        ui.printMessage("list budget");
        BudgetManager.listBudgets();
        return 0;
    }
}

