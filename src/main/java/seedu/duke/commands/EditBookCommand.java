package seedu.duke.commands;

import seedu.duke.data.Book;
import seedu.duke.data.Catalogue;
import seedu.duke.data.Item;
import seedu.duke.ui.TextUI;

import java.util.HashMap;

import static seedu.duke.common.Messages.*;

public class EditBookCommand extends Command {
    private Book toEdit;
    private HashMap<String, String> args;
    private String title;
    private String id;
    private String author;


    public EditBookCommand(HashMap<String,String> args, Item toEdit) {
        this.toEdit = (Book) toEdit;
        this.args = args;
    }

    public void processArgs() {
        if (args.containsKey(KEY_TITLE)) {
            this.title = args.get(KEY_TITLE);
        } else if (args.containsKey(KEY_ID)) {
            this.id = args.get(KEY_ID);
        } else if (args.containsKey(KEY_AUTHOR)) {
            this.author = args.get(KEY_AUTHOR);
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
        } else if (args.containsKey(KEY_AUTHOR)) {
            tempArgs.remove(KEY_AUTHOR);
        }
        return tempArgs.size() > 0;
    }

    /**
     * Checks for whether user has supplied any empty values any of the attributes to be edited.
     *
     * @return boolean True if any attributes are missing
     */
    public boolean checkMissingArgs() {
        boolean isMissingTitle = args.containsKey(KEY_TITLE) && (title == null || title.equals(""));
        boolean isMissingId = args.containsKey(KEY_ID) && (id == null || id.equals(""));
        boolean isMissingAuthor = args.containsKey(KEY_AUTHOR) && (author == null || author.equals(""));
        return isMissingTitle || isMissingId || isMissingAuthor;
    }

    public boolean checkEmptyArgs() {
        return !args.containsKey(KEY_TITLE) && !args.containsKey(KEY_ID) && !args.containsKey(KEY_AUTHOR);
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
        } else if (args.containsKey(KEY_AUTHOR)) {
            toEdit.setAuthor(author);
        }
        ui.print(EDIT_BOOK_MESSAGE, toEdit);
    }
}
