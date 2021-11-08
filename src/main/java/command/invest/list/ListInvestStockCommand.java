package command.invest.list;

import picocli.CommandLine.Command;
import service.InvestManager;
import terminal.Ui;

import java.util.concurrent.Callable;

@Command(name = "stocks", description = "Shows a list of all stocks you own.")
public class ListInvestStockCommand implements Callable<Integer> {

    public Integer call() throws Exception {
        Ui ui = Ui.getUi();
        InvestManager investMgr = InvestManager.getInvestManager();
        ui.printMessage("Your stocks:");
        investMgr.listAllStocks();
        return 0;
    }
}
