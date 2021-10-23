package seedu.duke;

public class FindClientCommand extends Command {
    private final String name;

    public FindClientCommand(String name) {
        this.name = name;
    }

    public void execute() {
        ui.showFindClient(clients, name);
    }
}
