package seedu.duke.commands.tours;

import seedu.duke.TourPlannerException;
import seedu.duke.commands.Command;

import java.util.ArrayList;

/**
 * Print a sorted list of tours, with the method of sorting specified by a filter.
 * Sorts tour prices by ascending order.
 * Sorts tour ID by alphabetical order.
 */
public class SortTourCommand extends Command {
    private final String filter;
    public static final String ERROR_MISSING_FILTER = "Missing filter! Sort tours with the format: \n"
            + "sort -t /id (sort by ID alphabetically) \n"
            + "sort -t /p (sort by increasing price)";

    /**
     * Class constructor for SortTourCommand.
     * Defines filter input by user.
     *
     * @param filter the user input filter that specifies how the tours should be sorted
     */
    public SortTourCommand(String filter) {
        this.filter = filter;
    }

    /**
     * Executes the printing of the sorted flight list, specified by the filter.
     * If filter given is /id, sorts the tours by id (in alphabetical order).
     * If filter given is /r, sorts the tours by price (in ascending order).
     */
    @Override
    public void execute() {
        try {
            switch (filter) {
            case "/id":
                ArrayList<String> sortedCodes = tours.getSortedTourIds();
                ui.showSortedTourById(tours, sortedCodes);
                break;
            case "/p":
                ArrayList<Float> sortedPrices = tours.getSortedTourPrices();
                ui.showSortedTourByPrice(tours, sortedPrices);
                break;
            default:
                throw new TourPlannerException(ERROR_MISSING_FILTER);
            }
        } catch (TourPlannerException e) {
            System.out.println(e.getMessage());
        }
    }
}
