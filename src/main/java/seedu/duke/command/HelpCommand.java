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
