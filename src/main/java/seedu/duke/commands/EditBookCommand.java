package seedu.duke.commands;

import seedu.duke.common.LibmgrException;
import seedu.duke.data.Book;
import seedu.duke.data.Catalogue;
import seedu.duke.data.Item;
import seedu.duke.ui.TextUI;

import java.util.HashMap;

import static seedu.duke.common.Messages.EDIT_BOOK_INVALID_FORMAT;
import static seedu.duke.common.Messages.EDIT_BOOK_MESSAGE;
import static seedu.duke.common.Messages.EDIT_UNCHANGED_AUTHOR;
import static seedu.duke.common.Messages.EDIT_UNCHANGED_ID;
import static seedu.duke.common.Messages.EDIT_UNCHANGED_TITLE;
import static seedu.duke.common.Messages.KEY_AUTHOR;
import static seedu.duke.common.Messages.KEY_ID;
import static seedu.duke.common.Messages.KEY_TITLE;
import static seedu.duke.common.Messages.WARN_INVALID_ARGS;

//@@author avellinwong01
/**
 * Class encapsulating an Edit Book Command.
 */
public class EditBookCommand extends Command {
    private Book toEdit;
    private HashMap<String, String> args;
    private String title;
    private String id;
    private String author;

    /**
     * Sole Constructor.
     *
     * @param toEdit The Book item to edit
     * @param args Hashmap containing all arguments supplied by the user.
     *             Key represents the type of argument (null represents command word and id of item to edit)
     *             Value represents value associated with argument type
     */
    public EditBookCommand(HashMap<String,String> args, Item toEdit) {
        this.toEdit = (Book) toEdit;
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
        if (args.containsKey(KEY_AUTHOR)) {
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
        HashMap<String, String> tempArgs = new HashMap<>(args);
        tempArgs.remove(null);
        if (args.containsKey(KEY_TITLE)) {
            tempArgs.remove(KEY_TITLE);
        }
        if (args.containsKey(KEY_ID)) {
            tempArgs.remove(KEY_ID);
        }
        if (args.containsKey(KEY_AUTHOR)) {
            tempArgs.remove(KEY_AUTHOR);
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
        boolean isMissingAuthor = args.containsKey(KEY_AUTHOR) && (author == null || author.equals(""));
        return isMissingTitle || isMissingId || isMissingAuthor;
    }

    /**
     * Checks for whether user has not supplied any valid arguments (attribute keys) to be edited.
     *
     * @return boolean True if no valid arguments are entered
     */
    public boolean checkEmptyArgs() {
        return !args.containsKey(KEY_TITLE) && !args.containsKey(KEY_ID) && !args.containsKey(KEY_AUTHOR);
    }

    /**
     * Processes Edit Book Command, including exceptions.
     *
     * @param ui Object that handles user IO
     * @param catalogue Object that encapsulates the library catalogue
     * @throws LibmgrException when the user input is invalid
     */
    public void handlesEditBookCommand(TextUI ui, Catalogue catalogue) throws LibmgrException {
        processArgs();
        if (checkMissingArgs() || checkEmptyArgs()) {
            throw new LibmgrException(EDIT_BOOK_INVALID_FORMAT);
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
        if (args.containsKey(KEY_AUTHOR)) {
            assert author != null && !author.equals("");
            if (toEdit.getAuthor().equals(author)) {
                throw new LibmgrException(EDIT_UNCHANGED_AUTHOR);
            }
            toEdit.setAuthor(author);
        }
        ui.print(EDIT_BOOK_MESSAGE, toEdit);
    }

    /**
     * Executes Edit Book command, including exception handling.
     * Overrides method from parent class.
     *
     * @param ui Object that handles user IO
     * @param catalogue Object that encapsulates the library catalogue
     */
    @Override
    public void execute(TextUI ui, Catalogue catalogue) {
        try {
            handlesEditBookCommand(ui, catalogue);
        } catch (LibmgrException e) {
            ui.print(e.getMessage());
        }
    }
}
//@@author avellinwong01
