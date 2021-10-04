package parser;

import command.CommandParameters;
import inventory.Stock;
import inventory.Medicine;
import ui.Ui;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * Contains all the methods to validate if a Medicine's input parameters are valid.
 */

public class StockValidator {
    /**
     * Checks if the given stock id is valid.
     *
     * @param ui        Reference to the UI object passed by Main to print messages.
     * @param id        ID of the medicine to be checked.
     * @param medicines List of all medicines.
     * @return Boolean value indicating if medicine ID is valid.
     */
    public static boolean isValidStockId(Ui ui, String id, ArrayList<Medicine> medicines) {
        try {
            int stockId = Integer.parseInt(id);
            if (stockId <= 0 || stockId > Stock.getStockCount()) {
                throw new Exception();
            }
            boolean stockExist = false;
            for (Medicine medicine : medicines) {
                Stock stock = (Stock) medicine;
                if (stock.getStockID() == stockId) {
                    stockExist = true;
                    break;
                }
            }
            if (!stockExist) {
                ui.print("Invalid stock id provided!");
                return false;
            }
            return true;
        } catch (Exception e) {
            ui.print("Invalid stock id provided!");
        }
        return false;
    }

    /**
     * Checks if a medicine name is empty.
     *
     * @param ui   Reference to the UI object passed by Main to print messages.
     * @param name Medicine name to be checked.
     * @return Boolean value indicating if medicine name is valid.
     */
    public static boolean isValidName(Ui ui, String name) {
        if (name.equals("")) {
            ui.print("Medication name cannot be empty!");
            return false;
        }
        return true;
    }

    /**
     * Checks if a medicine price is valid.
     *
     * @param ui          Reference to the UI object passed by Main to print messages.
     * @param priceString Price of the medicine to be checked.
     * @return Boolean value indicating if medicine price is valid.
     */
    public static boolean isValidPrice(Ui ui, String priceString) {
        try {
            double price = Double.parseDouble(priceString);
            if (price < 0) {
                throw new Exception();
            }
            return true;
        } catch (Exception e) {
            ui.print("Invalid price provided!");
        }
        return false;
    }

    /**
     * Checks if a medicine quantity is valid.
     *
     * @param ui             Reference to the UI object passed by Main to print messages.
     * @param quantityString Quantity of the medicine.
     * @return Boolean value indicating if medicine quantity is valid.
     */
    public static boolean isValidQuantity(Ui ui, String quantityString) {
        try {
            int quantity = Integer.parseInt(quantityString);
            if (quantity < 0) {
                throw new Exception();
            }
            return true;
        } catch (Exception e) {
            ui.print("Invalid quantity provided!");
        }
        return false;
    }

    /**
     * Checks if a medicine expiry date is valid.
     *
     * @param ui           Reference to the UI object passed by Main to print messages.
     * @param expiryString Expiry date of the medicine.
     * @return Boolean value indicating if medicine expiry date is valid.
     */
    public static boolean isValidExpiry(Ui ui, String expiryString) {
        try {
            DateParser.stringToDate(expiryString);
            return true;
        } catch (Exception e) {
            ui.print("Invalid expiry date! Ensure date is in " + DateParser.OUTPUT_DATE_FORMAT + ".");
        }
        return false;
    }

    /**
     * Checks if a medicine description is empty.
     *
     * @param ui          Reference to the UI object passed by Main to print messages.
     * @param description Medicine description to be checked.
     * @return Boolean value indicating if medicine name is valid.
     */
    public static boolean isValidDescription(Ui ui, String description) {
        if (description.equals("")) {
            ui.print("Description cannot be empty!");
            return false;
        }
        return true;
    }

    /**
     * Checks if a medicine max quantity is valid.
     *
     * @param ui                Reference to the UI object passed by Main to print messages.
     * @param maxQuantityString Max quantity of the medicine.
     * @return Boolean value indicating if max medicine quantity is valid.
     */
    public static boolean isValidMaxQuantity(Ui ui, String maxQuantityString) {
        try {
            int maxQuantity = Integer.parseInt(maxQuantityString);
            if (maxQuantity < 0) {
                throw new Exception();
            }
            return true;
        } catch (Exception e) {
            ui.print("Invalid max quantity provided!");
        }
        return false;
    }

    /**
     * Checks if a medicine column/alias exists.
     *
     * @param ui         Reference to the UI object passed by Main to print messages.
     * @param columnName Column name/alias to be validated.
     * @return Boolean value indicating if max medicine quantity is valid.
     */
    public static boolean isValidColumn(Ui ui, String columnName) {
        String[] columnAlias = new String[]{CommandParameters.STOCK_ID, CommandParameters.NAME, CommandParameters.PRICE,
            CommandParameters.QUANTITY, CommandParameters.EXPIRY_DATE, CommandParameters.DESCRIPTION,
            CommandParameters.MAX_QUANTITY};
        if (Arrays.asList(Stock.COLUMNS).contains(columnName) || Arrays.asList(columnAlias).contains(columnName)) {
            return true;
        }
        ui.print("Invalid column name/alias! Column names can only be " + Arrays.toString(Stock.COLUMNS) + " and "
                + "the respective aliases are " + Arrays.toString(columnAlias) + ".");
        return false;
    }
}
