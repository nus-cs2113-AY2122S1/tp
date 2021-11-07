package seedu.duke.parser;


import seedu.duke.Ui;
import seedu.duke.commands.ByeCommand;

import seedu.duke.commands.HelpCommand;
import seedu.duke.commands.Command;
import seedu.duke.commands.DeleteCommand;
import seedu.duke.commands.FindCommand;
import seedu.duke.exceptions.DukeException;
import seedu.duke.exceptions.parserexceptions.AttributeNotFoundException;
import seedu.duke.exceptions.parserexceptions.InvalidBudgetException;
import seedu.duke.exceptions.parserexceptions.InvalidItemTypeException;
import seedu.duke.exceptions.parserexceptions.NoCommandAttributesException;
import seedu.duke.parser.commandparser.AddParser;
import seedu.duke.parser.commandparser.FindParser;
import seedu.duke.parser.commandparser.ListParser;
import seedu.duke.parser.commandparser.NextParser;
import seedu.duke.parser.commandparser.SelectParser;
import seedu.duke.parser.commandparser.UpdateParser;
import seedu.duke.parser.commandparser.DoneUndoParser;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;


public abstract class Parser {
    protected static DateTimeFormatter formatter1 = DateTimeFormatter.ofPattern("dd-MM-yyyy HHmm");
    protected static DateTimeFormatter formatter2 = DateTimeFormatter.ofPattern("d MMM yyyy - HH:mm");
    private static int indexOfLastSelectedEvent = -1;

    public static Command parseCommand(String response) throws DukeException, NoCommandAttributesException,
            InvalidItemTypeException {
        // Trim and split response using first detected occurrence of whitespace(s) to get type of command requested
        //String[] command = response.trim().split(" +");

        // TODO: Once parser is restructured, replace above with following two lines
        String[] command = response.trim().split(" +");
        if (command.length == 1) {
            String commandType = command[0];
            return singleWordCommandProtocol(command, commandType);
        } else {
            String[] splitCommandIntoTwo = response.trim().split(" +", 2);
            String commandDetails = splitCommandIntoTwo[1];
            String commandType = command[0];
            return multiWordCommandProtocol(response, command, commandDetails, commandType);
        }
    }

    private static Command multiWordCommandProtocol(String response, String[] command, String commandDetails,
                                                    String commandType) throws DukeException {
        switch (commandType) {
        case "list":
            //return new ListCommand(command);
            return ListParser.getListCommand(commandDetails,response);
        case "done":
        case "undo":
            return DoneUndoParser.getDoneUndoCommand(command, commandDetails);
        case "delete":
            return new DeleteCommand(command);
        case "add":
            return AddParser.getAddCommand(commandDetails);
        case "bye":
            return new ByeCommand();
        case "help":
            return new HelpCommand();
        case "find":
            return FindParser.getFindCommand(command, commandDetails);
        case "select":
            return SelectParser.getSelectCommand(command, commandDetails);
        case "update":
            //return new UpdateCommand(command);
            return UpdateParser.getUpdateCommand(commandDetails);
        case "next":
            return NextParser.getNextCommand(commandDetails, response);
        default:
            throw new DukeException(Ui.getInvalidCommandMessage());
        }
    }

    private static Command singleWordCommandProtocol(String[] command, String commandType) throws DukeException {
        switch (commandType) {

        case "delete":
            return new DeleteCommand(command);
        case "bye":
            return new ByeCommand();
        case "help":
            return new HelpCommand();
        default:
            throw new DukeException(Ui.getInvalidCommandMessage());
        }
    }

    /**
     * Gets the item type (event, task or member) of the command given user input with the command
     * type (add, delete etc) filtered off.
     *
     * @param commandDetails Details of the command containing only the item flag and its
     *                       attributes (e.g. "-e n/TITLE...")
     * @return The specified item type of the user command
     * @throws InvalidItemTypeException If there is no flag detected that is valid
     */
    protected static ItemType getItemType(String commandDetails) throws InvalidItemTypeException {
        String itemFlagDeclaration = (commandDetails.trim().split(" +"))[0];
        switch (itemFlagDeclaration) {
        case "-e":
            return ItemType.EVENT;
        case "-t":
            return ItemType.TASK;
        case "-m":
            return ItemType.MEMBER;
        default:
            throw new InvalidItemTypeException();
        }
    }

