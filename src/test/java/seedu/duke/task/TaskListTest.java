package seedu.duke.task;

import org.junit.jupiter.api.Test;
import seedu.duke.Duke;
import seedu.duke.exception.DukeException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

public class TaskListTest {
    @Test
    public void isEmpty_emptyList_true() {
        assertTrue(new TaskList().isEmpty());
    }

    @Test
    public void isEmpty_nonEmptyList_false() {
        TaskList taskList = new TaskList();
        taskList.addTask(new Task("Study CS2113T", "mon", "JUnit test"));
        assertFalse(taskList.isEmpty());
    }

    @Test
    public void testDeleteTask() {
        try {
            TaskList taskList = new TaskList();
            taskList.addTask(new Task("Do CS2113T", "SAT", "Level 1 increment"));
            taskList.addTask(new Task("Do CS2113T", "SAT", "Level 2 increment"));
            taskList.addTask(new Task("Do CS2113T", "SAT", "Level 3 increment"));
            assertEquals(3, taskList.getSize());
            taskList.deleteTask(2);
            assertEquals(2, taskList.getSize());
            taskList.deleteTask(1);
            assertEquals(1, taskList.getSize());
            taskList.deleteTask(0);
            assertEquals(0, taskList.getSize());
        } catch (DukeException e) {
            fail(); // the test should not reach this line
        }
    }

    @Test
    public void deleteTask_indexOutOfBounds_exceptionThrown() {
        assertThrows(DukeException.class, () -> {
            TaskList taskList = new TaskList();
            taskList.addTask(new Task("Study CS2113T", "mon", "JUnit test"));
            taskList.deleteTask(1);
        });
    }

    @Test
    public void deleteTask_negativeIndex_exceptionThrown() {
        assertThrows(DukeException.class, () -> new TaskList().deleteTask(-1));
    }

    @Test
    public void markTaskAsDone_indexOutOfBounds_exceptionThrown() {
        assertThrows(DukeException.class, () -> {
            TaskList taskList = new TaskList();
            taskList.addTask(new Task("Study CS2113T", "mon", "JUnit test"));
            taskList.markTaskAsDone(1);
        });
    }

    @Test
    public void markTaskAsDone_negativeIndex_exceptionThrown() {
        assertThrows(DukeException.class, () -> new TaskList().markTaskAsDone(-1));
    }
}
