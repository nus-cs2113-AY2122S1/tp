package seedu.duke.logic.parser;

import seedu.duke.logic.commands.Command;
import seedu.duke.logic.commands.CommandMessages;
import seedu.duke.logic.commands.InvalidCommand;
import seedu.duke.logic.commands.ViewExerciseBankCommand;
import seedu.duke.logic.commands.ViewExerciseListCommand;
import seedu.duke.logic.commands.ViewFoodBankCommand;
import seedu.duke.logic.commands.ViewFoodListCommand;
import seedu.duke.logic.commands.ViewFutureExerciseListCommand;
import seedu.duke.logic.parser.exceptions.ItemNotSpecifiedException;

/**
 * Parses input arguments for View commands.
 */
public class ViewCommandParser implements Parser {

    @Override
    public Command parse(String params) {
        try {
            switch (params.trim()) {
            case Command.COMMAND_PREFIX_EXERCISE + Command.COMMAND_PREFIX_DELIMITER:
                return new ViewExerciseListCommand();
            case Command.COMMAND_PREFIX_FOOD + Command.COMMAND_PREFIX_DELIMITER:
                return new ViewFoodListCommand();
            case Command.COMMAND_PREFIX_UPCOMING_EXERCISE + Command.COMMAND_PREFIX_DELIMITER:
                return new ViewFutureExerciseListCommand();
            case Command.COMMAND_PREFIX_EXERCISE_BANK + Command.COMMAND_PREFIX_DELIMITER:
                return new ViewExerciseBankCommand();
            case Command.COMMAND_PREFIX_FOOD_BANK + Command.COMMAND_PREFIX_DELIMITER:
                return new ViewFoodBankCommand();
            default:
                throw new ItemNotSpecifiedException();
            }
        } catch (ItemNotSpecifiedException e) {
            return new InvalidCommand(CommandMessages.MESSAGE_VIEW_COMMAND_INVALID_FORMAT);
        }
    }
}
