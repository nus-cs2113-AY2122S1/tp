package seedu.situs.parser;

import seedu.situs.Situs;
import seedu.situs.command.AddCommand;
import seedu.situs.command.AlertExpiringSoonCommand;
import seedu.situs.command.AlertLowStockCommand;
import seedu.situs.command.DateCommand;
import seedu.situs.command.DeleteCommand;
import seedu.situs.command.ExpireCommand;
import seedu.situs.command.HelpCommand;
import seedu.situs.command.ListCommand;
import seedu.situs.command.UpdateCommand;
import seedu.situs.command.FindCommand;
import seedu.situs.command.SubtractCommand;
import seedu.situs.exceptions.SitusException;
import seedu.situs.ingredients.Ingredient;
import seedu.situs.localtime.CurrentDate;


import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.HashSet;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.logging.Level;
import java.util.logging.Logger;


public class Parser {
    private static final String COMMAND_LIST = "list";
    private static final String COMMAND_ADD = "add";
    private static final String COMMAND_SUBTRACT = "subtract";
    private static final String COMMAND_DELETE = "delete";
    private static final String COMMAND_UPDATE = "update";
    private static final String COMMAND_HELP = "help";
    private static final String COMMAND_EXIT = "exit";
    private static final String COMMAND_DATE = "date";
    private static final String COMMAND_EXPIRE = "expire";
    private static final String COMMAND_FIND = "find";
    private static final String COMMAND_ALERTS = "alerts";
    private static final String COMMAND_SET_THRESHOLD = "set";

    private static final String TYPE_EXPIRY = "expiry";
    private static final String TYPE_STOCK = "stock";

    public static final String DIVIDER = "____________________________________________________\n";
    private static final String INVALID_COMMAND_MESSAGE = "Invalid command!";
    private static final String NUMBER_FORMAT_ERROR_MESSAGE = "Invalid number format!";
    private static final String INVALID_INDEX_MESSAGE = "The index given is in an incorrect format!\n"
            + "The index MUST be of the form: X.Y (e.g. 1.1, 2.3, etc.)";
    private static final String INCORRECT_PARAMETERS_MESSAGE = "The number of parameters is wrong!";
    private static final String INCORRECT_PARAMETER_FORMAT_MESSAGE = "The parameter format is wrong!";
    private static final String EXPIRY_FORMAT_ERROR_MESSAGE = "Invalid expiry date format!"
            + '\n' + "Please key in the expiry date in the format dd/mm/yyyy!";
    private static final String INVALID_ALERT_TYPE_MESSAGE = "Not an alert type!";
    private static final String INVALID_THRESHOLD_TYPE_MESSAGE = "Not a threshold type!";
    private static final String SET_THRESHOLD_ERROR_MESSAGE = "Error in setting threshold";
    private static final String INVALID_THRESHOLD_MESSAGE = "Thresholds cannot be less than or equal to 0";
    private static final String INVALID_AMOUNT_MESSAGE = "The amount of an ingredient cannot be negative or 0";
    private static final String INVALID_AMOUNT_SUBTRACT_MESSAGE = "The amount to subtract cannot be negative";
    private static final String INVALID_EXPIRY_MESSAGE = "Your ingredient is already expired. Please throw it away.";
    private static final String USE_DELETE_INSTEAD_MESSAGE = "You are updating an ingredient's amount to 0. "
            + "Use delete to remove it instead.";
    private static final String INVALID_CHARACTERS_FIND_MESSAGE = "Only alphanumeric characters allowed in keyword";
    private static final String INVALID_CHARACTERS_ADD_MESSAGE = "Only alphanumeric characters allowed in name";

    private static final long MAX_EXPIRY_THRESHOLD = 1096; //3 years plus leap year
    private static final String MAX_EXPIRY_THRESHOLD_MESSAGE = "The max expiry threshold is 1096 days";
    private static final long MAX_STOCK_THRESHOLD = 1000;
    private static final String MAX_STOCK_THRESHOLD_MESSAGE = "The max stock threshold is 1000kg";

