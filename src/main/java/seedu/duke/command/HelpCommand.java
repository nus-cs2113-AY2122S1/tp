package seedu.duke.command;

import seedu.duke.lists.WorkoutList;
import seedu.duke.storage.Storage;
import seedu.duke.ui.Ui;

/**
 * To display a help message which shows the user the commands to use.
 */
public class HelpCommand extends Command {
    public static final String COMMAND_WORD = "help";
    private String commandDescription = null;

    public HelpCommand() {
    }

    /**
     * Creates an instance of HelpCommand and sets the help message.
     * The help message is the MESSAGE_USAGE attribute from other Command classes, which instructs the user on how to
     * use the command.
     * If HelpCommand is constructed without a specified description, then executing the command will just print the
     * generic help message.
     *
     * @param descriptionToPrint help message to print
     */
    public HelpCommand(String descriptionToPrint) {
        this.commandDescription = descriptionToPrint;
    }

    /**
     * Executes help command to show user the help message.
     *
     * @param workouts List of Workouts
     * @param ui       User-Interface object
     * @param storage  Storage object
     */
    @Override
    public void executeUserCommand(WorkoutList workouts, Ui ui, Storage storage) {
        if (commandDescription == null) {
            Ui.printHelpMessage();
        } else {
            ui.showToUser(commandDescription);
        }
    }
}
