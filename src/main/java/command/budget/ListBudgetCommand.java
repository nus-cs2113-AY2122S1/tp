package command.budget;

import picocli.CommandLine.Command;
import java.util.concurrent.Callable;

@Command(name="list", description = "Shows the budget for the current month.")
public class ListBudgetCommand implements Callable<Integer> {

    public Integer call() throws Exception {
        System.out.println("list budget");
        return 0;
    }
}
