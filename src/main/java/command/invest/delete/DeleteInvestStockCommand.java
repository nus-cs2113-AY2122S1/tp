package command.invest.delete;

import picocli.CommandLine;
import service.InvestManager;
import storage.DataManager;
import terminal.Ui;

import java.util.concurrent.Callable;

@CommandLine.Command(name = "stocks", description = "Delete a stock listing from your portfolio")
public class DeleteInvestStockCommand implements Callable<Integer> {

    @CommandLine.Option(names = {"-i", "--id"}, required = true, description = "Unique identifier of the stock")
    Integer index;

    @Override
    public Integer call() throws Exception {
        Ui ui = Ui.getUi();
        InvestManager investMgr = InvestManager.getInvestManager();
        DataManager dataMgr = DataManager.getDataMgr();

        try {
            investMgr.deleteStock(index);
            dataMgr.write();
        } catch (Exception error) {
            ui.printMessage(error.getMessage());
            return 1;
        }
        return 0;
    }
}
