package utilities.parser;

import command.CommandParameters;
import inventory.Medicine;
import utilities.ui.Ui;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Contains all the methods to validate if a Medicine's input parameters are valid.
 */
public abstract class MedicineValidator {
    private static Logger logger = Logger.getLogger("MedicineValidator");

    public MedicineValidator() {
    }

    public abstract boolean containsInvalidParameterValues(Ui ui, LinkedHashMap<String, String> parameters,
                                                           ArrayList<Medicine> medicines, String commandSyntax,
                                                           ArrayList<String> invalidParameters);

    public abstract boolean isValidColumn(Ui ui, String columnName);

    /**
     * Helps to check if the parameters and values required are provided by the user.
     *
     * @param ui                         Reference to the UI object to print messages.
     * @param medicines                  Arraylist of all medicines.
     * @param parameters                 Parameters entered in by the user.
     * @param requiredParameters         Parameters required by the command.
     * @param optionalParameters         Parameters that are optional.
     * @param commandSyntax              The command's valid syntax.
     * @param requiresOptionalParameters Boolean value of whether command required optional parameters.
     * @param validator                  Validator object used for validation checks.
     * @return A boolean value indicating if the parameters and values required are entered by the user.
     */
    public boolean containsInvalidParametersAndValues(Ui ui, ArrayList<Medicine> medicines,
                                                      LinkedHashMap<String, String> parameters,
                                                      String[] requiredParameters, String[] optionalParameters,
                                                      String commandSyntax, boolean requiresOptionalParameters,
                                                      MedicineValidator validator) {
        boolean isInvalidParameter = validator.containsInvalidParameters(ui, parameters, requiredParameters,
                optionalParameters, commandSyntax, requiresOptionalParameters);
        if (isInvalidParameter) {
            logger.log(Level.WARNING, "Invalid parameters given by user");
            return true;
        }

        int requiredParametersLength = requiredParameters.length;
        int optionalParametersLength = optionalParameters.length;
        // Combine both parameter array to check if optional parameter is valid
        String[] mergedParameters = new String[requiredParametersLength + optionalParametersLength];
        System.arraycopy(requiredParameters, 0, mergedParameters, 0, requiredParametersLength);
        System.arraycopy(optionalParameters, 0, mergedParameters, requiredParametersLength, optionalParametersLength);

        ArrayList<String> invalidParameters = getInvalidParameters(parameters, mergedParameters);

        boolean isInvalidParameterValues = validator.containsInvalidParameterValues(ui, parameters,
                medicines, commandSyntax, invalidParameters);
        if (isInvalidParameterValues) {
            logger.log(Level.WARNING, "Invalid parameters values given by user");
            return true;
        }

        return false;
    }

    /**
     * Filter out the invalid parameters provided by user.
     *
     * @param parameters       Parameters entered in by the user.
     * @param mergedParameters Parameters that are both required and optional.
     * @return ArrayList of invalid parameters.
     */
    private ArrayList<String> getInvalidParameters(LinkedHashMap<String, String> parameters,
                                                   String[] mergedParameters) {
        ArrayList<String> invalidParameters = new ArrayList<>();
        for (String parameter : parameters.keySet()) {
            boolean found = false;
            for (String mergedParameter : mergedParameters) {
                if (parameter.equalsIgnoreCase(mergedParameter)) {
                    found = true;
                    break;
                }
            }
            if (found) {
                continue;
            }
            invalidParameters.add(parameter);
        }
        return invalidParameters;
    }


