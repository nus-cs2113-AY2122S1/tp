package seedu.duke.logic.parser;

import seedu.duke.logic.commands.AddExerciseBankCommand;
import seedu.duke.logic.commands.AddExerciseCommand;
import seedu.duke.logic.commands.AddFoodBankCommand;
import seedu.duke.logic.commands.AddFoodCommand;
import seedu.duke.logic.commands.AddFutureExerciseCommand;
import seedu.duke.logic.commands.AddRecurringExerciseCommand;
import seedu.duke.logic.commands.Command;
import seedu.duke.logic.commands.CommandMessages;
import seedu.duke.logic.commands.InvalidCommand;
import seedu.duke.logic.parser.exceptions.ItemNotSpecifiedException;
import seedu.duke.logic.parser.exceptions.MissingParamException;
import seedu.duke.logic.parser.exceptions.ParserException;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Parses input arguments for Add commands.
 */
public class AddCommandParser implements Parser {

    public static final DateTimeFormatter DATE_FORMAT = DateTimeFormatter.ofPattern("dd MMM yyyy");
    protected static final Logger logger = Logger.getLogger(AddCommandParser.class.getName());

    @Override
    public Command parse(String params) {
        try {
            final String itemTypePrefix = ParserUtils.extractItemTypePrefix(params);
            switch (itemTypePrefix) {
            case Command.COMMAND_PREFIX_EXERCISE:
                return parseAddToExercise(params, itemTypePrefix);
            case Command.COMMAND_PREFIX_FOOD:
                return parseAddToFood(params, itemTypePrefix);
            case Command.COMMAND_PREFIX_RECURRING:
                return parseAddRecurring(params, itemTypePrefix);
            case Command.COMMAND_PREFIX_EXERCISE_BANK:
                return parseAddToExerciseBank(params, itemTypePrefix);
            case Command.COMMAND_PREFIX_FOOD_BANK:
                return parseAddToFoodBank(params, itemTypePrefix);
            default:
                throw new ItemNotSpecifiedException();
            }
        } catch (ItemNotSpecifiedException e) {
            return new InvalidCommand(CommandMessages.MESSAGE_ADD_COMMAND_INVALID_FORMAT);
        }
    }

    protected Command parseAddToExercise(String params, String itemTypePrefix) {
        try {
            if (ParserUtils.hasExtraDelimiters(params, AddExerciseCommand.EXPECTED_PREFIXES)) {
                return new InvalidCommand(ParserMessages.MESSAGE_ERROR_TOO_MANY_DELIMITERS);
            }
            final String description = ParserUtils.extractItemDescription(params, itemTypePrefix);
            final Integer calories = ParserUtils.extractItemCalories(params);
            final LocalDate date = ParserUtils.extractDate(params, false);
            logger.log(Level.FINE, String.format("date detected is: %s", date));
            if (ParserUtils.isSevenDaysBeforeToday(date)) {
                return new InvalidCommand(String.format(ParserMessages.MESSAGE_ERROR_ITEM_DATE_TOO_OLD,
                        LocalDate.now().minusDays(6).format(DATE_FORMAT),
                        LocalDate.now().format(DATE_FORMAT)));
            }
            if (ParserUtils.isFutureDate(date)) {
                logger.log(Level.FINE, String.format("adding to future list"));
                return new AddFutureExerciseCommand(description, calories, date);
            }
            return new AddExerciseCommand(description, calories, date);
        } catch (ParserException e) {
            return new InvalidCommand(e.getMessage());
        }
    }


    protected Command parseAddToFood(String params, String itemTypePrefix) {
        try {
            if (ParserUtils.hasExtraDelimiters(params, AddFoodCommand.EXPECTED_PREFIXES)) {
                return new InvalidCommand(ParserMessages.MESSAGE_ERROR_TOO_MANY_DELIMITERS);
            }
            final String description = ParserUtils.extractItemDescription(params, itemTypePrefix);
            final Integer calories = ParserUtils.extractItemCalories(params);
            final LocalDateTime dateTime = ParserUtils.extractDateTime(params);
            logger.log(Level.FINE, String.format("dateTime detected is: %s", dateTime));
            if (!ParserUtils.isWithinSevenDaysFromToday(dateTime.toLocalDate())) {
                return new InvalidCommand(String.format(ParserMessages.MESSAGE_ERROR_ITEM_DATE_TOO_OLD,
                        LocalDate.now().minusDays(6).format(DATE_FORMAT),
                        LocalDate.now().format(DATE_FORMAT)));
            }
            return new AddFoodCommand(description, calories, dateTime);
        } catch (ParserException e) {
            return new InvalidCommand(e.getMessage());
        }
    }

