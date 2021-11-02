package seedu.duke.commands;

import seedu.duke.common.LibmgrException;
import seedu.duke.data.Audio;
import seedu.duke.data.Book;
import seedu.duke.data.Catalogue;
import seedu.duke.data.Item;
import seedu.duke.data.Magazine;
import seedu.duke.data.Video;
import seedu.duke.ui.TextUI;

import java.util.HashMap;

import static seedu.duke.common.Messages.EDIT_INVALID_FORMAT;
import static seedu.duke.common.Messages.EDIT_INVALID_ITEM;

//@@author avellinwong01
/**
 * Class encapsulating an edit command.
 */
public class EditCommand extends Command {
    public static final String COMMAND_WORD = "edit";
    protected HashMap<String, String> args;
    private Item toEdit;

    /**
     * Sole Constructor.
     *
     * @param args Arguments supplied by user in the Edit command
     */
    public EditCommand(HashMap<String, String> args) {
        this.args = args;
    }

    /**
     * Processes Edit Command, including exceptions.
     *
     * @param ui Object that handles user IO
     * @param catalogue Object that encapsulates a library catalogue
     * @throws LibmgrException when user input is invalid
     */
    public void handlesEditCommand(TextUI ui, Catalogue catalogue) throws LibmgrException {
        String commandWord = args.get(null).strip();
        String[] command = commandWord.split("\\s+");
        if (!command[0].equals(COMMAND_WORD) || command.length < 2) {
            throw new LibmgrException(EDIT_INVALID_FORMAT);
        }
        String id = commandWord.substring(COMMAND_WORD.length() + 1).strip();
        try {
            toEdit = catalogue.getItem(id);
        } catch (NullPointerException e) {
            throw new LibmgrException(EDIT_INVALID_ITEM);
        }
        if (toEdit instanceof Book) {
            new EditBookCommand(args, toEdit).execute(ui, catalogue);
        } else if (toEdit instanceof Audio) {
            new EditAudioCommand(args, toEdit).execute(ui, catalogue);
        } else if (toEdit instanceof Magazine) {
            new EditMagazineCommand(args, toEdit).execute(ui, catalogue);
        } else if (toEdit instanceof Video) {
            new EditVideoCommand(args, toEdit).execute(ui, catalogue);
        }

    }

    /**
     * Executes edit command.
     * Overrides method from parent class.
     *
     * @param ui Object that handles user IO
     * @param catalogue Object that encapsulates the library catalogue
     */
    @Override
    public void execute(TextUI ui, Catalogue catalogue) {
        try {
            handlesEditCommand(ui, catalogue);
        } catch (LibmgrException e) {
            ui.print(e.getMessage());
        }
    }
}
//@@author avellinwong01
