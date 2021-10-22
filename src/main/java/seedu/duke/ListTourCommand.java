package seedu.duke;


public class ListTourCommand extends Command {

    public void execute() {
        ui.showListTour(tours);
    }
}
