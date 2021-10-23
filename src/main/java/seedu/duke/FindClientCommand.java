package seedu.duke;

public class FindClientCommand extends Command{
    private final int index;

    public FindClientCommand(int index) {
        this.index = index;
    }

    public void execute() {
        ui.showFindClient(clients, clientPackages, index);
    }
}
