package seedu.duke.logic.parser;

import seedu.duke.logic.commands.Command;
import seedu.duke.logic.commands.CommandMessages;
import seedu.duke.logic.commands.DeleteExerciseBankCommand;
import seedu.duke.logic.commands.DeleteExerciseCommand;
import seedu.duke.logic.commands.DeleteFoodBankCommand;
import seedu.duke.logic.commands.DeleteFoodCommand;
import seedu.duke.logic.commands.DeleteFutureExerciseCommand;
import seedu.duke.logic.commands.InvalidCommand;
import seedu.duke.logic.parser.exceptions.ExtraParamException;
import seedu.duke.logic.parser.exceptions.ItemNotSpecifiedException;
import seedu.duke.logic.parser.exceptions.MissingParamException;
import seedu.duke.logic.parser.exceptions.ParserException;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Parses input arguments for Delete commands.
 */
public class DeleteCommandParser implements Parser {

    protected static final Logger logger = Logger.getLogger(DeleteCommandParser.class.getName());

    @Override
    public Command parse(String params) {
        try {
            final String itemTypePrefix = ParserUtils.extractItemTypePrefix(params);
            switch (itemTypePrefix) {
            case Command.COMMAND_PREFIX_EXERCISE:
                return parseDeleteFromExercise(params, itemTypePrefix);
            case Command.COMMAND_PREFIX_FOOD:
                return parseDeleteFromFood(params, itemTypePrefix);
            case Command.COMMAND_PREFIX_UPCOMING_EXERCISE:
                return parseDeleteFromFuture(params, itemTypePrefix);
            case Command.COMMAND_PREFIX_EXERCISE_BANK:
                return parseDeleteFromExerciseBank(params, itemTypePrefix);
            case Command.COMMAND_PREFIX_FOOD_BANK:
                return parseDeleteFromFoodBank(params, itemTypePrefix);
            default:
                throw new ItemNotSpecifiedException();
            }
        } catch (ItemNotSpecifiedException e) {
            return new InvalidCommand(CommandMessages.MESSAGE_DELETE_COMMAND_INVALID_FORMAT);
        }
    }

    protected Command parseDeleteFromExercise(String params, String itemTypePrefix) {
        try {
            if (ParserUtils.hasExtraDelimiters(params, DeleteExerciseCommand.EXPECTED_PREFIXES)) {
                return new InvalidCommand(ParserMessages.MESSAGE_ERROR_TOO_MANY_DELIMITERS);
            }
            boolean isClear = extractIsClear(params, itemTypePrefix);
            if (isClear) {
                return new DeleteExerciseCommand(true);
            }
            final int itemIndex = ParserUtils.extractItemIndex(params, itemTypePrefix);
            final LocalDate date = ParserUtils.extractDate(params, true);
            logger.log(Level.FINE, String.format("date detected is: %s", date));

            logger.log(Level.FINE, String.format("deleting exercise item %s from %s", itemIndex, date));
            return new DeleteExerciseCommand(itemIndex, date);
        } catch (ParserException e) {
            return new InvalidCommand(e.getMessage());
        }
    }

    protected Command parseDeleteFromFood(String params, String itemTypePrefix) {
        try {
            if (ParserUtils.hasExtraDelimiters(params, DeleteFoodCommand.EXPECTED_PREFIXES)) {
                return new InvalidCommand(ParserMessages.MESSAGE_ERROR_TOO_MANY_DELIMITERS);
            }
            boolean isClear = extractIsClear(params, itemTypePrefix);
            if (isClear) {
                return new DeleteFoodCommand(true);
            }
            final int itemIndex = ParserUtils.extractItemIndex(params, itemTypePrefix);
            final LocalDate date = ParserUtils.extractDate(params, true);
            logger.log(Level.FINE, String.format("date detected is: %s", date));


            final LocalTime time = ParserUtils.extractTime(params, true);
            logger.log(Level.FINE, String.format("deleting food item %s from %s %s", itemIndex, date, time));
            return new DeleteFoodCommand(itemIndex, date, time);
        } catch (ParserException e) {
            return new InvalidCommand(e.getMessage());
        }
    }

