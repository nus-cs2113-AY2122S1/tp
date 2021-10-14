package seedu.duke.task.factory;

import java.util.HashMap;
import org.junit.jupiter.api.Test;
import seedu.duke.command.flags.TodoFlag;
import seedu.duke.exception.GetTaskFailedException;
import seedu.duke.exception.InvalidPriorityException;
import seedu.duke.exception.RecurrenceWithoutDateException;
import seedu.duke.exception.RequiredArgmentNotProvidedException;
import seedu.duke.parser.UtilityParser;
import seedu.duke.task.PriorityEnum;
import seedu.duke.task.RecurrenceEnum;
import seedu.duke.task.TypeEnum;
import seedu.duke.task.type.Todo;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;


class TodoFactoryTest {

    private static final String DESCRIPTION = "buy vegetables";
    private static final String VALID_DATE1 = "14-02-1998 02:00:00";

    @Test
    void getTask_validTodoInputs_expectTodo() throws GetTaskFailedException {
        HashMap<String, String> arguments = new HashMap<>();

        arguments.put(TodoFlag.DESCRIPTION, DESCRIPTION);
        arguments.put(TodoFlag.DO_ON_DATE, VALID_DATE1);
        arguments.put(TodoFlag.PRIORITY, PriorityEnum.HIGH.toString());

        Todo todo = TodoFactory.getTodo(arguments);

        assertEquals(todo.getDescription(), DESCRIPTION);
        assertEquals(UtilityParser.getDateAsString(todo.getDoOnDate()), VALID_DATE1);
        assertEquals(todo.getPriority(), PriorityEnum.HIGH);
        assertEquals(todo.getRecurrence(), RecurrenceEnum.NONE);
    }

    @Test
    void getTask_minimumTodoInputs_expectTodo() throws GetTaskFailedException {
        HashMap<String, String> arguments = new HashMap<>();

        arguments.put(TodoFlag.DESCRIPTION, DESCRIPTION);

        Todo todo = TodoFactory.getTodo(arguments);

        assertEquals(todo.getDescription(), DESCRIPTION);
        assertEquals(todo.getDoOnDate(), null);
        assertEquals(todo.getPriority(), PriorityEnum.MEDIUM);
        assertEquals(todo.getRecurrence(), RecurrenceEnum.NONE);
    }

    @Test
    void getTask_todoNoDateWithRecurrence_expectGetTaskFailedException() {
        HashMap<String, String> arguments = new HashMap<>();

        arguments.put(TodoFlag.DESCRIPTION, DESCRIPTION);
        arguments.put(TodoFlag.RECURRENCE, RecurrenceEnum.DAILY.toString());

        GetTaskFailedException thrown = assertThrows(
            GetTaskFailedException.class,
            () -> TodoFactory.getTodo(arguments));

        RecurrenceWithoutDateException rwde = new RecurrenceWithoutDateException();

        assertEquals(thrown.getMessage(), rwde.getMessage());
    }

    @Test
    void getTask_todoWithNoDescription_expectGetTaskFailedException() {
        HashMap<String, String> arguments = new HashMap<>();

        GetTaskFailedException thrown = assertThrows(
            GetTaskFailedException.class,
            () -> TodoFactory.getTodo(arguments));

        RequiredArgmentNotProvidedException ranpe =
            new RequiredArgmentNotProvidedException(TodoFlag.DESCRIPTION, TypeEnum.TODO.toString());

        assertEquals(thrown.getMessage(), ranpe.getMessage());
    }

    @Test
    void getTask_todoWithInvalidPriority_expectGetTaskFailedException() {
        HashMap<String, String> arguments = new HashMap<>();
        String invalidPriority = "blarg";

        arguments.put(TodoFlag.DESCRIPTION, DESCRIPTION);
        arguments.put(TodoFlag.PRIORITY, invalidPriority);

        GetTaskFailedException thrown = assertThrows(
            GetTaskFailedException.class,
            () -> TodoFactory.getTodo(arguments));

        InvalidPriorityException ipe = new InvalidPriorityException(invalidPriority);

        assertEquals(thrown.getMessage(), ipe.getMessage());
    }
}