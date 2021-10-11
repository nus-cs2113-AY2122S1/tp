package parser;

import ui.Ui;

/**
 * Contains all the methods to validate if an Order's input parameters are valid.
 */

public class OrderValidator {
    /**
     * Checks if a customer NRIC is valid.
     * @param ui                Reference to the UI object passed by Main to print messages.
     * @param customerNric      Customer NRIC to be checked.
     * @return Boolean value indicating if Customer NRIC is valid.
     */
    public static boolean isValidCustomerNric(Ui ui, String customerNric) {
        if (customerNric.equals("")) {
            ui.print("Customer NRIC cannot be empty!");
            return false;
        }
        return true;
    }

    /**
     * Checks if a customer NRIC is valid.
     * @param ui                Reference to the UI object passed by Main to print messages.
     * @param staffName         Staff Name to be checked.
     * @return Boolean value indicating if Staff Name is valid.
     */
    public static boolean isValidStaffName(Ui ui, String staffName) {
        if (staffName.equals("")) {
            ui.print("Staff Name cannot be empty!");
            return false;
        }
        return true;
    }
}