    protected Command parseDeleteFromExerciseBank(String params, String itemTypePrefix) {
        try {
            if (ParserUtils.hasExtraDelimiters(params, DeleteExerciseBankCommand.EXPECTED_PREFIXES)) {
                return new InvalidCommand(ParserMessages.MESSAGE_ERROR_TOO_MANY_DELIMITERS);
            }
            boolean isClear = extractIsClear(params, itemTypePrefix);
            if (isClear) {
                return new DeleteExerciseBankCommand(true);
            }
            final ArrayList<Integer> itemIndexes = extractItemIndexes(params, itemTypePrefix);
            return new DeleteExerciseBankCommand(itemIndexes);
        } catch (ParserException e) {
            return new InvalidCommand(e.getMessage());
        }
    }

    protected Command parseDeleteFromFoodBank(String params, String itemTypePrefix) {
        try {
            if (ParserUtils.hasExtraDelimiters(params, DeleteFoodBankCommand.EXPECTED_PREFIXES)) {
                return new InvalidCommand(ParserMessages.MESSAGE_ERROR_TOO_MANY_DELIMITERS);
            }
            boolean isClear = extractIsClear(params, itemTypePrefix);
            if (isClear) {
                return new DeleteFoodBankCommand(true);
            }
            final ArrayList<Integer> itemIndexes = extractItemIndexes(params, itemTypePrefix);
            return new DeleteFoodBankCommand(itemIndexes);

        } catch (ParserException e) {
            return new InvalidCommand(e.getMessage());
        }
    }

    protected Command parseDeleteFromFuture(String params, String itemTypePrefix) {
        try {
            if (ParserUtils.hasExtraDelimiters(params, DeleteFutureExerciseCommand.EXPECTED_PREFIXES)) {
                return new InvalidCommand(ParserMessages.MESSAGE_ERROR_TOO_MANY_DELIMITERS);
            }
            boolean isClear = extractIsClear(params, itemTypePrefix);
            if (isClear) {
                return new DeleteFutureExerciseCommand(true);
            }
            final ArrayList<Integer> itemIndexes = extractItemIndexes(params, itemTypePrefix);

            return new DeleteFutureExerciseCommand(itemIndexes);
        } catch (ParserException e) {
            return new InvalidCommand(e.getMessage());
        }
    }

    protected static ArrayList<Integer> extractItemIndexes(String params, String prefix)
            throws ParserException {
        try {
            String numberString = ParserUtils.extractRelevantParameter(params, prefix);
            String[] numberStringArray = numberString.split(Command.COMMAND_INDEX_DELIMITER);
            ArrayList<Integer> indices = new ArrayList<>();
            for (int i = 0; i < numberStringArray.length; i++) {
                String indexString = numberStringArray[i].trim();
                if (indexString.split(" ").length > 1) {
                    throw new ParserException(ParserMessages.MESSAGE_ERROR_EXTRA_PARAMETERS);
                }
                Integer index = (Double.parseDouble(indexString) > Integer.MAX_VALUE)
                        ? Integer.MAX_VALUE
                        : ParserUtils.convertItemNumToItemIndex(Integer.parseInt(indexString));
                if (indices.contains(index)) {
                    throw new ParserException(ParserMessages.MESSAGE_ERROR_DUPLICATE_NUMBERS);
                }
                if (index < 0) {
                    throw new ParserException(ParserMessages.MESSAGE_ERROR_INVALID_ITEM_NUM);
                }
                indices.add(index);
            }
            logger.log(Level.FINE, String.format("Indexes are %s", indices.toString()));
            return indices;
        } catch (NumberFormatException e) {
            throw new ParserException(ParserMessages.MESSAGE_ERROR_INVALID_ITEM_NUM);
        } catch (MissingParamException e) {
            logger.log(Level.FINE, "Detected empty item num input after prefix but item num is required!");
            throw new ParserException(ParserMessages.MESSAGE_ERROR_NO_ITEM_NUM);
        }
    }

    protected static boolean extractIsClear(String params, String prefix) {
        try {
            String description = ParserUtils.extractRelevantParameterWithoutWhitespace(params, prefix);
            return description.equalsIgnoreCase(Command.COMMAND_WORD_DELETE_ALL);
        } catch (ExtraParamException | MissingParamException e) {
            return false;
        }

    }

}

