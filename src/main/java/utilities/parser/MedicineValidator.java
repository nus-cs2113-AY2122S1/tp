package utilities.parser;

import utilities.ui.Ui;

/**
 * Contains all the methods to validate if a Medicine's input parameters are valid.
 */
public class MedicineValidator {

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
}
