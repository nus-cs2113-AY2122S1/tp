package seedu.duke.logic.commands;

import seedu.duke.data.item.Item;

import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Represents the command that when executed, deletes an Exercise item from the ExerciseBank.
 */
public class DeleteExerciseBankCommand extends Command {
    public static final String MESSAGE_COMMAND_FORMAT = CommandMessages.QUOTATION + COMMAND_WORD_DELETE
            + " " + COMMAND_PREFIX_EXERCISE_BANK + COMMAND_PREFIX_DELIMITER + "X" + CommandMessages.QUOTATION
            + ", where X is the item number in the exercise bank list";
    public static final String MESSAGE_SUCCESS = "An exercise item has been deleted from the exercise bank:"
            + CommandMessages.INDENTED_LS + "%s";
    public static  final String MESSAGE_ITEMS_LEFT =
            CommandMessages.LS + "Number of exercise item(s) left in the exercise bank: %d";
    private static final String MESSAGE_EXERCISE_CLEAR = "All exercise items in the exercise bank have been removed.";
    public static final String[] EXPECTED_PREFIXES = {COMMAND_PREFIX_EXERCISE_BANK};
    private static final String MESSAGE_REMOVED_MULTIPLE_EXERCISE_BANK_ITEM = "All of the following exercise bank "
            + "items have been deleted:";


    private static Logger logger = Logger.getLogger(DeleteExerciseBankCommand.class.getName());

    private boolean isClear = false;
    private ArrayList<Integer> itemIndexArray;

    public DeleteExerciseBankCommand(boolean isClear) {
        this.isClear = isClear;
    }

    public DeleteExerciseBankCommand(ArrayList<Integer> itemIndexArray) {
        this.itemIndexArray = itemIndexArray;
    }

    @Override
    public CommandResult execute() {
        if (super.exerciseBank.getSize() == 0) {
            logger.log(Level.FINE, "Exercise bank is empty.");
            return new CommandResult(CommandMessages.MESSAGE_EMPTY_EXERCISE_BANK);
        }
        if (this.isClear) {
            logger.log(Level.FINE, "Clearing exercise bank");
            super.exerciseBank.clearList();
            return new CommandResult(MESSAGE_EXERCISE_CLEAR);
        }
        logger.log(Level.FINE, "Trying to delete item now");
        try {
            if ((itemIndexArray.size() == 1)) {
                Item item = super.exerciseBank.deleteItem(itemIndexArray.get(0));
                return new CommandResult(
                        String.format(MESSAGE_SUCCESS, item.toStringWithoutDateAndTime())
                                + String.format(MESSAGE_ITEMS_LEFT, super.exerciseBank.getSize()));
            }
            if ((itemIndexArray.stream()
                    .filter(number -> number > super.exerciseBank.getSize() - 1).count()) > 0) {
                if (super.exerciseBank.getSize() == 1) {
                    return new CommandResult(CommandMessages.MESSAGE_ONLY_ONE_IN_LIST);
                }
                return new CommandResult(String.format(
                        CommandMessages.MESSAGE_LIST_OUT_OF_BOUNDS, super.exerciseBank.getSize()));
            } else {
                String listOfDeletedExerciseBank = super.exerciseBank.deleteMultipleItems(this.itemIndexArray);
                return new CommandResult(MESSAGE_REMOVED_MULTIPLE_EXERCISE_BANK_ITEM
                        + listOfDeletedExerciseBank
                        + String.format(MESSAGE_ITEMS_LEFT, super.exerciseBank.getSize()));
            }
        } catch (IndexOutOfBoundsException e) {
            logger.log(Level.FINE, "Detected invalid exercise item index.");
            if (super.exerciseBank.getSize() == 1) {
                return new CommandResult(CommandMessages.MESSAGE_ONLY_ONE_IN_LIST);
            }
            return new CommandResult(String.format(
                    CommandMessages.MESSAGE_LIST_OUT_OF_BOUNDS, super.exerciseBank.getSize()));
        }
    }
}