    protected Command parseAddRecurring(String params, String itemTypePrefix) {
        try {
            if (ParserUtils.hasExtraDelimiters(params, AddRecurringExerciseCommand.EXPECTED_PREFIXES)) {
                return new InvalidCommand(ParserMessages.MESSAGE_ERROR_TOO_MANY_DELIMITERS);
            }
            final String description = ParserUtils.extractItemDescription(params, itemTypePrefix);
            final Integer calories = ParserUtils.extractItemCalories(params);
            final LocalDate startDate = extractStartDate(params);
            final LocalDate endDate = extractEndDate(params);
            final ArrayList<Integer> dayOfTheWeek = extractDayOfTheWeek(params);
            return new AddRecurringExerciseCommand(description, calories,
                    startDate, endDate, dayOfTheWeek);
        } catch (ParserException e) {
            return new InvalidCommand(e.getMessage());
        }
    }


    protected Command parseAddToExerciseBank(String params, String itemTypePrefix) {
        try {
            if (ParserUtils.hasExtraDelimiters(params, AddExerciseBankCommand.EXPECTED_PREFIXES)) {
                return new InvalidCommand(ParserMessages.MESSAGE_ERROR_TOO_MANY_DELIMITERS);
            }
            final String description = ParserUtils.extractItemDescription(params, itemTypePrefix);
            final Integer calories = ParserUtils.extractItemCalories(params);
            if (calories == null) {
                return new InvalidCommand(ParserMessages.MESSAGE_ERROR_NO_CALORIES_INFO);
            }
            return new AddExerciseBankCommand(description, calories);
        } catch (ParserException e) {
            return new InvalidCommand(e.getMessage());
        }
    }

    protected Command parseAddToFoodBank(String params, String itemTypePrefix) {
        try {
            if (ParserUtils.hasExtraDelimiters(params, AddFoodBankCommand.EXPECTED_PREFIXES)) {
                return new InvalidCommand(ParserMessages.MESSAGE_ERROR_TOO_MANY_DELIMITERS);
            }
            final String description = ParserUtils.extractItemDescription(params, itemTypePrefix);
            final Integer calories = ParserUtils.extractItemCalories(params);
            if (calories == null) {
                return new InvalidCommand(ParserMessages.MESSAGE_ERROR_NO_CALORIES_INFO);
            }
            return new AddFoodBankCommand(description, calories);
        } catch (ParserException e) {
            return new InvalidCommand(e.getMessage());
        }
    }

    //@@author xingjie99
    protected static LocalDate extractStartDate(String params)
            throws ParserException {
        LocalDate startDate = ParserUtils.extractGeneralDate(params, Command.COMMAND_PREFIX_START_DATE);
        if (startDate == null) {
            logger.log(Level.FINE, "Detected empty start date input after prefix but date is required!");
            throw new ParserException(ParserMessages.MESSAGE_ERROR_NO_START_DATE);
        }
        return startDate;
    }

    protected static LocalDate extractEndDate(String params)
            throws ParserException {
        LocalDate endDate = ParserUtils.extractGeneralDate(params, Command.COMMAND_PREFIX_END_DATE);
        if (endDate == null) {
            logger.log(Level.FINE, "Detected empty end date input after prefix but date is required!");
            throw new ParserException(ParserMessages.MESSAGE_ERROR_NO_END_DATE);
        }
        return endDate;
    }

    protected static ArrayList<Integer> extractDayOfTheWeek(String params)
            throws ParserException {
        try {
            String numberString = ParserUtils.extractRelevantParameter(params, Command.COMMAND_PREFIX_DAY_OF_THE_WEEK);
            String[] numberStringArray = numberString.split(Command.COMMAND_INDEX_DELIMITER);
            ArrayList<Integer> daysOfTheWeek = new ArrayList<>();
            for (int i = 0; i < numberStringArray.length; i++) {
                String dayString = numberStringArray[i].trim();
                if (dayString.split(" ").length > 1) {
                    throw new ParserException(ParserMessages.MESSAGE_ERROR_EXTRA_PARAMETERS);
                }
                Integer day = Integer.parseInt(numberStringArray[i].trim());
                if (day < ParserMessages.MONDAY || day > ParserMessages.SUNDAY) {
                    throw new ParserException(ParserMessages.MESSAGE_ERROR_INVALID_DAY_OF_THE_WEEK);
                }
                if (daysOfTheWeek.contains(day)) {
                    throw new ParserException(ParserMessages.MESSAGE_ERROR_REPEATED_DAY_OF_THE_WEEK);
                }
                daysOfTheWeek.add(day);
            }
            logger.log(Level.FINE, String.format("Days of the week %s", daysOfTheWeek.toString()));
            return daysOfTheWeek;
        } catch (NumberFormatException e) {
            throw new ParserException(ParserMessages.MESSAGE_ERROR_INVALID_DAY_OF_THE_WEEK);
        } catch (MissingParamException e) {
            logger.log(Level.FINE, "Detected empty day input after prefix but day is required!");
            throw new ParserException(ParserMessages.MESSAGE_ERROR_NO_DAY_OF_THE_WEEK);
        }
    }


}
