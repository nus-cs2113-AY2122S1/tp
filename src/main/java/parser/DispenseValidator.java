package parser;

import ui.Ui;

/**
 * Contains all the methods to validate if a Dispense input parameters are valid.
 */
public class DispenseValidator {
    /**
     * Checks if a customer ID is valid.
     *
     * @param ui                Reference to the UI object passed by Main to print messages.
     * @param customerId        Customer ID to be checked.
     * @return                  Boolean value indicating if Customer ID is valid.
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
     * @param ui                Reference to the UI object passed by Main to print messages.
     * @param staffName         Staff Name to be checked.
     * @return                  Boolean value indicating if Staff Name is valid.
     */
    public static boolean isValidStaffName(Ui ui, String staffName) {
        if (staffName.equals("")) {
            ui.print("Staff Name cannot be empty!");
            return false;
        }
        return true;
    }
}
