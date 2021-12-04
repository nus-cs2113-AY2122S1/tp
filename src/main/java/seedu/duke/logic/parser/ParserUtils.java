package seedu.duke.logic.parser;

import seedu.duke.data.profile.utilities.ProfileUtils;
import seedu.duke.logic.commands.Command;
import seedu.duke.logic.commands.CommandMessages;
import seedu.duke.logic.parser.exceptions.ExtraParamException;
import seedu.duke.logic.parser.exceptions.InvalidParamException;
import seedu.duke.logic.parser.exceptions.MissingParamException;
import seedu.duke.logic.parser.exceptions.ParserException;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.YearMonth;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Contains utility methods used by Parser classes to extract relevant parameters.
 */
public class ParserUtils {
    protected static final Logger logger = Logger.getLogger(ParserUtils.class.getName());


    /**
     * Extracts the item type prefix for command words that are common across item types (add, delete, view).
     * Item type must be the first word after the command word. E.g "add f/pot" is valid, "add c/20 f/pot" is not
     *
     * @param params String containing all parameters
     * @return String that is supposed to be the item prefix
     */
    protected static String extractItemTypePrefix(String params) {
        return params.trim().split("/", 2)[0];

    }


    /*================== General methods to extract different data types ==================*/

    /**
     * Extracts only the parameter that is specified by the prefix so that any additional parameter
     * specified behind this string (if any) is removed.
     * E.g. "John Doe w/20" is returned as "John Doe".
     * NOTE: This is why users are not allowed to include the character "/" in their inputs
     * other than to specify a parameter.
     *
     * @throws MissingParamException When expected parameter is missing
     */
    protected static String extractRelevantParameter(String params, String prefix)
            throws MissingParamException {
        try {
            String stringAfterPrefix = params.split(" " + prefix + Command.COMMAND_PREFIX_DELIMITER, 2)[1];
            if (stringAfterPrefix.contains(Command.COMMAND_PREFIX_DELIMITER)) {
                return stringAfterPrefix.substring(0,
                        stringAfterPrefix.indexOf(Command.COMMAND_PREFIX_DELIMITER) - 1).trim();
            }
            return stringAfterPrefix.trim();
        } catch (IndexOutOfBoundsException e) {
            throw new MissingParamException();
        }
    }


    /**
     * Extracts only the parameter required so that any additional parameter
     * specified behind this string (if any) is removed.
     * Specific to parameters where only one word is expected, and any word separated by a whitespace in this
     * string is considered to be extra (and unnecessary).
     * E.g. "f/1" returns "1", and "f/1 hello" throws ExtraParamException
     *
     * @throws MissingParamException When expected parameter is missing
     * @throws ExtraParamException   When extra words are detected for the parameter
     */
    protected static String extractRelevantParameterWithoutWhitespace(String params, String prefix)
            throws MissingParamException, ExtraParamException {
        String[] paramsSplitByWhitespace = extractRelevantParameter(params, prefix).split(" ", 2);
        if (paramsSplitByWhitespace.length == 2) {
            throw new ExtraParamException();
        }
        return paramsSplitByWhitespace[0];

    }

    protected static LocalDate extractGeneralDate(String params, String prefix)
            throws ParserException {
        try {
            String dateString = extractRelevantParameterWithoutWhitespace(params, prefix);
            logger.log(Level.FINE, String.format("date string detected is: %s", dateString));
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern(ParserMessages.DATE_FORMAT);
            LocalDate date = LocalDate.parse(dateString, formatter);
            YearMonth currentMonth = YearMonth.of(date.getYear(), date.getMonth());
            if (Integer.parseInt(dateString.substring(0, dateString.indexOf("-")))
                    > currentMonth.atEndOfMonth().getDayOfMonth()) {
                throw new ParserException(ParserMessages.MESSAGE_ERROR_INVALID_DATE_FORMAT);
            }
            return date;
        } catch (DateTimeParseException e) {
            throw new ParserException(ParserMessages.MESSAGE_ERROR_INVALID_DATE_FORMAT);
        } catch (MissingParamException e) {
            return null;
        } catch (ExtraParamException e) {
            throw new ParserException(e.getMessage());
        }
    }

