package command.stock;

import command.Command;
import command.CommandParameters;
import command.CommandSyntax;
import inventory.Medicine;
import inventory.Stock;
import utilities.parser.DateParser;
import utilities.parser.MedicineValidator;
import utilities.parser.StockValidator;
import utilities.storage.Storage;
import utilities.ui.Ui;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.logging.Level;
import java.util.logging.Logger;

//@@author RemusTeo

/**
 * Delete medication based on user input given stock id.
 */
public class DeleteStockCommand extends Command {
    private static Logger logger = Logger.getLogger("DeleteStock");

    public DeleteStockCommand(LinkedHashMap<String, String> parameters) {
        this.parameters = parameters;
    }

    @Override
    public void execute() {
        logger.log(Level.INFO, "Start deletion of stock");

        Ui ui = Ui.getInstance();
        ArrayList<Medicine> medicines = Medicine.getInstance();

        String[] requiredParameters = {};
        String[] optionalParameters = {CommandParameters.ID, CommandParameters.EXPIRING};

        MedicineValidator validator = new StockValidator();
        boolean isInvalidInput = validator.containsInvalidParametersAndValues(ui, medicines, parameters,
                requiredParameters, optionalParameters, CommandSyntax.DELETE_STOCK_COMMAND, true, validator);
        if (isInvalidInput) {
            logger.log(Level.INFO, "Unsuccessful deletion of stock");
            return;
        }

        boolean hasStockId = parameters.containsKey(CommandParameters.ID);
        boolean hasExpiryDate = parameters.containsKey(CommandParameters.EXPIRING);

        // Both fields should not be provided for deletion of stock.
        if (hasStockId && hasExpiryDate) {
            ui.print("Deleted aborted! Please provide only one parameter");
            return;
        }

        if (hasStockId && !hasExpiryDate) {
            deleteStockById(ui, medicines);
        } else if (!hasStockId && hasExpiryDate) {
            deleteStockByExpiry(ui, medicines);
        }

        Storage storage = Storage.getInstance();
        storage.saveData(medicines);
        logger.log(Level.INFO, "Successful deletion of stock");
    }

    /**
     * Deletion of stock given an id.
     *
     * @param ui        Reference to the UI object to print messages.
     * @param medicines Arraylist of all medicines.
     */
    private void deleteStockById(Ui ui, ArrayList<Medicine> medicines) {
        String stockIdToDelete = parameters.get(CommandParameters.ID);
        int stockId = Integer.parseInt(stockIdToDelete);

        assert stockId > 0 : "Stock Id should be more than 0";
        assert stockId <= Stock.getStockCount() : "Stock Id should not exceed max stock count";

        for (Medicine medicine : medicines) {
            if (!(medicine instanceof Stock)) {
                continue;
            }
            Stock stock = (Stock) medicine;
            if (stock.getStockId() == stockId) {
                stock.setQuantity(0);
                stock.setDeleted(true);
                logger.log(Level.INFO, "Stock id found and deleted");
                break;
            }
        }
        ui.print("Deleted row with Stock Id: " + stockId);
    }

    //@@author a-tph

    /**
     * Deletion of expired stocks given a date.
     *
     * @param ui        Reference to the UI object to print messages.
     * @param medicines Arraylist of all medicines.
     */
    private void deleteStockByExpiry(Ui ui, ArrayList<Medicine> medicines) {
        String dateString = parameters.get(CommandParameters.EXPIRING);
        Date date = null;
        try {
            date = DateParser.stringToDate(dateString);
        } catch (ParseException e) {
            e.printStackTrace();
        }


        int rowsDeleted = 0;
        for (Medicine medicine : medicines) {
            if (!(medicine instanceof Stock)) {
                continue;
            }

            Stock stock = (Stock) medicine;
            Date stockExpiryDate = stock.getExpiry();
            if (stock.isDeleted()) {
                continue;
            }

            if (stockExpiryDate.before(date) || stockExpiryDate.equals(date)) {
                stock.setQuantity(0);
                stock.setDeleted(true);
                rowsDeleted++;
            }
        }

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
