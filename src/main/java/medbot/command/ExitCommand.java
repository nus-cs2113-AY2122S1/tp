package medbot.command;

import medbot.Scheduler;
import medbot.Ui;
import medbot.list.PersonList;

public class ExitCommand extends Command {

    public boolean isExit() {
        return true;
    }

    @Override
    public void execute(Scheduler scheduler, Ui ui) {
        String exitMessage = ui.getExitMessage();
        ui.printOutput(exitMessage);
    }
}
