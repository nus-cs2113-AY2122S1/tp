package seedu.duke.commands.flights;

import seedu.duke.TourPlannerException;
import seedu.duke.commands.Command;

import java.util.ArrayList;

public class SortFlightCommand extends Command {
    private final String filter;

    public SortFlightCommand(String filter) {
        this.filter = filter;
    }

    @Override
    public void execute() throws TourPlannerException {
        switch (filter) {
        case "/d":
            ArrayList<String> sortedFlightByDepartureDates = flights.getSortedDepartDates();
            ui.showSortedFlightByDeparture(flights, sortedFlightByDepartureDates);
            break;
        case "/r":
            ArrayList<String> sortedFlightByReturnDates = flights.getSortedReturnDates();
            ui.showSortedFlightByReturn(flights, sortedFlightByReturnDates);
            break;
        case "/id":
            ArrayList<String> sortedIds = flights.getSortedFlightIds();
            ui.showSortedFlightById(flights, sortedIds);
            break;
        default:
            break;
        }
    }
}
