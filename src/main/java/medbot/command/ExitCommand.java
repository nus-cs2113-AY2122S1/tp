package medbot.command;

import medbot.Ui;
import medbot.list.PersonList;

public class ExitCommand extends Command {

    public boolean isExit() {
        return true;
    }

    @Override
    public void execute(PersonList personList, Ui ui) {
        String exitMessage = ui.getExitMessage();
        ui.printOutput(exitMessage);
    }
}
