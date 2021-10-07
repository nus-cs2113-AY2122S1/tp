package command.expense;

import picocli.CommandLine.ArgGroup;
import picocli.CommandLine.Option;
import picocli.CommandLine.Command;
import service.ExpenseManager;

import java.util.concurrent.Callable;

@Command(name = "delete",
        description = "Deletes expense record(s) from the database "
                + "by the unique identifier (from list) or by the name.")
public class DeleteExpenseCommand implements Callable<Integer> {

    static class Exclusive {
        @Option(names = {"-n", "--name"}, required = true, description = "Name of the expense item")
        String names;

        @Option(names = {"-i", "--id"}, required = true, description = "Unique identifier of the expense item")
        Integer id;
    }

    @ArgGroup(exclusive = true, multiplicity = "1")
    Exclusive exclusive;

    @Override
    public Integer call() throws Exception {
        String expenseName;
        if (exclusive.names != null) {
            expenseName = String.join(" ", exclusive.names);
            ExpenseManager.deleteExpense(expenseName);
        } else {
            ExpenseManager.deleteExpense(exclusive.id);
        }
        return 0;
    }
}