    private static final long MAX_INGREDIENT_THRESHOLD = 1000;
    private static final String MAX_INGREDIENT_THRESHOLD_MESSAGE = "The maximum amount for an ingredient is 1000kg.";

    private static final String SPACE_SEPARATOR = " ";
    private static final String EMPTY_STRING = "";
    private static final String DELIMITER = "n/|a/|e/";
    private static final String DELETE_DELIM = "\\.";
    private static final String UPDATE_DELIM = "a/";
    private static final String SUBTRACT_DELIM = "n/|a/";

    private static final int ADD_COMMAND_ARGUMENT_COUNT = 4;
    private static final int SUBTRACT_COMMAND_ARGUMENT_COUNT = 3;
    private static final int UPDATE_COMMAND_ARGUMENT_COUNT = 2;
    private static final int DELETE_COMMAND_ARGUMENT_COUNT = 2;
    private static final int SET_COMMAND_ARGUMENT_COUNT = 3;

    private static final Logger LOGGER = Logger.getLogger(Situs.class.getName());

    public static boolean isExit(String command) {
        return (command.equals(COMMAND_EXIT));
    }

    /**
     * Sends the input to the relevant command parser.
     *
     * @param command The user input String
     * @return the output message
     * @throws SitusException when there is an issue with the input
     */
    public static String parse(String command) throws SitusException {

        String[] words = command.split(SPACE_SEPARATOR, 2);

        switch (words[0]) {
        case COMMAND_LIST:
            return parseListCommand();
        case COMMAND_ADD:
            return parseAddCommand(command);
        case COMMAND_SUBTRACT:
            return parseSubtractCommand(command);
        case COMMAND_DELETE:
            return parseDeleteCommand(command);
        case COMMAND_UPDATE:
            return parseUpdateCommand(command);
        case COMMAND_DATE:
            return parseDateCommand(command);
        case COMMAND_HELP:
            return parseHelpCommand();
        case COMMAND_EXPIRE:
            return parseExpireCommand(command);
        case COMMAND_FIND:
            return parseAndRunFindCommand(command);
        case COMMAND_ALERTS:
            return parseAlertsCommand(command);
        case COMMAND_SET_THRESHOLD:
            return parseSetCommand(command);
        case COMMAND_EXIT:
            return "";
        default:
            return INVALID_COMMAND_MESSAGE;
        }
    }

    //@@author mudkip8

    /**
     * Checks if a string contains valid characters(alphanumeric).
     *
     * @param stringToCheck The string to check
     * @return true if all valid, false otherwise
     */
    private static boolean containsInvalidCharacters(String stringToCheck) {
        String validCharacters = "^[0-9a-zA-Z]+$";
        Pattern pattern = Pattern.compile(validCharacters);
        Matcher matcher = pattern.matcher(stringToCheck);
        return !matcher.matches();
    }

    //@@author nishantrai-nus

    /**
     * Parses and executes the {@code find} command.
     *
     * @param command The user input string
     * @return Search results for entered keywords
     * @throws SitusException If no keywords are entered
     */
    private static String parseAndRunFindCommand(String command) throws SitusException {
        LOGGER.log(Level.INFO, "parsing find command");
        String parsedCommand = command.substring(COMMAND_FIND.length()).trim();
        String[] keywords = parsedCommand.split(SPACE_SEPARATOR);
        Set<String> keywordsUnique = new HashSet<>();

        assert (keywords != null);

        for (int i = 0; i < keywords.length; i++) {
            if (keywords[i].isEmpty()) {
                LOGGER.log(Level.WARNING, "error in parsing find command");
                throw new SitusException(INCORRECT_PARAMETERS_MESSAGE);
            }
            keywords[i] = keywords[i].trim();
            if (containsInvalidCharacters(keywords[i])) {
                LOGGER.log(Level.WARNING, "error in parsing find command");
                throw new SitusException(INVALID_CHARACTERS_FIND_MESSAGE);
            }
            keywordsUnique.add(keywords[i]);
        }

        String resultMsg = "";
        for (String keyword : keywordsUnique) {
            if (!resultMsg.isEmpty()) {
                resultMsg += "\n";
            }
            LOGGER.log(Level.INFO, "searching for keywords");
            resultMsg += new FindCommand(keyword).run();
        }
        return resultMsg;
    }

