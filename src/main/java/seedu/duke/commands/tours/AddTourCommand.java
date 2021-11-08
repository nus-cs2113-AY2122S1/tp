package seedu.duke.commands.tours;

import seedu.duke.commands.Command;
import seedu.duke.data.Tour;

/**
 * Adds a tour into the database.
 */
public class AddTourCommand extends Command {

    private final Tour tour;

    /**
     * Class constructor for AddTourCommand.
     *
     * @param tour tour to be added
     */
    public AddTourCommand(Tour tour) {
        this.tour = tour;
    }

    /**
     * Returns the tour object that was added.
     *
     * @return the added tour object
     */
    public Tour getTour() {
        return tour;
    }

    /**
     * Executes the addition of tour to tour list.
     * If given tour code already exists, the tour will not be added.
     */
    @Override
    public void execute() {
        final int newTourCount = tours.getTourCount() + 1;
        int count = tours.getTourCount();
        for (int i = 0; i < count; i++) {
            Tour currTour = tours.getTourByIndex(i);
            boolean sameId = currTour.getId().equals(tour.getId());
            if (sameId) {
                System.out.println("ERROR: Tour code already exists. Please try another tour code.");
                return;
            }
            boolean sameName = currTour.getName().equals(tour.getName());
            boolean samePrice = currTour.getPrice().equals(tour.getPrice());
            if (sameName && samePrice) {
                System.out.println("ERROR: Tour with same fields already exists with different ID.");
                return;
            }
        }
        tours.add(tour);
        ui.showAdd(tour);
        assert newTourCount == tours.getTourCount();
    }

}
