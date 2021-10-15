package seedu.duke.logic.commands.task;

import org.junit.jupiter.api.Test;
import seedu.duke.DukeException;
import seedu.duke.logic.commands.Command;
import seedu.duke.logic.commands.task.DoneTaskCommand;
import seedu.duke.model.lesson.LessonList;
import seedu.duke.storage.Storage;
import seedu.duke.model.task.Task;
import seedu.duke.model.task.TaskList;
import seedu.duke.ui.Ui;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class DoneTaskCommandTest {
    @Test
    public void testMarkTaskAsDone() {
        Ui ui = new Ui();
        TaskList taskList = new TaskList();
        LessonList lessonList = new LessonList();
        Storage storage = new Storage();
        storage.createNewData(ui);

        taskList.addTask(new Task("task 1", "mon", ""));
        taskList.addTask(new Task("task 2", "fri", "someInfo"));

        try {
            Command doneCommand = new DoneTaskCommand(0);
            doneCommand.execute(ui, storage, taskList, lessonList);
            assertEquals(taskList.getNumberOfPendingTasks(), 1);
        } catch (DukeException | IOException e) {
            // fail when the task list has any items
            fail();
        }
    }
}
