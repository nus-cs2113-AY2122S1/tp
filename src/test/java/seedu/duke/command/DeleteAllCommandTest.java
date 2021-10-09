package seedu.duke.command;

import org.junit.jupiter.api.Test;
import seedu.duke.exception.DukeException;
import seedu.duke.lesson.Lesson;
import seedu.duke.lesson.LessonList;
import seedu.duke.storage.Storage;
import seedu.duke.task.Task;
import seedu.duke.task.TaskList;
import seedu.duke.ui.Ui;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

class DeleteAllCommandTest {
    @Test
    public void testDeleteAll() {
        Ui ui = new Ui();
        TaskList taskList = new TaskList();
        LessonList lessonList = new LessonList();
        Storage storage = new Storage();

        taskList.addTask(new Task("task 1", "mon", ""));
        taskList.addTask(new Task("task 2", "fri", "someInfo"));
        lessonList.addLesson(new Lesson("lesson 1", "tue", "2pm", "6pm"));
        lessonList.addLesson(new Lesson("lesson 2", "thu", "1pm", "2pm"));

        try {
            Command deleteAllCommand = new DeleteAllCommand();
            deleteAllCommand.execute(ui, taskList, lessonList, storage);
            assertTrue(taskList.isEmpty());
            assertTrue(lessonList.isEmpty());
        } catch (DukeException e) {
            // fail when either the task or lesson list is not empty
            fail();
        }
    }
}
