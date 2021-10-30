package seedu.duke.commands;

import seedu.duke.common.LibmgrException;
import seedu.duke.common.Status;
import seedu.duke.data.Catalogue;
import seedu.duke.data.Magazine;
import seedu.duke.ui.TextUI;

import java.util.HashMap;

import static seedu.duke.common.Messages.INVALID_VALUES;
import static seedu.duke.common.Messages.WARN_INVALID_ARGS;

//@@author exetr

/**
 * This class consists of the logic for the AddMagazine command.
 */
public class AddMagazineCommand extends Command {
    public static final String COMMAND_FORMAT = "  Format: add m t/TITLE i/ID p/PUBLISHER e/EDITION";
    public static final String ADD_MESSAGE = "  (+) Added new magazine item to the catalogue";
    public static final String COMMAND_WORD = "add m";
    public static final String KEY_TITLE = "t";
    public static final String KEY_ID = "i";
    public static final String KEY_PUBLISHER = "p";
    public static final String KEY_EDITION = "e";

    private HashMap<String, String> args;
    private String title;
    private String id;
    private Status status;
    private String publisher;
    private String edition;

    /**
     * Sole constructor.
     * Status is set to AVAILABLE by default.
     * @param args Hashmap containing all arguments supplied by user.
     *             Key represents the type of argument (null represents command word)
     *             Value represents value associated with argument type
     */
    public AddMagazineCommand(HashMap<String, String> args) {
        this.args = args;
        this.title = args.get(KEY_TITLE);
        this.id = args.get(KEY_ID);
        this.status = Status.AVAILABLE;
        this.publisher = args.get(KEY_PUBLISHER);
        this.edition = args.get(KEY_EDITION);
    }

    /**
     * Checks for whether user has supplied any empty values any of the attributes.
     * @return Boolean True if any attributes are missing
     */
    private Boolean checkMissingArgs() {
        return title == null | id == null | publisher == null | edition == null;
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
        tempArgs.remove(KEY_PUBLISHER);
        tempArgs.remove(KEY_EDITION);
        return tempArgs.size() > 0;
    }

    /**
     * Executes add magazine command.
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
            Magazine newMagazine = new Magazine(title, id, status, publisher, edition);
            catalogue.add(newMagazine);
            ui.print(ADD_MESSAGE, newMagazine);
        } catch (LibmgrException e) {
            ui.print(e.getMessage());
        }
    }
}
