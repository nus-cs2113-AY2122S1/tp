package seedu.duke.command;


import org.junit.jupiter.api.Test;
import seedu.duke.exception.DukeException;
import seedu.duke.lesson.LessonList;
import seedu.duke.storage.Storage;
import seedu.duke.task.Task;
import seedu.duke.task.TaskList;
import seedu.duke.ui.Ui;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

class DeleteTaskCommandTest {
    @Test
    public void testDeleteAllTasks() {
        Ui ui = new Ui();
        TaskList taskList = new TaskList();
        LessonList lessonList = new LessonList();
        Storage storage = new Storage();

        taskList.addTask(new Task("task 1", "mon", ""));
        taskList.addTask(new Task("task 2", "fri", "someInfo"));

        try {
            Command deleteAllTasksCommand = new DeleteTaskCommand();
            deleteAllTasksCommand.execute(ui, taskList, lessonList, storage);
            assertTrue(taskList.isEmpty());
        } catch (DukeException e) {
            fail();
        }
    }

    @Test
    public void testDeleteTask() {
        Ui ui = new Ui();
        TaskList taskList = new TaskList();
        LessonList lessonList = new LessonList();
        Storage storage = new Storage();

        taskList.addTask(new Task("task 1", "mon", ""));
        taskList.addTask(new Task("task 2", "fri", "someInfo"));

        try {
            Command deleteTaskCommand = new DeleteTaskCommand(1);
            deleteTaskCommand.execute(ui, taskList, lessonList, storage);
            assertEquals(1, taskList.getSize());
        } catch (DukeException e) {
            fail();
        }
    }

    @Test
    public void testDeleteOobTask() {
        Ui ui = new Ui();
        TaskList taskList = new TaskList();
        LessonList lessonList = new LessonList();
        Storage storage = new Storage();

        taskList.addTask(new Task("task 1", "mon", ""));
        taskList.addTask(new Task("task 2", "fri", "someInfo"));

        Command deleteOobTaskCommand = new DeleteTaskCommand(4);
        assertThrows(DukeException.class, () -> deleteOobTaskCommand.execute(ui, taskList, lessonList, storage));
    }
}
