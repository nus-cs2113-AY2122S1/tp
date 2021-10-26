package seedu.duke.commands.clients;

import seedu.duke.TourPlannerException;
import seedu.duke.commands.Command;

import java.util.ArrayList;

public class SortClientCommand extends Command {
    private final String filter;

    public SortClientCommand(String filter) {
        this.filter = filter;
    }

    @Override
    public void execute() throws TourPlannerException {
        switch (filter) {
        case "/n":
            ArrayList<String> sortedNames = clients.getSortedClientNames();
            ui.showSortedClientByName(clients, sortedNames);
            break;
        case "/id":
            ArrayList<String> sortedIds = clients.getSortedClientIds();
            ui.showSortedClientById(clients, sortedIds);
            break;
        default:
            break;
        }
    }
}
