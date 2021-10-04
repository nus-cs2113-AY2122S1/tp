package command.income;

import picocli.CommandLine.Command;

import java.util.concurrent.Callable;

@Command(name="delete")
public class DeleteIncomeCommand implements Callable<Integer> {

    public Integer call() throws Exception{
        System.out.println("delete budget");
        return 0;
    }
}