    /**
     * Calls and executes the {@code help} command.
     *
     * @return a message summarising all possible commands recognised by SITUS
     */
    private static String parseHelpCommand() {
        return new HelpCommand().run();
    }

    //@@author AayushMathur7

    /**
     * Parses and executes the {@code update} command.
     *
     * @param command the user input string
     * @return Ingredient updated message
     */
    private static String parseUpdateCommand(String command) throws SitusException {
        String parsedCommand = command.substring(COMMAND_UPDATE.length()).trim();
        String[] details = parsedCommand.split(UPDATE_DELIM);
        if (details.length != UPDATE_COMMAND_ARGUMENT_COUNT) {
            throw new SitusException(INCORRECT_PARAMETERS_MESSAGE);
        }

        assert (details.length == UPDATE_COMMAND_ARGUMENT_COUNT);

        if (!details[0].contains(".")) {
            throw new SitusException(INVALID_INDEX_MESSAGE);
        }

        for (int i = 0; i < UPDATE_COMMAND_ARGUMENT_COUNT; i++) {
            details[i] = details[i].trim();
            if (details[i].equals(EMPTY_STRING) || details[i].contains(" ")) {
                throw new SitusException(INCORRECT_PARAMETERS_MESSAGE);
            }
        }

        try {
            String[] splitGroupAndIngredient = details[0].split("\\.");

            if (splitGroupAndIngredient.length != 2) {
                throw new SitusException(INCORRECT_PARAMETERS_MESSAGE);
            }

            if (Double.parseDouble(details[1]) < 0) {
                throw new SitusException(INVALID_AMOUNT_MESSAGE);
            }
            if (Math.abs(Double.parseDouble(details[1]) - 0.000) < 0.001) {
                throw new SitusException(USE_DELETE_INSTEAD_MESSAGE);
            }
            if (Double.parseDouble(details[1]) > MAX_INGREDIENT_THRESHOLD) {
                throw new SitusException(MAX_INGREDIENT_THRESHOLD_MESSAGE);
            }

            int groupNumber = Integer.parseInt(splitGroupAndIngredient[0]);
            int ingredientNumber = Integer.parseInt(splitGroupAndIngredient[1]);
            double newAmount = Double.parseDouble(details[1]);

            return new UpdateCommand(groupNumber, ingredientNumber, newAmount).run();
        } catch (NumberFormatException e) {
            throw new SitusException(NUMBER_FORMAT_ERROR_MESSAGE);
        }
    }

    //@@author ngoivanessa

    /**
     * Parses and executes the {@code add} command.
     *
     * @param command The user input string
     * @return Ingredient added message
     */
    private static String parseAddCommand(String command) throws SitusException {
        if (!command.contains("n/") || !command.contains("a/") || !command.contains("e/")) {
            throw new SitusException(INCORRECT_PARAMETERS_MESSAGE);
        }

        String parsedCommand = command.substring(COMMAND_ADD.length()).trim();
        String[] details = parsedCommand.split(DELIMITER);

        if (details.length != ADD_COMMAND_ARGUMENT_COUNT) {
            throw new SitusException(INCORRECT_PARAMETERS_MESSAGE);
        }
        if (!details[0].isEmpty()) {
            throw new SitusException(INVALID_COMMAND_MESSAGE);
        }


        assert (details.length == ADD_COMMAND_ARGUMENT_COUNT);

        for (int i = 1; i < ADD_COMMAND_ARGUMENT_COUNT; i++) {
            details[i] = details[i].trim();
            if (details[i].equals(EMPTY_STRING)) {
                throw new SitusException(INCORRECT_PARAMETERS_MESSAGE);
            }
        }

        try {
            String ingredientName = details[1];

            if (containsInvalidCharacters(ingredientName)) {
                throw new SitusException(INVALID_CHARACTERS_ADD_MESSAGE);
            }

            double ingredientAmount = Double.parseDouble(details[2]);

            if (ingredientAmount <= 0 || Math.abs(ingredientAmount - 0.000) < 0.001) {
                throw new SitusException(INVALID_AMOUNT_MESSAGE);
            }

            if (ingredientAmount > MAX_INGREDIENT_THRESHOLD) {
                throw new SitusException(MAX_INGREDIENT_THRESHOLD_MESSAGE);
            }
            LocalDate ingredientExpiry = Ingredient.stringToDate(details[3]);

            if (ingredientExpiry.isBefore(CurrentDate.getCurrentDate())) {
                throw new SitusException(INVALID_EXPIRY_MESSAGE);
            }

            Ingredient newIngredient = new Ingredient(ingredientName, ingredientAmount,
                    ingredientExpiry);
            return new AddCommand(newIngredient).run();
        } catch (NumberFormatException e) {
            throw new SitusException(NUMBER_FORMAT_ERROR_MESSAGE);
        } catch (DateTimeParseException e) {
            throw new SitusException(EXPIRY_FORMAT_ERROR_MESSAGE);
        }
    }

