package utilities.parser;

import command.CommandParameters;
import inventory.Medicine;
import inventory.Stock;
import utilities.ui.Ui;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.LinkedHashMap;

/**
 * Contains all the methods to validate if a Medicine's input parameters are valid.
 */

public class StockValidator extends MedicineValidator {
    /**
     * Checks if parameter values are valid for Stock objects.
     *
     * @param ui            Reference to the UI object passed by Main to print messages.
     * @param parameters    LinkedHashMap Key-Value set for parameter and user specified parameter value.
     * @param medicines     Arraylist of all medicines.
     * @param commandSyntax The command's valid syntax.
     * @return A boolean value indicating whether parameter values are valid.
     */
    public boolean containsInvalidParameterValues(Ui ui, LinkedHashMap<String, String> parameters,
                                                  ArrayList<Medicine> medicines, String commandSyntax) {
        for (String parameter : parameters.keySet()) {
            boolean isValid = false;
            String parameterValue = parameters.get(parameter);

            switch (parameter) {
            case CommandParameters.ID:
                isValid = isValidStockId(ui, parameterValue, medicines);
                break;
            case CommandParameters.NAME:
                isValid = isValidName(ui, parameterValue);
                break;
            case CommandParameters.PRICE:
                isValid = isValidPrice(ui, parameterValue);
                break;
            case CommandParameters.LOW:
            case CommandParameters.QUANTITY:
                isValid = isValidQuantity(ui, parameterValue);
                break;
            case CommandParameters.EXPIRING:
                // Fallthrough
            case CommandParameters.EXPIRY_DATE:
                isValid = isValidExpiry(ui, parameterValue);
                break;
            case CommandParameters.DESCRIPTION:
                isValid = isValidDescription(ui, parameterValue);
                break;
            case CommandParameters.MAX_QUANTITY:
                isValid = isValidMaxQuantity(ui, parameterValue);
                break;
            case CommandParameters.SORT:
                // Fallthrough
            case CommandParameters.REVERSED_SORT:
                isValid = isValidColumn(ui, parameterValue);
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
     * Checks if the given stock id is valid.
     *
     * @param ui        Reference to the UI object to print messages.
     * @param id        ID of the medicine to be checked.
     * @param medicines List of all medicines.
     * @return Boolean value indicating if medicine ID is valid.
     */
    public boolean isValidStockId(Ui ui, String id, ArrayList<Medicine> medicines) {
        try {
            int stockId = Integer.parseInt(id);
            if (stockId <= 0 || stockId > Stock.getStockCount()) {
                throw new Exception();
            }
            boolean stockExist = false;
            for (Medicine medicine : medicines) {
                if (!(medicine instanceof Stock)) {
                    continue;
                }
                Stock stock = (Stock) medicine;
                if (stock.getStockId() == stockId && !stock.isDeleted()) {
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
     * Checks if a medicine price is valid.
     *
     * @param ui          Reference to the UI object to print messages.
     * @param priceString Price of the medicine to be checked.
     * @return Boolean value indicating if medicine price is valid.
     */
    public boolean isValidPrice(Ui ui, String priceString) {
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
     * Checks if a medicine expiry date is valid.
     *
     * @param ui           Reference to the UI object to print messages.
     * @param expiryString Expiry date of the medicine.
     * @return Boolean value indicating if medicine expiry date is valid.
     */
    public boolean isValidExpiry(Ui ui, String expiryString) {
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
     * @param ui          Reference to the UI object to print messages.
     * @param description Medicine description to be checked.
     * @return Boolean value indicating if medicine name is valid.
     */
    public boolean isValidDescription(Ui ui, String description) {
        if (description.equals("")) {
            ui.print("Description cannot be empty!");
            return false;
        }
        return true;
    }

    /**
     * Checks if a medicine max quantity is valid.
     *
     * @param ui                Reference to the UI object to print messages.
     * @param maxQuantityString Max quantity of the medicine.
     * @return Boolean value indicating if max medicine quantity is valid.
     */
    public boolean isValidMaxQuantity(Ui ui, String maxQuantityString) {
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
     * @param ui         Reference to the UI object to print messages.
     * @param columnName Column name/alias to be validated.
     * @return Boolean value indicating if column name is value.
     */
    public boolean isValidColumn(Ui ui, String columnName) {
        String[] columnAlias = new String[]{CommandParameters.ID, CommandParameters.NAME, CommandParameters.PRICE,
                CommandParameters.QUANTITY, CommandParameters.EXPIRY_DATE, CommandParameters.DESCRIPTION,
                CommandParameters.MAX_QUANTITY};
        if (Arrays.asList(Stock.COLUMNS).contains(columnName.toUpperCase()) || Arrays.asList(columnAlias)
                .contains(columnName.toLowerCase())) {
            return true;
        }
        ui.print("Invalid column name/alias! Column names can only be " + Arrays.toString(Stock.COLUMNS) + " and "
                + "the respective aliases are " + Arrays.toString(columnAlias) + ".");
        return false;
    }

    /**
     * Checks if total quantity of medicine is below max quantity.
     *
     * @param ui          Reference to the UI object to print messages.
     * @param quantity    Quantity of the medicines.
     * @param maxQuantity Max quantity of medicines.
     * @return Boolean value indicating if total quantity is less than max quantity.
     */
    public boolean quantityValidityChecker(Ui ui, int quantity, int maxQuantity) {
        if (quantity > maxQuantity) {
            String message = "Quantity: " + quantity + ", Max Quantity: " + maxQuantity;
            ui.print("Quantity cannot be more than maximum quantity!");
            ui.print(message);
            return false;
        }
        return true;
    }

    /**
     * Checks if input date for medicine already exists.
     *
     * @param ui         Reference to the UI object to print messages.
     * @param medicines  List of all medicines.
     * @param expiryDate Expiry date given by user
     * @param name       Medicine name to check against
     * @return Boolean false if same expiry date exist
     */
    public boolean dateValidityChecker(Ui ui, ArrayList<Medicine> medicines, Date expiryDate, String name) {
        ArrayList<Stock> filteredStocks = new ArrayList<>();
        for (Medicine medicine : medicines) {
            if (!(medicine instanceof Stock)) {
                continue;
            }
            Stock stock = (Stock) medicine;
            boolean isSameName = name.equalsIgnoreCase(stock.getMedicineName());
            boolean isDeleted = stock.isDeleted();
            if (isSameName && !isDeleted) {
                filteredStocks.add(stock);
            }
        }

        Date currentDate = new Date();
        String currentDateString = DateParser.dateToString(currentDate);

        try {
            currentDate = DateParser.stringToDate(currentDateString);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        if (expiryDate.before(currentDate)) {
            ui.print("Expired date detected! Action aborted.");
            return false;
        }

        for (Stock filteredStock : filteredStocks) {
            if (expiryDate.equals(filteredStock.getExpiry())) {
                ui.print("Same expiry date already exists! Action aborted.");
                return false;
            }
        }
        return true;
    }
}
