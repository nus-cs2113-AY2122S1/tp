package seedu.duke;


//public class AddTourCommand extends TourCommand {

public class AddTourCommand extends Command {

    private final Tour tour;

    public AddTourCommand(Tour tour) {
        this.tour = tour;
    }

    @Override
    public void execute() {
        int newTourCount = tours.getTourCount() + 1;
        tours.add(tour, ui);
        assert newTourCount == tours.getTourCount();
    }
}
