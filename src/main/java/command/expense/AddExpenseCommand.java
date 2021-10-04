package command.expense;

import picocli.CommandLine.Option;
import picocli.CommandLine.Parameters;
import picocli.CommandLine.Command;
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
            String budgetName = String.join(" ", names);
            Double budgetValue = Money.truncate(value);

            // do something with the name and value
        }
        return 0;
    }
}