    /**
     * Helps to check if the parameters required are provided by the user.
     *
     * @param ui                         Reference to the UI object to print messages.
     * @param parameters                 Parameters entered in by the user.
     * @param requiredParameters         Parameters required by the command.
     * @param optionalParameters         Parameters that are optional.
     * @param commandSyntax              The command's valid syntax.
     * @param requiresOptionalParameters Boolean value of whether command required optional parameters.
     * @return A boolean value indicating if the parameters required are entered by the user.
     */
    public boolean containsInvalidParameters(Ui ui, LinkedHashMap<String, String> parameters,
                                             String[] requiredParameters, String[] optionalParameters,
                                             String commandSyntax, boolean requiresOptionalParameters) {

        // User did not provide parameters all the parameters
        /*if (parameters.keySet().size() < requiredParametersLength) {
            ui.printInvalidParameter("", commandSyntax);
            return true;
        }*/
        boolean providedRequiredParameter = true;
        ArrayList<String> missingParametersList = new ArrayList<>();
        for (String requiredParameter : requiredParameters) {
            if (!parameters.containsKey(requiredParameter)) { // Checks if required parameters are found
                missingParametersList.add(requiredParameter);
                providedRequiredParameter = false;
            }
        }

        if (!providedRequiredParameter) {
            ui.printRequiredParameters(missingParametersList, commandSyntax);
            return true;
        }

        int requiredParametersLength = requiredParameters.length;
        int optionalParametersLength = optionalParameters.length;

        // Optional parameters not provided considered valid
        if (optionalParameters == null || optionalParametersLength == 0) {
            return false;
        }

        int emptyOptionalFieldCount = parameters.size() - requiredParametersLength;
        if (emptyOptionalFieldCount <= 0 && requiresOptionalParameters) {
            ui.print("Please provide at least one optional field!");
            ui.printCommandSyntax(commandSyntax);
            return true;
        }

        // Combine both parameter array to check if optional parameter is valid
        String[] mergedParameters = new String[requiredParametersLength + optionalParametersLength];
        System.arraycopy(requiredParameters, 0, mergedParameters, 0, requiredParametersLength);
        System.arraycopy(optionalParameters, 0, mergedParameters, requiredParametersLength, optionalParametersLength);

        for (String parameter : parameters.keySet()) {
            boolean isValid = false;
            for (String mergedParameter : mergedParameters) {
                if (mergedParameter.equalsIgnoreCase(parameter)) {
                    isValid = true;
                    break;
                }
            }
            if (!isValid) {
                ui.print("Please enter a valid optional parameter!");
                ui.printCommandSyntax(commandSyntax);
                return true;
            }
        }

        return false;
    }

    /**
     * Checks if a medicine name is empty.
     *
     * @param ui   Reference to the UI object to print messages.
     * @param name Medicine name to be checked.
     * @return Boolean value indicating if medicine name is valid.
     */
    public boolean isValidName(Ui ui, String name) {
        if (name.equals("")) {
            ui.print("Medication name cannot be empty!");
            return false;
        }
        return true;
    }

    /**
     * Checks if a medicine quantity is valid.
     *
     * @param ui             Reference to the UI object to print messages.
     * @param quantityString Quantity of the medicine.
     * @return Boolean value indicating if medicine quantity is valid.
     */
    public boolean isValidQuantity(Ui ui, String quantityString) {
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

    /**
     * Checks if name parameter exists.
     *
     * @param parameters   Parameters entered in by the user.
     * @param medicineName Medicine name of the given object.
     * @return A boolean value to return whether a parameter exist.
     */
    public boolean hasNameParamChecker(LinkedHashMap<String, String> parameters, String medicineName) {
        boolean hasNameParam = parameters.containsKey(CommandParameters.NAME);
        if (hasNameParam) {
            String currentName = medicineName;
            String updatedName = parameters.get(CommandParameters.NAME);
            if (updatedName.equalsIgnoreCase(currentName)) {
                hasNameParam = false;
            }
        }
        return hasNameParam;
    }

    /**
     * Checks if given quantity and stored quantity are the same.
     *
     * @param parameters Parameters entered in by the user.
     * @param quantity   Current quantity in stocks.
     * @return A boolean value to return whether a parameter exist.
     */
    public boolean hasQuantityParamChecker(LinkedHashMap<String, String> parameters, int quantity) {
        boolean hasQuantityParam = parameters.containsKey(CommandParameters.QUANTITY);
        if (hasQuantityParam) {
            int currentQuantity = quantity;
            int updatedQuantity = Integer.parseInt(parameters.get(CommandParameters.QUANTITY));
            if (currentQuantity == updatedQuantity) {
                hasQuantityParam = false;
            }
        }
        return hasQuantityParam;
    }
}
