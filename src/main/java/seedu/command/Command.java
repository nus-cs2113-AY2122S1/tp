package seedu.command;

public abstract class Command {

    public Command() {

    }

    public abstract void execute();

    public boolean isExit() {
        return this instanceof ExitCommand;
    }
}
