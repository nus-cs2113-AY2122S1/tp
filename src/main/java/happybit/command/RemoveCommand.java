package happybit.command;

import java.util.ArrayList;

public class RemoveCommand {

     //Temporary method
    protected ArrayList<String> goals = new ArrayList<>();

    public void removeGoal(String goal) {
        goals.remove(goal);
    }

    //Semi-permanent methods

    //protected String goalName;
    //public DeleteCommand(int goalName) {
    //    this.goalName= goalName;
    //}
    /**
     * Deletes a goal of a habit.
     *
     * @param goal Name of habit to be deleted.
     */
    public void runCommand(String goal) {
        removeGoal(goal);
    }
}
