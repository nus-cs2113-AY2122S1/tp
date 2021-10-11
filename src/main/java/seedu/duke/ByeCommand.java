package seedu.duke;

public class ByeCommand extends Command {

    @Override
    public void execute(ClientList clientList, Ui ui) {
        ui.showBye();
    }

    @Override
    public boolean isExit() {
        return true;
    }
}
