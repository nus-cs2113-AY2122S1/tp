package command.budget;

import picocli.CommandLine.Command;
import java.util.concurrent.Callable;

@Command(name = "delete", description = "Delete any budget set for the current month.")
public class DeleteBudgetCommand implements Callable<Integer> {

    @Override
    public Integer call() throws Exception {

        return 0;
    }
}
