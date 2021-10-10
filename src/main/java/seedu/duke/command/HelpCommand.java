package seedu.duke.command;

import seedu.duke.exception.GetJackDException;
import seedu.duke.lists.WorkoutList;
import seedu.duke.storage.Storage;
import seedu.duke.ui.Ui;

/**
 * Used to display a help message which shows the user the commands to use.
 */
public class HelpCommand extends Command {
    private String commandDescription = null;
    public static final String COMMAND_WORD = "help";

    /**
     * Creates an instance of HelpCommand and sets the help message.
     * The help message is the MESSAGE_USAGE attribute from other Command classes, which instructs the user on how to
     * use the command.
     * If HelpCommand is constructed without a specified description, then executing the command will just print the
     * generic help message.
     * @param descriptionToPrint help message to print
     */
    public HelpCommand(String descriptionToPrint) {
        this.commandDescription = descriptionToPrint;
    }

    public HelpCommand() {
    }

    @Override
    public void executeUserCommand(WorkoutList workouts, Ui ui, Storage storage) throws GetJackDException {
        if (commandDescription == null) {
            Ui.printHelpMessage();
        } else {
            ui.showToUser(commandDescription);
        }
    }
}
