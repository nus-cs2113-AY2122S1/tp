package seedu.duke.commands.tours;

import seedu.duke.TourPlannerException;
import seedu.duke.commands.Command;

/**
 * Finds a tour based on a certain id.
 */
public class FindTourCommand extends Command {
    private final String id;

    /**
     * Class constructor for FindTourCommand.
     *
     * @param id id used to determine which tour to find.
     */
    public FindTourCommand(String id) {
        this.id = id;
    }

    /**
     * Executes the finding of tour, as well as finding said tour's subscribers.
     * If there are no found tours, it will be shown to the user.
     */
    public void execute() {
        try {
            ui.showFindTour(tours, clientPackages, id);
        } catch (TourPlannerException e) {
            System.out.println(e.getMessage());
        }
    }
}
