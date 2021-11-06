package seedu.duke.commands;

import seedu.duke.common.LibmgrException;
import seedu.duke.common.Status;
import seedu.duke.data.Audio;
import seedu.duke.data.Catalogue;
import seedu.duke.data.Item;
import seedu.duke.data.Miscellaneous;
import seedu.duke.ui.TextUI;

import java.util.HashMap;

import static seedu.duke.common.Messages.INVALID_VALUES;
import static seedu.duke.common.Messages.WARN_INVALID_ARGS;

//@@author exetr
public class AddMiscellaneousCommand extends Command {
    public static final String COMMAND_FORMAT = "  Format: add i t/TITLE i/ID";
    public static final String ADD_MESSAGE = "  (+) Added new miscellaneous item to the catalogue";
    public static final String COMMAND_WORD = "add i";
    public static final String KEY_TITLE = "t";
    public static final String KEY_ID = "i";

    private HashMap<String, String> args;
    private String title;
    private String id;
    private Status status;

    public AddMiscellaneousCommand(HashMap<String, String> args) {
        this.args = args;
        this.title = args.get(KEY_TITLE);
        this.id = args.get(KEY_ID);
        this.status = Status.AVAILABLE;
    }

    /**
     * Checks for whether user has supplied any empty values any of the attributes.
     * @return Boolean True if any attributes are missing
     */
    private Boolean checkMissingArgs() {
        return title == null | id == null;
    }

    /**
     * Checks for whether user supplied extra arguments, program will not record these
     * additional arguments.
     * @return Boolean True if any additional arguments detected
     */
    private Boolean checkAdditionalArgs() {
        HashMap<String, String> tempArgs = args;
        tempArgs.remove(null);
        tempArgs.remove(KEY_TITLE);
        tempArgs.remove(KEY_ID);
        return tempArgs.size() > 0;
    }

    /**
     * Executes add miscellaneous command.
     * Checks for missing and/or additional arguments, then adds to catalogue.
     * Overrides method from parent Command class.
     * @param ui Object that handles user IO
     * @param catalogue Object that encapsulates the library catalogue
     */
    @Override
    public void execute(TextUI ui, Catalogue catalogue) {
        if (checkMissingArgs()) {
            ui.print(INVALID_VALUES + System.lineSeparator() + COMMAND_FORMAT);
            return;
        }
        if (checkAdditionalArgs()) {
            ui.print(WARN_INVALID_ARGS);
        }
        try {
            Miscellaneous newItem = new Miscellaneous(title, id, status, null, null);
            catalogue.add(newItem);
            ui.print(ADD_MESSAGE, newItem);
        } catch (LibmgrException e) {
            ui.print(e.getMessage());
        }
    }
}
