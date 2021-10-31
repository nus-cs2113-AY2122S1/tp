package utilities.parser;

import command.Command;
import command.ExitCommand;
import command.HelpCommand;
import command.PurgeCommand;
import command.order.ReceiveOrderCommand;
import command.prescription.AddPrescriptionCommand;
import command.prescription.ArchivePrescriptionCommand;
import command.prescription.DeletePrescriptionCommand;
import command.prescription.ListPrescriptionCommand;
import command.prescription.UpdatePrescriptionCommand;
import command.stock.AddStockCommand;
import command.stock.DeleteStockCommand;
import command.stock.ListStockCommand;
import command.stock.UpdateStockCommand;
import command.order.AddOrderCommand;
import command.order.ArchiveOrderCommand;
import command.order.DeleteOrderCommand;
import command.order.ListOrderCommand;
import command.order.UpdateOrderCommand;
import errors.InvalidCommandException;
import utilities.ui.Ui;

import java.util.LinkedHashMap;

import static command.CommandList.ADD;
import static command.CommandList.ADD_PRESCRIPTION;
import static command.CommandList.ADD_ORDER;
import static command.CommandList.ADD_STOCK;
import static command.CommandList.ARCHIVE;
import static command.CommandList.ARCHIVE_PRESCRIPTION;
import static command.CommandList.ARCHIVE_ORDER;
import static command.CommandList.DELETE;
import static command.CommandList.DELETE_PRESCRIPTION;
import static command.CommandList.DELETE_STOCK;
import static command.CommandList.DELETE_ORDER;
import static command.CommandList.EXIT;
import static command.CommandList.HELP;
import static command.CommandList.LIST;
import static command.CommandList.LIST_PRESCRIPTION;
import static command.CommandList.LIST_STOCK;
import static command.CommandList.LIST_ORDER;
import static command.CommandList.PURGE;
import static command.CommandList.RECEIVE;
import static command.CommandList.RECEIVE_ORDER;
import static command.CommandList.UPDATE;
import static command.CommandList.UPDATE_PRESCRIPTION;
import static command.CommandList.UPDATE_STOCK;
import static command.CommandList.UPDATE_ORDER;
import static utilities.parser.Mode.PRESCRIPTION;
import static utilities.parser.Mode.ORDER;
import static utilities.parser.Mode.STOCK;

//@@author alvintan01

/**
 * Helps to parse the commands given by the user as well as extract the parameters provided.
 */

public class CommandParser {
    public CommandParser() {
    }

    private static final String DELIMITER = "/";
    private static final String SPACE_DELIMITER = "\\s+";

    /**
     * Processes the user input into a Command Object.
     *
     * @param command          Input provided by user.
     * @param parametersString String parameter entered by user.
     * @param mode             The current mode of the program.
     * @return A Command object.
     * @throws InvalidCommandException If a command does not exist.
     */
    public Command processCommand(String command, String parametersString, Mode mode) throws InvalidCommandException {
        // Append user's command with mode
        if (command.equals(ADD) || command.equals(LIST) || command.equals(UPDATE)
                || command.equals(DELETE) || command.equals(ARCHIVE) || command.equals(RECEIVE)) {
            command = command + mode.name().toLowerCase();
        }

        LinkedHashMap<String, String> parameters = parseParameters(parametersString);

        switch (command) {
        case ADD_PRESCRIPTION:
            return new AddPrescriptionCommand(parameters);
        case ADD_STOCK:
            return new AddStockCommand(parameters);
        case ADD_ORDER:
            return new AddOrderCommand(parameters);
        case ARCHIVE_ORDER:
            return new ArchiveOrderCommand(parameters);
        case ARCHIVE_PRESCRIPTION:
            return new ArchivePrescriptionCommand(parameters);
        case DELETE_PRESCRIPTION:
            return new DeletePrescriptionCommand(parameters);
        case DELETE_STOCK:
            return new DeleteStockCommand(parameters);
        case DELETE_ORDER:
            return new DeleteOrderCommand(parameters);
        case EXIT:
            return new ExitCommand();
        case HELP:
            return new HelpCommand();
        case LIST_PRESCRIPTION:
            return new ListPrescriptionCommand(parameters);
        case LIST_STOCK:
            return new ListStockCommand(parameters);
        case LIST_ORDER:
            return new ListOrderCommand(parameters);
        case PURGE:
            return new PurgeCommand();
        case RECEIVE_ORDER:
            return new ReceiveOrderCommand(parameters);
        case UPDATE_STOCK:
            return new UpdateStockCommand(parameters);
        case UPDATE_PRESCRIPTION:
            return new UpdatePrescriptionCommand(parameters);
        case UPDATE_ORDER:
            return new UpdateOrderCommand(parameters);
        default:
            throw new InvalidCommandException();
        }
    }

