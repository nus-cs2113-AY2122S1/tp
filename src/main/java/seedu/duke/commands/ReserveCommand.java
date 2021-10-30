package seedu.duke.commands;

import seedu.duke.common.LibmgrException;
import seedu.duke.data.Catalogue;
import seedu.duke.data.Item;
import seedu.duke.ui.TextUI;

import java.util.HashMap;


import static seedu.duke.common.Messages.INVALID_ID;
import static seedu.duke.common.Messages.INVALID_VALUES;
import static seedu.duke.common.Messages.UNAVAILABLE_ITEM_MESSAGE;
import static seedu.duke.common.Messages.WARN_INVALID_ARGS;
import static seedu.duke.common.Status.AVAILABLE;
import static seedu.duke.common.Status.RESERVED;

/**
 * Class encapsulating command to update the status of the item to be loaned out.
 */
public class ReserveCommand extends Command {
    public static final String COMMAND_FORMAT = "  (!) Format: res i/ID u/USER";
    public static final String COMMAND_WORD = "res";
    public static final String SUCCESS_RES = "  (+) You have successfully reserved an item:";
    public static final String ERR_RESERVED = "";
    public static final String KEY_ID = "i";
    public static final String KEY_USER = "u";

    protected HashMap<String, String> args;
    protected String id;
    protected String username;

    /**
     * Class Constructor.
     * @param args Arguments supplied by user in the loan command
     */
    public ReserveCommand(HashMap<String, String> args) {
        this.args = args;
        this.id = args.get(KEY_ID);
        this.username = args.get(KEY_USER);
    }

    /**
     * Checks for whether user has supplied any empty values any of the attributes.
     * @return Boolean True if any attributes are missing
     */
    private Boolean checkMissingArgs() {
        return id == null | username == null;
    }

    /**
     * Checks for whether user supplied extra arguments, program will not record these
     * additional arguments.
     * @return Boolean True if any additional arguments detected
     */
    private Boolean checkAdditionalArgs() {
        HashMap<String, String> tempArgs = args;
        tempArgs.remove(null);
        tempArgs.remove(KEY_ID);
        tempArgs.remove(KEY_USER);
        return tempArgs.size() > 0;
    }

    /**
     * Processes <b>loan</b> Command, including handle exceptions.
     * @param ui Object that handles user IO
     * @param catalogue Object that encapsulates the library catalogue
     * @throws LibmgrException when user input is invalid
     */
    public void handleLoanCommand(TextUI ui, Catalogue catalogue) throws LibmgrException {
        if (checkMissingArgs()) {
            ui.print(INVALID_VALUES + System.lineSeparator() + COMMAND_FORMAT);
            return;
        }
        if (checkAdditionalArgs()) {
            ui.print(WARN_INVALID_ARGS);
        }

        // Get item to be updated
        Item toBeReserved = catalogue.getItem(id);
        // Perform operations
        if (toBeReserved.getStatus().equals(AVAILABLE)) {
            toBeReserved.setStatus(RESERVED);
            toBeReserved.setLoanee(username);
            assert toBeReserved.getStatus() == RESERVED : "Status not set as reserved";
            assert toBeReserved.getLoanee() != null : "Loanee still null";
            ui.print(SUCCESS_RES, toBeReserved);
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
        } catch (LibmgrException e) {
            ui.print(e.getMessage());
        } catch (NullPointerException e) {
            ui.print(INVALID_ID);
        }
    }
}
