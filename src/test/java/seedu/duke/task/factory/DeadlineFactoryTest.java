package seedu.duke.task.factory;

import java.util.HashMap;
import java.util.Map;

import org.junit.jupiter.api.Test;
import seedu.duke.command.flags.DeadlineFlag;
import seedu.duke.exception.GetTaskFailedException;
import seedu.duke.exception.InvalidRecurrenceException;
import seedu.duke.exception.ParseDateFailedException;
import seedu.duke.exception.RequiredArgmentNotProvidedException;
import seedu.duke.parser.DateParser;
import seedu.duke.task.PriorityEnum;
import seedu.duke.task.RecurrenceEnum;
import seedu.duke.task.TypeEnum;
import seedu.duke.task.type.Deadline;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

//@@author SeanRobertDH
class DeadlineFactoryTest {

    private static final String DESCRIPTION = "buy vegetables";
    private static final String VALID_DATE1 = "14-02-1998 02:00";

    @Test
    void getDeadline_validTodoInputs_expectDeadline() throws GetTaskFailedException {

        Map<String, String> arguments = new HashMap<>();

        arguments.put(DeadlineFlag.DESCRIPTION, DESCRIPTION);
        arguments.put(DeadlineFlag.DUE_DATE, VALID_DATE1);
        arguments.put(DeadlineFlag.PRIORITY, Integer.toString(PriorityEnum.HIGH.getValue()));
        arguments.put(DeadlineFlag.RECURRENCE, RecurrenceEnum.MONTHLY.toString());

        Deadline deadline = (Deadline) new DeadlineFactory(arguments).getTask();

        assertEquals(deadline.getDescription(), DESCRIPTION);
        assertEquals(DateParser.dateToString(deadline.getDueDate()), VALID_DATE1);
        assertEquals(deadline.getPriority(), PriorityEnum.HIGH);
        assertEquals(deadline.getRecurrence(), RecurrenceEnum.MONTHLY);
    }

    @Test
    void getDeadline_minimumDeadlineInputs_expectDeadline() throws GetTaskFailedException {
        Map<String, String> arguments = new HashMap<>();

        arguments.put(DeadlineFlag.DESCRIPTION, DESCRIPTION);
        arguments.put(DeadlineFlag.DUE_DATE, VALID_DATE1);

        Deadline deadline = (Deadline) new DeadlineFactory(arguments).getTask();

        assertEquals(deadline.getDescription(), DESCRIPTION);
        assertEquals(DateParser.dateToString(deadline.getDueDate()), VALID_DATE1);
        assertEquals(deadline.getPriority(), PriorityEnum.MEDIUM);
        assertEquals(deadline.getRecurrence(), RecurrenceEnum.NONE);
    }

    @Test
    void getDeadline_deadlineWithNoDueDate_expectGetTaskFailedException() {
        Map<String, String> arguments = new HashMap<>();

        arguments.put(DeadlineFlag.DESCRIPTION, DESCRIPTION);

        GetTaskFailedException thrown = assertThrows(
            GetTaskFailedException.class,
            () -> new DeadlineFactory(arguments).getTask());

        RequiredArgmentNotProvidedException ranpe =
            new RequiredArgmentNotProvidedException(DeadlineFlag.DUE_DATE, TypeEnum.DEADLINE.toString());

        assertEquals(thrown.getMessage(), ranpe.getMessage());
    }

    @Test
    void getDeadline_deadlineWithInvalidRecurrence_expectGetTaskFailedException() {
        Map<String, String> arguments = new HashMap<>();
        String invalidRecurrence = "wibble";

        arguments.put(DeadlineFlag.DESCRIPTION, DESCRIPTION);
        arguments.put(DeadlineFlag.DUE_DATE, VALID_DATE1);
        arguments.put(DeadlineFlag.RECURRENCE, invalidRecurrence);

        GetTaskFailedException thrown = assertThrows(
            GetTaskFailedException.class,
            () -> new DeadlineFactory(arguments).getTask());

        InvalidRecurrenceException ire = new InvalidRecurrenceException(invalidRecurrence);

        assertEquals(thrown.getMessage(), ire.getMessage());
    }

    @Test
    void getDeadline_deadlineWithInvalidDate_expectGetTaskFailedException() {
        Map<String, String> arguments = new HashMap<>();
        String invalidDate = "blarg";

        arguments.put(DeadlineFlag.DESCRIPTION, DESCRIPTION);
        arguments.put(DeadlineFlag.DUE_DATE, "blarg");

        GetTaskFailedException thrown = assertThrows(
            GetTaskFailedException.class,
            () -> new DeadlineFactory(arguments).getTask());

        ParseDateFailedException pdfe = new ParseDateFailedException(DateParser.getDefaultDateFormat());

        assertEquals(thrown.getMessage(), pdfe.getMessage());
    }
}