package seedu.duke;

public class ListFlightCommand extends Command{
    public void execute() {
        ui.showListFlight(flights);
    }
}
