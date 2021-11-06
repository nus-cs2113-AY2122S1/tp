package command.invest;

import command.CommandLineFactory;
import entity.income.Income;
import entity.income.IncomeList;
import entity.invest.Saving;
import entity.invest.SavingList;
import entity.invest.Stock;
import entity.invest.StockList;
import org.junit.jupiter.api.Test;
import picocli.CommandLine;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class DeleteStockCommandTest {

    @Test
    public void deleteStockCommand_deleteStockById_success() {
        Stock stock = new Stock("dbs", 1000, 25.00);
        StockList.addStock(stock);

        CommandLine cmd = CommandLineFactory.getCmd();
        int exitCode = cmd.execute("invest", "delete", "stocks", "-i=1");
        assertEquals(0, exitCode);
    }
}
