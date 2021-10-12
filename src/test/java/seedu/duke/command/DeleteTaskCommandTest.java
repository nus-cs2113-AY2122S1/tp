package seedu.duke.command;


import org.junit.jupiter.api.Test;
import seedu.duke.exception.DukeException;
import seedu.duke.lesson.LessonList;
import seedu.duke.storage.Storage;
import seedu.duke.task.Task;
import seedu.duke.task.TaskList;
import seedu.duke.ui.Ui;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

class DeleteTaskCommandTest {
    @Test
    public void deleteAllTasks_nonEmptyTaskList_emptyTaskList() {
        Ui ui = new Ui();
        TaskList taskList = new TaskList();
        taskList.addTask(new Task("task 1", "mon", ""));
        taskList.addTask(new Task("task 2", "fri", "someInfo"));

        LessonList lessonList = new LessonList();
        Storage storage = new Storage();
        storage.createNewData(ui);
        try {
            Command deleteAllTasksCommand = new DeleteTaskCommand();
            deleteAllTasksCommand.execute(ui, taskList, lessonList, storage);
            assertTrue(taskList.isEmpty()); // task list should be empty
        } catch (DukeException | IOException e) {
            // fail when the task list has any items
            fail();
        }
    }

    @Test
    public void deleteTask_taskToDelete_taskDeleted() {
        Ui ui = new Ui();
        TaskList taskList = new TaskList();
        taskList.addTask(new Task("task 1", "mon", ""));
        taskList.addTask(new Task("task 2", "fri", "someInfo"));

        LessonList lessonList = new LessonList();
        Storage storage = new Storage();
        storage.createNewData(ui);
        try {
            Command deleteTaskCommand = new DeleteTaskCommand(1);
            deleteTaskCommand.execute(ui, taskList, lessonList, storage);
            assertEquals(1, taskList.getSize());
        } catch (DukeException | IOException e) {
            // fail when there are more or tasks in the task list than there should be (should be 1 item)
            fail();
        }
    }

    @Test
    public void deleteTask_outOfBoundsTask_exceptionThrown() {
        Ui ui = new Ui();
        TaskList taskList = new TaskList();
        taskList.addTask(new Task("task 1", "mon", ""));
        taskList.addTask(new Task("task 2", "fri", "someInfo"));

        LessonList lessonList = new LessonList();
        Storage storage = new Storage();
        storage.createNewData(ui);

        Command deleteOobTaskCommand = new DeleteTaskCommand(4);
        assertThrows(DukeException.class, () -> deleteOobTaskCommand.execute(ui, taskList, lessonList, storage));
    }
}
