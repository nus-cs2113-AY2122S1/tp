package seedu.duke;

public class AddTourCommand extends Command {
    private final Tour tour;

    public AddTourCommand(Tour tour) {
        this.tour = tour;
    }

    @Override
    public void execute() {
        tours.add(tour);
        ui.showAddTour(tour);
    }
}
