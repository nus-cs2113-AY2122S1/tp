package parser;

import command.CommandParameters;
import inventory.Medicine;
import inventory.Order;

import java.util.ArrayList;
import java.util.LinkedHashMap;

/**
 * Manages medicines that are order objects.
 */
public class OrderManager {

    /**
     * Extracts the order object for a given order id.
     *
     * @param parameters LinkedHashMap Key-Value set for parameter and user specified parameter value.
     * @param medicines  Arraylist of all medicines.
     * @return Stock object of the provided order id by user
     */
    public static Order extractOrderObject(LinkedHashMap<String, String> parameters, ArrayList<Medicine> medicines) {
        int orderId = Integer.parseInt(parameters.get(CommandParameters.ID));
        Order order = null;
        for (Medicine medicine : medicines) {
            if (medicine instanceof Order && orderId == ((Order) medicine).getOrderId()) {
                order = (Order) medicine;
            }
        }
        assert (order != null) : "Expected an order object but none extracted";
        return order;
    }

    /**
     * Retrieves the total order quantity for order with same name.
     *
     * @param medicines Arraylist of medicines.
     * @param name      Medicine name.
     * @return Total order quantity for the medicine.
     */
    public static int getTotalOrderQuantity(ArrayList<Medicine> medicines, String name) {
        int existingQuantity = 0;
        for (Medicine medicine : medicines) {
            if (!(medicine instanceof Order) || ((Order) medicine).isDelivered()) {
                continue;
            }
            boolean isSameMedicineName = medicine.getMedicineName().equalsIgnoreCase(name);
            if (isSameMedicineName) {
                existingQuantity += medicine.getQuantity();
            }
        }
        return existingQuantity;
    }
}
