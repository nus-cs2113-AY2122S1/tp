package seedu.duke.commands;

import seedu.duke.common.LibmgrException;
import seedu.duke.common.Status;
import seedu.duke.data.Book;
import seedu.duke.data.Catalogue;
import seedu.duke.ui.TextUI;

import java.util.HashMap;

import static seedu.duke.common.Messages.INVALID_VALUES;
import static seedu.duke.common.Messages.WARN_ADDITIONAL_ARGS;

public class AddBookCommand extends Command {
    public static final String COMMAND_FORMAT = "  Format: add b t/TITLE i/ID a/AUTHOR";
    public static final String ADD_MESSAGE = "  (+) Added new book item to the catalogue";
    public static final String COMMAND_WORD = "add b";
    public static final String KEY_TITLE = "t";
    public static final String KEY_ID = "i";
    public static final String KEY_AUTHOR = "a";

    private HashMap<String, String> args;
    private String title;
    private String id;
    private Status status;
    private String author;

    public AddBookCommand(HashMap<String, String> args) {
        this.args = args;
        this.title = args.get(KEY_TITLE);
        this.id = args.get(KEY_ID);
        this.status = Status.AVAILABLE;
        this.author = args.get(KEY_AUTHOR);
    }

    private Boolean checkMissingArgs() {
        return title == null | id == null | author == null;
    }

    private Boolean checkAdditionalArgs() {
        HashMap<String, String> tempArgs = args;
        tempArgs.remove(null);
        tempArgs.remove(KEY_TITLE);
        tempArgs.remove(KEY_ID);
        tempArgs.remove(KEY_AUTHOR);
        return tempArgs.size() > 0;
    }

    @Override
    public void execute(TextUI ui, Catalogue catalogue) {
        if (checkMissingArgs()) {
            ui.print(INVALID_VALUES + System.lineSeparator() + COMMAND_FORMAT);
            return;
        }
        if (checkAdditionalArgs()) {
            ui.print(WARN_ADDITIONAL_ARGS);
        }
        try {
            Book newBook = new Book(title, id, status, author);
            catalogue.add(newBook);
            ui.print(ADD_MESSAGE, newBook);
        } catch (LibmgrException e) {
            ui.print(e.getMessage());
        }
    }
}
