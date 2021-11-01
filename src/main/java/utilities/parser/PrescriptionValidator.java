package utilities.parser;

import command.CommandParameters;
import inventory.Prescription;
import inventory.Medicine;
import utilities.ui.Ui;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedHashMap;

//@@author deonchung

/**
 * Contains all the methods to validate if a Prescription input parameters are valid.
 */
public class PrescriptionValidator extends MedicineValidator {
    public PrescriptionValidator() {
    }

    /**
     * Checks if parameter values are valid for Prescription objects.
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
                isValid = isValidPrescriptionId(ui, parameterValue, medicines);
                break;
            case CommandParameters.NAME:
                isValid = isValidName(ui, parameterValue);
                break;
            case CommandParameters.QUANTITY:
                isValid = isValidQuantity(ui, parameterValue);
                break;
            case CommandParameters.CUSTOMER_ID:
                isValid = isValidCustomerId(ui, parameterValue);
                break;
            case CommandParameters.DATE:
                isValid = isValidDate(ui, parameterValue);
                break;
            case CommandParameters.STAFF:
                isValid = isValidStaffName(ui, parameterValue);
                break;
            case CommandParameters.STOCK_ID:
                StockValidator stockValidator = new StockValidator();
                isValid = stockValidator.isValidStockId(ui, parameterValue, medicines);
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
     * Checks if a prescription ID is valid.
     *
     * @param ui        Reference to the UI object to print messages.
     * @param id        ID of the prescription record to be checked.
     * @param medicines List of all medicines.
     * @return Boolean value indicating if Prescription ID is valid.
     */
    public boolean isValidPrescriptionId(Ui ui, String id, ArrayList<Medicine> medicines) {
        try {
            int prescriptionId = Integer.parseInt(id);
            if (prescriptionId <= 0 || prescriptionId > Prescription.getPrescriptionCount()) {
                throw new Exception();
            }
            boolean prescriptionExist = false;
            for (Medicine medicine : medicines) {
                if (!(medicine instanceof Prescription)) {
                    continue;
                }
                Prescription prescription = (Prescription) medicine;
                if (prescription.getPrescriptionId() == prescriptionId) {
                    prescriptionExist = true;
                    break;
                }
            }
            if (!prescriptionExist) {
                ui.print("Invalid prescription id provided!");
                return false;
            }
            return true;
        } catch (Exception e) {
            ui.print("Invalid prescription id provided!");
        }
        return false;
    }

    /**
     * Checks if a customer ID is valid.
     *
     * @param ui         Reference to the UI object to print messages.
     * @param customerId Customer ID to be checked.
     * @return Boolean value indicating if Customer ID is valid.
     */
    public boolean isValidCustomerId(Ui ui, String customerId) {
        if (customerId.equals("")) {
            ui.print("Customer ID cannot be empty!");
            return false;
        }
        return true;
    }

    /**
     * Checks if a Staff Name is valid.
     *
     * @param ui        Reference to the UI object to print messages.
     * @param staffName Staff Name to be checked.
     * @return Boolean value indicating if Staff Name is valid.
     */
    public boolean isValidStaffName(Ui ui, String staffName) {
        if (staffName.equals("")) {
            ui.print("Staff name cannot be empty!");
            return false;
        }
        return true;
    }

    /**
     * Checks if a prescription column/alias exists.
     *
     * @param ui         Reference to the UI object to print messages.
     * @param columnName Column name/alias to be validated.
     * @return Boolean value indicating column is valid.
     */
    public boolean isValidColumn(Ui ui, String columnName) {
        String[] columnAlias = new String[]{CommandParameters.ID, CommandParameters.NAME, CommandParameters.QUANTITY,
                CommandParameters.CUSTOMER_ID, CommandParameters.DATE, CommandParameters.STAFF,
                CommandParameters.STOCK_ID};
        if (Arrays.asList(Prescription.COLUMNS).contains(columnName.toUpperCase()) || Arrays.asList(columnAlias)
                .contains(columnName.toLowerCase())) {
            return true;
        }
        ui.print("Invalid column name/alias! Column names can only be " + Arrays.toString(Prescription.COLUMNS) + " and"
                + " the respective aliases are " + Arrays.toString(columnAlias) + ".");
        return false;
    }

    /**
     * Checks if a prescription date is valid.
     *
     * @param ui         Reference to the UI object to print messages.
     * @param dateString Date of the prescription.
     * @return Boolean value indicating if the date is valid.
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

}
