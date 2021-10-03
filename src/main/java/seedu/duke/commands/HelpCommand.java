package seedu.duke.commands;

public class HelpCommand extends Command {
    public HelpCommand() {
    }

    /**
     * Displays all the possible commands that can be used in this program for the user to
     * reference
     *
     * @return CommandResult indicating the end of the commands
     */
    public CommandResult execute() {
        //ui.displayUserGuide();
        return new CommandResult("HelpCommand");
    }
}
