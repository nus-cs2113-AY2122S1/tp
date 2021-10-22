package seedu.duke;

public abstract class TourCommand {
    public abstract void execute(TourList tours, Ui ui);

    public boolean isExit() {
        return false;
    }
}
