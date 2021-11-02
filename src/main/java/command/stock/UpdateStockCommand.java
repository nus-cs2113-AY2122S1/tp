package command.stock;

import command.Command;
import command.CommandParameters;
import command.CommandSyntax;
import inventory.Medicine;
import inventory.Stock;
import utilities.parser.DateParser;
import utilities.parser.MedicineValidator;
import utilities.parser.StockManager;
import utilities.parser.StockValidator;
import utilities.storage.Storage;
import utilities.ui.Ui;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.logging.Level;
import java.util.logging.Logger;

//@@author a-tph

/**
 * Update medication information based on user input given stock id.
 */

public class UpdateStockCommand extends Command {
    private static Logger logger = Logger.getLogger("UpdateStock");

    public UpdateStockCommand(LinkedHashMap<String, String> parameters) {
        this.parameters = parameters;
    }

    @Override
    public void execute() {
        logger.log(Level.INFO, "Start of UpdateStock command execution.");

        Ui ui = Ui.getInstance();

        String[] requiredParameters = {CommandParameters.ID};
        String[] optionalParameters = {CommandParameters.PRICE, CommandParameters.QUANTITY,
                CommandParameters.EXPIRY_DATE, CommandParameters.DESCRIPTION, CommandParameters.NAME,
                CommandParameters.MAX_QUANTITY};

        MedicineValidator validator = new StockValidator();
        boolean isInvalidInput = validator.containsInvalidParametersAndValues(ui, parameters, requiredParameters,
                optionalParameters, CommandSyntax.UPDATE_STOCK_COMMAND, true, validator);
        if (isInvalidInput) {
            return;
        }

        ArrayList<Medicine> medicines = Medicine.getInstance();
        Stock stock = StockManager.extractStockObject(parameters, medicines);
        boolean isValidQuantityValues = processQuantityValues(ui, parameters, medicines, stock);
        if (!isValidQuantityValues) {
            return;
        }
        boolean isValidExpDate = processDateInput(ui, parameters, medicines, stock);
        if (!isValidExpDate) {
            return;
        }

        ArrayList<Stock> oldFilteredStocks = StockManager.getFilteredStocksByName(medicines, stock.getMedicineName());
        if (parameters.containsKey(CommandParameters.NAME)) {
            addNewRowForUpdates(oldFilteredStocks, medicines);
            stock = getNewStock(medicines, stock);
        }
        ArrayList<Stock> filteredStocks = StockManager.getFilteredStocksByName(medicines, stock.getMedicineName());

        // Default value for updating all affected rows
        boolean isAffectedCommand = checkAffectedCommand();
        if (!isAffectedCommand) {
            filteredStocks.clear();
            filteredStocks.add(stock);
        }

        int rowsAffected = filteredStocks.size();
        setUpdatesByStockId(filteredStocks, stock);
        ui.print("Updated! Number of rows affected: " + rowsAffected);
        printUpdatedStockId(ui, filteredStocks, oldFilteredStocks);

        ui.printStocks(filteredStocks, medicines);
        Storage storage = Storage.getInstance();
        storage.saveData(medicines);
        logger.log(Level.INFO, "End of UpdateStock command execution.");
    }

    /**
     * Prints the change of stock id for affected records.
     *
     * @param ui                Reference to the UI object passed by Main to print messages.
     * @param filteredStocks    Filtered stocks of the updated records.
     * @param oldFilteredStocks Filtered stocks of the old records.
     */
    private void printUpdatedStockId(Ui ui, ArrayList<Stock> filteredStocks, ArrayList<Stock> oldFilteredStocks) {
        if (parameters.containsKey(CommandParameters.NAME)) {
            ui.print("Stock Id changed from:");
            for (int i = 0; i < filteredStocks.size(); i++) {
                ui.print(oldFilteredStocks.get(i).getStockId() + " -> " + filteredStocks.get(i).getStockId());
            }
        }
    }

