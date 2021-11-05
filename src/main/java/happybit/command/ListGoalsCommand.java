package happybit.command;

import happybit.exception.HaBitCommandException;
import happybit.goal.GoalList;
import happybit.storage.Storage;
import happybit.ui.PrintManager;

public class ListGoalsCommand extends ListCommand {

    protected String gibberish;

    /**
     * Constructor for ListGoalsCommand.
     *
     * @param gibberish Extra nonsense user types AFTER 'list', a one-word command.
     */
    public ListGoalsCommand(String gibberish) {
        this.gibberish = gibberish;
    }

    /**
     * Executes list goals command and prints out all goals in the goalList.
     *
     * @param goalList     List that stores all the goals.
     * @param printManager Prints messages to the console.
     * @param storage      Reference to the file where data is stored.
     * @throws HaBitCommandException If goalList is empty.
     */
    @Override
    public void runCommand(GoalList goalList, PrintManager printManager, Storage storage) throws HaBitCommandException {
        goalList.listGoals(printManager, gibberish);
    }

}
