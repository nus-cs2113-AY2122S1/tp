package seedu.duke.commands;

import seedu.duke.common.LibmgrException;
import seedu.duke.data.Catalogue;
import seedu.duke.ui.TextUI;

import java.util.HashMap;

import static seedu.duke.common.Messages.ADD_INVALID_FORMAT;
import static seedu.duke.common.Messages.INVALID_VALUES;

/**
 * Class encapsulating an add command.
 */
public class AddCommand extends Command {
    public static final String COMMAND_FORMAT = "  Format: add [a|b|m|v] [ARGUMENTS]";
    public static final String COMMAND_WORD = "add";

    public static final String COMMAND_ADD_BOOK = "add b";
    public static final String COMMAND_ADD_MAGAZINE = "add m";
    public static final String COMMAND_ADD_VIDEO = "add v";
    protected HashMap<String, String> args;

    /**
     * Sole Constructor.
     * @param args Arguments supplied by user in the add command
     */
    public AddCommand(HashMap<String, String> args) {
        this.args = args;
    }

    /**
     * Executes add command.
     * Overrides method from parent class.
     *
     * @param ui Object that handles user IO
     * @param catalogue Object that encapsulates the library catalogue
     */
    @Override
    public void execute(TextUI ui, Catalogue catalogue) {
        switch (args.get(null)) {
        case AddAudioCommand.COMMAND_WORD:
            new AddAudioCommand(args).execute(ui, catalogue);
            break;
        case COMMAND_ADD_BOOK:
            new AddBookCommand(args).execute(ui, catalogue);
            break;
        case COMMAND_ADD_MAGAZINE:
            new AddMagazineCommand(args).execute(ui, catalogue);
            break;
        case COMMAND_ADD_VIDEO:
            break;
        default:
            ui.print(INVALID_VALUES + System.lineSeparator() + COMMAND_FORMAT);
        }
    }
}