    //@@author AayushMathur7

    /**
     * Parses and executes the {@code subtract} command.
     *
     * @param command the user input string
     * @return subtracted message
     * @throws SitusException when error in subtracting
     */
    private static String parseSubtractCommand(String command) throws SitusException {
        String parsedCommand = command.substring(COMMAND_SUBTRACT.length()).trim();
        String[] details = parsedCommand.split(SUBTRACT_DELIM);

        if (details.length != SUBTRACT_COMMAND_ARGUMENT_COUNT) {
            throw new SitusException(INCORRECT_PARAMETERS_MESSAGE);
        }

        assert (details.length == SUBTRACT_COMMAND_ARGUMENT_COUNT);

        for (int i = 1; i < SUBTRACT_COMMAND_ARGUMENT_COUNT; i++) {
            details[i] = details[i].trim();
            if (details[i].equals(EMPTY_STRING) || details[i].contains(" ")) {
                throw new SitusException(INCORRECT_PARAMETERS_MESSAGE);
            }
        }
        try {
            String ingredientName = details[1];
            double subtractAmount = Double.parseDouble(details[2]);

            if (subtractAmount < 0) {
                throw new SitusException(INVALID_AMOUNT_SUBTRACT_MESSAGE);
            }
            return new SubtractCommand(ingredientName, subtractAmount).run();
        } catch (NumberFormatException e) {
            throw new SitusException(NUMBER_FORMAT_ERROR_MESSAGE);
        }
    }

    //@@author datn02

    /**
     * Calls and executes the {@code list} command.
     *
     * @return List of ingredients
     * @throws SitusException if trying to access non-existing ingredients
     */
    private static String parseListCommand() throws SitusException {
        return new ListCommand().run();
    }

    /**
     * Calls and executes the {@code delete} command.
     *
     * @param command The user input string
     * @return Ingredient Deleted Message
     * @throws SitusException if trying to access non-existing ingredients
     */
    private static String parseDeleteCommand(String command) throws SitusException {
        String[] details = command.substring(COMMAND_DELETE.length()).trim().split(DELETE_DELIM);

        if (details.length != DELETE_COMMAND_ARGUMENT_COUNT) {
            throw new SitusException(INCORRECT_PARAMETER_FORMAT_MESSAGE);
        }
        if (details[0].isEmpty()) {
            throw new SitusException(INCORRECT_PARAMETERS_MESSAGE);
        }

        int[] detailsToInt = new int[2];
        try {
            for (int i = 0; i < details.length; i++) {
                if (details[i].equals(EMPTY_STRING)) {
                    throw new SitusException(INCORRECT_PARAMETERS_MESSAGE);
                }
                details[i] = details[i].trim();
                if (details[i].contains(" ")) {
                    throw new SitusException(INCORRECT_PARAMETERS_MESSAGE);
                }
                detailsToInt[i] = Integer.parseInt(details[i]);
            }
            return new DeleteCommand(detailsToInt[0], detailsToInt[1]).run();
        } catch (NumberFormatException e) {
            throw new SitusException(NUMBER_FORMAT_ERROR_MESSAGE);
        }
    }

