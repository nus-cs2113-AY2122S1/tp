package seedu.duke.commands;

import seedu.duke.common.LibmgrException;
import seedu.duke.common.Status;
import seedu.duke.data.Audio;
import seedu.duke.data.Catalogue;
import seedu.duke.ui.TextUI;

import java.util.HashMap;

import static seedu.duke.common.Messages.INVALID_VALUES;
import static seedu.duke.common.Messages.WARN_ADDITIONAL_ARGS;

public class AddAudioCommand extends Command {
    public static final String COMMAND_FORMAT = "  Format: add a t/TITLE i/ID a/ARTIST d/DURATION";
    public static final String ADD_MESSAGE = "  (+) Added new audio item to the catalogue";
    public static final String COMMAND_WORD = "add a";
    public static final String KEY_TITLE = "t";
    public static final String KEY_ID = "i";
    public static final String KEY_ARTIST = "a";
    public static final String KEY_DURATION = "d";


    private HashMap<String, String> args;
    private String title;
    private String id;
    private Status status;
    private String artist;
    private String duration;

    public AddAudioCommand(HashMap<String, String> args) {
        this.args = args;
        this.title = args.get(KEY_TITLE);
        this.id = args.get(KEY_ID);
        this.status = Status.AVAILABLE;
        this.artist = args.get(KEY_ARTIST);
        this.duration = args.get(KEY_DURATION);
    }

    private Boolean checkMissingArgs() {
        return title == null | id == null | artist == null | duration == null;
    }

    private Boolean checkAdditionalArgs() {
        HashMap<String, String> tempArgs = args;
        tempArgs.remove(null);
        tempArgs.remove(KEY_TITLE);
        tempArgs.remove(KEY_ID);
        tempArgs.remove(KEY_ARTIST);
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
            Audio newAudio = new Audio(title, id, status, artist, duration);
            catalogue.add(newAudio);
            ui.print(ADD_MESSAGE, newAudio);
        } catch (LibmgrException e) {
            ui.print(e.getMessage());
        }
    }
}
