package seedu.duke;

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
            ArrayList<String> sortedIds = clients.getSortedClientCodes();
            ui.showSortedClientById(clients, sortedIds);
            break;
        default:
            break;
        }
    }
}
