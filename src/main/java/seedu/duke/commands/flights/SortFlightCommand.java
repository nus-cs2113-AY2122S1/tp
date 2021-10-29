package seedu.duke.commands.flights;

import seedu.duke.TourPlannerException;
import seedu.duke.commands.Command;

import java.util.ArrayList;

/**
 * Print a sorted list of flights, with the method of sorting specified by a filter.
 * Sorts departure time and return time of flight by time proximity.
 * Sorts flight ID by alphabetical order.
 */
public class SortFlightCommand extends Command {
    private final String filter;
    public static final String ERROR_MISSING_FILTER = "Missing filter! Sort flights with the format: \n"
            + "sort -f /d (sort by departure date and time) \n"
            + "sort -f /r (sort by return date and time) \n"
            + "sort -f /id (sort by id alphabetically)";

    /**
     * Class constructor for SortFlightCommand.
     * Defines filter input by user.
     *
     * @param filter the user input filter that specifies how the flights should be sorted
     */
    public SortFlightCommand(String filter) {
        this.filter = filter;
    }

    /**
     * Executes the printing of the sorted flight list, specified by the filter.
     * If filter given is /d, sorts the departure times (by natural order of time).
     * If filter given is /r, sorts the return times (by natural order of time).
     * If filter given is /id, sorts the flights by id (in alphabetical order).
     */
    @Override
    public void execute() {
        try {
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
                throw new TourPlannerException(ERROR_MISSING_FILTER);
            }
        } catch (TourPlannerException e) {
            System.out.println(e.getMessage());
        }
    }
}
