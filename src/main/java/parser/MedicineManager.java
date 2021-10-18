package parser;

import command.CommandParameters;
import inventory.Medicine;
import inventory.Order;
import inventory.Stock;

import java.util.ArrayList;
import java.util.HashMap;

public class MedicineManager {

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

    /**
     * Retrieves total number of rows for the medicine with same name.
     *
     * @param medicines Arraylist of medicines.
     * @param name Medicine name.
     * @return Total number of rows for the same medicine.
     */
    public static int getTotalRows(ArrayList<Medicine> medicines, String name) {
        int existingRowQuantity = 0;
        for (Medicine medicine : medicines) {
            if (medicine instanceof Stock) {
                boolean isSameMedicineName = medicine.getMedicineName().equalsIgnoreCase(name);
                if (isSameMedicineName) {
                    existingRowQuantity++;
                }
            }
        }
        return existingRowQuantity;
    }

}
