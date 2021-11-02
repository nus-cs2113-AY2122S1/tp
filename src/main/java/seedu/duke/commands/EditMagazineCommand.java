package seedu.duke.commands;

import seedu.duke.common.LibmgrException;
import seedu.duke.data.Catalogue;
import seedu.duke.data.Item;
import seedu.duke.data.Magazine;
import seedu.duke.ui.TextUI;

import java.util.HashMap;

import static seedu.duke.common.Messages.*;

//@@author avellinwong01
/**
 * Class that encapsulates an Edit Magazine Command.
 */
public class EditMagazineCommand extends Command {
    private Magazine toEdit;
    private HashMap<String, String> args;
    private String title;
    private String id;
    private String publisher;
    private String edition;


    /**
     * Sole Constructor.
     *
     * @param toEdit The Magazine item to edit
     * @param args Hashmap containing all arguments supplied by the user.
     *             Key represents the type of argument (null represents command word and id of item to edit)
     *             Value represents value associated with argument type
     */
    public EditMagazineCommand(HashMap<String,String> args, Item toEdit) {
        this.toEdit = (Magazine) toEdit;
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
        if (args.containsKey(KEY_PUBLISHER)) {
            this.publisher = args.get(KEY_PUBLISHER);
        }
        if (args.containsKey(KEY_EDITION)) {
            this.edition = args.get(KEY_EDITION);
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
        if (args.containsKey(KEY_PUBLISHER)) {
            tempArgs.remove(KEY_PUBLISHER);
        }
        if (args.containsKey(KEY_EDITION)) {
            tempArgs.remove(KEY_EDITION);
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
        boolean isMissingPublisher = args.containsKey(KEY_PUBLISHER) && (publisher == null || publisher.equals(""));
        boolean isMissingEdition = args.containsKey(KEY_EDITION) && (edition == null || edition.equals(""));
        return isMissingTitle || isMissingId || isMissingPublisher || isMissingEdition;
    }

    /**
     * Checks for whether user has not supplied any valid arguments (attribute keys) to be edited.
     *
     * @return boolean True if no valid arguments are entered
     */
    public boolean checkEmptyArgs() {
        return !args.containsKey(KEY_TITLE) && !args.containsKey(KEY_ID)
                && !args.containsKey(KEY_PUBLISHER) && !args.containsKey(KEY_EDITION);
    }

    /**
     * Executes Edit Magazine command.
     * Overrides method from parent class.
     *
     * @param ui Object that handles user IO
     * @param catalogue Object that encapsulates the library catalogue
     */
    @Override
    public void execute(TextUI ui, Catalogue catalogue) {
        processArgs();
        if (checkMissingArgs() || checkEmptyArgs()) {
            ui.print(EDIT_MAGAZINE_INVALID_FORMAT);
            return;
        }
        if (checkInvalidArgs()) {
            ui.print(WARN_INVALID_ARGS);
        }
        if (args.containsKey(KEY_TITLE)) {
            assert title != null && !title.equals("");
            if (toEdit.getTitle().equals(title)) {
                ui.print(EDIT_UNCHANGED_TITLE);
                return;
            }
            toEdit.setTitle(title);
        }
        if (args.containsKey(KEY_ID)) {
            assert id != null && !id.equals("");
            if (toEdit.getID().equals(id)) {
                ui.print(EDIT_UNCHANGED_ID);
                return;
            }
            try {
                catalogue.checkDuplicateID(id);
            } catch (LibmgrException e) {
                ui.print(e.getMessage());
            }
            toEdit.setID(id);
        }
        if (args.containsKey(KEY_PUBLISHER)) {
            assert publisher != null && !publisher.equals("");
            if (toEdit.getPublisher().equals(publisher)) {
                ui.print(EDIT_UNCHANGED_PUBLISHER);
                return;
            }
            toEdit.setPublisher(publisher);
        }
        if (args.containsKey(KEY_EDITION)) {
            assert edition != null && !edition.equals("");
            if (toEdit.getEdition().equals(edition)) {
                ui.print(EDIT_UNCHANGED_EDITION);
                return;
            }
            toEdit.setEdition(edition);
        }
        ui.print(EDIT_MAGAZINE_MESSAGE, toEdit);
    }
}
//@@author avellinwong01