    /**
     * Checks if the command triggers multiple row updates.
     *
     * @return Boolean true if the command triggers multiple row updates.
     */
    private boolean checkAffectedCommand() {
        String[] affectedCommands = {CommandParameters.NAME, CommandParameters.DESCRIPTION,
                CommandParameters.MAX_QUANTITY};
        boolean isAffectedCommand = false;
        for (String affectedCommand : affectedCommands) {
            if (parameters.containsKey(affectedCommand)) {
                isAffectedCommand = true;
                break;
            }
        }
        return isAffectedCommand;
    }

    /**
     * Retrieves the updated stock object.
     *
     * @param medicines Arraylist of all medicines.
     * @param stock     Stock object of the given stock id.
     * @return The updated stock object.
     */
    private Stock getNewStock(ArrayList<Medicine> medicines, Stock stock) {
        Stock newStock = null;
        for (Medicine medicine : medicines) {
            if (!(medicine instanceof Stock)) {
                continue;
            }
            if (((Stock) medicine).isDeleted()) {
                continue;
            }
            boolean isSameName = medicine.getMedicineName().equalsIgnoreCase(stock.getMedicineName());
            boolean isSameExpDate = ((Stock) medicine).getExpiry().equals(stock.getExpiry());
            if (isSameName && isSameExpDate) {
                String newStockId = String.valueOf(((Stock) medicine).getStockId());
                parameters.put(CommandParameters.ID, newStockId);
                newStock = (Stock) medicine;
                break;
            }
        }
        return newStock;
    }

    /**
     * Add new rows when medicine name gets updated.
     *
     * @param filteredStocks Arraylist of filtered medicine stocks.
     * @param medicines      Arraylist of all medicines.
     */
    private void addNewRowForUpdates(ArrayList<Stock> filteredStocks,
                                     ArrayList<Medicine> medicines) {
        // Initialise new stock to get a new Stock Id
        for (Stock stock : filteredStocks) {
            String name = stock.getMedicineName();
            double price = stock.getPrice();
            int quantity = stock.getQuantity();
            Date expiryDate = stock.getExpiry();
            String description = stock.getDescription();
            int maxQuantity = stock.getMaxQuantity();
            Stock newStock = new Stock(name, price, quantity, expiryDate, description, maxQuantity);
            medicines.add(newStock);
        }

        for (Stock stock : filteredStocks) {
            for (Medicine medicine : medicines) {
                if (!(medicine instanceof Stock)) {
                    continue;
                }
                int stockId = ((Stock) medicine).getStockId();
                if (stockId == stock.getStockId()) {
                    ((Stock) medicine).setDeleted(true);
                    medicine.setQuantity(0);
                }
            }
        }
    }

