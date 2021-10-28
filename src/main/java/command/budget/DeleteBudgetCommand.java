package command.budget;

import picocli.CommandLine.Command;
import service.BudgetManager;
import storage.DataManager;
import terminal.Ui;

import java.util.concurrent.Callable;

import static constants.ErrorMessage.deleteBudgetErrorMsg;

@Command(name = "delete", description = "Delete any budget set for the current month.")
public class DeleteBudgetCommand implements Callable<Integer> {

    @Override
    public Integer call() throws Exception {
        Ui ui = Ui.getUi();
        BudgetManager budgetMgr = BudgetManager.getBudgetMgr();
        DataManager dataMgr = DataManager.getDataMgr();

        try {
            budgetMgr.deleteBudget();
            dataMgr.write();
        } catch (Exception error) {
            ui.printMessage(deleteBudgetErrorMsg);
            return 1;
        }
        return 0;
    }
}

