package command.budget;

import picocli.CommandLine.Command;
import service.BudgetManager;
import terminal.Ui;

import java.util.concurrent.Callable;

import static constants.ErrorMessage.deleteBudgetErrorMsg;

@Command(name = "delete", description = "Delete any budget set for the current month.")
public class DeleteBudgetCommand implements Callable<Integer> {

    @Override
    public Integer call() throws Exception {
        Ui ui = Ui.getUi();

        try {
            BudgetManager.deleteBudget();
        } catch (Exception error) {
            ui.printMessage(deleteBudgetErrorMsg);
        }
        return 0;
    }
}

