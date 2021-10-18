package command;

import ui.Ui;

import java.util.LinkedHashMap;

/**
 * Contains all the valid command syntax accepted by the application. Also contains methods to validate if the
 * parameter and its value is valid for a given command.
 */

public class CommandSyntax {
    private String commandName;
    private String commandSyntax;
    public static final String COMMAND = "COMMAND";
    public static final String COMMAND_SYNTAX = "COMMAND SYNTAX";
    public static final String[] COLUMNS = {COMMAND, COMMAND_SYNTAX};
    public static final int NO_OF_COLUMNS = 2;

    public static final String ADD_DISPENSE_COMMAND = "adddispense n/NAME q/QUANTITY c/CUSTOMER_ID s/STAFF_NAME";
    public static final String ADD_STOCK_COMMAND = "addstock n/NAME p/PRICE q/QUANTITY e/EXPIRY_DATE "
            + "d/DESCRIPTION m/MAX_QUANTITY";
    public static final String ADD_ORDER_COMMAND = "addorder n/NAME q/QUANTITY d/DATE";
    public static final String DELETE_STOCK_COMMAND = "deletestock [i/ID e/EXPIRY_DATE]";
    public static final String DELETE_ORDER_COMMAND = "deleteorder i/ID";
    public static final String EXIT_COMMAND = "exit";
    public static final String HELP_COMMAND = "help";
    public static final String LIST_DISPENSE_COMMAND = "listdispense {i/ID q/QUANTITY c/CUSTOMER_ID d/DATE "
            + "s/STAFF_NAME sid/STOCK_ID sort/COLUMN_NAME rsort/COLUMN NAME}";
    public static final String LIST_ORDER_COMMAND = "listorder {i/ID n/NAME q/QUANTITY d/DATE status/STATUS}";
    public static final String LIST_STOCK_COMMAND = "liststock {i/ID p/PRICE q/QUANTITY e/EXPIRY_DATE "
            + "d/DESCRIPTION m/MAX_QUANTITY sort/COLUMN_NAME rsort/COLUMN NAME}";
    public static final String PURGE_COMMAND = "purge";
    public static final String UPDATE_ORDER_COMMAND = "updateorder i/ID [n/NAME q/QUANTITY d/DATE]";
    public static final String UPDATE_STOCK_COMMAND = "updatestock i/ID [n/NAME p/PRICE q/QUANTITY e/EXPIRY_DATE "
            + "d/DESCRIPTION m/MAX_QUANTITY]";

    public CommandSyntax(String commandName, String commandSyntax) {
        this.commandName = commandName;
        this.commandSyntax = commandSyntax;
    }

    public String getCommandName() {
        return commandName;
    }

    public void setCommandName(String commandName) {
        this.commandName = commandName;
    }

    public String getCommandSyntax() {
        return commandSyntax;
    }

    public void setCommandSyntax(String commandSyntax) {
        this.commandSyntax = commandSyntax;
    }


    /**
     * Helps to check if the parameters required are provided by the user.
     *
     * @param ui                         Reference to the UI object passed by Main to print messages.
     * @param parameters                 Parameters entered in by the user.
     * @param requiredParameters         Parameters required by the command.
     * @param optionalParameters         Parameters that are optional.
     * @param commandSyntax              The command's valid syntax.
     * @param requiresOptionalParameters Boolean value of whether command required optional parameters.
     * @return A boolean value indicating if the parameters required are entered by the user.
     */
    public static boolean containsInvalidParameters(Ui ui, LinkedHashMap<String, String> parameters,
                                                    String[] requiredParameters, String[] optionalParameters,
                                                    String commandSyntax, boolean requiresOptionalParameters) {
        int requiredParametersLength = requiredParameters.length;
        int optionalParametersLength = optionalParameters.length;

        // User did not provide parameters all the parameters
        if (parameters.keySet().size() < requiredParametersLength) {
            ui.printInvalidParameter("", commandSyntax);
            return true;
        }

        for (String requiredParameter : requiredParameters) {
            if (!parameters.containsKey(requiredParameter)) { // Checks if required parameters are found
                ui.printRequiredParameter(requiredParameter, commandSyntax);
                return true;
            }
        }

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
}
