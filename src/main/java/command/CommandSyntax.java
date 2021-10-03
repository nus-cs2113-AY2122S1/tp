package command;

import java.util.Arrays;
import java.util.HashMap;

public class CommandSyntax {
    public static final String LIST_COMMAND = "LIST {I/STOCK_ID P/PRICE Q/QUANTITY E/EXPIRY_DATE "
            + "D/DESCRIPTION M/MAX_QUANTITY}";
    public static final String DELETE_COMMAND = "DELETE I/STOCK_ID";
    public static final String ADD_COMMAND = "ADD {N/NAME P/PRICE Q/QUANTITY E/EXPIRY_DATE "
            + "D/DESCRIPTION M/MAX_QUANTITY}";

    /**
     * Helps to check if the parameters required are provided by the user.
     *
     * @param parameters        Parameters entered in by the user.
     * @param commandParameters Parameters required by the command.
     * @return A boolean value indicating if the parameters required are enterd by the user.
     */
    public static boolean checkRequiredParameters(HashMap<String, String> parameters, String[] commandParameters) {
        boolean isValidInput = true;

        for (String userParameter : parameters.keySet()) {
            if (!Arrays.asList(commandParameters).contains(userParameter)) { // Checks if required parameters are found
                isValidInput = false;
                break;
            }
        }
        // When user did not provide parameters but it is required
        if (parameters.keySet().size() == 0 && commandParameters.length != 0) {
            isValidInput = false;
        }
        return isValidInput;
    }
}
