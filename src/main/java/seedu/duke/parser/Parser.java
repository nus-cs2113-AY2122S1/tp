package seedu.duke.parser;


import seedu.duke.Duke;
import seedu.duke.Ui;
import seedu.duke.commands.AddCommand;
import seedu.duke.commands.ByeCommand;

import seedu.duke.commands.DoneUndoCommand;
import seedu.duke.commands.HelpCommand;
import seedu.duke.commands.SelectCommand;
import seedu.duke.commands.Command;
import seedu.duke.commands.DeleteCommand;
import seedu.duke.commands.FindCommand;
import seedu.duke.commands.ListCommand;
import seedu.duke.commands.NextCommand;
import seedu.duke.commands.UpdateCommand;
import seedu.duke.exceptions.DukeException;
import seedu.duke.exceptions.parserexceptions.AttributeNotFoundException;
import seedu.duke.exceptions.parserexceptions.InvalidItemTypeException;
import seedu.duke.exceptions.parserexceptions.NoCommandAttributesException;
import seedu.duke.items.Item;
import seedu.duke.parser.commandparser.AddParser;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;


public abstract class Parser {
    protected static DateTimeFormatter formatter1 = DateTimeFormatter.ofPattern("dd-MM-yyyy HHmm");
    protected static DateTimeFormatter formatter2 = DateTimeFormatter.ofPattern("d MMM yyyy - HH:mm");
    private static int indexOfLastSelectedEvent = -1;

    public static Command parseCommand(String response) throws DukeException {
        // Trim and split response using first detected occurrence of whitespace(s) to get type of command requested
        String[] command = response.trim().split(" +", 1);
        String commandType = command[0];
        String commandDetails = command[1];

        switch (commandType) {
        case "list":
            return new ListCommand(command);
        case "done":
        case "undo":
            return new DoneUndoCommand(command, response);
        case "delete":
            return new DeleteCommand(command);
        case "add":
            return new AddCommand(command, response);
            // TODO: Replace with the following commented out code
            // return AddParser.getAddCommand(commandDetails);
        case "bye":
            return new ByeCommand();
        case "help":
            return new HelpCommand();
        case "find":
            return new FindCommand(command);
        case "select":
            return new SelectCommand(command);
        case "update":
            return new UpdateCommand(command);
        case "next":
            return new NextCommand(command);
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
        String[] commandAttributes = (commandDetails.trim().split(" +"));

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

    public static LocalDateTime convertDateTime(String dateTime) {
        return LocalDateTime.parse(dateTime, formatter1);
    }

    public static String convertDateTimeForSaving(LocalDateTime dateTime) {
        return dateTime.format(formatter1);
    }

    public static ArrayList<Item> makeMainList() {
        ArrayList<Item> sortedList = new ArrayList<>();
        sortedList.addAll(Duke.eventCatalog);
        sortedList.addAll(Duke.taskList);
        return sortedList;
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
