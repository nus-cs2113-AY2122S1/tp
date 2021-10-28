package command.expense;

import picocli.CommandLine.Command;
import picocli.CommandLine.Option;
import picocli.CommandLine.Parameters;
import service.BudgetManager;
import service.ExpenseManager;
import storage.DataManager;
import terminal.Ui;
import utils.Money;

import java.io.IOException;
import java.util.concurrent.Callable;

import static constants.ErrorMessage.addExpenseErrorMsg;

@Command(name = "add", mixinStandardHelpOptions = true,
        description = "Adds an expense in the current month to the database.")
public class AddExpenseCommand implements Callable<Integer> {

    @Parameters(paramLabel = "NAME", arity = "1..*", description = "Name of the expense item")
    String[] names;

    @Option(names = {"-v", "--value"}, required = true, description = "Value of the expense item")
    Double value;

    @Override
    public Integer call() throws Exception {
        Ui ui = Ui.getUi();
        ExpenseManager expenseMgr = ExpenseManager.getExpenseMgr();
        DataManager dataMgr = DataManager.getDataMgr();

        try {
            String expenseName = String.join(" ", names);
            Double expenseValue = Money.truncate(value);
            expenseMgr.addExpense(expenseName, expenseValue);
        } catch (Exception error) {
            ui.printMessage(addExpenseErrorMsg);
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
