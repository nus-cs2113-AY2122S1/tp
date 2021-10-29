package command.expense;

import picocli.CommandLine.Option;
import picocli.CommandLine.Command;
import service.ExpenseManager;
import terminal.Ui;
import utils.Money;

import java.util.concurrent.Callable;

import static constants.ErrorMessage.expenseWarningErrorMsg;

@Command(name = "warning", mixinStandardHelpOptions = true,
        description = "Set expense warning with respect to the budget set.")
public class WarningExpenseCommand implements Callable<Integer> {

    @Option(names = {"-v", "--value"}, required = true,
            description = "Value of warning limit when expense is about to exceed budget.")
    double warning;

    public Integer call() throws Exception {
        Ui ui = Ui.getUi();
        ExpenseManager expenseMgr = ExpenseManager.getExpenseMgr();

        try {
            double warningValue = Money.truncate(warning);
            expenseMgr.setWarningAmt(warningValue);
        } catch (Exception error) {
            ui.printMessage(expenseWarningErrorMsg);
        }

        return 0;
    }
}
