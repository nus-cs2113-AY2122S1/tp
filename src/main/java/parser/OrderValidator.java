package parser;

import inventory.Medicine;
import inventory.Order;
import ui.Ui;

import java.util.ArrayList;

/**
 * Contains all the methods to validate if an Order's input parameters are valid.
 */
public class OrderValidator {

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