    /**
     * Gets the combined string of command attributes given user input with the command type (add, delete etc)
     * filtered off.
     *
     * @param commandDetails String containing details of the command
     * @return The combined string of command attributes e.g. "n/TITLE d/DATE v/VENUE b/BUDGET"
     * @throws NoCommandAttributesException If there is no command attributes detected
     */
    protected static String getCommandAttributes(String commandDetails) throws NoCommandAttributesException {
        String[] commandAttributes = (commandDetails.trim().split(" +", 2));

        if (commandAttributes.length < 2) {
            throw new NoCommandAttributesException();
        }

        return commandAttributes[1];
    }

    // @@author Alvinlj00
    /**
     * Takes in an item attribute and searches the response String (e.g. commandDetails) for the item attribute, and
     * returns the parsed attribute.
     *
     * @param response User input/details regarding the command to search the attribute for.
     * @param itemAttribute The item attribute to search for (e.g. title, venue etc.).
     * @return The parsed attribute (does not contain the attribute flag) that was found.
     * @throws AttributeNotFoundException If no such attribute is found within the provided string.
     */
    protected static String retrieveItemAttribute(String response, ItemAttribute itemAttribute)
            throws AttributeNotFoundException {
        int startOfItemAttribute = response.indexOf(ItemAttribute.getItemFlag(itemAttribute)) + 2;
        int endOfItemAttribute = response.indexOf("/", startOfItemAttribute) - 2;

        String result;
        if (endOfItemAttribute < 0) {
            result = response.trim().substring(startOfItemAttribute);
        } else {
            result = response.trim().substring(startOfItemAttribute, endOfItemAttribute);
        }

        if (result.isBlank()) {
            throw new AttributeNotFoundException(itemAttribute);
        }

        return result;
    }
    // @@author

    public static String convertDateTime(LocalDateTime dateTime) {
        return dateTime.format(formatter2);
    }

    public static LocalDateTime convertDateTime(String dateTime) throws DukeException {
        try {
            LocalDateTime result = LocalDateTime.parse(dateTime, formatter1);
            if (result.isBefore(LocalDateTime.now())) {
                throw new DukeException("Unfortunately, we cannot travel back in time. Please "
                        + "enter a valid date and time in the format 'dd-MM-yyyy HHmm'. ");
            }
            return result;
        } catch (DateTimeParseException e) {
            System.out.println("Please enter a valid date and time in the format 'dd-MM-yyyy HHmm'.");

        }
        return null;
    }

    public static String convertDateTimeForSaving(LocalDateTime dateTime) {
        return dateTime.format(formatter1);
    }

    /**
     * Converts a budget as a string and formats it into a double.
     *
     * @param budget The budget provided as a String
     * @return The converted budget as a double
     * @throws InvalidBudgetException If the provided budget converts into a negative number of has more than 2 decimals
     */
    public static double convertEventBudgetToDouble(String budget) throws InvalidBudgetException {
        Double result = null;
        try {
            if (!budget.matches("[0-9.]+")) {
                throw new InvalidBudgetException("Event budget can only be a valid number. ");
            }
            result = Double.parseDouble(budget);
            if (result < 0) {
                throw new InvalidBudgetException("Event budget needs to be a positive number.");
            }

            if (BigDecimal.valueOf(result).scale() > 2) {
                throw new InvalidBudgetException("Event budget cannot have more than 2 decimal places.");
            }
        } catch (NumberFormatException e) {
            System.out.println("Event budget needs to be a number.");
        }

        // If conditional checks above fail internally, result will remain null. Throw exception
        if (result == null) {
            throw new InvalidBudgetException("Event budget is null!");
        }

        return result;
    }

    public static int[] extractInt(String input) throws DukeException {
        String parsedInput = input.replaceAll("[^\\d]", " ").trim();
        if (parsedInput.isBlank()) {
            throw new DukeException("Indexes entered need to be valid numbers. ");
        }

        String[] stringIndexes = parsedInput.split(" +");
        int [] indexes = new int[stringIndexes.length];

        for (int i = 0; i < stringIndexes.length; i++) {
            // -1 to obtain the real indexes instead of what the user sees
            indexes[i] = Integer.parseInt(stringIndexes[i]) - 1;
        }
        return indexes;
    }

    public static void updateIndexOfLastSelectedEvent(int index) {
        indexOfLastSelectedEvent = index;
    }

    public static int getIndexOfLastSelectedEvent() {
        return indexOfLastSelectedEvent;
    }

    public static void updateIndexToNoEventSelected() {
        updateIndexOfLastSelectedEvent(-1);
    }

    public static boolean noEventSelected() {
        return getIndexOfLastSelectedEvent() == -1;
    }
}
