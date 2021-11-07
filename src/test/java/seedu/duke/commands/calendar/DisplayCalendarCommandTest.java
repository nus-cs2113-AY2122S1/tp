package seedu.duke.commands.calendar;

import org.junit.jupiter.api.Test;
import seedu.duke.commands.Command;
import seedu.duke.commands.calendar.DisplayCommand;

import static org.junit.jupiter.api.Assertions.assertEquals;

class DisplayCalendarCommandTest {

    /**
     * Checks the exception thrown when user gives incorrect input for calendar command.
     */
    @Test
        void displayCalendar_wrongCommandFormat_throwException() {
        try {
            Command command = new DisplayCommand("calendar 22-2021");
        } catch (Exception e) {
            assertEquals("Invalid Input for Calendar Command!", e.getMessage());
        }
    }

}