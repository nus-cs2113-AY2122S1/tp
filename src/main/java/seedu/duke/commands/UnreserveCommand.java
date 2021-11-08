package seedu.duke.commands;

import seedu.duke.common.Status;
import seedu.duke.data.Catalogue;
import seedu.duke.data.Item;
import seedu.duke.ui.TextUI;

import static seedu.duke.common.Messages.INVALID_ID;
import static seedu.duke.common.Messages.INVALID_VALUES;

//@@author exetr
public class UnreserveCommand extends Command {
    public static final String COMMAND_WORD = "unres";
    public static final String COMMAND_FORMAT = "  (!) Format: unres ID";
    public static final String ERR_NOT_RESERVED = "  (!) Item not reserved!";
    public static final String SUCCESS_UNRES = "  (+) Item unreserved:";
    protected String args;
    protected String id;

    /**
     * Sole constructor, takes in full line of input.
     * @param args Full input as entered by user
     */
    public UnreserveCommand(String args) {
        this.args = args;
    }

    /**
     * Gets the ID of object to be un-reserved,
     * Check validity of request and performs operations on item in catalogue.
     * @param ui Object that handles user IO
     * @param catalogue Underlying list of all items.
     */
    @Override
    public void execute(TextUI ui, Catalogue catalogue) {
        try {
            id = args.split(" ", 2)[1].strip();
            Item toUnreserve = catalogue.getItem(id);
            // Validity check: item has to be previously reserved by someone
            if (toUnreserve.getStatus().equals(Status.RESERVED)) {
                toUnreserve.setStatus(Status.AVAILABLE);
                toUnreserve.setLoanee(null);
                ui.print(SUCCESS_UNRES, toUnreserve);
            } else {
                ui.print(ERR_NOT_RESERVED);
            }
        } catch (IndexOutOfBoundsException e) {
            ui.print(INVALID_VALUES + System.lineSeparator() + COMMAND_FORMAT);
        } catch (NullPointerException e) {
            ui.print(INVALID_ID);
        }
    }
}
