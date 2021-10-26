package seedu.duke.command;

import java.util.HashMap;
import java.util.Map;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import seedu.duke.command.flags.DeadlineFlag;
import seedu.duke.command.flags.EventFlag;
import seedu.duke.exception.EmptyTasklistException;
import seedu.duke.exception.InvalidTaskIndexException;
import seedu.duke.local.DataManager;
import seedu.duke.local.TasktoLineConverter;
import seedu.duke.storage.FileCreater;
import seedu.duke.task.TaskManager;
import seedu.duke.task.factory.DeadlineFactory;
import seedu.duke.task.factory.EventFactory;
import seedu.duke.task.factory.TodoFactory;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

//@@author SeanRobertDH
class DeleteCommandTest {

    private static final String VALID_DATE1 = "14-02-1998 02:00";
    private static final String VALID_DATE2 = "14-02-1998 04:40";

    private static final String TO_DELETE = "TO DELETE";

    @AfterEach
    void clearTaskmanager() {
        TaskManager.clear();
    }

    @Test
    void executeCommand_validSingleInput_expectTaskDeleted() throws Exception {
        Map<String, String> arguments = new HashMap<>();

        arguments.put(EventFlag.DESCRIPTION, "1");
        arguments.put(EventFlag.START_DATE, VALID_DATE1);
        arguments.put(EventFlag.END_DATE, VALID_DATE2);
        TaskManager.addTask(EventFactory.getEvent(arguments));

        arguments.put(EventFlag.DESCRIPTION, "2");
        arguments.put(DeadlineFlag.DUE_DATE, VALID_DATE1);
        TaskManager.addTask(DeadlineFactory.getDeadline(arguments));

        arguments.put(EventFlag.DESCRIPTION, TO_DELETE);
        TaskManager.addTask(TodoFactory.getTodo(arguments));
        arguments.put(EventFlag.DESCRIPTION, "4");
        TaskManager.addTask(TodoFactory.getTodo(arguments));
        arguments.put(EventFlag.DESCRIPTION, "5");
        TaskManager.addTask(TodoFactory.getTodo(arguments));

        arguments.put(Command.MAIN_ARGUMENT, "   __3__, , _");
        DataManager.setUpDataManager();
        new DeleteCommand(arguments).executeCommand();

        assertFalse(TaskManager.listTasklist(new HashMap<>()).contains(TO_DELETE));
    }

    @Test
    void executeCommand_validListInput_expectTasksDeleted() throws Exception {
        Map<String, String> arguments = new HashMap<>();

        arguments.put(EventFlag.DESCRIPTION, "1");
        arguments.put(EventFlag.START_DATE, VALID_DATE1);
        arguments.put(EventFlag.END_DATE, VALID_DATE2);
        TaskManager.addTask(EventFactory.getEvent(arguments));

        arguments.put(EventFlag.DESCRIPTION, "2");
        arguments.put(DeadlineFlag.DUE_DATE, VALID_DATE1);
        TaskManager.addTask(DeadlineFactory.getDeadline(arguments));

        arguments.put(EventFlag.DESCRIPTION, TO_DELETE);
        TaskManager.addTask(TodoFactory.getTodo(arguments));
        arguments.put(EventFlag.DESCRIPTION, TO_DELETE);
        TaskManager.addTask(TodoFactory.getTodo(arguments));
        arguments.put(EventFlag.DESCRIPTION, TO_DELETE);
        TaskManager.addTask(TodoFactory.getTodo(arguments));

        arguments.put(Command.MAIN_ARGUMENT, "  _3-5      ");
        DataManager.setUpDataManager();
        new DeleteCommand(arguments).executeCommand();

        assertFalse(TaskManager.listTasklist(new HashMap<>()).contains(TO_DELETE));
    }

    @Test
    void executeCommand_validSingleInputAndListInput_expectTasksDeleted() throws Exception {
        Map<String, String> arguments = new HashMap<>();

        arguments.put(EventFlag.DESCRIPTION, TO_DELETE);
        arguments.put(EventFlag.START_DATE, VALID_DATE1);
        arguments.put(EventFlag.END_DATE, VALID_DATE2);
        TaskManager.addTask(EventFactory.getEvent(arguments));

        arguments.put(EventFlag.DESCRIPTION, "2");
        arguments.put(DeadlineFlag.DUE_DATE, VALID_DATE1);
        TaskManager.addTask(DeadlineFactory.getDeadline(arguments));

        arguments.put(EventFlag.DESCRIPTION, TO_DELETE);
        TaskManager.addTask(TodoFactory.getTodo(arguments));
        arguments.put(EventFlag.DESCRIPTION, TO_DELETE);
        TaskManager.addTask(TodoFactory.getTodo(arguments));
        arguments.put(EventFlag.DESCRIPTION, TO_DELETE);
        TaskManager.addTask(TodoFactory.getTodo(arguments));

        arguments.put(Command.MAIN_ARGUMENT, "  _3-5,   1   ");
        DataManager.setUpDataManager();
        new DeleteCommand(arguments).executeCommand();

        assertFalse(TaskManager.listTasklist(new HashMap<>()).contains(TO_DELETE));
    }

