package seedu.duke.commands;

import seedu.duke.data.Catalogue;
import seedu.duke.ui.TextUI;

import java.util.HashMap;

import static seedu.duke.common.Messages.INVALID_VALUES;

//@@author exetr
/**
 * Class encapsulating an add command.
 */
public class AddCommand extends Command {
    public static final String COMMAND_FORMAT = "  Format: add [a|b|i|m|v] [ARGUMENTS]";
    public static final String COMMAND_WORD = "add";
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
        case AddBookCommand.COMMAND_WORD:
            new AddBookCommand(args).execute(ui, catalogue);
            break;
        case AddItemCommand.COMMAND_WORD:
            new AddItemCommand(args).execute(ui, catalogue);
            break;
        case AddMagazineCommand.COMMAND_WORD:
            new AddMagazineCommand(args).execute(ui, catalogue);
            break;
        case AddVideoCommand.COMMAND_WORD:
            new AddVideoCommand(args).execute(ui, catalogue);
            break;
        default:
            ui.print(INVALID_VALUES + System.lineSeparator() + COMMAND_FORMAT);
        }
    }
}
