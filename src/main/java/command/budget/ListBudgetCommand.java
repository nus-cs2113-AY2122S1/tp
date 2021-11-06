package command.budget;

import picocli.CommandLine.Command;
import service.BudgetManager;
import terminal.Ui;

import java.util.concurrent.Callable;

@Command(name = "list", description = "Shows the budget for the current month.")
public class ListBudgetCommand implements Callable<Integer> {

    public Integer call() throws Exception {
        Ui ui = Ui.getUi();
        BudgetManager budgetMgr = BudgetManager.getBudgetMgr();

        ui.printMessage("list budget");
        budgetMgr.listBudgets();
        return 0;
    }
}

