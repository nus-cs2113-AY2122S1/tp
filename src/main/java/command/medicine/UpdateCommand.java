package command.medicine;

import command.Command;
import command.CommandParameters;
import command.CommandSyntax;
import inventory.Stock;
import inventory.Medicine;
import parser.DateParser;
import parser.StockValidator;
import ui.Ui;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * Update medication information based on user input given stock id.
 */

public class UpdateCommand extends Command {
    private static final int PARAM_COUNT_ONLY_ID = 1;

    @Override
    public void execute(Ui ui, HashMap<String, String> parameters, ArrayList<Medicine> medicines) {
        String[] stockIdParameter = {CommandParameters.STOCK_ID};
        boolean isPresentStockId = !CommandSyntax.containsInvalidParameters(ui, parameters, stockIdParameter,
                CommandSyntax.UPDATE_COMMAND);

        if (!isPresentStockId) {
            return;
        }

        // Checks validity of compulsory parameter
        boolean isValidID = StockValidator.isValidStockId(ui, parameters.get(CommandParameters.STOCK_ID), medicines);
        if (!isValidID) {
            return;
        }

        // Checks empty optional parameter
        if (parameters.size() == PARAM_COUNT_ONLY_ID) {
            ui.print("Please provide at least one optional parameter!\n"
                    + "COMMAND SYNTAX: " + CommandSyntax.UPDATE_COMMAND);
            return;
        }

        String[] optionalParameters = {CommandParameters.PRICE, CommandParameters.QUANTITY,
            CommandParameters.EXPIRY_DATE, CommandParameters.DESCRIPTION, CommandParameters.UPDATED_MEDICINE_NAME,
            CommandParameters.MAX_QUANTITY};
        // Checks validity of optional parameters
        boolean containValidParameters = CommandSyntax.validOptionalParameterChecker(ui, parameters, medicines,
                optionalParameters);
        if (!containValidParameters) {
            return;
        }

        processUpdatesByStockID(ui, parameters, medicines);
    }

    /**
     * Process values to be updated given a stock id.
     *
     * @param ui         Reference to the UI object passed by Main to print messages.
     * @param parameters HashMap Key-Value set for parameter and user specified parameter value.
     * @param medicines  Arraylist of all medicines.
     */
    private void processUpdatesByStockID(Ui ui, HashMap<String, String> parameters, ArrayList<Medicine> medicines) {
        int stockID = Integer.parseInt(parameters.get(CommandParameters.STOCK_ID));

        Stock stock = null;
        for (Medicine medicine : medicines) {
            if (((Stock) medicine).getStockID() == stockID) {
                stock = (Stock) medicine;
                break;
            }
        }

        int currentQuantity = stock.getQuantity();
        int currentMaxQuantity = stock.getMaxQuantity();

        boolean hasQuantity = parameters.containsKey(CommandParameters.QUANTITY);
        boolean hasMaxQuantity = parameters.containsKey(CommandParameters.MAX_QUANTITY);

        if (hasQuantity && !hasMaxQuantity) {
            int quantity = Integer.parseInt(parameters.get(CommandParameters.QUANTITY));
            if (quantity > currentMaxQuantity) {
                ui.print("Update aborted! New quantity cannot be more than the current maximum quantity!");
                return;
            }
        }

        if (!hasQuantity && hasMaxQuantity) {
            int maxQuantity = Integer.parseInt(parameters.get(CommandParameters.MAX_QUANTITY));
            if (currentQuantity > maxQuantity) {
                ui.print("Update aborted! New maximum quantity cannot be less than the current quantity!");
                return;
            }
        }

        if (hasQuantity && hasMaxQuantity) {
            int quantity = Integer.parseInt(parameters.get(CommandParameters.QUANTITY));
            int maxQuantity = Integer.parseInt(parameters.get(CommandParameters.MAX_QUANTITY));
            if (quantity > maxQuantity) {
                ui.print("Update aborted! New quantity cannot be more than the new maximum quantity!");
                return;
            }
        }

        setUpdatesByStockID(parameters, stock);
        ui.print("Updated");
        ui.printMedicine(stock);
    }

    /**
     * Update values provided by user for a given stock id.
     *
     * @param parameters HashMap Key-Value set for parameter and user specified parameter value.
     * @param stock      Stock object of the given stock id.
     */
    private void setUpdatesByStockID(HashMap<String, String> parameters, Stock stock) {
        for (String parameter : parameters.keySet()) {
            String parameterValue = parameters.get(parameter);
            switch (parameter) {
            case CommandParameters.UPDATED_MEDICINE_NAME:
                stock.setMedicineName(parameterValue);
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
                stock.setDescription(parameterValue);
                break;
            case CommandParameters.MAX_QUANTITY:
                stock.setMaxQuantity(Integer.parseInt(parameterValue));
                break;
            default:
                break;
            }
        }
    }
}
