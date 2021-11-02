package seedu.duke.commands;

import seedu.duke.common.LibmgrException;
import seedu.duke.data.Catalogue;
import seedu.duke.data.Item;
import seedu.duke.ui.TextUI;

import java.time.format.DateTimeParseException;
import java.util.HashMap;

import static seedu.duke.common.Messages.*;
import static seedu.duke.common.Status.AVAILABLE;
import static seedu.duke.common.Status.LOANED;
import static seedu.duke.common.Status.RESERVED;

/**
 * Class encapsulating command to update the status of the item to be loaned out.
 */
public class LoanCommand extends Command {
    // Format: loan i/ID u/USERNAME d/DUE_DATE
    public static final String COMMAND_FORMAT = "  (!) Format: loan i/ID u/USER d/DUE_DATE(dd-mm-yyyy)";
    public static final String COMMAND_WORD = "loan";
    public static final String SUCCESS_LOAN = "  (+) Item has been loaned out:";
    public static final String ERR_RESERVED = "  (!) Sorry, the item has already been reserved for someone else";
    public static final String KEY_ID = "i";
    public static final String KEY_USER = "u";
    public static final String KEY_DUE = "d";

    protected HashMap<String, String> args;
    protected String id;
    protected String username;
    protected String dueDate;

    /**
     * Class Constructor.
     * @param args Hashmap containing all arguments supplied by user.
     *             Key represents the type of argument (null represents command word)
     *             Value represents value associated with argument type
     */
    public LoanCommand(HashMap<String, String> args) {
        this.args = args;
        this.id = args.get(KEY_ID);
        this.username = args.get(KEY_USER);
        this.dueDate = args.get(KEY_DUE);
    }

    /**
     * Checks for whether user has supplied any empty values any of the attributes.
     * @return Boolean True if any attributes are missing
     */
    private Boolean checkMissingArgs() {
        return id == null | username == null | dueDate == null;
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
        tempArgs.remove(KEY_DUE);
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
            throw new LibmgrException(INVALID_VALUES + System.lineSeparator() + COMMAND_FORMAT);
        }
        if (checkAdditionalArgs()) {
            ui.print(WARN_ADDITIONAL_ARGS);
        }

        // Get item to be updated
        Item toBeLoaned = catalogue.getItem(id);
        // Perform operations
        if (toBeLoaned.getStatus().equals(AVAILABLE)) {
            toBeLoaned.setDueDate(dueDate);
            toBeLoaned.setStatus(LOANED);
            toBeLoaned.setLoanee(username);
            assert toBeLoaned.getStatus() == LOANED : "Status not set as loaned";
            assert toBeLoaned.getLoanee() != null : "Loanee still null";
            assert toBeLoaned.getDueDate() != null : "Due date still null";
            ui.print(SUCCESS_LOAN, toBeLoaned);
        } else if (toBeLoaned.getStatus().equals(RESERVED)) {
            if (toBeLoaned.getLoanee().equals(username)) {
                toBeLoaned.setDueDate(dueDate);
                toBeLoaned.setStatus(LOANED);
                ui.print(SUCCESS_LOAN, toBeLoaned);
            } else {
                ui.print(ERR_RESERVED);
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
        } catch (LibmgrException e) {
            ui.print(e.getMessage());
        } catch (DateTimeParseException e) {
            ui.print(INVALID_DATE);
        } catch (NullPointerException e) {
            ui.print(INVALID_ID);
        }
    }
}
