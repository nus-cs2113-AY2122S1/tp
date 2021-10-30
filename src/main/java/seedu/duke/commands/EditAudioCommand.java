package seedu.duke.commands;

import seedu.duke.common.LibmgrException;
import seedu.duke.common.Status;
import seedu.duke.data.Audio;
import seedu.duke.data.Catalogue;
import seedu.duke.data.Item;
import seedu.duke.ui.TextUI;

import java.util.HashMap;

import static seedu.duke.common.Messages.*;
import static seedu.duke.common.Messages.WARN_ADDITIONAL_ARGS;

public class EditAudioCommand extends Command {
    private Item toEdit;
    private HashMap<String, String> args;
    private String title;
    private String id;
    private String artist;
    private String duration;


    public EditAudioCommand(HashMap<String,String> args, Item toEdit) {
        this.toEdit = toEdit;
        this.args = args;
    }

    public boolean checkInvalidArgs() {
        for (String s: args.keySet()) {
            if (s.equals(KEY_TITLE)) {
                this.title = args.get(s);
            } else if (s.equals(KEY_ID)) {
                this.id = args.get(s);
            } else if (s.equals(KEY_ARTIST)) {
                this.artist = args.get(s);
            } else if (s.equals(KEY_DURATION)) {
                this.duration = args.get(s);
            }
    }

    /**
     * Checks for whether user has supplied any empty values any of the attributes.
     * @return Boolean True if any attributes are missing
     */
    private boolean checkMissingArgs() {
        boolean isMissingTitle = title == null || title.equals("");
        boolean isMissingId = id == null || id.equals("");
        boolean isMissingArtist = artist == null || artist.equals("");
        boolean isMissingDuration = duration == null || duration.equals("");
        return isMissingTitle || isMissingId || isMissingArtist || isMissingDuration;
    }

    /**
     * Checks for whether user supplied extra arguments, program will not record these
     * additional arguments.
     * @return boolean True if any additional arguments detected
     */
    private boolean checkAdditionalArgs() {
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
        if (checkMissingArgs()) {
            ui.print(INVALID_VALUES + System.lineSeparator() + COMMAND_FORMAT);
            return;
        }
        if (checkAdditionalArgs()) {
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
