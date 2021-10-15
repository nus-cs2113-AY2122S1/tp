package parser;

import command.CommandParameters;
import inventory.Dispense;
import inventory.Medicine;
import ui.Ui;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

/**
 * Contains all the methods to validate if a Dispense input parameters are valid.
 */
public class DispenseValidator {

    /**
     * Checks if parameter values are valid for Dispense objects.
     *
     * @param ui            Reference to the UI object passed by Main to print messages.
     * @param parameters    HashMap Key-Value set for parameter and user specified parameter value.
     * @param medicines     Arraylist of all medicines.
     * @param commandSyntax The command's valid syntax.
     * @return A boolean value indicating whether parameter values are valid.
     */
    public static boolean containsInvalidParameterValues(Ui ui, HashMap<String, String> parameters,
                                                         ArrayList<Medicine> medicines, String commandSyntax) {
        for (String parameter : parameters.keySet()) {
            boolean isValid = false;
            String parameterValue = parameters.get(parameter);

            switch (parameter) {
            case CommandParameters.ID:
                isValid = isValidDispenseId(ui, parameterValue);
                break;
            case CommandParameters.NAME:
                isValid = MedicineValidator.isValidName(ui, parameterValue);
                break;
            case CommandParameters.QUANTITY:
                isValid = MedicineValidator.isValidQuantity(ui, parameterValue);
                break;
            case CommandParameters.CUSTOMER_ID:
                isValid = isValidCustomerId(ui, parameterValue);
                break;
            case CommandParameters.DATE:
                isValid = isValidDate(ui, parameterValue);
                break;
            case CommandParameters.STAFF:
                isValid = isValidStaffName(ui, parameterValue);
                break;
            case CommandParameters.STOCK_ID:
                isValid = StockValidator.isValidStockId(ui, parameterValue, medicines);
                break;
            case CommandParameters.SORT:
            case CommandParameters.REVERSED_SORT:
                isValid = DispenseValidator.isValidColumn(ui, parameterValue);
                break;
            default:
                ui.printInvalidParameter(parameter, commandSyntax);
                break;
            }
            if (!isValid) {
                return true;
            }
        }
        return false;
    }

    /**
     * Checks if a dispense ID is valid.
     *
     * @param ui               Reference to the UI object passed by Main to print messages.
     * @param stringDispenseId Stock ID to be checked.
     * @return Boolean value indicating if Dispense ID is valid.
     */
    public static boolean isValidDispenseId(Ui ui, String stringDispenseId) {
        try {
            int stockId = Integer.parseInt(stringDispenseId);
            if (stockId < 0) {
                throw new Exception();
            }
            return true;
        } catch (Exception e) {
            ui.print("Invalid dispense ID provided!");
        }
        return false;
    }

    /**
     * Checks if a customer ID is valid.
     *
     * @param ui         Reference to the UI object passed by Main to print messages.
     * @param customerId Customer ID to be checked.
     * @return Boolean value indicating if Customer ID is valid.
     */
    public static boolean isValidCustomerId(Ui ui, String customerId) {
        if (customerId.equals("")) {
            ui.print("Customer ID cannot be empty!");
            return false;
        }
        return true;
    }

    /**
     * Checks if a Staff Name is valid.
     *
     * @param ui        Reference to the UI object passed by Main to print messages.
     * @param staffName Staff Name to be checked.
     * @return Boolean value indicating if Staff Name is valid.
     */
    public static boolean isValidStaffName(Ui ui, String staffName) {
        if (staffName.equals("")) {
            ui.print("Staff Name cannot be empty!");
            return false;
        }
        return true;
    }

    /**
     * Checks if a dispense column/alias exists.
     *
     * @param ui         Reference to the UI object passed by Main to print messages.
     * @param columnName Column name/alias to be validated.
     * @return Boolean value indicating column is valid.
     */
    public static boolean isValidColumn(Ui ui, String columnName) {
        String[] columnAlias = new String[]{CommandParameters.ID, CommandParameters.NAME, CommandParameters.QUANTITY,
            CommandParameters.CUSTOMER_ID, CommandParameters.DATE, CommandParameters.STAFF,
            CommandParameters.STOCK_ID};
        if (Arrays.asList(Dispense.COLUMNS).contains(columnName.toUpperCase()) || Arrays.asList(columnAlias)
                .contains(columnName.toLowerCase())) {
            return true;
        }
        ui.print("Invalid column name/alias! Column names can only be " + Arrays.toString(Dispense.COLUMNS) + " and "
                + "the respective aliases are " + Arrays.toString(columnAlias) + ".");
        return false;
    }

    /**
     * Checks if a dispense date is valid.
     *
     * @param ui         Reference to the UI object passed by Main to print messages.
     * @param dateString Date of the dispense.
     * @return Boolean value indicating if the date is valid.
     */
    public static boolean isValidDate(Ui ui, String dateString) {
        try {
            DateParser.stringToDate(dateString);
            return true;
        } catch (Exception e) {
            ui.print("Invalid date! Ensure date is in " + DateParser.OUTPUT_DATE_FORMAT + ".");
        }
        return false;
    }
}
