package seedu.duke.logic.commands.task;

import org.junit.jupiter.api.Test;
import seedu.duke.DukeException;
import seedu.duke.model.lesson.LessonList;
import seedu.duke.model.module.ModuleList;
import seedu.duke.storage.Storage;
import seedu.duke.model.task.Task;
import seedu.duke.model.task.TaskList;
import seedu.duke.ui.Ui;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.fail;

//@@author ptejasv
class DeleteTaskCommandTest {
    @Test
    public void deleteTask_taskToDelete_taskDeleted() {
        TaskList taskList = new TaskList();
        taskList.addTask(new Task("task 1", "Monday", "L", "-"));
        taskList.addTask(new Task("task 2", "Friday", "L", "someInfo"));

        Ui ui = new Ui();
        Storage storage = new Storage();
        storage.createNewDataFile(ui, "TASK");
        LessonList lessonList = new LessonList();
        ModuleList moduleList = new ModuleList();

        try {
            new DeleteTaskCommand(1).execute(ui, storage, taskList, lessonList, moduleList);
            assertEquals(1, taskList.getSize());
            new DeleteTaskCommand(0).execute(ui, storage, taskList, lessonList, moduleList);
            assertEquals(0, taskList.getSize());
        } catch (DukeException | IOException e) {
            fail(); // fail when there are more or tasks in the task list than there should be (should be 1 item)
        }
    }

    //@@author richwill28
    @Test
    public void deleteTask_indexOutOfBounds_exceptionThrown() {
        TaskList taskList = new TaskList();
        taskList.addTask(new Task("task 1", "Monday", "M", "-"));
        taskList.addTask(new Task("task 2", "Friday", "H", "someInfo"));

        Ui ui = new Ui();
        Storage storage = new Storage();
        storage.createNewDataFile(ui, "TASK");
        LessonList lessonList = new LessonList();
        ModuleList moduleList = new ModuleList();

        assertThrows(DukeException.class, () -> new DeleteTaskCommand(2)
                .execute(ui, storage, taskList, lessonList, moduleList));
    }
}
