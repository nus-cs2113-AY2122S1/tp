package seedu.duke.commands;

public class HelpCommand extends Command {
    public HelpCommand() {
    }

    public CommandResult execute() {
        //ui.displayUserGuide();
        return new CommandResult("HelpCommand");
    }
}
