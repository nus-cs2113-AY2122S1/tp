package command.invest.add;

import picocli.CommandLine;
import service.InvestManager;
import storage.DataManager;
import terminal.Ui;
import utils.Money;

import java.util.concurrent.Callable;


@CommandLine.Command(name = "stocks", description = "Add a stock to your portfolio")
public class AddInvestStockCommand implements Callable<Integer> {
    @CommandLine.Parameters(paramLabel = "NAME", arity = "1..*", description = "Stock ticker")
    String[] names;

    @CommandLine.Option(names = {"-n", "--number"}, required = true, description = "Number of stocks you owned")
    Integer numberOfStocks;

    @CommandLine.Option(names = {"-p", "--price"}, required = true, description = "Cost of each stock")
    Double stockPrice;

    @Override
    public Integer call() throws Exception {
        Ui ui = Ui.getUi();
        InvestManager investMgr = InvestManager.getInvestManager();
        DataManager dataMgr = DataManager.getDataMgr();

        try {
            String stockName = String.join(" ", names);
            Double price = Money.truncate(stockPrice);
            investMgr.addStock(stockName, numberOfStocks, price);
            dataMgr.write();
        } catch (Exception error) {
            ui.printMessage(error.getMessage());
            return 1;
        }
        return 0;
    }
}
