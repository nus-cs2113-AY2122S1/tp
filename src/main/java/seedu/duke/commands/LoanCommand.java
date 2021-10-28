package seedu.duke.commands;

import seedu.duke.common.LibmgrException;
import seedu.duke.data.Catalogue;
import seedu.duke.data.Item;
import seedu.duke.ui.TextUI;

import static seedu.duke.common.Status.AVAILABLE;
import static seedu.duke.common.Status.LOANED;
import static seedu.duke.common.Status.RESERVED;
import static seedu.duke.common.Messages.INVALID_ID;
import static seedu.duke.common.Messages.LOAN_SUCCESS;
import static seedu.duke.common.Messages.UNAVAILABLE_ITEM_MESSAGE;
import static seedu.duke.common.Messages.LOAN_INVALID_FORMAT;
import static seedu.duke.data.Item.ARG_ID;
import static seedu.duke.data.Item.ARG_LOANEE;
import static seedu.duke.data.Item.ARG_DUE;
import static seedu.duke.common.Messages.ALREADY_RESERVED_MESSAGE;

/**
 * Class encapsulating command to update the status of the item to be loaned out.
 */
public class LoanCommand extends Command {
    // Format: loan i/ID u/USERNAME d/DUE_DATE
    public static final String COMMAND_WORD = "loan";
    protected String args;
    protected String id;
    protected String username;
    protected String dueDate;

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
        // Check validity of arguments
        if (!args.contains(ARG_ID) | !args.contains(Item.ARG_LOANEE) | !args.contains(Item.ARG_DUE)) {
            throw new LibmgrException(LOAN_INVALID_FORMAT);
        }
        // Parse arguments
        id = args.substring(args.indexOf(ARG_ID) + ARG_ID.length(), args.indexOf(ARG_LOANEE)).strip();
        username = args.substring(args.indexOf(ARG_LOANEE) + ARG_LOANEE.length(), args.indexOf(ARG_DUE)).strip();
        dueDate = args.substring(args.indexOf(ARG_DUE) + ARG_DUE.length()).strip();

        // Get item to be updated
        Item toBeLoaned = catalogue.getItem(id);
        // Perform operations
        if (toBeLoaned.getStatus().equals(AVAILABLE)) {
            toBeLoaned.setStatus(LOANED);
            toBeLoaned.setLoanee(username);
            toBeLoaned.setDueDate(dueDate);
            assert toBeLoaned.getStatus() == LOANED : "Status not set as loaned";
            assert toBeLoaned.getLoanee() != null : "Loanee still null";
            assert toBeLoaned.getDueDate() != null : "Due date still null";
            ui.print(LOAN_SUCCESS, toBeLoaned);
        } else if (toBeLoaned.getStatus().equals(RESERVED)) {
            if (toBeLoaned.getLoanee().equals(username)) {
                toBeLoaned.setStatus(LOANED);
                toBeLoaned.setDueDate(dueDate);
                ui.print(LOAN_SUCCESS, toBeLoaned);
            } else {
                ui.print(ALREADY_RESERVED_MESSAGE);
            }
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
        } catch (StringIndexOutOfBoundsException e) {
            ui.print(LOAN_INVALID_FORMAT);
        } catch (IndexOutOfBoundsException e) {
            ui.print(INVALID_ID);
        } catch (NullPointerException e) {
            ui.print(INVALID_ID);
        } catch (LibmgrException e) {
            ui.print(e.getMessage());
        }
    }
}
