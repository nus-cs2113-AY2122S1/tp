package seedu.duke.commands;

import seedu.duke.common.LibmgrException;
import seedu.duke.data.Catalogue;
import seedu.duke.data.Item;
import seedu.duke.ui.TextUI;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import static seedu.duke.common.Messages.DIVIDER;
import static seedu.duke.common.Messages.EMPTY_DATE;
import static seedu.duke.common.Messages.EMPTY_DEADLINE_COMMAND;
import static seedu.duke.common.Messages.INVALID_DATE;
import static seedu.duke.common.Messages.INVALID_DEADLINE_COMMAND;
import static seedu.duke.common.Messages.LIST_DEADLINE_DATE;
import static seedu.duke.common.Messages.LIST_DEADLINE_OVERDUE;
import static seedu.duke.common.Messages.LIST_DEADLINE_TODAY;

//@@author dyahnafisah
/**
 * Command that lists out the items according to their return deadlines.
 */
public class DeadlineCommand extends Command {
    public static final String COMMAND_WORD = "deadline";
    public static final String DESCRIPTION_TODAY = "today";
    public static final String DESCRIPTION_OVERDUE = "overdue";
    public static final String dateFormat = "dd-MM-yyyy";
    protected DateTimeFormatter dtFormatter = DateTimeFormatter.ofPattern(dateFormat);
    public String input = "";

    /**
     * Single constructor.
     *
     * @param input Input given by the user
     */
    public DeadlineCommand(String input) {
        this.input = input.strip();
    }

    /**
     * Processes <b>deadline</b> Command, including handle exceptions.
     * @param ui Object that handles user IO
     * @param catalogue Object that stores the list of all items
     */
    public void handleDeadlineCommand(TextUI ui, Catalogue catalogue) throws LibmgrException {
        LocalDate today = LocalDate.now();
        String desc = input.substring(8).trim();
        if (desc.equals(DESCRIPTION_TODAY)) {
            ui.print(LIST_DEADLINE_TODAY);
            ui.print(DIVIDER);
            for (Item temp : catalogue.getAllItems()) {
                if (temp.getDueDate() != null && temp.getDueDate().equals(today)) {
                    ui.print(temp);
                }
            }
        } else if (desc.equals(DESCRIPTION_OVERDUE)) {
            ui.print(LIST_DEADLINE_OVERDUE);
            ui.print(DIVIDER);
            for (Item temp : catalogue.getAllItems()) {
                if (temp.getDueDate() != null && temp.getDueDate().isBefore(today)) {
                    ui.print(temp);
                }
            }
        } else if (desc.contains("d/")) {
            LocalDate dueDate = LocalDate.parse(input.split("d/")[1], dtFormatter);
            ui.print(LIST_DEADLINE_DATE + dueDate.format(dtFormatter));
            ui.print(DIVIDER);
            for (Item temp : catalogue.getAllItems()) {
                if (temp.getDueDate() != null && temp.getDueDate().equals(dueDate)) {
                    ui.print(temp);
                }
            }
        } else if (input.equals(COMMAND_WORD)) {
            throw new LibmgrException(EMPTY_DEADLINE_COMMAND);
        } else {
            throw new LibmgrException(INVALID_DEADLINE_COMMAND);
        }
        ui.print(DIVIDER);
    }

    /**
     * Prints out items in the list according to their return deadline.
     * Overrides method from parent class.
     * @param ui Object that handles user IO
     * @param catalogue Object that stores the list of all items
     */
    @Override
    public void execute(TextUI ui, Catalogue catalogue) throws LibmgrException {
        try {
            handleDeadlineCommand(ui, catalogue);
        } catch (DateTimeParseException e) {
            ui.print(INVALID_DATE);
        } catch (StringIndexOutOfBoundsException e) {
            ui.print(INVALID_DATE);
        } catch (IndexOutOfBoundsException e) {
            ui.print(EMPTY_DATE);
        } catch (LibmgrException e) {
            ui.print(e.getMessage());
        }
    }
}