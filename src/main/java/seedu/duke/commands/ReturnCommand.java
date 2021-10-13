package seedu.duke.commands;

import seedu.duke.LibmgrException;
import seedu.duke.data.Catalogue;
import seedu.duke.data.Item;
import seedu.duke.ui.TextUI;

import static seedu.duke.common.Messages.NO_ID;
import static seedu.duke.common.Messages.RETURN_SUCCESS;
import static seedu.duke.common.Messages.RM_INVALID_ID;
import static seedu.duke.common.Messages.WRONG_ITEM_MESSAGE;

/**
 * Class encapsulating command to update the status of the item to be returned.
 */
public class ReturnCommand extends Command {
    public static final String COMMAND_WORD = "return";
    private static final String AVAILABLE_STATUS = "Available";
    private static final String BORROWED_STATUS = "Borrowed";
    protected String args; // Format: loan [ID]
    protected String id;

    /**
     * Class Constructor.
     * @param args Arguments supplied by user in the loan command
     */
    public ReturnCommand(String args) {
        this.args = args;
    }

    /**
     * Processes <b>return</b> Command, including handle exceptions.
     * @param ui Object that handles user IO
     * @param catalogue Object that encapsulates the library catalogue
     * @throws LibmgrException when user input is invalid
     */
    public void handleLoanCommand(TextUI ui, Catalogue catalogue) throws LibmgrException {
        if (!args.contains(" ")) {
            throw new LibmgrException(NO_ID);
        }
        id = args.split(" ")[1];
        Item toBeReturned = catalogue.getItem(id);

        if (toBeReturned.getStatus().equals(BORROWED_STATUS)) {
            toBeReturned.setStatus(AVAILABLE_STATUS);
            ui.print(RETURN_SUCCESS);
            ui.print(toBeReturned.getID() + " " + toBeReturned.getTitle());
        } else {
            ui.print(WRONG_ITEM_MESSAGE);
        }
    }

    /**
     * Executes <b>return</b> Command.
     * @param ui Object that handles user IO
     * @param catalogue Object that encapsulates the library catalogue
     */
    @Override
    public void execute(TextUI ui, Catalogue catalogue) {
        try {
            handleLoanCommand(ui, catalogue);
        } catch (IndexOutOfBoundsException e) {
            ui.print(RM_INVALID_ID);
        } catch (NullPointerException e) {
            ui.print(RM_INVALID_ID);
        } catch (LibmgrException e) {
            ui.print(e.getMessage());
        }
    }
}
