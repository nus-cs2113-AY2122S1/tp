package happybit.command;

import happybit.exception.HaBitCommandException;
import happybit.exception.HaBitStorageException;
import happybit.goal.GoalList;
import happybit.storage.Storage;
import happybit.ui.PrintManager;

public class UpdateHabitNameCommand extends UpdateCommand {

    protected int goalIndex;
    protected int habitIndex;
    protected String habitName;

    /**
     * Constructor for UpdateHabitNameCommand.
     *
     * @param goalIndex  Index of goal in goalList, where the newHabitName of a habit of that goal is to be updated.
     * @param habitIndex Index of habit in goal, where the newHabitName is to be updated.
     * @param newHabitName  New name of the habit.
     */
    public UpdateHabitNameCommand(int goalIndex, int habitIndex, String newHabitName) {
        this.goalIndex = goalIndex;
        this.habitIndex = habitIndex;
        this.habitName = newHabitName;
    }

    /**
     * Executes update habit name command and changes the goalName.
     *
     * @param goalList     List that stores all the goals.
     * @param printManager Prints messages to the console.
     * @param storage      Reference to the file where data is stored.
     * @throws HaBitCommandException If the goalIndex and/or habitIndex provided is invalid.
     */
    @Override
    public void runCommand(GoalList goalList, PrintManager printManager, Storage storage) throws HaBitCommandException {
        goalList.updateHabitNameFromGoal(goalIndex, habitIndex, habitName, printManager);

        try {
            storage.export(goalList.getGoalList());
        } catch (HaBitStorageException e) {
            printManager.printError(e.getMessage());
        }
    }

    /**
     * Getter for new habit name.
     *
     * @return Updated habit name.
     */
    public String getNewHabitName() {
        return habitName;
    }
}
