package seedu.duke.commands.tours;


//public class AddTourCommand extends TourCommand {

import seedu.duke.TourPlannerException;
import seedu.duke.commands.Command;
import seedu.duke.data.Flight;
import seedu.duke.data.Tour;

public class AddTourCommand extends Command {

    private final Tour tour;

    public AddTourCommand(Tour tour) {
        this.tour = tour;
    }

    @Override
    public void execute() {
        int newTourCount = tours.getTourCount() + 1;
        try {
            Tour existingTour = tours.getTourByCode(tour.getCode());
            System.out.println("Tour code already exists. Please try another tour code.");
        } catch (TourPlannerException e) {
            tours.add(tour);
            ui.showAddTour(tour);
        }
        assert newTourCount == tours.getTourCount();
    }
}