    @Test
    void executeCommand_validMultiSingleInput_expectTasksDeleted() throws Exception {
        Map<String, String> arguments = new HashMap<>();

        arguments.put(EventFlag.DESCRIPTION, TO_DELETE);
        arguments.put(EventFlag.START_DATE, VALID_DATE1);
        arguments.put(EventFlag.END_DATE, VALID_DATE2);
        TaskManager.addTask(EventFactory.getEvent(arguments));

        arguments.put(EventFlag.DESCRIPTION, "2");
        arguments.put(DeadlineFlag.DUE_DATE, VALID_DATE1);
        TaskManager.addTask(DeadlineFactory.getDeadline(arguments));

        arguments.put(EventFlag.DESCRIPTION, TO_DELETE);
        TaskManager.addTask(TodoFactory.getTodo(arguments));
        arguments.put(EventFlag.DESCRIPTION, "4");
        TaskManager.addTask(TodoFactory.getTodo(arguments));
        arguments.put(EventFlag.DESCRIPTION, TO_DELETE);
        TaskManager.addTask(TodoFactory.getTodo(arguments));

        arguments.put(Command.MAIN_ARGUMENT, "1, _ _ 3    _,_   5");
        DataManager.setUpDataManager();
        new DeleteCommand(arguments).executeCommand();

        assertFalse(TaskManager.listTasklist(new HashMap<>()).contains(TO_DELETE));
    }

    @Test
    void executeCommand_validMultiInput_expectTasksDeleted() throws Exception {
        Map<String, String> arguments = new HashMap<>();

        arguments.put(EventFlag.DESCRIPTION, TO_DELETE);
        arguments.put(EventFlag.START_DATE, VALID_DATE1);
        arguments.put(EventFlag.END_DATE, VALID_DATE2);
        TaskManager.addTask(EventFactory.getEvent(arguments));

        arguments.put(EventFlag.DESCRIPTION, TO_DELETE);
        arguments.put(DeadlineFlag.DUE_DATE, VALID_DATE1);
        TaskManager.addTask(DeadlineFactory.getDeadline(arguments));

        arguments.put(EventFlag.DESCRIPTION, "3");
        TaskManager.addTask(TodoFactory.getTodo(arguments));
        arguments.put(EventFlag.DESCRIPTION, TO_DELETE);
        TaskManager.addTask(TodoFactory.getTodo(arguments));
        arguments.put(EventFlag.DESCRIPTION, TO_DELETE);
        TaskManager.addTask(TodoFactory.getTodo(arguments));

        arguments.put(Command.MAIN_ARGUMENT, "1-2, _ _ 4    _,_   5");
        DataManager.setUpDataManager();
        new DeleteCommand(arguments).executeCommand();

        assertFalse(TaskManager.listTasklist(new HashMap<>()).contains(TO_DELETE));
    }

    @Test
    void executeCommand_noMainArgument_expectUsage() throws Exception {
        Map<String, String> arguments = new HashMap<>();

        Command delete = new DeleteCommand(arguments);
        String result = delete.executeCommand().getMessage();

        assertEquals(delete.getUsage(), result);
    }

    @Test
    void executeCommand_emptyTaskList_expectEmptyTaskListExceptionMessage() throws Exception {
        Map<String, String> arguments = new HashMap<>();
        arguments.put(Command.MAIN_ARGUMENT, "1");

        Command delete = new DeleteCommand(arguments);
        String result = delete.executeCommand().getMessage();

        assertEquals(new EmptyTasklistException().getMessage(), result);
    }

    /**
     * Message taken from {@link seedu.duke.command.DeleteCommand#INVALID_TASK_INDEX}.
     */
    @Test
    void executeCommand_invalidInteger_expectDeleteClassNumberFormatExceptionMessage() throws Exception {
        Map<String, String> arguments = new HashMap<>();

        arguments.put(EventFlag.DESCRIPTION, "3");
        TaskManager.addTask(TodoFactory.getTodo(arguments));

        arguments.put(Command.MAIN_ARGUMENT, "1, 2, 3-4, blarg");
        DataManager.setUpDataManager();

        Command delete = new DeleteCommand(arguments);
        String result = delete.executeCommand().getMessage();

        assertEquals("blarg is not an integer!", result);
    }

    @Test
    void executeCommand_invalidTaskIndex_expectInvalidTaskIndexExceptionMessage() throws Exception {
        Map<String, String> arguments = new HashMap<>();

        arguments.put(EventFlag.DESCRIPTION, "3");
        TaskManager.addTask(TodoFactory.getTodo(arguments));
        DataManager.setUpDataManager();

        arguments.put(Command.MAIN_ARGUMENT, "1, 2, 3-4");

        Command delete = new DeleteCommand(arguments);
        String result = delete.executeCommand().getMessage();

        assertEquals(new InvalidTaskIndexException(4).getMessage(), result);
    }
}