    /**
     * Parses and executes the {@code date} command.
     *
     * @param command The user's input string
     * @return Date changed success message
     * @throws SitusException if the date format is incorrect
     */
    private static String parseDateCommand(String command) throws SitusException {
        String detail = command.substring(COMMAND_DATE.length()).trim();

        return new DateCommand(detail).run();
    }

    //@@author mudkip8

    /**
     * Parses and executes the {@code expire} command.
     *
     * @param command The user's input string
     * @return List of ingredients expiring by the specified date
     * @throws SitusException if the date format is incorrect
     */
    private static String parseExpireCommand(String command) throws SitusException {
        String[] details = command.trim().split(SPACE_SEPARATOR);

        try {
            LocalDate expireBeforeDate = Ingredient.stringToDate(details[1]);
            return new ExpireCommand(expireBeforeDate).run();
        } catch (DateTimeParseException e) {
            throw new SitusException(EXPIRY_FORMAT_ERROR_MESSAGE);
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new SitusException(INCORRECT_PARAMETERS_MESSAGE);
        }
    }

    /**
     * Parses and executes the {@code alerts} command.
     *
     * @param command The user's input string
     * @return The list of ingredients for each alert type
     * @throws SitusException if alert type, date format or amount format is incorrect
     */
    private static String parseAlertsCommand(String command) throws SitusException {
        String[] details = command.trim().split(SPACE_SEPARATOR);
        try {
            switch (details[1]) {
            case "all":
                String expiryAlerts = new AlertExpiringSoonCommand().run();
                String stockAlerts = new AlertLowStockCommand().run();
                return expiryAlerts + "\n" + DIVIDER + stockAlerts;
            case TYPE_EXPIRY:
                return new AlertExpiringSoonCommand().run();
            case TYPE_STOCK:
                return new AlertLowStockCommand().run();
            default:
                throw new SitusException(INVALID_ALERT_TYPE_MESSAGE);
            }
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new SitusException(INCORRECT_PARAMETERS_MESSAGE);
        }
    }

    /**
     * Parses and executes the {@code set} command.
     *
     * @param command The user's input string
     * @return threshold successfully set message
     * @throws SitusException if threshold type, date format or amount format is incorrect
     */
    private static String parseSetCommand(String command) throws SitusException {
        String[] details = command.trim().split(SPACE_SEPARATOR, 3);
        if (details.length < 3) {
            throw new SitusException(INCORRECT_PARAMETERS_MESSAGE);
        }

        assert (details.length == SET_COMMAND_ARGUMENT_COUNT);

        try {
            String newThreshold = details[2].trim();
            switch (details[1].trim()) {
            case TYPE_EXPIRY:
                long newExpiryThreshold = Long.parseLong(newThreshold);
                if (newExpiryThreshold <= 0) {
                    throw new SitusException(INVALID_THRESHOLD_MESSAGE);
                }
                if (newExpiryThreshold > MAX_EXPIRY_THRESHOLD) {
                    throw new SitusException(MAX_EXPIRY_THRESHOLD_MESSAGE);
                }
                AlertExpiringSoonCommand.setExpiryThreshold(newExpiryThreshold);
                return "Successfully set expiry threshold to " + newExpiryThreshold + " days";
            case TYPE_STOCK:
                double newStockThreshold = Double.parseDouble(newThreshold);
                if (newStockThreshold <= 0) {
                    throw new SitusException(INVALID_THRESHOLD_MESSAGE);
                }
                if (newStockThreshold > MAX_STOCK_THRESHOLD) {
                    throw new SitusException(MAX_STOCK_THRESHOLD_MESSAGE);
                }
                AlertLowStockCommand.setLowStockThreshold(newStockThreshold);
                return "Successfully set low stock threshold to " + newStockThreshold + " kg";
            default:
                throw new SitusException(INVALID_THRESHOLD_TYPE_MESSAGE);
            }
        } catch (NumberFormatException e) {
            throw new SitusException(NUMBER_FORMAT_ERROR_MESSAGE);
        } catch (IOException e) {
            throw new SitusException(SET_THRESHOLD_ERROR_MESSAGE);
        }
    }
}
