package happybit.command;

import happybit.exception.HaBitCommandException;
import happybit.exception.HaBitStorageException;
import happybit.goal.GoalList;
import happybit.goal.GoalType;
import happybit.storage.Storage;
import happybit.ui.PrintManager;

public class UpdateGoalTypeCommand extends UpdateCommand {

    protected int goalIndex;
    protected GoalType goalType;

    /**
     * Constructor for UpdateGoalTypeCommand.
     *  @param goalIndex Index of goal in goalList to be updated with newGoalType.
     * @param newGoalType  New type of the goal.
     */
    public UpdateGoalTypeCommand(int goalIndex, GoalType newGoalType) {
        this.goalIndex = goalIndex;
        this.goalType = newGoalType;
    }

    /**
     * Executes update goal type command and changes the goalType.
     *
     * @param goalList     List that stores all the goals.
     * @param printManager Prints messages to the console.
     * @param storage      Reference to the file where data is stored.
     * @throws HaBitCommandException If the goalIndex provided is invalid.
     */
    @Override
    public void runCommand(GoalList goalList, PrintManager printManager, Storage storage) throws HaBitCommandException {
        goalList.updateGoalType(goalIndex, goalType, printManager);

        try {
            storage.export(goalList.getGoalList());
        } catch (HaBitStorageException e) {
            printManager.printError(e.getMessage());
        }
    }

    /**
     * Getter for goalIndex (Used in JUnit test).
     *
     * @return Index of goal in goalList, whose goalType is to be updated.
     */
    public int getGoalIndex() {
        return goalIndex;
    }

    /**
     * Getter for goalType (Used in JUnit test).
     *
     * @return New goal type to be updated.
     */
    public GoalType getNewGoalType() {
        return goalType;
    }
}
