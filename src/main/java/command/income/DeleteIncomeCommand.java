package command.income;

import picocli.CommandLine.ArgGroup;
import picocli.CommandLine.Command;
import picocli.CommandLine.Option;

import java.util.concurrent.Callable;

@Command(name = "delete",
        description = "Deletes an income source from the database by its unique identifier or by its name.")
public class DeleteIncomeCommand implements Callable<Integer> {

    @ArgGroup(exclusive = true)
    Exclusive exclusive;

    public Integer call() throws Exception {
        String incomeName;
        if (exclusive.names != null) {
            incomeName = String.join(" ", exclusive.names);
            //do something with the name
        } else {
            //do something with the id
        }

        return 0;
    }

    static class Exclusive {
        @Option(names = {"-n", "--name"}, required = true, description = "Name of the income source")
        String names;

        @Option(names = {"-i", "--id"}, required = true, description = "Unique identifier of the income source")
        Integer id;
    }
}
