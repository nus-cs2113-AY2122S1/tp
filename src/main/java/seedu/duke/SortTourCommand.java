package seedu.duke;

import java.util.ArrayList;
import java.util.Arrays;

public class SortTourCommand extends Command {
    private final String filter;

    public SortTourCommand(String filter) {
        this.filter = filter;
    }

    @Override
    public void execute() {
        switch (filter) {
        case "/a":
            String[] sortedCodes = new String[tours.getTourCount()];
            for (int i = 0; i < tours.getTourCount(); i++) {
                Tour currTour = tours.getTourByIndex(i);
                sortedCodes[i] = currTour.getCode();
            }
            Arrays.sort(sortedCodes);
            ui.showSortedTour(tours, sortedCodes);
            break;
        case "/p":
            Float[] sortedPrices = new Float[tours.getTourCount()];
            for (int i = 0; i < tours.getTourCount(); i++) {
                Tour currTour = tours.getTourByIndex(i);
                sortedPrices[i] = currTour.getPrice();
            }
            Arrays.sort(sortedPrices);
            ui.showSortedTour(tours, sortedPrices);
            break;
        default:
            break;
        }
    }
}
