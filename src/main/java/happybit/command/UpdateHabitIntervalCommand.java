//package happybit.command;
//
//import happybit.exception.HaBitCommandException;
//import happybit.exception.HaBitStorageException;
//import happybit.goal.GoalList;
//import happybit.storage.Storage;
//import happybit.ui.PrintManager;
//
//public class UpdateHabitIntervalCommand extends UpdateCommand {
//    protected int goalIndex;
//    protected int habitIndex;
//    protected int newInterval;
//
//    /**
//     * Constructor for UpdateHabitIntervalCommand.
//     *
//     * @param goalIndex Index of goal in goalList where habit to be updated is located in.
//     * @param habitIndex Index of habit in habitList of goal.
//     * @param newInterval newInterval to be changed to.
//     */
//    public UpdateHabitIntervalCommand(int goalIndex, int habitIndex, int newInterval) {
//        this.goalIndex = goalIndex;
//        this.habitIndex = habitIndex;
//        this.newInterval = newInterval;
//    }
//
//    @Override
//    public void runCommand(GoalList goalList, PrintManager printManager, Storage storage)
//          throws HaBitCommandException {
//        goalList.updateHabitIntervalFromGoal(goalIndex, habitIndex, newInterval, printManager);
//
//        try {
//            storage.export(goalList.getGoalList());
//        } catch (HaBitStorageException e) {
//            printManager.printError(e.getMessage());
//        }
//    }
//
//    public int getGoalIndex() {
//        return goalIndex;
//    }
//
//    public int getHabitIndex() {
//        return habitIndex;
//    }
//
//    /**
//     * Getter for new interval.
//     *
//     * @return Updated interval.
//     */
//    public int getNewInterval() {
//        return newInterval;
//    }
//}
