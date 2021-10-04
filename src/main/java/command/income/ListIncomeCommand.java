package command.income;

import picocli.CommandLine.Command;

import java.util.concurrent.Callable;

@Command(name = "list", description = "Shows a list of all income sources for the current month.")
public class ListIncomeCommand implements Callable<Integer> {

    public Integer call() throws Exception {
        System.out.println("list budget");
        return 0;
    }
}
