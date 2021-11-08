package seedu.duke.model.task;

import org.junit.jupiter.api.Test;
import seedu.duke.DukeException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

//@@author richwill28
public class TaskListTest {
    @Test
    public void testGetSize() {
        TaskList taskList = new TaskList();
        assertEquals(0, taskList.getSize());
        taskList.addTask(new Task("Do homework", "Friday", "MEDIUM", "-"));
        assertEquals(1, taskList.getSize());
        taskList.addTask(new Task("Shopping", "Sunday", "HIGH", "-"));
        assertEquals(2, taskList.getSize());
    }

    @Test
    public void isEmpty_emptyList_true() {
        assertTrue(new TaskList().isEmpty());
    }

    @Test
    public void isEmpty_nonEmptyList_false() {
        TaskList taskList = new TaskList();
        taskList.addTask(new Task("Study CS2113T", "Monday", "LOW", "JUnit test"));
        assertFalse(taskList.isEmpty());
    }

    @Test
    public void testDeleteTask() {
        try {
            TaskList taskList = new TaskList();
            taskList.addTask(new Task("Do CS2113T iP", "Saturday", "LOW", "Level 1 increment"));
            taskList.addTask(new Task("Do CS2113T iP", "Saturday", "MEDIUM", "Level 2 increment"));
            taskList.addTask(new Task("Do CS2113T iP", "Saturday", "HIGH", "Level 3 increment"));

            assertEquals(3, taskList.getSize());
            taskList.deleteTask(2);
            assertEquals(2, taskList.getSize());
            taskList.deleteTask(1);
            assertEquals(1, taskList.getSize());
            taskList.deleteTask(0);
            assertEquals(0, taskList.getSize());
        } catch (DukeException e) {
            fail(); // the program should never reach this line
        }
    }

    @Test
    public void deleteTask_indexOutOfBounds_exceptionThrown() {
        assertThrows(DukeException.class, () -> {
            TaskList taskList = new TaskList();
            taskList.addTask(new Task("Study CS2113T", "Monday", "HIGH", "JUnit test"));
            taskList.deleteTask(1);
        });
    }

    @Test
    public void deleteTask_negativeIndex_exceptionThrown() {
        assertThrows(DukeException.class, () -> new TaskList().deleteTask(-1));
    }

    @Test
    public void testMarkTaskAsDone() {
        try {
            TaskList taskList = new TaskList();
            taskList.addTask(new Task("Study CS2105", "Wednesday", "LOW", "Chapter 1"));
            taskList.addTask(new Task("Study CS2106", "Thursday", "MEDIUM", "Chapter 2"));
            taskList.addTask(new Task("Study CS2107", "Friday", "HIGH", "Chapter 3"));

            taskList.markTaskAsDone(2);
            assertFalse(taskList.getTask(0).isDone());
            assertFalse(taskList.getTask(1).isDone());
            assertTrue(taskList.getTask(2).isDone());

            taskList.markTaskAsDone(1);
            assertFalse(taskList.getTask(0).isDone());
            assertTrue(taskList.getTask(1).isDone());
            assertTrue(taskList.getTask(2).isDone());

            taskList.markTaskAsDone(0);
            assertTrue(taskList.getTask(0).isDone());
            assertTrue(taskList.getTask(1).isDone());
            assertTrue(taskList.getTask(2).isDone());
        } catch (DukeException e) {
            fail(); // the program should never reach this line
        }
    }

    @Test
    public void markTaskAsDone_indexOutOfBounds_exceptionThrown() {
        assertThrows(DukeException.class, () -> {
            TaskList taskList = new TaskList();
            taskList.addTask(new Task("Study CS2113T", "Monday", "MEDIUM", "JUnit test"));
            taskList.markTaskAsDone(1);
        });
    }

    @Test
    public void markTaskAsDone_negativeIndex_exceptionThrown() {
        assertThrows(DukeException.class, () -> new TaskList().markTaskAsDone(-1));
    }

    @Test
    public void testGetNumberOfPendingTasks() {
        try {
            TaskList taskList = new TaskList();
            taskList.addTask(new Task("Study CS2105", "Wednesday", "LOW", "Chapter 1"));
            taskList.addTask(new Task("Study CS2106", "Thursday", "MEDIUM", "Chapter 2"));
            taskList.addTask(new Task("Study CS2107", "Friday", "HIGH", "Chapter 3"));

            assertEquals(3, taskList.getNumberOfPendingTasks());
            taskList.markTaskAsDone(2);
            assertEquals(2, taskList.getNumberOfPendingTasks());
            taskList.markTaskAsDone(1);
            assertEquals(1, taskList.getNumberOfPendingTasks());
            taskList.markTaskAsDone(0);
            assertEquals(0, taskList.getNumberOfPendingTasks());
        } catch (DukeException e) {
            fail(); // the program should never reach this line
        }
    }

