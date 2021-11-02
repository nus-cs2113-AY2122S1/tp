package happybit.command;

import happybit.exception.HaBitCommandException;
import happybit.exception.HaBitStorageException;
import happybit.goal.GoalList;
import happybit.storage.Storage;
import happybit.ui.PrintManager;

public class DoneHabitCommand extends Command {

    protected int goalIndex;
    protected int habitIndex;

    /**
     * Constructor of DoneHabitCommand.
     *
     * @param goalIndex  Index of goal in goalList, where a habit is to be marked as completed.
     * @param habitIndex Index of habit in goal to be marked as completed.
     */
    public DoneHabitCommand(int goalIndex, int habitIndex) {
        this.goalIndex = goalIndex;
        this.habitIndex = habitIndex;
    }

    /**
     * Executes done habit command and marks a habit as completed.
     *
     * @param goalList     List that stores all the goals.
     * @param printManager Prints messages to the console.
     * @param storage      Reference to the file where data is stored.
     * @throws HaBitCommandException If the goalIndex and/or habitIndex provided is invalid.
     */
    @Override
    public void runCommand(GoalList goalList, PrintManager printManager, Storage storage) throws HaBitCommandException {
        goalList.doneHabitFromGoal(goalIndex, habitIndex, printManager);

        try {
            storage.export(goalList.getGoalList());
        } catch (HaBitStorageException e) {
            printManager.printError(e.getMessage());
        }
    }

    /**
     * Getter for goalIndex (Used in JUnit test).
     *
     * @return Index of goal in goalList, where a habit is to be marked as completed.
     */
    public int getGoalIndex() {
        return goalIndex;
    }

    /**
     * Getter for habitIndex (Used in JUnit test).
     *
     * @return Index of habit in goal to be marked as completed.
     */
    public int getHabitIndex() {
        return habitIndex;
    }

}

