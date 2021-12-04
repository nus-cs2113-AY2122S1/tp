package seedu.duke.logic.parser;

import seedu.duke.logic.commands.Command;
import seedu.duke.logic.commands.CommandMessages;
import seedu.duke.logic.commands.EditExerciseBankCommand;
import seedu.duke.logic.commands.EditFoodBankCommand;
import seedu.duke.logic.commands.EditFutureExerciseCommand;
import seedu.duke.logic.commands.InvalidCommand;
import seedu.duke.logic.parser.exceptions.ItemNotSpecifiedException;
import seedu.duke.logic.parser.exceptions.ParserException;

import java.time.LocalDate;
import java.util.logging.Logger;

//@@author xingjie99

/**
 * Parses input arguments for Edit commands.
 */
public class EditCommandParser implements Parser {

    protected static final Logger logger = Logger.getLogger(EditCommandParser.class.getName());

    @Override
    public Command parse(String params) {
        try {
            final String itemTypePrefix = ParserUtils.extractItemTypePrefix(params);
            switch (itemTypePrefix) {
            case Command.COMMAND_PREFIX_EXERCISE_BANK:
                return parseEditExerciseBank(params, itemTypePrefix);
            case Command.COMMAND_PREFIX_FOOD_BANK:
                return parseEditFoodBank(params, itemTypePrefix);
            case Command.COMMAND_PREFIX_UPCOMING_EXERCISE:
                return parseEditUpcomingExercise(params, itemTypePrefix);
            default:
                throw new ItemNotSpecifiedException();
            }
        } catch (ItemNotSpecifiedException e) {
            return new InvalidCommand(
                    CommandMessages.MESSAGE_EDIT_COMMAND_INVALID_FORMAT);
        }
    }

    /**
     * Parses input arguments for Edit command for exercise bank.
     *
     * @param params         User input arguments
     * @param itemTypePrefix Prefix of the exercise banks
     * @return Command to execute
     */
    protected Command parseEditExerciseBank(String params, String itemTypePrefix) {
        try {
            if (ParserUtils.hasExtraDelimiters(params, EditExerciseBankCommand.EXPECTED_PREFIXES)) {
                return new InvalidCommand(ParserMessages.MESSAGE_ERROR_TOO_MANY_DELIMITERS);
            }
            final int itemIndex = ParserUtils.extractItemIndex(params, itemTypePrefix);
            if (ParserUtils.getNumberOfCorrectParamsDetected(params,
                    Command.COMMAND_PREFIX_NAME, Command.COMMAND_PREFIX_CALORIES) == 0) {
                return new InvalidCommand(CommandMessages.MESSAGE_EDIT_BANK_NEED_DETAILS);
            }
            final String description = ParserUtils.extractName(params);
            final Integer calories = ParserUtils.extractItemCalories(params);
            return new EditExerciseBankCommand(itemIndex, description, calories);
        } catch (ParserException e) {
            return new InvalidCommand(e.getMessage());
        }
    }

    /**
     * Parses input arguments for Edit command for food bank.
     *
     * @param params         User input arguments
     * @param itemTypePrefix Prefix of food bank
     * @return Command to execute
     */
    protected Command parseEditFoodBank(String params, String itemTypePrefix) {
        try {
            if (ParserUtils.hasExtraDelimiters(params, EditFoodBankCommand.EXPECTED_PREFIXES)) {
                return new InvalidCommand(ParserMessages.MESSAGE_ERROR_TOO_MANY_DELIMITERS);
            }
            final int itemIndex = ParserUtils.extractItemIndex(params, itemTypePrefix);
            if (ParserUtils.getNumberOfCorrectParamsDetected(params,
                    Command.COMMAND_PREFIX_NAME, Command.COMMAND_PREFIX_CALORIES) == 0) {
                return new InvalidCommand(CommandMessages.MESSAGE_EDIT_BANK_NEED_DETAILS);
            }
            final String description = ParserUtils.extractName(params);
            final Integer calories = ParserUtils.extractItemCalories(params);
            return new EditFoodBankCommand(itemIndex, description, calories);
        } catch (ParserException e) {
            return new InvalidCommand(e.getMessage());
        }
    }

    /**
     * Parses input arguments for Edit commands for upcoming exercise.
     *
     * @param params         User input arguments
     * @param itemTypePrefix Prefix of upcoming exercise
     * @return Command to execute
     */
    protected Command parseEditUpcomingExercise(String params, String itemTypePrefix)
            throws ItemNotSpecifiedException {
        try {
            if (ParserUtils.hasExtraDelimiters(params, EditFutureExerciseCommand.EXPECTED_PREFIXES)) {
                return new InvalidCommand(ParserMessages.MESSAGE_ERROR_TOO_MANY_DELIMITERS);
            }
            final int itemIndex = ParserUtils.extractItemIndex(params, itemTypePrefix);
            if (ParserUtils.getNumberOfCorrectParamsDetected(params,
                    Command.COMMAND_PREFIX_NAME, Command.COMMAND_PREFIX_CALORIES, Command.COMMAND_PREFIX_DATE) == 0) {
                return new InvalidCommand(CommandMessages.MESSAGE_EDIT_UPCOMING_EXERCISE_LIST_NEED_DETAILS);
            }
            final String description = ParserUtils.extractName(params);
            final Integer calories = ParserUtils.extractItemCalories(params);
            final LocalDate date = ParserUtils.hasRequiredParams(params, Command.COMMAND_PREFIX_DATE)
                    ? ParserUtils.extractDate(params, false)
                    : null;
            return new EditFutureExerciseCommand(itemIndex, description, calories, date);
        } catch (ParserException e) {
            return new InvalidCommand(e.getMessage());
        }
    }
}
