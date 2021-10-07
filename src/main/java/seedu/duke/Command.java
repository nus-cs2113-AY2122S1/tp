package seedu.duke;

public abstract class Command {
    public abstract void execute();

    public boolean isExit() {
        return false;
    }
}
