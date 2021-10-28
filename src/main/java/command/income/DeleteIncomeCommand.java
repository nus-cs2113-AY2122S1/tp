package command.income;

import picocli.CommandLine.ArgGroup;
import picocli.CommandLine.Command;
import picocli.CommandLine.Option;
import service.IncomeManager;
import terminal.Ui;

import java.util.concurrent.Callable;

import static constants.ErrorMessage.deleteIncomeErrorMsg;

@Command(name = "delete",
        description = "Deletes an income source from the database by its unique identifier or by its name.")
public class DeleteIncomeCommand implements Callable<Integer> {

    @ArgGroup(exclusive = true)
    Exclusive exclusive;

    public Integer call() throws Exception {
        Ui ui = Ui.getUi();
        IncomeManager incomeMgr = IncomeManager.getIncomeManager();
        String incomeName;

        try {
            if (exclusive.names != null) {
                incomeName = String.join(" ", exclusive.names);
                incomeMgr.deleteIncome(incomeName);
            } else {
                incomeMgr.deleteIncome(exclusive.id);
            }
        } catch (Exception error) {
            ui.printMessage(deleteIncomeErrorMsg);
            return 1;
        }
        return 0;
    }

    static class Exclusive {
        @Option(names = {"-n", "--name"}, required = true, arity = "1..*", description = "Name of the income source")
        String[] names;

        @Option(names = {"-i", "--id"}, required = true, description = "Unique identifier of the income source")
        Integer id;
    }
}
