package seedu.duke.commands.calendar;

import org.junit.jupiter.api.Test;

import seedu.duke.commands.calendar.AddTodoCommand;
import seedu.duke.exceptions.calendar.InvalidDateException;

import static org.junit.jupiter.api.Assertions.assertEquals;

class AddTodoCommandTest {

    /**
     * Checks the date validation function for add todo command.
     */
    @Test
    void addTodoCommand_checkDate_returnFalse() {
        Boolean isValidDate = AddTodoCommand.isValid("11-22-2021");
        assertEquals(false, isValidDate);
    }

    /**
     * Checks the exception thrown when user gives incorrect
     * date for add todo command.
     */
    @Test
    void addTodoCommand_checkDate_throwInvalidDateException() {
        String todoDateStringFormat = "22-22-2021";
        try {
            AddTodoCommand.checkIfDateValid(todoDateStringFormat);
        } catch (Exception e) {
            assertEquals("Invalid date given!", e.getMessage());
        }
    }

}