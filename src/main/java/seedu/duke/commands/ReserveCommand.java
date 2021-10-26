package seedu.duke.commands;

import seedu.duke.LibmgrException;
import seedu.duke.data.Catalogue;
import seedu.duke.data.Item;
import seedu.duke.ui.TextUI;

import static seedu.duke.Status.AVAILABLE;
import static seedu.duke.Status.RESERVED;
import static seedu.duke.common.Messages.RESERVE_INVALID_FORMAT;
import static seedu.duke.common.Messages.RESERVE_SUCCESS;
import static seedu.duke.common.Messages.UNAVAILABLE_ITEM_MESSAGE;
import static seedu.duke.common.Messages.INVALID_ID;
import static seedu.duke.data.Item.ARG_ID;
import static seedu.duke.data.Item.ARG_LOANEE;

/**
 * Class encapsulating command to update the status of the item to be loaned out.
 */
public class ReserveCommand extends Command {
    // Format: reserve i/ID u/USERNAME
    public static final String COMMAND_WORD = "reserve";
    protected String args;
    protected String id;
    protected String username;

    /**
     * Class Constructor.
     * @param args Arguments supplied by user in the loan command
     */
    public ReserveCommand(String args) {
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
        if (!args.contains(ARG_ID) | !args.contains(ARG_LOANEE)) {
            throw new LibmgrException(RESERVE_INVALID_FORMAT);
        }
        // Process arguments
        try {
            id = args.substring(args.indexOf(ARG_ID) + ARG_ID.length(), args.indexOf(ARG_LOANEE)).strip();
            username = args.substring(args.indexOf(ARG_LOANEE) + ARG_LOANEE.length()).strip();
        } catch (StringIndexOutOfBoundsException e) {
            throw new LibmgrException(RESERVE_INVALID_FORMAT);
        }

        // Get item to be updated
        Item toBeReserved = catalogue.getItem(id);
        // Perform operations
        if (toBeReserved.getStatus().equals(AVAILABLE)) {
            toBeReserved.setStatus(RESERVED);
            toBeReserved.setLoanee(username);
            assert toBeReserved.getStatus() == RESERVED : "Status not set as reserved";
            assert toBeReserved.getLoanee() != null : "Loanee still null";
            ui.print(RESERVE_SUCCESS, toBeReserved);
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