    /**
     * Process valid date input to be updated given a stock id.
     *
     * @param ui         Reference to the UI object passed by Main to print messages.
     * @param parameters LinkedHashMap Key-Value set for parameter and user specified parameter value.
     * @param medicines  Arraylist of all medicines.
     * @param stock      Stock object of the given stock id.
     * @return Boolean value indicating if quantity values are valid.
     */
    private boolean processDateInput(Ui ui, LinkedHashMap<String, String> parameters, ArrayList<Medicine> medicines,
                                     Stock stock) {
        logger.log(Level.INFO, "Processing date input for update stock...");
        boolean hasExpiryDate = parameters.containsKey(CommandParameters.EXPIRY_DATE);
        if (!hasExpiryDate) {
            return true;
        }

        Date expiryDate = null;
        try {
            expiryDate = DateParser.stringToDate(parameters.get(CommandParameters.EXPIRY_DATE));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        StockValidator stockValidator = new StockValidator();
        String name = stock.getMedicineName();
        logger.log(Level.INFO, "End processing date input for update stock.");
        return stockValidator.dateValidityChecker(ui, medicines, expiryDate, name);
    }

    /**
     * Process quantity values to be updated given a stock id.
     *
     * @param ui         Reference to the UI object passed by Main to print messages.
     * @param parameters LinkedHashMap Key-Value set for parameter and user specified parameter value.
     * @param medicines  Arraylist of all medicines.
     * @param stock      Stock object of the given stock id.
     * @return Boolean value indicating if quantity values are valid.
     */
    private boolean processQuantityValues(Ui ui, LinkedHashMap<String, String> parameters,
                                          ArrayList<Medicine> medicines, Stock stock) {
        logger.log(Level.INFO, "Processing quantity values for update stock...");
        String name = stock.getMedicineName();
        int quantity = 0;
        int maxQuantity = 0;
        int totalStockQuantity = 0;
        int initialQuantity = 0;
        int updatedQuantity = 0;

        boolean hasQuantity = parameters.containsKey(CommandParameters.QUANTITY);
        boolean hasMaxQuantity = parameters.containsKey(CommandParameters.MAX_QUANTITY);

        // initialise quantity and max quantity based on the different combinations of user inputs
        if (hasQuantity && hasMaxQuantity) {
            totalStockQuantity = StockManager.getTotalStockQuantity(medicines, name);
            initialQuantity = stock.getQuantity();
            updatedQuantity = Integer.parseInt(parameters.get(CommandParameters.QUANTITY));
            quantity = totalStockQuantity - initialQuantity + updatedQuantity;
            maxQuantity = Integer.parseInt(parameters.get(CommandParameters.MAX_QUANTITY));
        }

        if (hasQuantity && !hasMaxQuantity) {
            totalStockQuantity = StockManager.getTotalStockQuantity(medicines, name);
            initialQuantity = stock.getQuantity();
            updatedQuantity = Integer.parseInt(parameters.get(CommandParameters.QUANTITY));
            quantity = totalStockQuantity - initialQuantity + updatedQuantity;
            maxQuantity = StockManager.getMaxStockQuantity(medicines, name);
        }

        if (!hasQuantity && hasMaxQuantity) {
            quantity = StockManager.getTotalStockQuantity(medicines, name);
            maxQuantity = Integer.parseInt(parameters.get(CommandParameters.MAX_QUANTITY));
        }
        StockValidator stockValidator = new StockValidator();
        logger.log(Level.INFO, "End processing quantity values for update stock.");
        return stockValidator.quantityValidityChecker(ui, quantity, maxQuantity);
    }

    /**
     * Update values provided by user for a given stock id.
     *
     * @param filteredStocks Arraylist of filtered medicine stocks.
     * @param stock          Stock object of the given stock id.
     */
    private void setUpdatesByStockId(ArrayList<Stock> filteredStocks,
                                     Stock stock) {
        logger.log(Level.INFO, "Attempt to update stock information.");
        for (String parameter : parameters.keySet()) {
            String parameterValue = parameters.get(parameter);
            switch (parameter) {
            case CommandParameters.NAME:
                for (Stock targetStock : filteredStocks) {
                    targetStock.setMedicineName(parameterValue);
                }
                break;
            case CommandParameters.PRICE:
                stock.setPrice(Double.parseDouble(parameterValue));
                break;
            case CommandParameters.QUANTITY:
                stock.setQuantity(Integer.parseInt(parameterValue));
                break;
            case CommandParameters.EXPIRY_DATE:
                try {
                    stock.setExpiry(DateParser.stringToDate(parameterValue));
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                break;
            case CommandParameters.DESCRIPTION:
                for (Stock targetStock : filteredStocks) {
                    targetStock.setDescription(parameterValue);
                }
                break;
            case CommandParameters.MAX_QUANTITY:
                for (Stock targetStock : filteredStocks) {
                    targetStock.setMaxQuantity(Integer.parseInt(parameterValue));
                }
                break;
            default:
                break;
            }
        }
        logger.log(Level.INFO, "Updated stock information with given user input");
    }
}
