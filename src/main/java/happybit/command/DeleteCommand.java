package happybit.command;

import happybit.exception.HappyBitException;

import java.security.InvalidParameterException;
import java.util.ArrayList;

public class DeleteCommand {

    //Temporary methods
    protected ArrayList<String> habits = new ArrayList<>();

    public void deleteHabit(String habit) {
        habits.remove(habit);
    }


    //Semi-permanent methods

    //protected String habitName;
    //public DeleteCommand(int habitName) {
    //    this.habitName= habitName;
    //}


    /**
     * Deletes a habit from the list.
     *
     * @param habit Name of habit to be deleted.
     */
    //@Override
    public void runCommand(String habit) {
        deleteHabit(habit);
    }
}
