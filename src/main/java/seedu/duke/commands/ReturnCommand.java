package seedu.duke.commands;

import seedu.duke.common.LibmgrException;
import seedu.duke.data.Catalogue;
import seedu.duke.data.Item;
import seedu.duke.ui.TextUI;

import static seedu.duke.common.Status.AVAILABLE;
import static seedu.duke.common.Status.LOANED;
import static seedu.duke.common.Messages.RETURN_SUCCESS;
import static seedu.duke.common.Messages.INVALID_ID;
import static seedu.duke.common.Messages.WRONG_ITEM_MESSAGE;

/**
 * Class encapsulating command to update the status of the item to be returned.
 */
public class ReturnCommand extends Command {
    public static final String COMMAND_WORD = "return";
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
    public void handleReturnCommand(TextUI ui, Catalogue catalogue) throws LibmgrException {
        if (!args.contains(" ")) {
            throw new LibmgrException(INVALID_ID);
        }
        id = args.split("return ")[1];
        Item toBeReturned = catalogue.getItem(id);

        if (toBeReturned.getStatus().equals(LOANED)) {
            toBeReturned.setStatus(AVAILABLE);
            toBeReturned.setLoanee(null);
            toBeReturned.setDueDate();
            ui.print(RETURN_SUCCESS, toBeReturned);
        } else {
            throw new LibmgrException(WRONG_ITEM_MESSAGE);
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
            handleReturnCommand(ui, catalogue);
        } catch (IndexOutOfBoundsException e) {
            ui.print(INVALID_ID);
        } catch (NullPointerException e) {
            ui.print(INVALID_ID);
        } catch (LibmgrException e) {
            ui.print(e.getMessage());
        }
    }
}