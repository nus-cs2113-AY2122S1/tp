package command.medicine;

import command.Command;
import command.CommandParameters;
import command.CommandSyntax;
import inventory.Medicine;
import inventory.Stock;
import parser.DateParser;
import parser.StockValidator;
import storage.Storage;
import ui.Ui;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Delete medication based on user input given stock id.
 */

public class DeleteStockCommand extends Command {
    private static Logger logger = Logger.getLogger("DeleteStock");

    @Override
    public void execute(Ui ui, LinkedHashMap<String, String> parameters, ArrayList<Medicine> medicines,
                        Storage storage) {
        logger.log(Level.INFO, "Start deletion of stock");

        String[] requiredParameters = {};
        String[] optionalParameters = {CommandParameters.ID, CommandParameters.EXPIRY_DATE};

        if (CommandSyntax.containsInvalidParameters(ui, parameters, requiredParameters, optionalParameters,
                CommandSyntax.DELETE_STOCK_COMMAND, true)) {
            logger.log(Level.WARNING, "Invalid parameter is specified by user");
            logger.log(Level.INFO, "Unsuccessful deletion of stock");
            return;
        }

        boolean isInvalidParameterValues = StockValidator.containsInvalidParameterValues(ui, parameters, medicines,
                CommandSyntax.DELETE_STOCK_COMMAND);
        if (isInvalidParameterValues) {
            logger.log(Level.WARNING, "Invalid parameter value specified by user");
            return;
        }

        boolean hasStockId = parameters.containsKey(CommandParameters.ID);
        boolean hasExpiryDate = parameters.containsKey(CommandParameters.EXPIRY_DATE);

        // Both fields should not be provided for deletion of stock.
        if (hasStockId && hasExpiryDate) {
            ui.print("Deleted aborted! Please provide only one parameter");
            return;
        }

        if (hasStockId && !hasExpiryDate) {
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
        }

        if (!hasStockId && hasExpiryDate) {
            removeExpiredStocks(ui, parameters, medicines);
        }

        storage.saveData(medicines);
        logger.log(Level.INFO, "Successful deletion of stock");
    }

    /**
     * Deletion of expired stocks given a date.
     *
     * @param ui         Reference to the UI object passed by Main to print messages.
     * @param parameters LinkedHashMap Key-Value set for parameter and user specified parameter value.
     * @param medicines  Arraylist of all medicines.
     */
    private static void removeExpiredStocks(Ui ui, LinkedHashMap<String, String> parameters,
                                            ArrayList<Medicine> medicines) {
        String dateString = parameters.get(CommandParameters.EXPIRY_DATE);
        Date date = null;
        try {
            date = DateParser.stringToDate(dateString);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        ArrayList<Stock> stocks = new ArrayList<>();
        int totalRows = medicines.size();
        for (Medicine medicine : medicines) {
            if (!(medicine instanceof Stock)) {
                continue;
            }
            stocks.add((Stock) medicine);
        }
        for (Stock stock : stocks) {
            Date stockDate = stock.getExpiry();
            if (stockDate.before(date)) {
                medicines.remove(stock);
            }
        }
        int remainingRows = medicines.size();
        int rowsDeleted = totalRows - remainingRows;

        assert rowsDeleted >= 0 : "Rows deleted cannot be negative";
        if (rowsDeleted > 0) {
            logger.log(Level.INFO, "Expired stock found: deleted " + rowsDeleted);
            ui.print("Deleted expired medications! Rows deleted: " + rowsDeleted);
        } else {
            logger.log(Level.INFO, "No expired stocks found");
            ui.print("Delete aborted! Unable to find expired medications!");
        }
    }
}
