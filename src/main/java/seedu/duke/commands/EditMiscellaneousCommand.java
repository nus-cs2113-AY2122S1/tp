package seedu.duke.commands;

import seedu.duke.common.LibmgrException;
import seedu.duke.data.Catalogue;
import seedu.duke.data.Item;
import seedu.duke.ui.TextUI;

import java.util.HashMap;

import static seedu.duke.common.Messages.EDIT_MISCELLANEOUS_INVALID_FORMAT;
import static seedu.duke.common.Messages.EDIT_MISCELLANEOUS_MESSAGE;
import static seedu.duke.common.Messages.EDIT_UNCHANGED_ID;
import static seedu.duke.common.Messages.EDIT_UNCHANGED_TITLE;
import static seedu.duke.common.Messages.KEY_ID;
import static seedu.duke.common.Messages.KEY_TITLE;
import static seedu.duke.common.Messages.WARN_INVALID_ARGS;

/**
 * Class encapsulating an Edit Miscellaneous Item Command.
 */
public class EditMiscellaneousCommand extends Command {
    private Item toEdit;
    private HashMap<String, String> args;
    private String title;
    private String id;

    /**
     * Sole Constructor.
     *
     * @param toEdit The miscellaneous item to edit
     * @param args Hashmap containing all arguments supplied by the user.
     *             Key represents the type of argument (null represents command word and id of item to edit)
     *             Value represents value associated with argument type
     */
    public EditMiscellaneousCommand(HashMap<String,String> args, Item toEdit) {
        this.toEdit = toEdit;
        this.args = args;
    }

    /**
     * Processes arguments of hashmap, extracting them into attributes of the class.
     */
    public void processArgs() {
        if (args.containsKey(KEY_TITLE)) {
            this.title = args.get(KEY_TITLE);
        }
        if (args.containsKey(KEY_ID)) {
            this.id = args.get(KEY_ID);
        }
    }

    /**
     * Checks for whether user supplied invalid arguments, program will not record these
     * arguments.
     *
     * @return boolean True if any invalid arguments detected
     */
    public boolean checkInvalidArgs() {
        HashMap<String, String> tempArgs = new HashMap<>(args);
        tempArgs.remove(null);
        if (args.containsKey(KEY_TITLE)) {
            tempArgs.remove(KEY_TITLE);
        }
        if (args.containsKey(KEY_ID)) {
            tempArgs.remove(KEY_ID);
        }
        return tempArgs.size() > 0;
    }

    /**
     * Checks for whether user has supplied any empty values to any of the attributes to be edited.
     *
     * @return boolean True if any attribute values are missing
     */
    public boolean checkMissingArgs() {
        boolean isMissingTitle = args.containsKey(KEY_TITLE) && (title == null || title.equals(""));
        boolean isMissingId = args.containsKey(KEY_ID) && (id == null || id.equals(""));
        return isMissingTitle || isMissingId;
    }

    /**
     * Checks for whether user has not supplied any valid arguments (attribute keys) to be edited.
     *
     * @return boolean True if no valid arguments are entered
     */
    public boolean checkEmptyArgs() {
        return !args.containsKey(KEY_TITLE) && !args.containsKey(KEY_ID);
    }

    /**
     * Processes Edit Miscellaneous Command, including exceptions.
     *
     * @param ui Object that handles user IO
     * @param catalogue Object that encapsulates the library catalogue
     * @throws LibmgrException when the user input is invalid
     */
    public void handlesEditMiscellaneousCommand(TextUI ui, Catalogue catalogue) throws LibmgrException {
        processArgs();
        if (checkMissingArgs() || checkEmptyArgs()) {
            throw new LibmgrException(EDIT_MISCELLANEOUS_INVALID_FORMAT);
        }
        if (checkInvalidArgs()) {
            ui.print(WARN_INVALID_ARGS);
        }
        if (args.containsKey(KEY_TITLE)) {
            assert title != null && !title.equals("");
            if (toEdit.getTitle().equals(title)) {
                throw new LibmgrException(EDIT_UNCHANGED_TITLE);
            }
            toEdit.setTitle(title);
        }
        if (args.containsKey(KEY_ID)) {
            assert id != null && !id.equals("");
            if (toEdit.getID().equals(id)) {
                throw new LibmgrException(EDIT_UNCHANGED_ID);
            }
            catalogue.checkDuplicateID(id);
            toEdit.setID(id);
        }
        ui.print(EDIT_MISCELLANEOUS_MESSAGE, toEdit);
    }

    /**
     * Executes Edit Miscellaneous command, including exception handling.
     * Overrides method from parent class.
     *
     * @param ui Object that handles user IO
     * @param catalogue Object that encapsulates the library catalogue
     */
    @Override
    public void execute(TextUI ui, Catalogue catalogue) {
        try {
            handlesEditMiscellaneousCommand(ui, catalogue);
        } catch (LibmgrException e) {
            ui.print(e.getMessage());
        }
    }
}
