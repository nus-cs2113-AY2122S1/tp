package seedu.duke.commands;

import seedu.duke.common.Messages;
import seedu.duke.data.Audio;
import seedu.duke.data.Catalogue;
import seedu.duke.data.Item;
import seedu.duke.ui.TextUI;

import java.util.HashMap;

import static seedu.duke.common.Messages.*;
import static seedu.duke.common.Messages.WARN_INVALID_ARGS;

public class EditAudioCommand extends Command {
    private Audio toEdit;
    private HashMap<String, String> args;
    private String title;
    private String id;
    private String artist;
    private String duration;

    public EditAudioCommand(HashMap<String,String> args, Item toEdit) {
        this.toEdit = (Audio) toEdit;
        this.args = args;
    }

    public void processArgs() {
        if (args.containsKey(KEY_TITLE)) {
            this.title = args.get(KEY_TITLE);
        }
        if (args.containsKey(KEY_ID)) {
            this.id = args.get(KEY_ID);
        }
        if (args.containsKey(KEY_ARTIST)) {
            this.artist = args.get(KEY_ARTIST);
        }
        if (args.containsKey(KEY_DURATION)) {
            this.duration = args.get(KEY_DURATION);
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
        if (args.containsKey(KEY_ARTIST)) {
            tempArgs.remove(KEY_ARTIST);
        }
        if (args.containsKey(KEY_DURATION)) {
            tempArgs.remove(KEY_DURATION);
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
        boolean isMissingArtist = args.containsKey(KEY_ARTIST) && (artist == null || artist.equals(""));
        boolean isMissingDuration = args.containsKey(KEY_DURATION) && (duration == null || duration.equals(""));
        return isMissingTitle || isMissingId || isMissingArtist || isMissingDuration;
    }

    public boolean checkEmptyArgs() {
        return !args.containsKey(KEY_TITLE) && !args.containsKey(KEY_ID)
                && !args.containsKey(KEY_ARTIST) && !args.containsKey(KEY_DURATION);
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
        }
        if (args.containsKey(KEY_ID)) {
            toEdit.setID(id);
        }
        if (args.containsKey(KEY_ARTIST)) {
            toEdit.setArtist(artist);
        }
        if (args.containsKey(KEY_DURATION)) {
            toEdit.setDuration(duration);
        }
        ui.print(EDIT_AUDIO_MESSAGE, toEdit);
    }

}
