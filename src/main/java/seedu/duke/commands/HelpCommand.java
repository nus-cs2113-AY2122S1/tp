package seedu.duke.commands;

public class HelpCommand extends Command {
    public void execute() {
        ui.showHelp();
    }
}
