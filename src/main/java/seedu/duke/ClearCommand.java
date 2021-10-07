package seedu.duke;

public class ClearCommand extends Command {
    public void execute(ClientList clientList, Ui ui) {
        clientList.clearAllClients();
        ui.showClear();
    }

    public boolean isExit() {
        return false;
    }
}
