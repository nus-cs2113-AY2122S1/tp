package seedu.duke;

public abstract class FindTourCommand extends Command{
    private final String code;

    public FindTourCommand(String code) {
        this.code = code;
    }

    public void execute() {
        ui.showFindTour(tours, code);
    }
}
