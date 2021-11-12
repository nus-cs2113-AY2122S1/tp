package seedu.parser;

import seedu.commands.AddCommand;
import seedu.commands.Command;
import seedu.commands.ExitCommand;
import seedu.commands.ExpiringCommand;
import seedu.commands.FindCommand;
import seedu.commands.HelpCommand;
import seedu.commands.ListCommand;
import seedu.commands.NotificationCommand;
import seedu.commands.RemoveCommand;
import seedu.commands.ResetCommand;
import seedu.commands.ShopListCommand;
import seedu.commands.ShopResetCommand;
import seedu.commands.UpdateCommand;
import seedu.data.exception.FridgetException;
import seedu.data.item.Item;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;

public class Parser {

    private static final String ADD_FORMAT = " Try: [add] <ITEM_NAME> /<YYYY-MM-DD>";
    private static final String REMOVE_FORMAT = " Try: [remove] <ITEM_NAME>";
    private static final String FIND_FORMAT = " Try: [find] <ITEM_NAME>";
    private static final String UPDATE_FORMAT = " Try: [update] <ITEM_NAME>";
    private static final String DATE_FORMAT = "Please try this format for the date:\n\n"
            + "    /YYYY-MM-DD\n"
            + "    Example: '... /2022-08-03";
    private static final String INVALID_COMMAND_MESSAGE = "No command found!\n"
            + "Enter help if you need the list of available commands.";
    private static final String NAME_ERROR_MESSAGE = "You are not able to use '/' and '|' in item name.";
    private static final String UPDATE_ERROR_MESSAGE = "Update quantity cannot be set to negative values.\n"
            + "Command has been shutdown.";
    private static final String ABORT_MESSAGE = "You have opted to abort. Command has been shutdown.";
    private static final String ADD_ERROR_MESSAGE = "No items have been added. Command has been shutdown.";

    public enum CommandType {
        ADD,
        REMOVE,
        FIND,
        UPDATE
    }

    /**
     * Constructor for parser.
     */
    public Parser() {
    }

    /**
     * Returns a Command class based on user input.
     *
     * @param userInput The input from the user.
     * @return Command class that user input is referencing.
     * @throws FridgetException thrown when invalid command is input.
     */
    public Command parseCommand(String userInput) throws FridgetException {
        String[] processedInput = processInput(userInput);
        String userCommand = processedInput[0];

        switch (userCommand.toLowerCase()) {
        case "add":
            return new AddCommand();
        case "remove":
            return new RemoveCommand();
        case "list":
            return new ListCommand();
        case "shoplist":
            return new ShopListCommand();
        case "expiring":
            return new ExpiringCommand();
        case "exit":
            return new ExitCommand();
        case "help":
            return new HelpCommand();
        case "find":
            return new FindCommand();
        case "notifs":
            return new NotificationCommand();
        case "update":
            return new UpdateCommand();
        case "reset":
            return new ResetCommand();
        case "shopreset":
            return new ShopResetCommand();
        default:
            assert !userCommand.equalsIgnoreCase("add");
            assert !userCommand.equalsIgnoreCase("remove");
            assert !userCommand.equalsIgnoreCase("list");
            assert !userCommand.equalsIgnoreCase("shoplist");
            assert !userCommand.equalsIgnoreCase("expiring");
            assert !userCommand.equalsIgnoreCase("exit");
            assert !userCommand.equalsIgnoreCase("help");
            assert !userCommand.equalsIgnoreCase("find");
            assert !userCommand.equalsIgnoreCase("notifs");
            assert !userCommand.equalsIgnoreCase("reset");
            assert !userCommand.equalsIgnoreCase("shopreset");
            assert !userCommand.equalsIgnoreCase("update");
            throw new FridgetException(INVALID_COMMAND_MESSAGE);
        }
    }

    //@@author uosjapuelks
    /**
     * Splits user inputs by " ".
     *
     * @param userInput String input to be split by " ".
     * @return String[] from splitting input.
     */
    private String[] splitUserInputByWhitespace(String userInput) {
        String[] splitInput = userInput.trim().split(" ", 2);
        assert splitInput.length >= 1;
        return splitInput;
    }

    /**
     * Removes unnecessary " " and "\t" from every iteration.
     *
     * @param splitInput Input that has been split.
     * @return Trimmed output.
     */
    private String[] trimContents(String[] splitInput) {
        String[] trimmedInput = new String[splitInput.length];
        for (int i = 0; i < splitInput.length; i++) {
            trimmedInput[i] = splitInput[i].trim();
        }
        return trimmedInput;
    }

    /**
     * Execute splitUserInput and trimContents on userInput.
     *
     * @param userInput String input by user.
     * @return String[] after processing using splitUserInputByWhitespace and trimContents.
     */
    private String[] processInput(String userInput) {
        String[] splitInput = splitUserInputByWhitespace(userInput);
        return trimContents(splitInput);
    }

    //@@author alvynben
    /**
     * Extracts expiry as String to parse into LocalDate.
     *
     * @param processedInput userInput after processInput().
     * @return expiry as String in format ("yyyy-mm-dd")
     * @throws FridgetException thrown when there are is no expiry date.
     */
    private String extractExpiry(String[] processedInput) throws FridgetException {
        String expiry = "";
        for (String str : processedInput) {
            expiry = (str.contains("/")) ? str.substring(str.indexOf("/") + 1).trim() : expiry;
        }
        if (expiry.equals("")) {
            throw new FridgetException("Missing expiry date." + ADD_FORMAT);
        } else if (expiry.startsWith("-")) {
            throw new FridgetException("Extra \"-\" detected before date input.\n" + DATE_FORMAT);
        }
        return expiry;
    }

