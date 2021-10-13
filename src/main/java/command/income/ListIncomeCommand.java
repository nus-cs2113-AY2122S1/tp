package command.income;

import picocli.CommandLine.Command;
import service.IncomeManager;
import terminal.Ui;

import java.util.concurrent.Callable;

@Command(name = "list", description = "Shows a list of all income sources for the current month.")
public class ListIncomeCommand implements Callable<Integer> {

    public Integer call() throws Exception {
        Ui ui = Ui.getUi();
        ui.printMessage("Your incomes:");
        IncomeManager.listIncomes();
        return 0;
    }
}
