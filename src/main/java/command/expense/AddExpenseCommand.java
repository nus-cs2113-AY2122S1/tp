package command.expense;

import picocli.CommandLine.Command;
import picocli.CommandLine.Option;
import picocli.CommandLine.Parameters;
import service.ExpenseManager;
import terminal.Ui;
import utils.Money;

import java.util.concurrent.Callable;

@Command(name = "add", mixinStandardHelpOptions = true,
        description = "Adds an expense in the current month to the database.")
public class AddExpenseCommand implements Callable<Integer> {

    @Parameters(paramLabel = "NAME", description = "Name of the expense item")
    String[] names;
    @Option(names = {"-v", "--value"}, required = true, description = "Value of the expense item")
    Double value;

    @Override
    public Integer call() throws Exception {
        Ui ui = Ui.getUi();
        String errorMsg = "Error with adding expense. Ensure you keyed in both an expense name"
                + " and a value and try again!";

        if (names == null || value == null) {
            ui.printMessage(errorMsg);
            return 0;
        }
        try {
            String expenseName = String.join(" ", names);
            Double expenseValue = Money.truncate(value);
            ExpenseManager.addExpense(expenseName, expenseValue);
        } catch (Exception error) {
            ui.printMessage(errorMsg);
        }
        return 0;
    }
}