    /**
     * Extracts name or item description from processed input.
     *
     * @param processedInput userInput after processInput().
     * @param commandType    an enum that represent a commandType.
     * @return name or item description.
     * @throws FridgetException thrown when there are missing inputs or have '|' or '/' in description.
     */
    private String extractDescription(String[] processedInput, CommandType commandType) throws FridgetException {
        String correctFormat;
        switch (commandType) {
        case ADD:
            correctFormat = ADD_FORMAT;
            break;
        case REMOVE:
            correctFormat = REMOVE_FORMAT;
            break;
        case FIND:
            correctFormat = FIND_FORMAT;
            break;
        case UPDATE:
            correctFormat = UPDATE_FORMAT;
            break;
        default:
            correctFormat = "";
            break;
        }

        //@@author uosjapuelks
        if (processedInput.length < 2) {
            throw new FridgetException("Missing item name." + correctFormat);
        } else if (!processedInput[1].contains("/") && commandType == CommandType.ADD) {
            throw new FridgetException("This description has an error: ["
                    + processedInput[1] + "]\nMissing expiry date." + correctFormat);
        }

        //@@author alvynben
        String description = "";
        if (commandType == CommandType.ADD) {
            description = processedInput[1].substring(0, processedInput[1].indexOf("/")).trim();
            if (description.contains("|")) {
                throw new FridgetException("Please do not use '|' in your item name. Found in: [" + description + "]");
            } else if (!description.equals("")) {
                return description;
            }
            throw new FridgetException("Missing item name." + correctFormat);
        } else {
            description = processedInput[1].trim();
            if (description.contains("|") | description.contains("/")) {
                throw new FridgetException(NAME_ERROR_MESSAGE);
            }
            return description;
        }
    }

    //@@author alvynben
    /**
     * Returns an Item based on user input.
     *
     * @param userInput The input from the user in this manner - "add burger /2021-09-23".
     * @return An item.
     * @throws FridgetException thrown when date formatting is wrong.
     */
    public Item parseItemForAdding(String userInput) throws FridgetException {
        String[] processedInput = processInput(userInput);
        String itemName = extractDescription(processedInput, CommandType.ADD);
        LocalDate expiryDate;
        String errorMessage;
        assert !itemName.isEmpty();

        String expiryString = extractExpiry(processedInput);
        assert !expiryString.isEmpty();
        try {
            expiryDate = LocalDate.parse(expiryString);
        } catch (DateTimeParseException e) {
            errorMessage = expiryString + " is not formatted properly.\n"
                    + DATE_FORMAT + "\n"
                    + ADD_ERROR_MESSAGE;
            throw new FridgetException(errorMessage);
        }

        //@@author uosjapuelks
        if (expiryDate.isBefore(LocalDate.now())) {
            long daysPast = ChronoUnit.DAYS.between(expiryDate, LocalDate.now());
            errorMessage = "[" + itemName + "]" + " expired " + daysPast + " days ago."
                    + "\n" + ADD_ERROR_MESSAGE;
            throw new FridgetException(errorMessage);
        }
        //@@author

        return new Item(itemName, expiryDate);
    }

    //@@author alvynben
    /**
     * Extract multiple items to be added using an ArrayList.
     *
     * @param userInput raw userInput of format "add ITEM_NAME /2021-11-11 ; ITEM_NAME /2021-10-10"
     * @return an ArrayList containing all items to be added based on user input.
     * @throws FridgetException thrown when date formatting is wrong.
     */
    public ArrayList<Item> parseMultipleItemsForAdding(String userInput) throws FridgetException {
        String[] processedInput = processInput(userInput);
        String[] itemsInfo = processedInput[1].split(";");

        ArrayList<Item> allItemsToBeAdded = new ArrayList<>();
        for (String itemInfo : itemsInfo) {
            Item newItem = parseItemForAdding(processedInput[0] + " " + itemInfo);
            allItemsToBeAdded.add(newItem);
        }

        return allItemsToBeAdded;
    }

    //@@author uosjapuelks
    /**
     * Extracts the sortType from the list command.
     *
     * @param userInput Input from user.
     * @return Either "default", "e", or "r".
     */
    public String parseSortTypeForList(String userInput) {
        String[] processedInput = processInput(userInput);
        if (processedInput.length == 1) {
            return "default";
        }
        String sortType = processedInput[1].substring(processedInput[1].indexOf("-") + 1).trim();
        assert !sortType.isEmpty();
        return sortType;
    }

    //@@author alvynben
    /**
     * Returns a search term provided by the "find" command.
     *
     * @param userInput   The input from the user in this manner - "find burger".
     * @param commandType The commandType calling the function.
     * @return The search term.
     * @throws FridgetException If there are missing inputs or '|' or '/' is in description.
     */
    public String parseSearchTerm(String userInput, CommandType commandType) throws FridgetException {
        String[] processedInput = processInput(userInput);
        String searchTerm = extractDescription(processedInput, commandType);
        assert !searchTerm.isEmpty();
        return searchTerm;
    }

    //@@author uosjapuelks
    /**
     * Verify if quantity is valid.
     *
     * @param newQty new quantity to be updated.
     * @return Boolean of true if new quantity is equal to 0.
     * @throws FridgetException When new quantity is less than 0.
     */
    public boolean parseQuantity(int newQty) throws FridgetException {
        if (newQty < 0) {
            throw new FridgetException(UPDATE_ERROR_MESSAGE);
        } else {
            return newQty == 0;
        }
    }

    /**
     * Check suggestion.
     *
     * @param acceptSuggestion If suggestion was accepted with "y".
     * @throws FridgetException If Suggestion was rejected.
     */
    public void parseSuggestion(boolean acceptSuggestion) throws FridgetException {
        if (!acceptSuggestion) {
            throw new FridgetException(ABORT_MESSAGE);
        }
    }
    //@@author
}
