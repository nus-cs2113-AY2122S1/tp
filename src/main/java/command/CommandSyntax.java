package command;

import inventory.Stock;
import parser.MedicineValidator;
import ui.Ui;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

public class CommandSyntax {
    public static final String ADD_COMMAND = "ADD N/NAME P/PRICE Q/QUANTITY E/EXPIRY_DATE "
            + "D/DESCRIPTION M/MAX_QUANTITY";
    public static final String LIST_COMMAND = "LIST [I/STOCK_ID P/PRICE Q/QUANTITY E/EXPIRY_DATE "
            + "D/DESCRIPTION M/MAX_QUANTITY]";
    public static final String UPDATE_COMMAND = "UPDATE I/STOCK_ID [U/UPDATED_NAME P/PRICE Q/QUANTITY E/EXPIRY_DATE "
            + "D/DESCRIPTION M/MAX_QUANTITY]";
    public static final String DELETE_COMMAND = "DELETE I/STOCK_ID";
    public static final String HELP_COMMAND = "HELP";
    public static final String PURGE_COMMAND = "PURGE";
    public static final String EXIT_COMMAND = "EXIT";

    /**
     * Helps to check if the parameters required are provided by the user.
     *
     * @param ui                Reference to the UI object passed by Main to print messages.
     * @param parameters        Parameters entered in by the user.
     * @param commandParameters Parameters required by the command.
     * @param commandSyntax     The command's valid syntax,
     * @return A boolean value indicating if the parameters required are entered by the user.
     */
    public static boolean containsInvalidParameters(Ui ui, HashMap<String, String> parameters,
                                                    String[] commandParameters, String commandSyntax) {
        // User did not provide parameters all the parameters
        if (parameters.keySet().size() < commandParameters.length) {
            ui.printInvalidParameter("", commandSyntax);
            return true;
        }

        for (String requiredParameter : commandParameters) {
            if (!parameters.containsKey(requiredParameter)) { // Checks if required parameters are found
                ui.printRequiredParameter(requiredParameter, commandSyntax);
                return true;
            }
        }
        return false;
    }

    /**
     * Checks if optional parameters are valid.
     *
     * @param ui                 Reference to the UI object passed by Main to print messages.
     * @param parameters         HashMap Key-Value set for parameter and user specified parameter value.
     * @param stocks             Arraylist of all stocks.
     * @param optionalParameters Optional parameters by the command.
     * @return A boolean value indicating whether parameters are valid.
     */
    public static boolean validOptionalParameterChecker(Ui ui, HashMap<String, String> parameters,
                                                        ArrayList<Stock> stocks, String[] optionalParameters) {
        boolean isValid;
        for (String parameter : parameters.keySet()) {
            String parameterValue = parameters.get(parameter);
            boolean isOptional = Arrays.stream(optionalParameters)
                    .anyMatch(parameter::equals);
            if (!isOptional) {
                continue;
            }

            switch (parameter) {
            case CommandParameters.PRICE:
                isValid = MedicineValidator.isValidPrice(ui, parameterValue);
                break;
            case CommandParameters.QUANTITY:
                isValid = MedicineValidator.isValidQuantity(ui, parameterValue);
                break;
            case CommandParameters.EXPIRY_DATE:
                isValid = MedicineValidator.isValidExpiry(ui, parameterValue);
                break;
            case CommandParameters.DESCRIPTION:
                isValid = MedicineValidator.isValidDescription(ui, parameterValue);
                break;
            case CommandParameters.UPDATED_MEDICINE_NAME:
                isValid = MedicineValidator.isValidName(ui, parameterValue);
                break;
            case CommandParameters.MAX_QUANTITY:
                isValid = MedicineValidator.isValidMaxQuantity(ui, parameterValue);
                break;
            case CommandParameters.STOCK_ID:
                isValid = MedicineValidator.isValidStockId(ui, parameterValue, stocks);
                break;
            default:
                ui.printInvalidParameter(parameter, CommandSyntax.UPDATE_COMMAND);
                isValid = false;
            }
            if (!isValid) {
                return false;
            }
        }
        return true;
    }
}
