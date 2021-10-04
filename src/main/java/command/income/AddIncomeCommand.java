package command.income;

import picocli.CommandLine.Command;
import picocli.CommandLine.Option;
import picocli.CommandLine.Parameters;

import java.util.concurrent.Callable;

@Command(name="add", mixinStandardHelpOptions = true)
public class AddIncomeCommand implements Callable<Integer> {

    @Parameters(paramLabel = "NAME", description = "The name of budget")
    String name;

    @Option(names="-t", description = "To eat")
    boolean t;

    @Override
    public Integer call() throws Exception{
        System.out.println("add budget");
        return 0;
    }
}
