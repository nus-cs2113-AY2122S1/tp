package seedu.tp.task.factory;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

import org.junit.jupiter.api.Test;
import seedu.tp.command.flags.TodoFlag;
import seedu.tp.exception.GetTaskFailedException;
import seedu.tp.exception.InvalidPriorityException;
import seedu.tp.exception.RequiredArgmentNotProvidedException;
import seedu.tp.parser.DateParser;
import seedu.tp.task.PriorityEnum;
import seedu.tp.task.RecurrenceEnum;
import seedu.tp.task.TypeEnum;
import seedu.tp.task.type.Todo;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

//@@author SeanRobertDH
class TodoFactoryTest {

    private static final String DESCRIPTION = "buy vegetables";
    private static final String VALID_DATE1 = "14-02-1998 02:00";

    @Test
    void getTask_validTodoInputs_expectTodo() throws GetTaskFailedException {
        Map<String, String> arguments = new HashMap<>();

        arguments.put(TodoFlag.DESCRIPTION, DESCRIPTION);
        arguments.put(TodoFlag.DO_ON_DATE, VALID_DATE1);
        arguments.put(TodoFlag.PRIORITY, PriorityEnum.HIGH.toString());

        Todo todo = new TodoFactory(arguments).getTask();

        assertEquals(todo.getDescription(), DESCRIPTION);
        assertEquals(DateParser.dateToString(todo.getDoOnDate()), VALID_DATE1);
        assertEquals(todo.getPriority(), PriorityEnum.HIGH);
        assertEquals(todo.getRecurrence(), RecurrenceEnum.NONE);
    }

    @Test
    void getTask_minimumTodoInputs_expectTodo() throws GetTaskFailedException {
        Map<String, String> arguments = new HashMap<>();

        arguments.put(TodoFlag.DESCRIPTION, DESCRIPTION);

        Todo todo = new TodoFactory(arguments).getTask();

        assertEquals(todo.getDescription(), DESCRIPTION);
        assertEquals(0, todo.getDoOnDate().getMinute());
        assertEquals(LocalDateTime.now().getHour() + 1, todo.getDoOnDate().getHour());
        assertEquals(todo.getPriority(), PriorityEnum.MEDIUM);
        assertEquals(todo.getRecurrence(), RecurrenceEnum.NONE);
    }

    @Test
    void getTask_todoNoDate_expectTaskWithRoundedUpDateTime() throws GetTaskFailedException {
        Map<String, String> arguments = new HashMap<>();

        arguments.put(TodoFlag.DESCRIPTION, DESCRIPTION);
        arguments.put(TodoFlag.RECURRENCE, RecurrenceEnum.DAILY.toString());

        Todo todo = new TodoFactory(arguments).getTask();

        assertEquals(0, todo.getDoOnDate().getMinute());
        assertEquals(LocalDateTime.now().getHour() + 1, todo.getDoOnDate().getHour());
    }

    @Test
    void getTask_todoWithNoDescription_expectGetTaskFailedException() {
        Map<String, String> arguments = new HashMap<>();

        GetTaskFailedException thrown = assertThrows(
            GetTaskFailedException.class,
            () -> new TodoFactory(arguments).getTask());

        RequiredArgmentNotProvidedException ranpe =
            new RequiredArgmentNotProvidedException(TodoFlag.DESCRIPTION, TypeEnum.TODO.toString());

        assertEquals(thrown.getMessage(), ranpe.getMessage());
    }

    @Test
    void getTask_todoWithInvalidPriority_expectGetTaskFailedException() {
        Map<String, String> arguments = new HashMap<>();
        String invalidPriority = "blarg";

        arguments.put(TodoFlag.DESCRIPTION, DESCRIPTION);
        arguments.put(TodoFlag.PRIORITY, invalidPriority);

        GetTaskFailedException thrown = assertThrows(
            GetTaskFailedException.class,
            () -> new TodoFactory(arguments).getTask());

        InvalidPriorityException ipe = new InvalidPriorityException(invalidPriority);

        assertEquals(thrown.getMessage(), ipe.getMessage());
    }
}