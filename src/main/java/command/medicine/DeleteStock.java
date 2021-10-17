package command.medicine;

import command.Command;
import command.CommandParameters;
import command.CommandSyntax;
import inventory.Medicine;
import inventory.Stock;
import parser.StockValidator;
import storage.Storage;
import ui.Ui;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Delete medication based on user input given stock id.
 */

public class DeleteStock extends Command {
    private static Logger logger = Logger.getLogger("DeleteStock");

    @Override
    public void execute(Ui ui, LinkedHashMap<String, String> parameters, ArrayList<Medicine> medicines,
                        Storage storage) {
        logger.log(Level.INFO, "Start deletion of stock");

        String[] requiredParameters = {CommandParameters.ID};
        String[] optionalParameters = {};

        if (CommandSyntax.containsInvalidParameters(ui, parameters, requiredParameters, optionalParameters,
                CommandSyntax.DELETE_STOCK_COMMAND, true)) {
            logger.log(Level.WARNING, "Invalid parameter is specified by user");
            logger.log(Level.INFO, "Unsuccessful deletion of stock");
            return;
        }

        String stockIdToDelete = parameters.get(CommandParameters.ID);
        if (!StockValidator.isValidStockId(ui, stockIdToDelete, medicines)) {
            logger.log(Level.WARNING, "Invalid stock id is specified by user");
            logger.log(Level.INFO, "Unsuccessful deletion of stock");
            return;
        }

        int stockId = Integer.parseInt(stockIdToDelete);

        assert stockId > 0 : "Stock Id should be more than 0";
        assert stockId <= Stock.getStockCount() : "Stock Id should not exceed max stock count";

        for (Medicine medicine : medicines) {
            if (!(medicine instanceof Stock)) {
                continue;
            }
            Stock stock = (Stock) medicine;
            if (stock.getStockID() == stockId) {
                medicines.remove(medicine);
                logger.log(Level.INFO, "Stock id found and deleted");
                break;
            }
        }
        ui.print("Medication deleted: Stock_Id=" + stockId);
        storage.saveData(medicines);
        logger.log(Level.INFO, "Successful deletion of stock");
    }
}