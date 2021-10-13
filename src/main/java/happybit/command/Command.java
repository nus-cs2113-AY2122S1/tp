package happybit.command;

import happybit.exception.HBCommandException;
import happybit.goal.GoalList;
import happybit.storage.Storage;
import happybit.ui.Ui;

public abstract class Command {

    /**
     * Abstract method that runs user input commands.
     *
     * @param goalList List that stores all the tasks.
     * @param ui       User interface of duke.
     * @param storage  Reference to the file where data is stored.
     * @throws HBCommandException If exception is thrown by Command subclasses.
     */
    public abstract void runCommand(GoalList goalList, Ui ui, Storage storage) throws HBCommandException;

    /**
     * Checks if the exit command is being called.
     *
     * @return True if Command subclass is exitCommand.
     *         False if other Command subclasses.
     */
    public boolean isExit() {
        return false;
    }

}
