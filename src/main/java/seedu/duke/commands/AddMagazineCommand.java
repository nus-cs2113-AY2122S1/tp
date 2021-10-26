package seedu.duke.commands;

import seedu.duke.common.LibmgrException;
import seedu.duke.common.Status;
import seedu.duke.data.Catalogue;
import seedu.duke.data.Magazine;
import seedu.duke.ui.TextUI;

import java.util.HashMap;

import static seedu.duke.common.Messages.INVALID_VALUES;
import static seedu.duke.common.Messages.WARN_ADDITIONAL_ARGS;

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

    public AddMagazineCommand(HashMap<String, String> args) {
        this.args = args;
        this.title = args.get(KEY_TITLE);
        this.id = args.get(KEY_ID);
        this.status = Status.AVAILABLE;
        this.publisher = args.get(KEY_PUBLISHER);
        this.edition = args.get(KEY_EDITION);
    }

    private Boolean checkMissingArgs() {
        return title == null | id == null | publisher == null | edition == null;
    }

    private Boolean checkAdditionalArgs() {
        HashMap<String, String> tempArgs = args;
        tempArgs.remove(null);
        tempArgs.remove(KEY_TITLE);
        tempArgs.remove(KEY_ID);
        tempArgs.remove(KEY_PUBLISHER);
        tempArgs.remove(KEY_EDITION);
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
            Magazine newMagazine = new Magazine(title, id, status, publisher, edition);
            catalogue.add(newMagazine);
            ui.print(ADD_MESSAGE, newMagazine);
        } catch (LibmgrException e) {
            ui.print(e.getMessage());
        }
    }
}
