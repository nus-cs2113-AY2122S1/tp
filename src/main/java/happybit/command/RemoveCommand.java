package happybit.command;

import happybit.exception.HappyBitException;
import happybit.goal.GoalList;

import java.util.ArrayList;

public class RemoveCommand {

    //Temporary method
    // protected ArrayList<String> goals = new ArrayList<>();

    /*
    public void removeGoal(String goal) {
        goals.remove(goal);
    }
    */
    private int goalIndex;

    public RemoveCommand(int goalIndex) {
        this.goalIndex = goalIndex;
    }

    /**
     * Deletes a goal of a habit.
     */
    public void runCommand() throws IndexOutOfBoundsException {
        GoalList.removeGoal(goalIndex);
    }
}
