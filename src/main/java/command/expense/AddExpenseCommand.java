package command.expense;

import picocli.CommandLine.Option;
import picocli.CommandLine.Parameters;
import picocli.CommandLine.Command;
import service.ExpenseManager;
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

        if (names != null) {
            String expenseName = String.join(" ", names);
            Double expenseValue = Money.truncate(value);

            ExpenseManager.addExpense(expenseName, expenseValue);
        }
        return 0;
    }
}
