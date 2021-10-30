package seedu.duke.commands;

import seedu.duke.data.Catalogue;
import seedu.duke.data.Item;
import seedu.duke.data.Magazine;
import seedu.duke.ui.TextUI;

import java.util.HashMap;

import static seedu.duke.common.Messages.*;

public class EditMagazineCommand extends Command {
    private Magazine toEdit;
    private HashMap<String, String> args;
    private String title;
    private String id;
    private String publisher;
    private String edition;


    public EditMagazineCommand(HashMap<String,String> args, Item toEdit) {
        this.toEdit = (Magazine) toEdit;
        this.args = args;
    }

    public void processArgs() {
        if (args.containsKey(KEY_TITLE)) {
            this.title = args.get(KEY_TITLE);
        } else if (args.containsKey(KEY_ID)) {
            this.id = args.get(KEY_ID);
        } else if (args.containsKey(KEY_PUBLISHER)) {
            this.publisher = args.get(KEY_PUBLISHER);
        } else if (args.containsKey(KEY_EDITION)) {
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
        HashMap<String, String> tempArgs = args;
        tempArgs.remove(null);
        if (args.containsKey(KEY_TITLE)) {
            tempArgs.remove(KEY_TITLE);
        } else if (args.containsKey(KEY_ID)) {
            tempArgs.remove(KEY_ID);
        } else if (args.containsKey(KEY_PUBLISHER)) {
            tempArgs.remove(KEY_PUBLISHER);
        } else if (args.containsKey(KEY_EDITION)) {
            tempArgs.remove(KEY_EDITION);
        }
        return tempArgs.size() > 0;
    }

    /**
     * Checks for whether user has supplied any empty values any of the attributes to be edited.
     *
     * @return boolean True if any attributes are missing
     */
    private boolean checkMissingArgs() {
        boolean isMissingTitle = args.containsKey(KEY_TITLE) && (title == null || title.equals(""));
        boolean isMissingId = args.containsKey(KEY_ID) && (id == null || id.equals(""));
        boolean isMissingPublisher = args.containsKey(KEY_PUBLISHER) && (publisher == null || publisher.equals(""));
        boolean isMissingEdition = args.containsKey(KEY_EDITION) && (edition == null || edition.equals(""));
        return isMissingTitle || isMissingId || isMissingPublisher || isMissingEdition;
    }

    public boolean checkEmptyArgs() {
        return !args.containsKey(KEY_TITLE) && !args.containsKey(KEY_ID)
                && !args.containsKey(KEY_PUBLISHER) && !args.containsKey(KEY_EDITION);
    }

    @Override
    public void execute(TextUI ui, Catalogue catalogue) {
        processArgs();
        if (checkMissingArgs() || checkEmptyArgs()) {
            ui.print(EDIT_INVALID_FORMAT);
            return;
        }
        if (checkInvalidArgs()) {
            ui.print(WARN_INVALID_ARGS);
        }
        if (args.containsKey(KEY_TITLE)) {
            toEdit.setTitle(title);
        } else if (args.containsKey(KEY_ID)) {
            toEdit.setID(id);
        } else if (args.containsKey(KEY_PUBLISHER)) {
            toEdit.setPublisher(publisher);
        } else if (args.containsKey(KEY_EDITION)) {
            toEdit.setEdition(edition);
        }
        ui.print(EDIT_MAGAZINE_MESSAGE, toEdit);
    }
}
