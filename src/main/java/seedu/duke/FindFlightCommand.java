package seedu.duke;

public class FindFlightCommand extends Command {
    private final String code;

    public FindFlightCommand(String code) {
        this.code = code;
    }

    public void execute() {
        ui.showFindFlight(flights, clientPackages, code);
    }
}