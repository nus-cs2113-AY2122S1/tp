package seedu.duke.commands;

import seedu.duke.common.LibmgrException;
import seedu.duke.common.Messages;
import seedu.duke.data.Audio;
import seedu.duke.data.Catalogue;
import seedu.duke.data.Item;
import seedu.duke.ui.TextUI;

import java.util.HashMap;

import static seedu.duke.common.Messages.EDIT_AUDIO_INVALID_FORMAT;
import static seedu.duke.common.Messages.EDIT_AUDIO_MESSAGE;
import static seedu.duke.common.Messages.EDIT_UNCHANGED_ARTIST;
import static seedu.duke.common.Messages.EDIT_UNCHANGED_DURATION;
import static seedu.duke.common.Messages.EDIT_UNCHANGED_ID;
import static seedu.duke.common.Messages.EDIT_UNCHANGED_TITLE;
import static seedu.duke.common.Messages.KEY_ARTIST;
import static seedu.duke.common.Messages.KEY_DURATION;
import static seedu.duke.common.Messages.KEY_ID;
import static seedu.duke.common.Messages.KEY_TITLE;
import static seedu.duke.common.Messages.WARN_INVALID_ARGS;

//@@author avellinwong01
/**
 * Class encapsulating an Edit Audio Command.
 */
public class EditAudioCommand extends Command {
    private Audio toEdit;
    private HashMap<String, String> args;
    private String title;
    private String id;
    private String artist;
    private String duration;

    /**
     * Sole Constructor.
     *
     * @param toEdit The Audio item to edit
     * @param args Hashmap containing all arguments supplied by the user.
     *             Key represents the type of argument (null represents command word and id of item to edit)
     *             Value represents value associated with argument type
     */
    public EditAudioCommand(HashMap<String,String> args, Item toEdit) {
        this.toEdit = (Audio) toEdit;
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
     * Checks for whether user has supplied any empty values to any of the attributes to be edited.
     *
     * @return boolean True if any attribute values are missing
     */
    private boolean checkMissingArgs() {
        boolean isMissingTitle = args.containsKey(KEY_TITLE) && (title == null || title.equals(""));
        boolean isMissingId = args.containsKey(KEY_ID) && (id == null || id.equals(""));
        boolean isMissingArtist = args.containsKey(KEY_ARTIST) && (artist == null || artist.equals(""));
        boolean isMissingDuration = args.containsKey(KEY_DURATION) && (duration == null || duration.equals(""));
        return isMissingTitle || isMissingId || isMissingArtist || isMissingDuration;
    }

    /**
     * Checks for whether user has not supplied any valid arguments (attribute keys) to be edited.
     *
     * @return boolean True if no valid arguments are entered
     */
    public boolean checkEmptyArgs() {
        return !args.containsKey(KEY_TITLE) && !args.containsKey(KEY_ID)
                && !args.containsKey(KEY_ARTIST) && !args.containsKey(KEY_DURATION);
    }

    /**
     * Executes Edit Audio command.
     * Overrides method from parent class.
     *
     * @param ui Object that handles user IO
     * @param catalogue Object that encapsulates the library catalogue
     */
    @Override
    public void execute(TextUI ui, Catalogue catalogue) {
        processArgs();
        if (checkMissingArgs() || checkEmptyArgs()) {
            ui.print(EDIT_AUDIO_INVALID_FORMAT);
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
        if (args.containsKey(KEY_ARTIST)) {
            assert artist != null && !artist.equals("");
            if (toEdit.getArtist().equals(artist)) {
                ui.print(EDIT_UNCHANGED_ARTIST);
                return;
            }
            toEdit.setArtist(artist);
        }
        if (args.containsKey(KEY_DURATION)) {
            assert duration != null && !duration.equals("");
            if (toEdit.getDuration().equals(duration)) {
                ui.print(EDIT_UNCHANGED_DURATION);
                return;
            }
            toEdit.setDuration(duration);
        }
        ui.print(EDIT_AUDIO_MESSAGE, toEdit);
    }
}
//@@author avellinwong01
