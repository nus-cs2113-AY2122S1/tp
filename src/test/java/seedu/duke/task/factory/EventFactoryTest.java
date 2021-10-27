package seedu.duke.task.factory;

import java.util.HashMap;
import java.util.Map;

import org.junit.jupiter.api.Test;
import seedu.duke.command.flags.EventFlag;
import seedu.duke.exception.GetTaskFailedException;
import seedu.duke.exception.InvalidPriorityException;
import seedu.duke.exception.RequiredArgmentNotProvidedException;
import seedu.duke.exception.StartDateAfterEndDateException;
import seedu.duke.parser.DateParser;
import seedu.duke.task.PriorityEnum;
import seedu.duke.task.RecurrenceEnum;
import seedu.duke.task.TypeEnum;
import seedu.duke.task.type.Event;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

//@@author SeanRobertDH
class EventFactoryTest {
    private static final String DESCRIPTION = "buy vegetables";
    private static final String VALID_DATE1 = "14-02-1998 02:00";
    private static final String VALID_DATE2 = "14-02-1998 03:30";

    @Test
    void getEvent_validEventInputs_expectEvent() throws GetTaskFailedException {
        Map<String, String> arguments = new HashMap<>();

        arguments.put(EventFlag.DESCRIPTION, DESCRIPTION);
        arguments.put(EventFlag.START_DATE, VALID_DATE1);
        arguments.put(EventFlag.END_DATE, VALID_DATE2);
        arguments.put(EventFlag.PRIORITY, Integer.toString(PriorityEnum.LOW.getValue()));
        arguments.put(EventFlag.RECURRENCE, RecurrenceEnum.YEARLY.toString());

        Event event = (Event) new EventFactory(arguments).getTask();

        assertEquals(event.getDescription(), DESCRIPTION);
        assertEquals(DateParser.dateToString(event.getStartDate()), VALID_DATE1);
        assertEquals(DateParser.dateToString(event.getEndDate()), VALID_DATE2);
        assertEquals(event.getPriority(), PriorityEnum.LOW);
        assertEquals(event.getRecurrence(), RecurrenceEnum.YEARLY);
    }

    @Test
    void getEvent_minimumEventInputs_expectEvent() throws GetTaskFailedException {
        Map<String, String> arguments = new HashMap<>();

        arguments.put(EventFlag.DESCRIPTION, DESCRIPTION);
        arguments.put(EventFlag.START_DATE, VALID_DATE1);
        arguments.put(EventFlag.END_DATE, VALID_DATE2);

        Event event = (Event) new EventFactory(arguments).getTask();

        assertEquals(event.getDescription(), DESCRIPTION);
        assertEquals(DateParser.dateToString(event.getStartDate()), VALID_DATE1);
        assertEquals(DateParser.dateToString(event.getEndDate()), VALID_DATE2);
        assertEquals(event.getPriority(), PriorityEnum.MEDIUM);
        assertEquals(event.getRecurrence(), RecurrenceEnum.NONE);
    }

    @Test
    void getEvent_eventWithNoEndDate_expectGetTaskFailedException() {
        Map<String, String> arguments = new HashMap<>();

        arguments.put(EventFlag.DESCRIPTION, DESCRIPTION);
        arguments.put(EventFlag.START_DATE, VALID_DATE1);

        GetTaskFailedException thrown = assertThrows(
            GetTaskFailedException.class,
            () -> new EventFactory(arguments).getTask());

        RequiredArgmentNotProvidedException ranpe =
            new RequiredArgmentNotProvidedException(EventFlag.END_DATE, TypeEnum.EVENT.toString());

        assertEquals(thrown.getMessage(), ranpe.getMessage());
    }

    @Test
    void getEvent_eventWithNoStartDate_expectGetTaskFailedException() {
        Map<String, String> arguments = new HashMap<>();

        arguments.put(EventFlag.DESCRIPTION, DESCRIPTION);
        arguments.put(EventFlag.END_DATE, VALID_DATE2);

        GetTaskFailedException thrown = assertThrows(
            GetTaskFailedException.class,
            () -> new EventFactory(arguments).getTask());

        RequiredArgmentNotProvidedException ranpe =
            new RequiredArgmentNotProvidedException(EventFlag.START_DATE, TypeEnum.EVENT.toString());

        assertEquals(thrown.getMessage(), ranpe.getMessage());
    }

    @Test
    void getEvent_eventStartDateAfterEndDate_expectGetTaskFailedException() {
        Map<String, String> arguments = new HashMap<>();

        arguments.put(EventFlag.DESCRIPTION, DESCRIPTION);
        arguments.put(EventFlag.START_DATE, VALID_DATE2);
        arguments.put(EventFlag.END_DATE, VALID_DATE1);

        GetTaskFailedException thrown = assertThrows(
            GetTaskFailedException.class,
            () -> new EventFactory(arguments).getTask());

        StartDateAfterEndDateException sdaede = new StartDateAfterEndDateException();

        assertEquals(thrown.getMessage(), sdaede.getMessage());
    }

    @Test
    void getEvent_eventInvalidPriorityInteger_expectGetTaskFailedException() {
        Map<String, String> arguments = new HashMap<>();

        String invalidPriority = "69";

        arguments.put(EventFlag.DESCRIPTION, DESCRIPTION);
        arguments.put(EventFlag.START_DATE, VALID_DATE2);
        arguments.put(EventFlag.END_DATE, VALID_DATE1);
        arguments.put(EventFlag.PRIORITY, invalidPriority);

        GetTaskFailedException thrown = assertThrows(
            GetTaskFailedException.class,
            () -> new EventFactory(arguments).getTask());

        InvalidPriorityException ipe = new InvalidPriorityException(invalidPriority);

        assertEquals(thrown.getMessage(), ipe.getMessage());
    }
}