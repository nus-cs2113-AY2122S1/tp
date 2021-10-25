package medbot.command;

import medbot.Scheduler;
import medbot.ui.Ui;

public class ExitCommand extends Command {

    public boolean isExit() {
        return true;
    }

    @Override
    public void execute(Scheduler scheduler, Ui ui) {
        String exitMessage = Ui.getExitMessage();
        ui.printOutput(exitMessage);
    }
}
