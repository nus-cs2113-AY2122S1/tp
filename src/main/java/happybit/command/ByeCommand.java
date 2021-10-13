package happybit.command;

import happybit.exception.HaBitCommandException;
import happybit.goal.GoalList;
import happybit.storage.Storage;
import happybit.ui.Ui;

public class ByeCommand extends Command {

    @Override
    public void runCommand(GoalList goalList, Ui ui, Storage storage) throws HaBitCommandException {
        isExit();
        // do nothing
    }

    @Override
    public boolean isExit() {
        return true;
    }

}
