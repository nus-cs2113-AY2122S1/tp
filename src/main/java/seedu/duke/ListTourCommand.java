package seedu.duke;

public class ListTourCommand extends TourCommand{

    public void execute(TourList tours, Ui ui) {
        ui.showListTour(tours);
    }
}
