package happybit.command;

import happybit.exception.HaBitCommandException;
import happybit.exception.HaBitStorageException;
import happybit.goal.Goal;
import happybit.goal.GoalList;
import happybit.habit.Habit;
import happybit.storage.Storage;
import happybit.ui.PrintManager;

public class AddHabitCommand extends AddCommand {

    protected Habit habit;
    protected int goalIndex;

    /**
     * Constructor of AddHabitCommand.
     *
     * @param habit     Habit to be added.
     * @param goalIndex Index of goal in goalList where habit is to be added.
     */
    public AddHabitCommand(Habit habit, int goalIndex) {
        this.habit = habit;
        this.goalIndex = goalIndex;
    }

    /**
     * Executes add habit command and adds the habit to the goal.
     *
     * @param goalList     List that stores all the goals.
     * @param printManager Prints messages to the console.
     * @param storage      Reference to the file where data is stored.
     * @throws HaBitCommandException If the goalIndex provided is invalid.
     */
    @Override
    public void runCommand(GoalList goalList, PrintManager printManager, Storage storage) throws HaBitCommandException {
        goalList.addHabitToGoal(this.habit, goalIndex, printManager);

        try {
            storage.export(this.habit, goalIndex);
        } catch (HaBitStorageException e) {
            printManager.showError(e.getMessage());
        }
    }

    /**
     * Getter for Habit (Used in JUnit test).
     *
     * @return New habit to be added to specified goal number.
     */
    public Habit getHabit() {
        return habit;
    }

    /**
     * Getter for goal index (Used in JUnit test).
     *
     * @return Goal index to add new habit to.
     */
    public int getGoalIndex() {
        return goalIndex;
    }
}
