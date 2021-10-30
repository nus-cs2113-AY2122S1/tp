package seedu.duke.commands;

import seedu.duke.common.LibmgrException;
import seedu.duke.data.*;
import seedu.duke.ui.TextUI;

import java.util.HashMap;

import static seedu.duke.common.Messages.*;

public class EditCommand2 extends Command {
    public static final String COMMAND_WORD = "edit";
    protected HashMap<String, String> args;

    /**
     * Sole Constructor.
     * @param args Arguments supplied by user in the Edit command
     */
    public EditCommand2(HashMap<String, String> args) {
        this.args = args;
    }

    public void handlesEditCommand(TextUI ui, Catalogue catalogue) throws LibmgrException {
        String commandWord = args.get(null);
        String[] command = commandWord.split("\\s+");
        if (!command[0].equals(COMMAND_WORD) || command.length != 2) {
            throw new LibmgrException(EDIT_INVALID_FORMAT);
        }
        Item toEdit;
        try {
            toEdit = catalogue.getItem(command[1]); // command[1] is the ID
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

    @Override
    public void execute(TextUI ui, Catalogue catalogue) {
        try {
            handlesEditCommand(ui, catalogue);
        } catch (LibmgrException e) {
            ui.print(e.getMessage());
        }
    }
}
