package command.medicine;

import command.Command;
import command.CommandParameters;
import command.CommandSyntax;
import inventory.Medicine;
import inventory.Stock;
import parser.DateParser;
import parser.MedicineValidator;
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
    public void execute(Ui ui, HashMap<String, String> parameters, ArrayList<Stock> stocks) {
        String[] stockIdParameter = {CommandParameters.STOCK_ID};
        boolean isPresentStockId = CommandSyntax.checkRequiredParameters(parameters, stockIdParameter);

        if (!isPresentStockId) {
            return;
        }

        // Checks validity of compulsory parameter
        boolean isValidID = MedicineValidator.isValidStockId(ui, parameters.get(CommandParameters.STOCK_ID), stocks);
        if (!isValidID) {
            return;
        }

        // Checks empty optional parameter
        if (parameters.size() == PARAM_COUNT_ONLY_ID) {
            ui.print("Please provide at least one optional parameter!\n"
                    + "COMMAND SYNTAX: " + CommandSyntax.UPDATE_COMMAND);
            return;
        }

        // Checks validity of optional parameters
        boolean containValidParameters = validOptionalParameterChecker(ui, parameters);
        if (!containValidParameters) {
            return;
        }

        if (isPresentStockId) {
            processUpdatesByStockID(ui, parameters, stocks);
        }
    }

    /**
     * Checks if optional parameters are valid.
     *
     * @param ui         Reference to the UI object passed by Main to print messages.
     * @param parameters HashMap Key-Value set for parameter and user specified parameter value.
     * @return A boolean value indicating whether parameters are valid.
     */
    private boolean validOptionalParameterChecker(Ui ui, HashMap<String, String> parameters) {
        boolean isValid;
        for (String parameter : parameters.keySet()) {
            String parameterValue = parameters.get(parameter);
            switch (parameter) {
            case CommandParameters.PRICE:
                isValid = MedicineValidator.isValidPrice(ui, parameterValue);
                break;
            case CommandParameters.QUANTITY:
                isValid = MedicineValidator.isValidQuantity(ui, parameterValue);
                break;
            case CommandParameters.EXPIRY_DATE:
                isValid = MedicineValidator.isValidExpiry(ui, parameterValue);
                break;
            case CommandParameters.DESCRIPTION:
                isValid = MedicineValidator.isValidDescription(ui, parameterValue);
                break;
            case CommandParameters.UPDATED_MEDICINE_NAME:
                isValid = MedicineValidator.isValidName(ui, parameterValue);
                break;
            case CommandParameters.MAX_QUANTITY:
                isValid = MedicineValidator.isValidMaxQuantity(ui, parameterValue);
                break;
            case CommandParameters.STOCK_ID:
                isValid = true;
                break;
            default:
                ui.printInvalidParameter(parameter, CommandSyntax.UPDATE_COMMAND);
                isValid = false;
            }
            if (!isValid) {
                return false;
            }
        }
        return true;
    }

    /**
     * Process values to be updated given a stock id.
     *
     * @param ui         Reference to the UI object passed by Main to print messages.
     * @param parameters HashMap Key-Value set for parameter and user specified parameter value.
     * @param stocks     Arraylist of all stocks.
     */
    private void processUpdatesByStockID(Ui ui, HashMap<String, String> parameters, ArrayList<Stock> stocks) {
        int stockID = Integer.parseInt(parameters.get(CommandParameters.STOCK_ID));

        Medicine medicine = null;
        for (Stock stock : stocks) {
            if (((Medicine) stock).getStockID() == stockID) {
                medicine = (Medicine) stock;
                break;
            }
        }

        int currentQuantity = medicine.getQuantity();
        int currentMaxQuantity = medicine.getMaxQuantity();

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

        setUpdatesByStockID(parameters, medicine);
        ArrayList<Stock> medicines = new ArrayList<>();
        medicines.add(medicine);
        ui.print("Updated");
        ui.printMedicines(medicines);
    }

    /**
     * Update values provided by user for a given stock id.
     *
     * @param parameters HashMap Key-Value set for parameter and user specified parameter value.
     * @param medicine   Medicine object of the given stock id.
     */
    private void setUpdatesByStockID(HashMap<String, String> parameters, Medicine medicine) {
        for (String parameter : parameters.keySet()) {
            String parameterValue = parameters.get(parameter);
            switch (parameter) {
            case CommandParameters.UPDATED_MEDICINE_NAME:
                medicine.setMedicineName(parameterValue);
                break;
            case CommandParameters.PRICE:
                medicine.setPrice(Double.parseDouble(parameterValue));
                break;
            case CommandParameters.QUANTITY:
                medicine.setQuantity(Integer.parseInt(parameterValue));
                break;
            case CommandParameters.EXPIRY_DATE:
                try {
                    medicine.setExpiry(DateParser.stringToDate(parameterValue));
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                break;
            case CommandParameters.DESCRIPTION:
                medicine.setDescription(parameterValue);
                break;
            case CommandParameters.MAX_QUANTITY:
                medicine.setMaxQuantity(Integer.parseInt(parameterValue));
                break;
            default:
                break;
            }
        }
    }
}
