package seedu.duke.commands.tours;

import seedu.duke.commands.Command;

public class ListTourCommand extends Command {

    public void execute() {
        ui.showListTour(tours);
    }
}
