package seedu.duke.commands.tours;

import seedu.duke.commands.Command;

/**
 * Lists all tours in the database.
 */
public class ListTourCommand extends Command {
    /**
     * Executes the listing of tours to the terminal.
     */
    public void execute() {
        ui.showListTour(tours);
    }
}
