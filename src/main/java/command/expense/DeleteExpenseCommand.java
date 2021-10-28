package command.expense;

import picocli.CommandLine.ArgGroup;
import picocli.CommandLine.Command;
import picocli.CommandLine.Option;
import service.ExpenseManager;
import storage.DataManager;
import terminal.Ui;

import java.util.concurrent.Callable;

import static constants.ErrorMessage.deleteExpenseErrorMsg;

@Command(name = "delete",
        description = "Deletes expense record(s) from the database "
                + "by the unique identifier (from list) or by the name.")
public class DeleteExpenseCommand implements Callable<Integer> {

    @ArgGroup(exclusive = true, multiplicity = "1")
    Exclusive exclusive;

    @Override
    public Integer call() throws Exception {
        Ui ui = Ui.getUi();
        ExpenseManager expenseMgr = ExpenseManager.getExpenseMgr();
        DataManager dataMgr = DataManager.getDataMgr();
        String expenseName;

        try {
            if (exclusive.names != null) {
                expenseName = String.join(" ", exclusive.names);
                expenseMgr.deleteExpense(expenseName);
            } else {
                expenseMgr.deleteExpense(exclusive.id);
            }
            dataMgr.write();
        } catch (Exception error) {
            ui.printMessage(deleteExpenseErrorMsg);
            return 1;
        }
        return 0;
    }

    static class Exclusive {
        @Option(names = {"-n", "--name"}, required = true, arity = "1..*", description = "Name of the expense item")
        String[] names;

        @Option(names = {"-i", "--id"}, required = true, description = "Unique identifier of the expense item")
        Integer id;
    }
}

