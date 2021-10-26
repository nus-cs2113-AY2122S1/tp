package seedu.duke.commands;

import seedu.duke.common.LibmgrException;
import seedu.duke.common.Status;
import seedu.duke.data.Catalogue;
import seedu.duke.data.Video;
import seedu.duke.ui.TextUI;

import java.util.HashMap;

import static seedu.duke.common.Messages.INVALID_VALUES;
import static seedu.duke.common.Messages.WARN_ADDITIONAL_ARGS;

public class AddVideoCommand extends Command {
    public static final String COMMAND_FORMAT = "  Format: add b t/TITLE i/ID p/PUBLISHER e/DURATION";
    public static final String ADD_MESSAGE = "  (+) Added new video item to the catalogue";
    public static final String COMMAND_WORD = "add b";
    public static final String KEY_TITLE = "t";
    public static final String KEY_ID = "i";
    public static final String KEY_PUBLISHER = "p";
    public static final String KEY_DURATION = "d";

    private HashMap<String, String> args;
    private String title;
    private String id;
    private Status status;
    private String publisher;
    private String duration;

    public AddVideoCommand(HashMap<String, String> args) {
        this.args = args;
        this.title = args.get(KEY_TITLE);
        this.id = args.get(KEY_ID);
        this.status = Status.AVAILABLE;
        this.publisher = args.get(KEY_PUBLISHER);
        this.duration = args.get(KEY_DURATION);
    }

    private Boolean checkMissingArgs() {
        return title == null | id == null | publisher == null | duration == null;
    }

    private Boolean checkAdditionalArgs() {
        HashMap<String, String> tempArgs = args;
        tempArgs.remove(null);
        tempArgs.remove(KEY_TITLE);
        tempArgs.remove(KEY_ID);
        tempArgs.remove(KEY_PUBLISHER);
        tempArgs.remove(KEY_DURATION);
        return tempArgs.size() > 0;
    }

    @Override
    public void execute(TextUI ui, Catalogue catalogue) {
        if(checkMissingArgs()) {
            ui.print(INVALID_VALUES + System.lineSeparator() + COMMAND_FORMAT);
            return;
        }
        if(checkAdditionalArgs()) {
            ui.print(WARN_ADDITIONAL_ARGS);
        }
        try {
            Video newVideo = new Video(title, id, status, publisher, duration);
            catalogue.add(newVideo);
            ui.print(ADD_MESSAGE, newVideo);
        } catch (LibmgrException e) {
            ui.print(e.getMessage());
        }
    }
}
