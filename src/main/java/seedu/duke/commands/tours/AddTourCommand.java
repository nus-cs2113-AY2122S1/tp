package seedu.duke.commands.tours;

import seedu.duke.TourPlannerException;
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
     * Executes the addition of tour to tour list.
     * If given tour code already exists, the tour will not be added.
     */
    @Override
    public void execute() {
        int newTourCount = tours.getTourCount() + 1;
        try {
            Tour existingTour = tours.getTourByCode(tour.getCode());
            System.out.println("Tour code already exists. Please try another tour code.");
        } catch (TourPlannerException e) {
            tours.add(tour);
            ui.showAdd(tour);
        }
        assert newTourCount == tours.getTourCount();
    }

}