    /**
     * Splits the user input into command and command parameters.
     *
     * @param userInput String input from user.
     * @return Array of string with size 2 with index 0 representing the command and index 1 representing the
     *     command parameters.
     */
    public String[] parseCommand(String userInput) {
        // Splits user input by spaces
        String[] userInputSplit = userInput.trim().split(SPACE_DELIMITER, 2);

        assert (userInputSplit.length <= 2) : "Command extraction failed! More than 2 values were returned!";

        String command = userInputSplit[0].toLowerCase();
        String commandParameters = "";
        if (userInputSplit.length > 1) { // Ensure command parameter exists
            commandParameters = userInputSplit[1];
        }
        return new String[]{command, commandParameters};
    }

    /**
     * Returns all the parameters entered as a LinkedHashMap.
     *
     * @param parameterString String of parameters.
     * @return LinkedHashMap with parameter as key and parameter contents as value.
     */
    public LinkedHashMap<String, String> parseParameters(String parameterString) {
        LinkedHashMap<String, String> parameters = new LinkedHashMap<>();

        if (parameterString.equals("")) { // Ensure parameter string is not empty
            return parameters;
        }

        String[] parameterSplit = parameterString.split(SPACE_DELIMITER); // Split by space

        String commandParameter = "";
        StringBuilder parameterContents = new StringBuilder();

        for (String s : parameterSplit) {
            if (s.contains(DELIMITER)) {
                if (!commandParameter.equals("")) { // Ensure it is not the first iteration
                    // Add to linkedhashmap before resetting values
                    parameters.put(commandParameter, parameterContents.toString());
                }

                parameterContents = new StringBuilder(); // Reset the values
                String[] commandSplit = s.split(DELIMITER);

                if (commandSplit.length != 0) { // Ensure '/' exists
                    commandParameter = commandSplit[0].toLowerCase();
                }

                if (commandSplit.length > 1) {
                    parameterContents = new StringBuilder(commandSplit[1]);
                }
            } else { // Add the rest of the string
                parameterContents.append(" ").append(s);
            }
        }
        parameters.put(commandParameter, parameterContents.toString()); // Add to linkedhashmap for the last parameter
        return parameters;
    }

    /**
     * Helps to set the mode of the program.
     *
     * @param ui      Reference to the UI object to print messages.
     * @param command Command entered by the user.
     * @param mode    Current mode of the program.
     * @return New mode requested by the user.
     */
    public Mode changeMode(Ui ui, String command, Mode mode) {
        Mode newMode = mode;
        if (command.equalsIgnoreCase(STOCK.name()) && !mode.name().equalsIgnoreCase(STOCK.name())) {
            newMode = STOCK;
            ui.print("Mode has changed to STOCK.");
        } else if (command.equalsIgnoreCase(Mode.PRESCRIPTION.name())
                && !mode.name().equalsIgnoreCase(PRESCRIPTION.name())) {
            newMode = Mode.PRESCRIPTION;
            ui.print("Mode has changed to PRESCRIPTION.");
        } else if (command.equalsIgnoreCase(ORDER.name()) && !mode.name().equalsIgnoreCase(ORDER.name())) {
            newMode = ORDER;
            ui.print("Mode has changed to ORDER.");
        } else {
            ui.print("Already in " + mode.name() + " mode!");
        }
        return newMode;
    }

}