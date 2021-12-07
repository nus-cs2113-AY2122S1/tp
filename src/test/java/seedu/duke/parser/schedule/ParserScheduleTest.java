package seedu.duke.parser.schedule;

import org.junit.jupiter.api.Test;

import java.time.YearMonth;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.duke.constants.Messages.NAME_DIVIDER_NOT_FOUND;
import static seedu.duke.constants.Messages.DATE_DIVIDER_NOT_FOUND;

//@author swatimahadevan
class ParserScheduleTest {

    static ParserSchedule parser = new ParserSchedule();

    /**
     * Checks if the YearMonth object is returned correctly.
     */
    @Test
    void formatYearMonth() {
        assertEquals(YearMonth.of(2021, 10), parser.parseCalendarCommandForJunit("calendar display 10-2021"));
    }

    /**
     * Checks the argument values returned when there are incorrect arguments.
     */
    @Test
    void parseTodoCommand_noTaskName_returnNullArguments() {
        ArrayList<String> todoArguments = null;
        try {
            todoArguments = parser.parseTodoArgumentsArray("calendar todo n/ ");
        } catch (Exception e) {
            assertEquals(null, todoArguments);
        }

    }

    /**
     * Checks the exception thrown when date is incorrect in add todo command.
     */
    @Test
    void parseTodoCommand_throwExceptionInvalidDate() {
        try {
            parser.parseTodoCommand("calendar todo n/ random d/ 22-22-2021");
        } catch (Exception e) {
            assertEquals("Invalid date given!", e.getMessage());
        }
    }

    /**
     * Checks the exception thrown when "n/" is not found in add todo command.
     */
    @Test
    void parseTodoCommand_throwException_taskNameDividerNotFound() {
        try {
            parser.parseTodoCommand("calendar todo random d/ 10-10-2021");
        } catch (Exception e) {
            assertEquals(NAME_DIVIDER_NOT_FOUND, e.getMessage());
        }
    }

    /**
     * Checks the exception thrown when "d/" is not found in add todo command.
     */
    @Test
    void parseTodoCommand_throwException_taskDateDividerNotFound() {
        try {
            parser.parseTodoCommand("calendar todo n/ random 10-10-2021");
        } catch (Exception e) {
            assertEquals(DATE_DIVIDER_NOT_FOUND, e.getMessage());
        }
    }
}