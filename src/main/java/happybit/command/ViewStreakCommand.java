package happybit.command;

public class ViewStreakCommand extends Command {
    protected int goalIndex;
    protected int habitIndex;

    /**
     * Constructor of ViewStreakCommand.
     *
     * @param goalIndex Index of goal in goalList
     * @param habitIndex Index of habit in habitList of specified goal, where
     */
    public ViewStreakCommand(int goalIndex, int habitIndex) {
        this.goalIndex = goalIndex;
        this.habitIndex = habitIndex;
    }
}
