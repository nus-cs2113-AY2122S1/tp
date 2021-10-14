package seedu.duke;

public class ClearCommand extends Command {

    public void execute(ClientList clientList, Ui ui) {
        try {
            clientList.clearAllClients();
            assert clientList.getClientCount() == 0;
            ui.showClear();
        } catch (TourPlannerException e) {
            ui.show(e.getMessage());
        }
    }

    public boolean isExit() {
        return false;
    }
}
