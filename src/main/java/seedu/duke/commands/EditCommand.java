package seedu.duke.commands;

import seedu.duke.LibmgrException;
import seedu.duke.data.Catalogue;
import seedu.duke.ui.TextUI;

/**
 * Class encapsulating an edit command
 */
public class EditCommand extends Command {
    public static final String COMMAND_WORD = "edit";
    protected String args;

    /**
     * Sole Constructor.
     *
     * @param args Arguments supplied by user in the edit command
     */
    public EditCommand(String args) {
        this.args = args;
    }
    /**
     * Processes Edit Command, including exceptions.
     *
     * @param ui Object that handles user IO
     * @param catalogue Object that encapsulates the library catalogue
     * @throws LibmgrException when user input is invalid
     */
    public void handlesEditCommand(TextUI ui, Catalogue catalogue) throws LibmgrException {

    }


    /**
     * Executes edit command.
     * Overrides method from parent class.
     *
     * @param ui Object that handles user IO
     * @param catalogue Object that encapsulates the library catalogue
     */
    @Override
    public void execute(TextUI ui, Catalogue catalogue) {
        try {
            handlesEditCommand(ui, catalogue);
        } catch (LibmgrException e) {
            ui.print(e.getMessage());
        }
    }
}
