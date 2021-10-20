package seedu.duke.commands;

import seedu.duke.LibmgrException;
import seedu.duke.data.Catalogue;
import seedu.duke.data.Item;
import seedu.duke.ui.TextUI;

import static seedu.duke.Status.AVAILABLE;
import static seedu.duke.Status.LOANED;
import static seedu.duke.common.Messages.LOAN_SUCCESS;
import static seedu.duke.common.Messages.INVALID_ID;
import static seedu.duke.common.Messages.UNAVAILABLE_ITEM_MESSAGE;

/**
 * Class encapsulating command to update the status of the item to be loaned out.
 */
public class LoanCommand extends Command {
    public static final String COMMAND_WORD = "loan";
    protected String args; // Format: loan [ID]
    protected String id;

    /**
     * Class Constructor.
     * @param args Arguments supplied by user in the loan command
     */
    public LoanCommand(String args) {
        this.args = args;
    }

    /**
     * Processes <b>loan</b> Command, including handle exceptions.
     * @param ui Object that handles user IO
     * @param catalogue Object that encapsulates the library catalogue
     * @throws LibmgrException when user input is invalid
     */
    public void handleLoanCommand(TextUI ui, Catalogue catalogue) throws LibmgrException {
        if (!args.contains(" ")) {
            throw new LibmgrException(INVALID_ID);
        }
        id = args.split(" ")[1];
        Item toBeLoaned = catalogue.getItem(id);

        if (toBeLoaned.getStatus().equals(AVAILABLE)) {
            toBeLoaned.setStatus(LOANED);
            ui.print(LOAN_SUCCESS, toBeLoaned);
        } else {
            ui.print(UNAVAILABLE_ITEM_MESSAGE);
        }
    }

    /**
     * Executes <b>loan</b> Command.
     * @param ui Object that handles user IO
     * @param catalogue Object that encapsulates the library catalogue
     */
    @Override
    public void execute(TextUI ui, Catalogue catalogue) {
        try {
            handleLoanCommand(ui, catalogue);
        } catch (IndexOutOfBoundsException e) {
            ui.print(INVALID_ID);
        } catch (NullPointerException e) {
            ui.print(INVALID_ID);
        } catch (LibmgrException e) {
            ui.print(e.getMessage());
        }
    }
}
