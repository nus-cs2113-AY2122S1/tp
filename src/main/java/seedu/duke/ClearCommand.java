package seedu.duke;

public class ClearCommand extends Command {
    public void execute(ClientList clientList, Ui ui) {
        clientList.clearAllClients();
        assert clientList.getClientCount() == 0;
        ui.showClear();
    }

    public boolean isExit() {
        return false;
    }
}
