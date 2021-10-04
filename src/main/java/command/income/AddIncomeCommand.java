package command.income;

import picocli.CommandLine.Command;
import picocli.CommandLine.Option;
import picocli.CommandLine.Parameters;
import utils.Money;

import java.util.concurrent.Callable;

@Command(name = "add", mixinStandardHelpOptions = true, description = "Adds an income source.")
public class AddIncomeCommand implements Callable<Integer> {

    @Parameters(paramLabel = "NAME", description = "Name of the income source")
    String name;

    @Option(names = {"-v", "--value"}, description = "Value of the income")
    Double value;

    @Override
    public Integer call() throws Exception {

        Double incomeValue = Money.truncate(value);

        return 0;
    }
}
