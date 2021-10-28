package command.invest.list;

import picocli.CommandLine.Command;
import service.IncomeManager;
import service.InvestManager;
import terminal.Ui;

import java.util.concurrent.Callable;

@Command(name = "savings", description = "Shows a list of all savings.")
public class ListInvestSavingCommand implements Callable<Integer> {

    public Integer call() throws Exception {
        Ui ui = Ui.getUi();
        InvestManager investMgr = InvestManager.getInvestManager();
        ui.printMessage("Your savings:");
        investMgr.listAllSavings();
        return 0;
    }
}
