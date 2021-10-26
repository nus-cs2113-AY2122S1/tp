package seedu.duke.commands;

import seedu.duke.LibmgrException;
import seedu.duke.Status;
import seedu.duke.data.Catalogue;
import seedu.duke.data.Item;
import seedu.duke.ui.TextUI;

import static seedu.duke.common.Messages.FORMAT_INCORRECT;
import static seedu.duke.common.Messages.INVALID_TITLE;
import static seedu.duke.common.Messages.INVALID_ID;
import static seedu.duke.common.Messages.ADD_MESSAGE;

/**
 * Class encapsulating an add command.
 */
public class AddCommand extends Command {
    public static final String COMMAND_WORD = "add";
    protected String args; // Format: add t/TITLE i/ID
    protected String title;
    protected String id;

    /**
     * Sole Constructor.
     *
     * @param args Arguments supplied by user in the add command
     */
    public AddCommand(String args) {
        this.args = args;
    }

    /**
     * Processes Add Command, including exceptions.
     *
     * @param ui Object that handles user IO
     * @param catalogue Object that encapsulates the library catalogue
     * @throws LibmgrException when user input is invalid
     */
    public void handlesAddCommand(TextUI ui, Catalogue catalogue) throws LibmgrException {
        if (args.length() <= 4 || !args.contains("t/") || !args.contains("i/")) {
            throw new LibmgrException(FORMAT_INCORRECT);
        }
        String parameters = args.substring(COMMAND_WORD.length() + 1);
        String[] argList = parameters.split("/");
        int stringLen = argList[1].length();
        // Check for wrong format order: i/ID t/TITLE
        if (!argList[0].equals("t") || !argList[1].substring(stringLen - 1).equals("i")) {
            throw new LibmgrException(FORMAT_INCORRECT);
        }
        int endIndex = argList[1].length() - 1;
        if (endIndex <= 0) {
            throw new LibmgrException(INVALID_TITLE);
        }
        title = argList[1].substring(0, endIndex).trim();
        if (title.equals("")) {
            throw new LibmgrException(INVALID_TITLE);
        }
        if (argList.length < 3) {
            throw new LibmgrException(INVALID_ID);
        }
        id = argList[2].trim();
        if (id.equals("")) {
            throw new LibmgrException(INVALID_ID);
        }
        Item newItem = new Item(title, id, Status.AVAILABLE);
        catalogue.add(newItem);
        ui.print(ADD_MESSAGE, newItem);
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
        try {
            handlesAddCommand(ui, catalogue);
        } catch (LibmgrException e) {
            ui.print(e.getMessage());
        }
    }
}
