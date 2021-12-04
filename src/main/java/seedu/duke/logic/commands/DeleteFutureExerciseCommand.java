package seedu.duke.logic.commands;

import seedu.duke.data.item.Item;

import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Represents the command that when executed, deletes an Exercise item from the Future ExerciseList.
 */
public class DeleteFutureExerciseCommand extends Command {
    public static final String MESSAGE_COMMAND_FORMAT = CommandMessages.QUOTATION + COMMAND_WORD_DELETE
            + " " + COMMAND_PREFIX_UPCOMING_EXERCISE + COMMAND_PREFIX_DELIMITER + "X" + CommandMessages.QUOTATION
            + ", where X is the item number in the future exercise list";
    public static final String MESSAGE_SUCCESS = "An exercise item for the future has been deleted:"
            + CommandMessages.INDENTED_LS + "%s";
    public static final String MESSAGE_ITEMS_LEFT =
            CommandMessages.LS + "Number of upcoming exercise(s) left: %d";
    private static final String MESSAGE_FUTURE_EXERCISE_CLEAR = "All upcoming exercise items have been removed.";
    public static final String[] EXPECTED_PREFIXES = {COMMAND_PREFIX_UPCOMING_EXERCISE};
    public static final String MESSAGE_REMOVED_MULTIPLE_UPCOMING_EXERCISES = "All of the following upcoming exercises "
            + "have been deleted:";


    private static Logger logger = Logger.getLogger(DeleteFutureExerciseCommand.class.getName());

    private boolean isClear = false;
    private ArrayList<Integer> itemIndexArray;


    public DeleteFutureExerciseCommand(boolean isClear) {
        this.isClear = isClear;
    }

    public DeleteFutureExerciseCommand(ArrayList<Integer> itemIndexArray) {
        this.itemIndexArray = itemIndexArray;
    }

    @Override
    public CommandResult execute() {
        if (super.futureExerciseItems.getSize() == 0) {
            logger.log(Level.FINE, "Future exercise list is empty.");
            return new CommandResult(CommandMessages.MESSAGE_EMPTY_FUTURE_EXERCISE_LIST);
        }
        if (this.isClear) {
            logger.log(Level.FINE, "Clearing future exercise list");
            super.futureExerciseItems.clearList();
            return new CommandResult(MESSAGE_FUTURE_EXERCISE_CLEAR);
        }

        logger.log(Level.FINE, "Trying to delete item now");
        try {
            if ((itemIndexArray.size() == 1)) {
                Item item = super.futureExerciseItems.deleteItem(itemIndexArray.get(0));
                return new CommandResult(
                        String.format(MESSAGE_SUCCESS, item.toStringWithDate())
                                + String.format(MESSAGE_ITEMS_LEFT, super.futureExerciseItems.getSize()));
            }
            if ((itemIndexArray.stream()
                    .filter(number -> number > super.futureExerciseItems.getSize() - 1).count()) > 0) {
                if (super.futureExerciseItems.getSize() == 1) {
                    return new CommandResult(CommandMessages.MESSAGE_ONLY_ONE_IN_LIST);
                }
                return new CommandResult(String.format(CommandMessages.MESSAGE_LIST_OUT_OF_BOUNDS,
                        super.futureExerciseItems.getSize()));
            } else {
                String listOfDeletedFutureExercises = super.futureExerciseItems
                        .deleteMultipleItems(this.itemIndexArray);
                return new CommandResult(MESSAGE_REMOVED_MULTIPLE_UPCOMING_EXERCISES
                        + listOfDeletedFutureExercises
                        + String.format(MESSAGE_ITEMS_LEFT, super.futureExerciseItems.getSize()));
            }
        } catch (IndexOutOfBoundsException e) {
            logger.log(Level.FINE, "Detected invalid exercise item index.");
            if (super.futureExerciseItems.getSize() == 1) {
                return new CommandResult(CommandMessages.MESSAGE_ONLY_ONE_IN_LIST);
            }
            return new CommandResult(String.format(CommandMessages.MESSAGE_LIST_OUT_OF_BOUNDS,
                    super.futureExerciseItems.getSize()));
        }
    }
}
