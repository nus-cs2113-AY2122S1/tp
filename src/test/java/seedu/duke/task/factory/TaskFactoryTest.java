package seedu.duke.task.factory;

import java.util.HashMap;
import org.junit.jupiter.api.Test;
import seedu.duke.command.flags.DeadlineFlag;
import seedu.duke.command.flags.EventFlag;
import seedu.duke.command.flags.TodoFlag;
import seedu.duke.exception.GetTaskFailedException;
import seedu.duke.parser.UtilityParser;
import seedu.duke.task.PriorityEnum;
import seedu.duke.task.RecurrenceEnum;
import seedu.duke.task.TypeEnum;
import seedu.duke.task.type.Deadline;
import seedu.duke.task.type.Event;
import seedu.duke.task.type.Todo;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class TaskFactoryTest {

    private static final String DESCRIPTION = "buy vegetables";
    private static final String VALID_DATE1 = "14-02-1998 02:00:00";
    private static final String VALID_DATE2 = "14-02-1998 03:30:00";

    @Test
    void getTask_validTodoInputs_expectTodo() throws GetTaskFailedException {
        HashMap<String, String> arguments = new HashMap<>();

        arguments.put(TodoFlag.TYPE, TypeEnum.TODO.toString());
        arguments.put(TodoFlag.DESCRIPTION, DESCRIPTION);
        arguments.put(TodoFlag.DO_ON_DATE, VALID_DATE1);
        arguments.put(TodoFlag.PRIORITY, PriorityEnum.HIGH.toString());

        Todo todo = (Todo) TaskFactory.getTask(arguments);

        assertEquals(todo.getDescription(), DESCRIPTION);
        assertEquals(UtilityParser.getDateAsString(todo.getDoOnDate()), VALID_DATE1);
        assertEquals(todo.getPriority(), PriorityEnum.HIGH);
        assertEquals(todo.getRecurrence(), RecurrenceEnum.NONE);
    }

    @Test
    void getTask_validDeadlineInputs_expectDeadline() throws GetTaskFailedException {
        HashMap<String, String> arguments = new HashMap<>();

        arguments.put(DeadlineFlag.TYPE, TypeEnum.DEADLINE.toString());
        arguments.put(DeadlineFlag.DESCRIPTION, DESCRIPTION);
        arguments.put(DeadlineFlag.DUE_DATE, VALID_DATE1);
        arguments.put(DeadlineFlag.PRIORITY, Integer.toString(PriorityEnum.HIGH.getValue()));
        arguments.put(DeadlineFlag.RECURRENCE, RecurrenceEnum.MONTHLY.toString());

        Deadline deadline = (Deadline) TaskFactory.getTask(arguments);

        assertEquals(deadline.getDescription(), DESCRIPTION);
        assertEquals(UtilityParser.getDateAsString(deadline.getDueDate()), VALID_DATE1);
        assertEquals(deadline.getPriority(), PriorityEnum.HIGH);
        assertEquals(deadline.getRecurrence(), RecurrenceEnum.MONTHLY);
    }

    @Test
    void getTask_validEventInputs_expectEvent() throws GetTaskFailedException {
        HashMap<String, String> arguments = new HashMap<>();

        arguments.put(EventFlag.TYPE, TypeEnum.EVENT.toString());
        arguments.put(EventFlag.DESCRIPTION, DESCRIPTION);
        arguments.put(EventFlag.START_DATE, VALID_DATE1);
        arguments.put(EventFlag.END_DATE, VALID_DATE2);
        arguments.put(EventFlag.PRIORITY, Integer.toString(PriorityEnum.LOW.getValue()));
        arguments.put(EventFlag.RECURRENCE, RecurrenceEnum.YEARLY.toString());

        Event event = (Event) TaskFactory.getTask(arguments);

        assertEquals(event.getDescription(), DESCRIPTION);
        assertEquals(UtilityParser.getDateAsString(event.getStartDate()), VALID_DATE1);
        assertEquals(UtilityParser.getDateAsString(event.getEndDate()), VALID_DATE2);
        assertEquals(event.getPriority(), PriorityEnum.LOW);
        assertEquals(event.getRecurrence(), RecurrenceEnum.YEARLY);
    }

    @Test
    void getTask_minimumTodoInputs_expectTodo() throws GetTaskFailedException {
        HashMap<String, String> arguments = new HashMap<>();

        arguments.put(TodoFlag.TYPE, TypeEnum.TODO.toString());
        arguments.put(TodoFlag.DESCRIPTION, DESCRIPTION);

        Todo todo = (Todo) TaskFactory.getTask(arguments);

        assertEquals(todo.getDescription(), DESCRIPTION);
        assertEquals(todo.getDoOnDate(), null);
        assertEquals(todo.getPriority(), PriorityEnum.MEDIUM);
        assertEquals(todo.getRecurrence(), RecurrenceEnum.NONE);
    }

    @Test
    void getTask_minimumDeadlineInputs_expectDeadline() throws GetTaskFailedException {
        HashMap<String, String> arguments = new HashMap<>();

        arguments.put(DeadlineFlag.TYPE, TypeEnum.DEADLINE.toString());
        arguments.put(DeadlineFlag.DESCRIPTION, DESCRIPTION);
        arguments.put(DeadlineFlag.DUE_DATE, VALID_DATE1);

        Deadline deadline = (Deadline) TaskFactory.getTask(arguments);

        assertEquals(deadline.getDescription(), DESCRIPTION);
        assertEquals(UtilityParser.getDateAsString(deadline.getDueDate()), VALID_DATE1);
        assertEquals(deadline.getPriority(), PriorityEnum.MEDIUM);
        assertEquals(deadline.getRecurrence(), RecurrenceEnum.NONE);
    }

    @Test
    void getTask_minimumEventInputs_expectEvent() throws GetTaskFailedException {
        HashMap<String, String> arguments = new HashMap<>();

        arguments.put(EventFlag.TYPE, TypeEnum.EVENT.toString());
        arguments.put(EventFlag.DESCRIPTION, DESCRIPTION);
        arguments.put(EventFlag.START_DATE, VALID_DATE1);
        arguments.put(EventFlag.END_DATE, VALID_DATE2);

        Event event = (Event) TaskFactory.getTask(arguments);

        assertEquals(event.getDescription(), DESCRIPTION);
        assertEquals(UtilityParser.getDateAsString(event.getStartDate()), VALID_DATE1);
        assertEquals(UtilityParser.getDateAsString(event.getEndDate()), VALID_DATE2);
        assertEquals(event.getPriority(), PriorityEnum.MEDIUM);
        assertEquals(event.getRecurrence(), RecurrenceEnum.NONE);
    }

    @Test
    void getTask_invalidTypeFlag_expectGetTaskFailedException() {
        HashMap<String, String> arguments = new HashMap<>();

        arguments.put(TodoFlag.TYPE, "blarg");
        arguments.put(TodoFlag.DESCRIPTION, DESCRIPTION);
        arguments.put(TodoFlag.DO_ON_DATE, VALID_DATE1);
        arguments.put(TodoFlag.PRIORITY, PriorityEnum.HIGH.toString());

        assertThrows(GetTaskFailedException.class,
            () -> TaskFactory.getTask(arguments));
    }

    @Test
    void getTask_todoNoDateWithRecurrence_expectGetTaskFailedException() {
        HashMap<String, String> arguments = new HashMap<>();

        arguments.put(TodoFlag.TYPE, TypeEnum.TODO.toString());
        arguments.put(TodoFlag.DESCRIPTION, DESCRIPTION);
        arguments.put(TodoFlag.RECURRENCE, RecurrenceEnum.DAILY.toString());

        assertThrows(GetTaskFailedException.class,
            () -> TaskFactory.getTask(arguments));
    }

    @Test
    void getTask_todoWithNoDescription_expectGetTaskFailedException() {
        HashMap<String, String> arguments = new HashMap<>();

        arguments.put(TodoFlag.TYPE, TypeEnum.TODO.toString());

        assertThrows(GetTaskFailedException.class,
            () -> TaskFactory.getTask(arguments));
    }

    @Test
    void getTask_todoWithInvalidPriority_expectGetTaskFailedException() {
        HashMap<String, String> arguments = new HashMap<>();
        arguments.put(TodoFlag.TYPE, TypeEnum.TODO.toString());
        arguments.put(TodoFlag.DESCRIPTION, DESCRIPTION);
        arguments.put(TodoFlag.PRIORITY, "blarg");

        assertThrows(GetTaskFailedException.class,
            () -> TaskFactory.getTask(arguments));
    }

    @Test
    void getTask_deadlineWithNoDueDate_expectGetTaskFailedException() {
        HashMap<String, String> arguments = new HashMap<>();

        arguments.put(DeadlineFlag.TYPE, TypeEnum.DEADLINE.toString());
        arguments.put(DeadlineFlag.DESCRIPTION, DESCRIPTION);

        assertThrows(GetTaskFailedException.class,
            () -> TaskFactory.getTask(arguments));
    }

    @Test
    void getTask_deadlineWithInvalidRecurrence_expectGetTaskFailedException() {
        HashMap<String, String> arguments = new HashMap<>();

        arguments.put(DeadlineFlag.TYPE, TypeEnum.DEADLINE.toString());
        arguments.put(DeadlineFlag.DESCRIPTION, DESCRIPTION);
        arguments.put(DeadlineFlag.DUE_DATE, VALID_DATE1);
        arguments.put(DeadlineFlag.RECURRENCE, "wibble");

        assertThrows(GetTaskFailedException.class,
            () -> TaskFactory.getTask(arguments));
    }

    @Test
    void getTask_deadlineWithInvalidDate_expectGetTaskFailedException() {
        HashMap<String, String> arguments = new HashMap<>();

        arguments.put(DeadlineFlag.TYPE, TypeEnum.DEADLINE.toString());
        arguments.put(DeadlineFlag.DESCRIPTION, DESCRIPTION);
        arguments.put(DeadlineFlag.DUE_DATE, "blarg");

        assertThrows(GetTaskFailedException.class,
            () -> TaskFactory.getTask(arguments));
    }

    @Test
    void getTask_eventWithNoEndDate_expectGetTaskFailedException() {
        HashMap<String, String> arguments = new HashMap<>();

        arguments.put(EventFlag.TYPE, TypeEnum.EVENT.toString());
        arguments.put(EventFlag.DESCRIPTION, DESCRIPTION);
        arguments.put(EventFlag.START_DATE, VALID_DATE1);


        assertThrows(GetTaskFailedException.class,
            () -> TaskFactory.getTask(arguments));
    }

    @Test
    void getTask_eventWithNoStartDate_expectGetTaskFailedException() {
        HashMap<String, String> arguments = new HashMap<>();

        arguments.put(EventFlag.TYPE, TypeEnum.EVENT.toString());
        arguments.put(EventFlag.DESCRIPTION, DESCRIPTION);
        arguments.put(EventFlag.END_DATE, VALID_DATE2);

        assertThrows(GetTaskFailedException.class,
            () -> TaskFactory.getTask(arguments));
    }

    @Test
    void getTask_eventStartDateAfterEndDate_expectGetTaskFailedException() {
        HashMap<String, String> arguments = new HashMap<>();

        arguments.put(EventFlag.TYPE, TypeEnum.EVENT.toString());
        arguments.put(EventFlag.DESCRIPTION, DESCRIPTION);
        arguments.put(EventFlag.START_DATE, VALID_DATE2);
        arguments.put(EventFlag.END_DATE, VALID_DATE1);

        assertThrows(GetTaskFailedException.class,
            () -> TaskFactory.getTask(arguments));
    }

    @Test
    void getTask_eventInvalidPriorityInteger_expectGetTaskFailedException() {
        HashMap<String, String> arguments = new HashMap<>();

        arguments.put(EventFlag.TYPE, TypeEnum.EVENT.toString());
        arguments.put(EventFlag.DESCRIPTION, DESCRIPTION);
        arguments.put(EventFlag.START_DATE, VALID_DATE2);
        arguments.put(EventFlag.END_DATE, VALID_DATE1);
        arguments.put(EventFlag.PRIORITY, "69");

        assertThrows(GetTaskFailedException.class,
            () -> TaskFactory.getTask(arguments));
    }
}