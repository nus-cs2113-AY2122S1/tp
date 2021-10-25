package seedu.duke.commands.tours;

import seedu.duke.TourPlannerException;
import seedu.duke.commands.Command;

import java.util.ArrayList;

public class SortTourCommand extends Command {
    private final String filter;

    public SortTourCommand(String filter) {
        this.filter = filter;
    }

    @Override
    public void execute() {
        try {
            switch (filter) {
            case "/id":
                ArrayList<String> sortedCodes = tours.getSortedTourCodes();
                ui.showSortedTourById(tours, sortedCodes);
                break;
            case "/p":
                ArrayList<Float> sortedPrices = tours.getSortedTourPrices();
                ui.showSortedTourByPrice(tours, sortedPrices);
                break;
            default:
                break;
            }
        } catch (TourPlannerException e) {
            System.out.println(e.getMessage());
        }
    }
}
