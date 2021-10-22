package command.budget;

import picocli.CommandLine.Command;
import picocli.CommandLine.Option;
import service.BudgetManager;
import terminal.Ui;
import utils.Money;

import java.util.concurrent.Callable;

import static constants.ErrorMessage.updateBudgetErrorMsg;

@Command(name = "update", mixinStandardHelpOptions = true, description = "Updates a budget plan for the current month.")
public class UpdateBudgetCommand implements Callable<Integer> {

    @Option(names = {"-v", "--value"}, required = true, description = "Value of the current month's budget")
    Double value;

    @Override
    public Integer call() throws Exception {
        Ui ui = Ui.getUi();

        try {
            Double budgetValue = Money.truncate(value);
            BudgetManager.updateBudget(budgetValue);
        } catch (Exception error) {
            ui.printMessage(updateBudgetErrorMsg);
        }
        return 0;
    }
}