    protected static LocalTime extractGeneralTime(String params, String prefix)
            throws ParserException {
        try {
            String timeString = extractRelevantParameterWithoutWhitespace(params, Command.COMMAND_PREFIX_TIME);
            logger.log(Level.FINE, String.format("time string detected is: %s", timeString));
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern(ParserMessages.TIME_FORMAT);
            return LocalTime.parse(timeString, formatter);
        } catch (DateTimeParseException e) {
            throw new ParserException(ParserMessages.MESSAGE_ERROR_INVALID_TIME_FORMAT);
        } catch (MissingParamException e) {
            return null;
        } catch (ExtraParamException e) {
            throw new ParserException(e.getMessage());
        }
    }


    protected static Integer extractGeneralInteger(String params, String prefix)
            throws InvalidParamException, ParserException {
        try {
            String intString = extractRelevantParameterWithoutWhitespace(params, prefix);
            if (Double.parseDouble(intString) > Integer.MAX_VALUE) {
                return Integer.MAX_VALUE;
            }
            return Integer.parseInt(intString);
        } catch (NumberFormatException e) {
            throw new InvalidParamException();
        } catch (MissingParamException e) {
            logger.log(Level.FINE, "No integer given, return null");
            return null;
        } catch (ExtraParamException e) {
            throw new ParserException(e.getMessage());
        }
    }

    protected static Double extractGeneralDouble(String params, String prefix)
            throws InvalidParamException, ParserException {
        try {
            String doubleString = extractRelevantParameterWithoutWhitespace(params, prefix);
            return Double.parseDouble(doubleString);
        } catch (NumberFormatException e) {
            throw new InvalidParamException();
        } catch (MissingParamException e) {
            logger.log(Level.FINE, "No double given but not required, return null");
            return null;
        } catch (ExtraParamException e) {
            throw new ParserException(e.getMessage());
        }
    }


    /*===== Methods to extract specific parameters that is needed across all Parser classes ========*/
    /*===== All methods here should throw general ParserException, with specific error messages ====*/
    /*Methods which are specific to the type of Command are found in their respective Parser classes*/

    protected static String extractItemDescription(String params, String prefix)
            throws ParserException {
        try {
            String description = extractRelevantParameter(params, prefix);
            logger.log(Level.FINE, String.format("Item name:%s", description));
            if (description.equals(ParserMessages.EMPTY)) {
                logger.log(Level.FINE, "Detected empty description");
                throw new ParserException(ParserMessages.MESSAGE_ERROR_NO_DESCRIPTION);
            }
            return description;
        } catch (MissingParamException e) {
            logger.log(Level.FINE, String.format("Detected missing command prefix (%s)", prefix));
            throw new ParserException(ParserMessages.MESSAGE_ERROR_NO_DESCRIPTION);
        }
    }

    protected static Integer extractItemCalories(String params)
            throws ParserException {
        try {
            Integer calories = extractGeneralInteger(params, Command.COMMAND_PREFIX_CALORIES);
            return calories;
        } catch (InvalidParamException e) {
            logger.log(Level.FINE, "Detected non-digit calories input");
            throw new ParserException(CommandMessages.MESSAGE_INVALID_CALORIES);
        }
    }

    protected static String extractName(String params) throws ParserException {
        try {
            String name = extractRelevantParameter(params, Command.COMMAND_PREFIX_NAME);
            if (name.equals(ParserMessages.EMPTY)) {
                logger.log(Level.FINE, "Detected empty name input.");
                throw new ParserException(ParserMessages.MESSAGE_ERROR_NAME_EMPTY_STRING);
            }
            return name;
        } catch (MissingParamException e) {
            logger.log(Level.FINE, "Detected missing name prefix, returning null string.");
            return null;
        }
    }

    protected static Double extractHeight(String params) throws ParserException {
        try {
            return extractGeneralDouble(params, Command.COMMAND_PREFIX_HEIGHT);
        } catch (InvalidParamException e) {
            logger.log(Level.FINE, "Detected non-digit height input.");
            throw new ParserException(ProfileUtils.ERROR_HEIGHT);
        }
    }

    protected static Double extractWeight(String params) throws ParserException {
        try {
            return extractGeneralDouble(params, Command.COMMAND_PREFIX_WEIGHT);
        } catch (InvalidParamException e) {
            logger.log(Level.FINE, "Detected non-digit weight input.");
            throw new ParserException(ProfileUtils.ERROR_WEIGHT);
        }
    }

