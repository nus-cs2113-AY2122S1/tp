package seedu.duke.command;

import org.junit.jupiter.api.Test;
import seedu.duke.exception.DukeException;
import seedu.duke.lesson.Lesson;
import seedu.duke.lesson.LessonList;
import seedu.duke.storage.Storage;
import seedu.duke.task.Task;
import seedu.duke.task.TaskList;
import seedu.duke.ui.Ui;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

class DeleteAllCommandTest {
    @Test
    public void deleteAllTasksAndLessons_nonEmptyTaskListLessonList_emptyTaskListLessonList() {
        TaskList taskList = new TaskList();
        taskList.addTask(new Task("task 1", "mon", ""));
        taskList.addTask(new Task("task 2", "fri", "someInfo"));

        LessonList lessonList = new LessonList();
        lessonList.addLesson(new Lesson("lesson 1", "tue", "2pm", "6pm"));
        lessonList.addLesson(new Lesson("lesson 2", "thu", "1pm", "2pm"));

        Ui ui = new Ui();
        Storage storage = new Storage();
        storage.createNewData(ui);
        try {
            Command deleteAllCommand = new DeleteAllCommand();
            deleteAllCommand.execute(ui, taskList, lessonList, storage);
            assertTrue(taskList.isEmpty());
            assertTrue(lessonList.isEmpty());
        } catch (IOException | DukeException e) {
            // fail when either the task or lesson list is not empty
            fail();
        }
    }
}
