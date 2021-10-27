package seedu.duke.commands;

import seedu.duke.data.Catalogue;
import seedu.duke.data.Item;
import seedu.duke.ui.TextUI;

import java.time.LocalDate;

import static seedu.duke.common.Messages.DIVIDER;
import static seedu.duke.common.Messages.LIST_DEADLINE_OVERDUE;
import static seedu.duke.common.Messages.LIST_DEADLINE_TODAY;

/**
 * Command that lists out the items according to their return deadlines.
 */
public class DeadlineCommand extends Command {
    public static final String COMMAND_WORD = "deadline";
    public static final String DEADLINE_TODAY_COMMAND = "deadline today";
    public static final String DEADLINE_OVERDUE_COMMAND = "deadline overdue";
    public String input = "";

    /**
     * Single constructor, no parameters.
     */
    public DeadlineCommand(String input) {
        this.input = input.strip();
    }

    /**
     * Prints out items in the list according to their return deadline.
     * Overrides method from parent class.
     * @param ui Object that handles user IO
     * @param catalogue Object that stores the list of all items
     */
    @Override
    public void execute(TextUI ui, Catalogue catalogue) {
        LocalDate today = LocalDate.now();
        if (input.equals(DEADLINE_TODAY_COMMAND)) {
            ui.print(LIST_DEADLINE_TODAY);
            ui.print(DIVIDER);
            for (Item temp : catalogue.getAllItems()) {
                if (temp.getDueDate().equals(today)) {
                    ui.print(temp);
                }
            }
        } else if (input.equals(DEADLINE_OVERDUE_COMMAND)) {
            ui.print(LIST_DEADLINE_OVERDUE);
            ui.print(DIVIDER);
            for (Item temp : catalogue.getAllItems()) {
                if (temp.getDueDate().isBefore(today)) {
                    ui.print(temp);
                }
            }
        }
        ui.print(DIVIDER);
    }
}