    protected static LocalDate extractDate(String params, boolean isRequired)
            throws ParserException {
        LocalDate localDate = extractGeneralDate(params, Command.COMMAND_PREFIX_DATE);
        if (localDate == null && isRequired) {
            logger.log(Level.FINE, "Detected empty date input after prefix but date is required!");
            throw new ParserException(ParserMessages.MESSAGE_ERROR_NO_DATE);
        }
        if (localDate == null && !isRequired) {
            logger.log(Level.FINE, "Detected empty date input after prefix, assuming date to be now");
            return LocalDate.now();
        }
        return localDate;
    }

    protected static LocalTime extractTime(String params, boolean isRequired)
            throws ParserException {
        LocalTime localTime = extractGeneralTime(params, Command.COMMAND_PREFIX_TIME);
        if (localTime == null && isRequired) {
            logger.log(Level.FINE, "Detected empty time input after prefix but time is required!");
            throw new ParserException(ParserMessages.MESSAGE_ERROR_NO_TIME);
        }
        if (localTime == null && !isRequired) {
            logger.log(Level.FINE, "Detected empty time input after prefix, assuming time to be now");
            return LocalTime.now();
        }
        return localTime;
    }

    protected static LocalDateTime extractDateTime(String params) throws ParserException {
        final LocalTime time = extractTime(params, false);
        final LocalDate date = extractDate(params, false);
        return date.atTime(time);
    }

    protected static int extractItemIndex(String params, String prefix)
            throws ParserException {
        try {
            final Integer itemNum = extractGeneralInteger(params, prefix);
            if (itemNum == null) {
                throw new ParserException(ParserMessages.MESSAGE_ERROR_NO_ITEM_NUM);
            }
            final int itemIndex = convertItemNumToItemIndex(itemNum);
            if (itemIndex < 0) {
                throw new InvalidParamException();
            }
            return itemIndex;
        } catch (InvalidParamException e) {
            throw new ParserException(ParserMessages.MESSAGE_ERROR_INVALID_ITEM_NUM);
        }
    }

    protected static int convertItemNumToItemIndex(int itemNum) {
        return itemNum - 1;
    }

    /*======================== Methods to check data validity ============================*/

    protected static boolean isSevenDaysBeforeToday(LocalDate date) {
        return date.isBefore(LocalDate.now().minusDays(6));
    }

    protected static boolean isWithinSevenDaysFromToday(LocalDate date) {
        return !isSevenDaysBeforeToday(date) && !date.isAfter(LocalDate.now());
    }

    protected static boolean isFutureDate(LocalDate date) {
        return date.isAfter(LocalDate.now());
    }

    protected static boolean hasRequiredParams(String params, String... prefixes) {
        for (String prefix : prefixes) {
            if (!params.toLowerCase().contains(" " + prefix + Command.COMMAND_PREFIX_DELIMITER)) {
                return false;
            }
        }
        return true;
    }

    /**
     * Returns the number of parameters detected that is valid for the specific command.
     * This is required as some parameters are optional, therefore an absolute number cannot be expected.
     *
     * @param params   User input string containing all parameters
     * @param prefixes Variable number of prefixes that is valid for the specific command
     * @return Number of parameters detected that is valid for the specific command
     */
    protected static int getNumberOfCorrectParamsDetected(String params, String... prefixes) {
        int count = 0;
        for (String prefix : prefixes) {
            if (params.toLowerCase().contains(" " + prefix + Command.COMMAND_PREFIX_DELIMITER)) {
                count++;
            }
        }
        logger.log(Level.FINE, String.format("no. of corrected params detected: %s", count));
        return count;
    }

    /**
     * Returns true if there are too many '/' characters in the parameter string.
     *
     * @param params   User input string containing all parameters
     * @param prefixes Variable number of prefixes that is valid for the specific command
     */
    protected static boolean hasExtraDelimiters(String params, String... prefixes) {
        final int expectedNum = getNumberOfCorrectParamsDetected(params, prefixes);
        int numOfDelimiters = 0;
        for (int i = 0; i < params.length(); i++) {
            if (params.charAt(i) == Command.COMMAND_PREFIX_DELIMITER.charAt(0)) {
                numOfDelimiters++;
            }
        }
        logger.log(Level.FINE, String.format("no. of delimiters detected: %s", numOfDelimiters));
        return numOfDelimiters > expectedNum;
    }
}
