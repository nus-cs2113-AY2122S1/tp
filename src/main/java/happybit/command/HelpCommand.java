package happybit.command;

import happybit.goal.GoalList;
import happybit.storage.Storage;
import happybit.ui.PrintManager;

public class HelpCommand extends Command {

    /**
     * Executes help command and print out a command list.
     *
     * @param goalList     List that stores all the goals.
     * @param printManager Prints messages to the console.
     * @param storage      Reference to the file where data is stored.
     */
    @Override
    public void runCommand(GoalList goalList, PrintManager printManager, Storage storage) {
        printManager.printCommandList();
    }

}
