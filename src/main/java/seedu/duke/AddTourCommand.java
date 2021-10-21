package seedu.duke;

public class AddTourCommand extends Command {
    private final Tour tour;

    public AddTourCommand(Tour tour) {
        this.tour = tour;
    }

    @Override
    public void execute(ClientList clients, FlightList flights, TourList tours, Ui ui) {
        tours.add(tour);
    }
}
