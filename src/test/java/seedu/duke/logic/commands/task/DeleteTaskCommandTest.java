package seedu.duke.logic.commands.task;


import org.junit.jupiter.api.Test;
import seedu.duke.DukeException;
import seedu.duke.logic.commands.Command;
import seedu.duke.model.lesson.LessonList;
import seedu.duke.storage.Storage;
import seedu.duke.model.task.Task;
import seedu.duke.model.task.TaskList;
import seedu.duke.ui.Ui;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.fail;

class DeleteTaskCommandTest {
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
            deleteTaskCommand.execute(ui, storage, taskList, lessonList);
            assertEquals(1, taskList.getSize());
        } catch (DukeException | IOException e) {
            fail(); // fail when there are more or tasks in the task list than there should be (should be 1 item)
        }
    }

    @Test
    public void deleteTask_indexOutOfBounds_exceptionThrown() {
        Ui ui = new Ui();
        TaskList taskList = new TaskList();
        taskList.addTask(new Task("task 1", "mon", ""));
        taskList.addTask(new Task("task 2", "fri", "someInfo"));

        LessonList lessonList = new LessonList();
        Storage storage = new Storage();
        storage.createNewData(ui);

        Command deleteOobTaskCommand = new DeleteTaskCommand(4);
        assertThrows(DukeException.class, () -> deleteOobTaskCommand.execute(ui, storage,
                taskList, lessonList));
    }
}
