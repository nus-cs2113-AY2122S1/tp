package command.expense;

import picocli.CommandLine.Command;
import picocli.CommandLine.Option;
import picocli.CommandLine.Parameters;
import service.ExpenseManager;
import storage.DataManager;
import terminal.Ui;
import utils.Money;

import java.io.IOException;
import java.util.concurrent.Callable;

import static constants.ErrorMessage.updateExpenseErrorMsg;

@Command(name = "update", mixinStandardHelpOptions = true,
        description = "Updates an expense in the current month from the list of expenses.")
public class UpdateExpenseCommand implements Callable<Integer> {

    @Parameters(paramLabel = "NAME", arity = "1..*", description = "Name of the expense item")
    String[] names;

    @Option(names = {"-v", "--value"}, required = true, description = "Value of the expense item")
    Double value;

    @Option(names = {"-c", "--category"}, description = "Category of the expense item")
    String category;

    @Override
    public Integer call() throws Exception {
        Ui ui = Ui.getUi();
        ExpenseManager expenseMgr = ExpenseManager.getExpenseMgr();
        DataManager dataMgr = DataManager.getDataMgr();

        try {
            String expenseName = String.join(" ", names);
            double expenseValue = Money.truncate(value);
            if (category != null) {
                expenseMgr.updateExpense(expenseName, expenseValue, category);
            } else {
                expenseMgr.updateExpense(expenseName, expenseValue);
            }
        } catch (Exception error) {
            ui.printMessage(updateExpenseErrorMsg);
            return 1;
        }

        try {
            dataMgr.write();
        } catch (IOException e) {
            ui.printErrorMessage(e.getMessage());
        }
        return 0;
    }
}
