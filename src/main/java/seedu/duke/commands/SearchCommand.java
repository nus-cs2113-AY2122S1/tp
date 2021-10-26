package seedu.duke.commands;

import seedu.duke.common.LibmgrException;
import seedu.duke.data.Catalogue;
import seedu.duke.data.Item;
import seedu.duke.ui.TextUI;

import static seedu.duke.common.Messages.SEARCH_MESSAGE;
import static seedu.duke.common.Messages.SEARCH_FORMAT_INCORRECT;
import static seedu.duke.common.Messages.DIVIDER;

/**
 * Command that lists out all items that matches the search criteria.
 * For searching by ID and STATUS, the keyword must be exactly the same.
 * For searching by TITLE, the keyword can be part of the actual title.
 */
public class SearchCommand extends Command {
    public static final String COMMAND_WORD = "search";
    public static final String SEARCH_ID_COMMAND = "search i/";
    public static final String SEARCH_TITLE_COMMAND = "search t/";
    public static final String SEARCH_STATUS_COMMAND = "search s/";
    public String input = "";

    /**
     * Single constructor that takes in the input line as argument.
     * @param input The whole input line
     */
    public SearchCommand(String input) {
        this.input = input.strip();
    }

    /**
     * Process the search command, including exceptions.
     * @param ui Object that handles user IO
     * @param catalogue Object that encapsulates the library catalogue
     * @throws LibmgrException when user input is invalid
     */
    public void handlesSearchCommand(TextUI ui, Catalogue catalogue) throws LibmgrException {
        if (input.length() <= 9) {
            throw new LibmgrException(SEARCH_FORMAT_INCORRECT);
        }
        if (input.startsWith(SEARCH_ID_COMMAND)) {
            ui.print(SEARCH_MESSAGE);
            ui.print(DIVIDER);
            for (Item temp : catalogue.getAllItems()) {
                if (temp.getID().equals(input.substring(SEARCH_ID_COMMAND.length()))) {
                    ui.print(temp);
                }
            }
        } else if (input.startsWith(SEARCH_TITLE_COMMAND)) {
            ui.print(SEARCH_MESSAGE);
            ui.print(DIVIDER);
            for (Item temp : catalogue.getAllItems()) {
                if (temp.getTitle().contains(input.substring(SEARCH_TITLE_COMMAND.length()))) {
                    ui.print(temp);
                }
            }
        } else if (input.startsWith(SEARCH_STATUS_COMMAND)) {
            ui.print(SEARCH_MESSAGE);
            ui.print(DIVIDER);
            for (Item temp : catalogue.getAllItems()) {
                if (temp.getStatus().name().equals(input.substring(SEARCH_STATUS_COMMAND.length()))) {
                    ui.print(temp);
                }
            }
        }
    }

    /**
     * Executes search command.
     * Overrides method from parent class.
     *
     * @param ui Object that handles user IO
     * @param catalogue Object that encapsulates the library catalogue
     */
    @Override
    public void execute(TextUI ui, Catalogue catalogue) {
        try {
            handlesSearchCommand(ui, catalogue);
            ui.print(DIVIDER);
        } catch (LibmgrException e) {
            ui.print(e.getMessage());
        }
    }

}