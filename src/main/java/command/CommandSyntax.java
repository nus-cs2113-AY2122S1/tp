package command;

import ui.Ui;

import java.util.HashMap;

public class CommandSyntax {
    public static final String LIST_COMMAND = "LIST {I/STOCK_ID P/PRICE Q/QUANTITY E/EXPIRY_DATE "
            + "D/DESCRIPTION M/MAX_QUANTITY}";
    public static final String DELETE_COMMAND = "DELETE I/STOCK_ID";
    public static final String ADD_COMMAND = "ADD N/NAME P/PRICE Q/QUANTITY E/EXPIRY_DATE "
            + "D/DESCRIPTION M/MAX_QUANTITY";
    public static final String UPDATE_COMMAND = "UPDATE I/STOCK_ID {U/UPDATED_NAME P/PRICE Q/QUANTITY E/EXPIRY_DATE "
            + "D/DESCRIPTION M/MAX_QUANTITY}\n";

    /**
     * Helps to check if the parameters required are provided by the user.
     *
     * @param parameters        Parameters entered in by the user.
     * @param commandParameters Parameters required by the command.
     * @return A boolean value indicating if the parameters required are enterd by the user.
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
}
