package seedu.duke.commands.tours;

import seedu.duke.TourPlannerException;
import seedu.duke.commands.Command;

public class FindTourCommand extends Command {
    private final String code;

    public FindTourCommand(String code) {
        this.code = code;
    }

    public void execute() {
        try {
            ui.showFindTour(tours, clientPackages, code);
        } catch (TourPlannerException e) {
            System.out.println(e.getMessage());
        }
    }
}
