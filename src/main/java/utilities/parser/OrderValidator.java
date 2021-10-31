package utilities.parser;

import command.CommandParameters;
import inventory.Medicine;
import inventory.Order;
import utilities.ui.Ui;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedHashMap;

//@@author deonchung
/**
 * Contains all the methods to validate if an Order's input parameters are valid.
 */
public class OrderValidator extends MedicineValidator {
    public OrderValidator() {
    }

    /**
     * Checks if parameter values are valid for Order objects.
     *
     * @param ui            Reference to the UI object to print messages.
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
                isValid = isValidOrderId(ui, parameterValue, medicines);
                break;
            case CommandParameters.NAME:
                isValid = isValidName(ui, parameterValue);
                break;
            case CommandParameters.QUANTITY:
                isValid = isValidQuantity(ui, parameterValue);
                break;
            case CommandParameters.DATE:
                isValid = isValidDate(ui, parameterValue);
                break;
            case CommandParameters.STATUS:
                isValid = isValidStatus(ui, parameterValue);
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
     * Checks if the given order id is valid.
     *
     * @param ui        Reference to the UI object to print messages.
     * @param oid       ID of the order to be checked.
     * @param medicines List of all medicines.
     * @return Boolean value indicating if order ID is valid.
     */
    public boolean isValidOrderId(Ui ui, String oid, ArrayList<Medicine> medicines) {
        try {
            int orderId = Integer.parseInt(oid);
            if (orderId <= 0 || orderId > Order.getOrderCount()) {
                throw new Exception();
            }
            boolean orderExist = false;
            for (Medicine medicine : medicines) {
                if (!(medicine instanceof Order)) {
                    continue;
                }
                Order order = (Order) medicine;
                if (order.getOrderId() == orderId) {
                    orderExist = true;
                    break;
                }
            }
            if (!orderExist) {
                throw new Exception();
            }
            return true;
        } catch (Exception e) {
            ui.print("Invalid order id provided!");
        }
        return false;
    }

    /**
     * Checks if a medicine order date is valid.
     *
     * @param ui         Reference to the UI object to print messages.
     * @param dateString Date of the medicine.
     * @return Boolean value indicating if medicine expiry date is valid.
     */
    public boolean isValidDate(Ui ui, String dateString) {
        try {
            DateParser.stringToDate(dateString);
            return true;
        } catch (Exception e) {
            ui.print("Invalid date! Ensure date is in " + DateParser.OUTPUT_DATE_FORMAT + ".");
        }
        return false;
    }

    /**
     * Checks if a medicine order status is valid.
     *
     * @param ui           Reference to the UI object to print messages.
     * @param statusString Status of medicine order.
     * @return Boolean value indicating if medicine expiry date is valid.
     */
    public boolean isValidStatus(Ui ui, String statusString) {
        if (statusString.equalsIgnoreCase("PENDING")
                || statusString.equalsIgnoreCase("DELIVERED")) {
            return true;
        }
        ui.print("Invalid status! Ensure status is either PENDING or DELIVERED");
        return false;
    }

    /**
     * Checks if a order column/alias exists.
     *
     * @param ui         Reference to the UI object to print messages.
     * @param columnName Column name/alias to be validated.
     * @return Boolean value indicating if column name is value.
     */
    public boolean isValidColumn(Ui ui, String columnName) {
        String[] columnAlias = new String[]{CommandParameters.ID, CommandParameters.NAME, CommandParameters.QUANTITY,
                CommandParameters.DATE, CommandParameters.STATUS};
        if (Arrays.asList(Order.COLUMNS).contains(columnName.toUpperCase()) || Arrays.asList(columnAlias)
                .contains(columnName.toLowerCase())) {
            return true;
        }
        ui.print("Invalid column name/alias! Column names can only be " + Arrays.toString(Order.COLUMNS) + " and "
                + "the respective aliases are " + Arrays.toString(columnAlias) + ".");
        return false;
    }
}
