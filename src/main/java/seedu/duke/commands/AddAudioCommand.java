package seedu.duke.commands;

import seedu.duke.common.LibmgrException;
import seedu.duke.common.Status;
import seedu.duke.data.Audio;
import seedu.duke.data.Catalogue;
import seedu.duke.ui.TextUI;

import java.util.HashMap;

import static seedu.duke.common.Messages.INVALID_VALUES;
import static seedu.duke.common.Messages.KEY_ARTIST;
import static seedu.duke.common.Messages.KEY_DURATION;
import static seedu.duke.common.Messages.KEY_ID;
import static seedu.duke.common.Messages.KEY_TITLE;
import static seedu.duke.common.Messages.WARN_INVALID_ARGS;

//@@author exetr
/**
 * This class consists of the logic for the AddAudio command.
 */
public class AddAudioCommand extends Command {
    public static final String COMMAND_FORMAT = "  (!) Format: add a t/TITLE i/ID a/ARTIST d/DURATION";
    public static final String ADD_MESSAGE = "  (+) Added new audio item to the catalogue";
    public static final String COMMAND_WORD = "add a";


    private HashMap<String, String> args;
    private String title;
    private String id;
    private Status status;
    private String artist;
    private String duration;

    /**
     * Sole constructor.
     * Status is set to AVAILABLE by default.
     * @param args Hashmap containing all arguments supplied by user.
     *             Key represents the type of argument (null represents command word)
     *             Value represents value associated with argument type
     */
    public AddAudioCommand(HashMap<String, String> args) {
        this.args = args;
        this.title = args.get(KEY_TITLE);
        this.id = args.get(KEY_ID);
        this.status = Status.AVAILABLE;
        this.artist = args.get(KEY_ARTIST);
        this.duration = args.get(KEY_DURATION);
        assert args != null : "Args hashmap is null";
    }

    /**
     * Checks for whether user has supplied any empty values any of the attributes.
     * @return Boolean True if any attributes are missing
     */
    private Boolean checkMissingArgs() {
        return title == null | id == null | artist == null | duration == null;
    }

    /**
     * Checks for whether user supplied extra arguments, program will not record these
     * additional arguments.
     * @return Boolean True if any additional arguments detected
     */
    private Boolean checkAdditionalArgs() {
        HashMap<String, String> tempArgs = args;
        tempArgs.remove(null);
        tempArgs.remove(KEY_TITLE);
        tempArgs.remove(KEY_ID);
        tempArgs.remove(KEY_ARTIST);
        tempArgs.remove(KEY_DURATION);
        return tempArgs.size() > 0;
    }

    /**
     * Executes add audio command.
     * Checks for missing and/or additional arguments, then adds to catalogue.
     * Overrides method from parent Command class.
     * @param ui Object that handles user IO
     * @param catalogue Object that encapsulates the library catalogue
     */
    @Override
    public void execute(TextUI ui, Catalogue catalogue) {
        if (checkMissingArgs()) {
            ui.print(INVALID_VALUES + System.lineSeparator() + COMMAND_FORMAT);
            return;
        }
        if (checkAdditionalArgs()) {
            ui.print(WARN_INVALID_ARGS);
        }
        try {
            Audio newAudio = new Audio(title, id, status, null, null, artist, duration);
            assert newAudio != null : "newAudio is null";
            catalogue.add(newAudio);
            ui.print(ADD_MESSAGE, newAudio);
        } catch (LibmgrException e) {
            ui.print(e.getMessage());
        }
    }
}
