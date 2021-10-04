package command.budget;

import picocli.CommandLine.Option;
import picocli.CommandLine.Command;
import utils.Money;
import java.util.concurrent.Callable;

@Command(name="add", mixinStandardHelpOptions = true, description = "Adds a budget plan for the current month.")
public class AddBudgetCommand implements Callable<Integer> {

    @Option(names= {"-v", "--value"}, required = true, description = "Value of the current month's budget")
    Double value;

    @Override
    public Integer call() throws Exception{
        Double budgetValue = Money.truncate(value);

        // do something with the value
        return 0;
    }
}