    @Test
    public void testfilterTasksByKeyword() {
        try {
            TaskList taskList = new TaskList();
            taskList.addTask(new Task("Study CS2105", "Wednesday", "LOW", "Chapter 1"));
            taskList.addTask(new Task("Study CS2106", "Thursday", "MEDIUM", "Chapter 2"));
            taskList.addTask(new Task("Do CS2105 homework", "Wednesday", "LOW", "Chapter 1"));
            taskList.addTask(new Task("Do CS2106 homework", "Thursday", "MEDIUM", "Chapter 2"));

            TaskList filteredTaskList = taskList.filterTasksByKeyword("study");
            assertEquals(2, filteredTaskList.getSize());
            assertEquals("Study CS2105", filteredTaskList.getTask(0).getTitle());
            assertEquals("Study CS2106", filteredTaskList.getTask(1).getTitle());

            filteredTaskList = taskList.filterTasksByKeyword("homework");
            assertEquals(2, filteredTaskList.getSize());
            assertEquals("Do CS2105 homework", filteredTaskList.getTask(0).getTitle());
            assertEquals("Do CS2106 homework", filteredTaskList.getTask(1).getTitle());

            filteredTaskList = taskList.filterTasksByKeyword("CS2113T");
            assertEquals(0, filteredTaskList.getSize());
        } catch (DukeException e) {
            fail(); // the program should nevevr reach here
        }
    }

    @Test
    public void testSortTasksByDay() {
        // Tasks should be sorted according to day by default
        try {
            TaskList taskList = new TaskList();
            taskList.addTask(new Task("Task 2", "Tuesday", "MEDIUM", "-"));
            taskList.addTask(new Task("Task 5", "Friday", "HIGH", "-"));
            taskList.addTask(new Task("Task 1", "Monday", "HIGH", "-"));
            taskList.addTask(new Task("Task 7", "Sunday", "LOW", "-"));
            taskList.addTask(new Task("Task 4", "Thursday", "LOW", "-"));
            taskList.addTask(new Task("Task 6", "Saturday", "MEDIUM", "-"));
            taskList.addTask(new Task("Task 3", "Wednesday", "LOW", "-"));

            assertEquals("Task 1", taskList.getTask(0).getTitle());
            assertEquals("Task 2", taskList.getTask(1).getTitle());
            assertEquals("Task 3", taskList.getTask(2).getTitle());
            assertEquals("Task 4", taskList.getTask(3).getTitle());
            assertEquals("Task 5", taskList.getTask(4).getTitle());
            assertEquals("Task 6", taskList.getTask(5).getTitle());
            assertEquals("Task 7", taskList.getTask(6).getTitle());
        } catch (DukeException e) {
            fail(); // the program should never reach this line
        }
    }

    @Test
    public void testSortTasksByPriority() {
        try {
            TaskList taskList = new TaskList();
            taskList.addTask(new Task("Task 1", "Monday", "HIGH", "-"));
            taskList.addTask(new Task("Task 2", "Tuesday", "MEDIUM", "-"));
            taskList.addTask(new Task("Task 3", "Wednesday", "LOW", "-"));
            taskList.addTask(new Task("Task 4", "Thursday", "LOW", "-"));
            taskList.addTask(new Task("Task 5", "Friday", "HIGH", "-"));
            taskList.addTask(new Task("Task 6", "Saturday", "MEDIUM", "-"));
            taskList.addTask(new Task("Task 7", "Sunday", "LOW", "-"));

            TaskList sortedTaskList = taskList.sortTasksByPriority();
            assertEquals("Task 1", sortedTaskList.getTask(0).getTitle());
            assertEquals("Task 5", sortedTaskList.getTask(1).getTitle());
            assertEquals("Task 2", sortedTaskList.getTask(2).getTitle());
            assertEquals("Task 6", sortedTaskList.getTask(3).getTitle());
            assertEquals("Task 3", sortedTaskList.getTask(4).getTitle());
            assertEquals("Task 4", sortedTaskList.getTask(5).getTitle());
            assertEquals("Task 7", sortedTaskList.getTask(6).getTitle());
        } catch (DukeException e) {
            fail(); // the program should never reach here
        }
    }
}
