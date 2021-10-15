package command.medicine;

import command.Command;
import command.CommandParameters;
import command.CommandSyntax;
import inventory.Medicine;
import inventory.Stock;
import parser.DateParser;
import parser.MedicineManager;
import parser.StockValidator;
import ui.Ui;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Update medication information based on user input given stock id.
 */

public class UpdateStock extends Command {
    private static Logger logger = Logger.getLogger("UpdateStock");

    @Override
    public void execute(Ui ui, LinkedHashMap<String, String> parameters, ArrayList<Medicine> medicines) {
        String[] requiredParameter = {CommandParameters.ID};
        String[] optionalParameters = {CommandParameters.PRICE, CommandParameters.QUANTITY,
            CommandParameters.EXPIRY_DATE, CommandParameters.DESCRIPTION, CommandParameters.NAME,
            CommandParameters.MAX_QUANTITY};

        boolean isInvalidParameter = CommandSyntax.containsInvalidParameters(ui, parameters, requiredParameter,
                optionalParameters, CommandSyntax.UPDATE_STOCK_COMMAND, true);
        if (isInvalidParameter) {
            return;
        }

        boolean isInvalidParameterValues = StockValidator.containsInvalidParameterValues(ui, parameters, medicines,
            CommandSyntax.UPDATE_STOCK_COMMAND);
        if (isInvalidParameterValues) {
            return;
        }

        Stock stock = MedicineManager.extractStockObject(parameters, medicines);
        boolean isValidQuantityValues = processQuantityValues(ui, parameters, medicines, stock);
        if (!isValidQuantityValues) {
            return;
        }

        boolean isValidExpDate = processDateInput(ui, parameters, medicines, stock);
        if (!isValidExpDate) {
            return;
        }

        ArrayList<Stock> filteredStocks = new ArrayList<>();
        for (Medicine medicine : medicines) {
            if (medicine instanceof Stock && medicine.getMedicineName().equalsIgnoreCase(stock.getMedicineName())) {
                filteredStocks.add((Stock) medicine);
            }
        }

        // Default value for updating all affected rows
        int rowsAffected = filteredStocks.size();
        String[] affectedCommands = {CommandParameters.NAME, CommandParameters.DESCRIPTION,
            CommandParameters.MAX_QUANTITY};
        boolean isAffectedCommand = false;
        for (String affectedCommand : affectedCommands) {
            if (parameters.containsKey(affectedCommand)) {
                isAffectedCommand = true;
                break;
            }
        }

        if (!isAffectedCommand) {
            filteredStocks.clear();
            filteredStocks.add(stock);
            rowsAffected = filteredStocks.size();
        }

        setUpdatesByStockID(parameters, filteredStocks, stock);
        ui.print("Updated! Number of rows affected: " + rowsAffected);
        ui.printStocks(filteredStocks, medicines);

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
        String name = stock.getMedicineName();

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

        return StockValidator.dateValidityChecker(ui, medicines, expiryDate, name);
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
            totalStockQuantity = MedicineManager.getTotalStockQuantity(medicines, name);
            initialQuantity = stock.getQuantity();
            updatedQuantity = Integer.parseInt(parameters.get(CommandParameters.QUANTITY));
            quantity = totalStockQuantity - initialQuantity + updatedQuantity;
            maxQuantity = Integer.parseInt(parameters.get(CommandParameters.MAX_QUANTITY));
        }

        if (hasQuantity && !hasMaxQuantity) {
            totalStockQuantity = MedicineManager.getTotalStockQuantity(medicines, name);
            initialQuantity = stock.getQuantity();
            updatedQuantity = Integer.parseInt(parameters.get(CommandParameters.QUANTITY));
            quantity = totalStockQuantity - initialQuantity + updatedQuantity;
            maxQuantity = MedicineManager.getMaxStockQuantity(medicines, name);
        }

        if (!hasQuantity && hasMaxQuantity) {
            quantity = MedicineManager.getTotalStockQuantity(medicines, name);
            maxQuantity = Integer.parseInt(parameters.get(CommandParameters.MAX_QUANTITY));
        }

        return StockValidator.quantityValidityChecker(ui, quantity, maxQuantity);
    }

    /**
     * Update values provided by user for a given stock id.
     *
     * @param parameters     LinkedHashMap Key-Value set for parameter and user specified parameter value.
     * @param filteredStocks Arraylist of filtered medicine stocks.
     * @param stock          Stock object of the given stock id.
     */
    private void setUpdatesByStockID(LinkedHashMap<String, String> parameters, ArrayList<Stock> filteredStocks,
                                     Stock stock) {
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
