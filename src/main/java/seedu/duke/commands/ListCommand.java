package seedu.duke.commands;

import seedu.duke.LibmgrException;
import seedu.duke.Status;
import seedu.duke.data.Catalogue;
import seedu.duke.data.Item;
import seedu.duke.ui.TextUI;

import static seedu.duke.common.Messages.LIST_AVAILABLE_MESSAGE;
import static seedu.duke.common.Messages.LIST_ALL_MESSAGE;
import static seedu.duke.common.Messages.LIST_LOANED_MESSAGE;
import static seedu.duke.common.Messages.DIVIDER;
import static seedu.duke.common.Messages.LIST_FORMAT_INCORRECT;

/**
 * Command that lists out all items, or only available or loaned items,
 * based on the exact command.
 */
public class ListCommand extends Command {
    public static final String COMMAND_WORD = "list";
    public static final String LIST_ALL_COMMAND = "list all";
    public static final String LIST_AVAILABLE_COMMAND = "list available";
    public static final String LIST_LOANED_COMMAND = "list loaned";
    public String input = "";

    /**
     * Single constructor that takes in the input line as argument.
     * @param input The whole input line
     */
    public ListCommand(String input) {
        this.input = input.strip();
    }

    /**
     * Process the list command, including exceptions.
     * @param ui Object that handles user IO
     * @param catalogue Object that encapsulates the library catalogue
     * @throws LibmgrException when user input is invalid
     */

    public void handlesListCommand(TextUI ui, Catalogue catalogue) throws LibmgrException {
        if (input.equals(LIST_ALL_COMMAND)) {
            ui.print(LIST_ALL_MESSAGE);
            ui.print(DIVIDER);
            for (Item temp : catalogue.getAllItems()) {
                ui.print(temp);
            }
            ui.print(DIVIDER);
        } else if (input.equals(LIST_AVAILABLE_COMMAND)) {
            ui.print(LIST_AVAILABLE_MESSAGE);
            ui.print(DIVIDER);
            for (Item temp : catalogue.getAllItems()) {
                if (temp.getStatus().equals(Status.AVAILABLE)) {
                    ui.print(temp);
                }
            }
            ui.print(DIVIDER);
        } else if (input.equals(LIST_LOANED_COMMAND)) {
            ui.print(LIST_LOANED_MESSAGE);
            ui.print(DIVIDER);
            for (Item temp : catalogue.getAllItems()) {
                if (temp.getStatus().equals(Status.LOANED)) {
                    ui.print(temp);
                }
            }
            ui.print(DIVIDER);
        } else {
            throw new LibmgrException(LIST_FORMAT_INCORRECT);
        }
    }

    /**
     * Executes list command.
     * Overrides method from parent class.
     *
     * @param ui Object that handles user IO
     * @param catalogue Object that encapsulates the library catalogue
     */
    @Override
    public void execute(TextUI ui, Catalogue catalogue) {
        try {
            handlesListCommand(ui, catalogue);
        } catch (LibmgrException e) {
            ui.print(e.getMessage());
        }
    }

}