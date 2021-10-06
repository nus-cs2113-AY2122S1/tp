package seedu.duke.commands;

public class ByeCommand extends Command {
    public static boolean isRunning = true;

    public ByeCommand() {
    }

    public CommandResult execute() {
        isRunning = false;
        return new CommandResult("You will be missed!!");
    }
}
