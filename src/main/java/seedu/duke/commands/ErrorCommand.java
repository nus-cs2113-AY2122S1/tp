package seedu.duke.commands;

import seedu.duke.data.Catalogue;
import seedu.duke.ui.TextUI;

//@@author exetr
/**
 * Represents erroneous commands.
 */
public class ErrorCommand extends Command {
    private String message;

    /**
     * Sole Constructor.
     * @param message Details about error
     */
    public ErrorCommand(String message) {
        this.message = message;
    }

    /**
     * Prints out error message.
     * Overrides from parent Command Class.
     * @param ui Object that handles user IO
     * @param catalogue Object that encapsulates the library catalogue
     */
    @Override
    public void execute(TextUI ui, Catalogue catalogue) {
        ui.print(message);
    }
}
