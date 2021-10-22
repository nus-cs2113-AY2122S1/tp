package seedu.duke;

public abstract class FindTourCommand extends TourCommand{
    private final String code;

    public FindTourCommand(String code) {
        this.code = code;
    }

    public void execute(TourList tours, Ui ui) {
        ui.showFindTour(tours, code);
    }
}
