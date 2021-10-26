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
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

public class DoneTaskCommandTest {
    @Test
    public void testMarkTaskAsDone() {
        Ui ui = new Ui();
        Storage storage = new Storage();
        storage.createNewDataFile(ui, "TASK");
        TaskList taskList = new TaskList();
        LessonList lessonList = new LessonList();
        ModuleList moduleList = new ModuleList();

        taskList.addTask(new Task("task 1", "Monday", "L", "-"));
        taskList.addTask(new Task("task 2", "Friday", "L", "someInfo"));

        try {
            new DoneTaskCommand(0).execute(ui, storage, taskList, lessonList, moduleList);
            assertTrue(taskList.getTask(0).isDone());
            new DoneTaskCommand(1).execute(ui, storage, taskList, lessonList, moduleList);
            assertTrue(taskList.getTask(1).isDone());
        } catch (DukeException | IOException e) {
            fail(); // fail when the task list has any items
        }
    }
}
