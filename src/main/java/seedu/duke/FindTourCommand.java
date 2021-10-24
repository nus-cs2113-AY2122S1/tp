package seedu.duke;

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
