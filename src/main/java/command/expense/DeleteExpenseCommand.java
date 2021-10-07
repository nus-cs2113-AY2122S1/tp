package command.expense;

import picocli.CommandLine.ArgGroup;
import picocli.CommandLine.Command;
import picocli.CommandLine.Option;
import service.ExpenseManager;
import terminal.Ui;

import java.util.concurrent.Callable;

@Command(name = "delete",
        description = "Deletes expense record(s) from the database "
                + "by the unique identifier (from list) or by the name.")
public class DeleteExpenseCommand implements Callable<Integer> {

    @ArgGroup(exclusive = true, multiplicity = "1")
    Exclusive exclusive;

    @Override
    public Integer call() throws Exception {
        Ui ui = Ui.getUi();
        String expenseName;
        String errorMsg = "Error with deleting expense. Ensure you keyed in the correct"
                + " id or expense name and try again!";

        try {
            if (exclusive.names != null) {
                expenseName = String.join(" ", exclusive.names);
                ExpenseManager.deleteExpense(expenseName);
            } else if (exclusive.id <= 0) {
                ui.printMessage(errorMsg);
            } else {
                ExpenseManager.deleteExpense(exclusive.id);
            }
        } catch (Exception error) {
            ui.printMessage(errorMsg);
        }
        return 0;
    }

    static class Exclusive {
        @Option(names = {"-n", "--name"}, required = true, description = "Name of the expense item")
        String names;

        @Option(names = {"-i", "--id"}, required = true, description = "Unique identifier of the expense item")
        Integer id;
    }
}

