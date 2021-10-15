package parser;

import command.CommandParameters;
import inventory.Medicine;
import inventory.Order;
import ui.Ui;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Contains all the methods to validate if an Order's input parameters are valid.
 */
public class OrderValidator {
    /**
     * Checks if parameter values are valid for Order objects.
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
            case CommandParameters.ORDER_ID:
                isValid = isValidOrderId(ui, parameterValue, medicines);
                break;
            case CommandParameters.NAME:
                isValid = MedicineValidator.isValidName(ui, parameterValue);
                break;
            case CommandParameters.QUANTITY:
                isValid = MedicineValidator.isValidQuantity(ui, parameterValue);
                break;
            case CommandParameters.SORT:
            case CommandParameters.REVERSED_SORT:
                //isValid = isValidColumn(ui, parameterValue);
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
     * @param ui        Reference to the UI object passed by Main to print messages.
     * @param oid       ID of the order to be checked.
     * @param medicines List of all medicines.
     * @return Boolean value indicating if order ID is valid.
     */
    public static boolean isValidOrderId(Ui ui, String oid, ArrayList<Medicine> medicines) {
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

}
