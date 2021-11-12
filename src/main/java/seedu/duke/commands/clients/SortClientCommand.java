package seedu.duke.commands.clients;

import seedu.duke.TourPlannerException;
import seedu.duke.commands.Command;

import java.util.ArrayList;

/**
 * Print a sorted list of clients, with the method of sorting specified by a filter.
 * Sorts name of client, or ID of client alphabetically.
 */
public class SortClientCommand extends Command {
    public static final String ERROR_MISSING_FILTER = "Missing filter! Sort clients with the format: \n"
            + "sort -c /n (sort by name)\n"
            + "sort -c /id (sort by id)";
    public static final String SORT_NAME_FILTER = "/n";
    public static final String SORT_ID_FILTER = "/id";

    private final String filter;

    /**
     * Class constructor for SortClientCommand.
     * Defines filter input by user.
     *
     * @param filter the user input filter that specifies how the clients should be sorted
     */
    public SortClientCommand(String filter) {
        this.filter = filter;
    }

    /**
     * Executes the printing of the sorted client list, specified by the filter.
     * If filter given is /n, sorts the clients by name (in alphabetical order).
     * If filter given is /id, sorts the clients by ID (in alphabetical order).
     */
    @Override
    public void execute() {
        try {
            switch (filter) {
            case SORT_NAME_FILTER:
                ArrayList<String> sortedNames = clients.getSortedClientNames();
                ui.showSortedClientByName(clients, sortedNames);
                break;
            case SORT_ID_FILTER:
                ArrayList<String> sortedIds = clients.getSortedClientIds();
                ui.showSortedClientById(clients, sortedIds);
                break;
            default:
                throw new TourPlannerException(ERROR_MISSING_FILTER);
            }
        } catch (TourPlannerException e) {
            System.out.println(e.getMessage());
        }
    }
}
