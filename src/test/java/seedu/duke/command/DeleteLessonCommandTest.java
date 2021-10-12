package seedu.duke.command;

import org.junit.jupiter.api.Test;
import seedu.duke.exception.DukeException;
import seedu.duke.lesson.Lesson;
import seedu.duke.lesson.LessonList;
import seedu.duke.storage.Storage;
import seedu.duke.task.TaskList;
import seedu.duke.ui.Ui;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

class DeleteLessonCommandTest {
    @Test
    public void testDeleteAllLessons() {
        LessonList lessonList = new LessonList();
        lessonList.addLesson(new Lesson("lesson 1", "tue", "2pm", "6pm"));
        lessonList.addLesson(new Lesson("lesson 2", "thu", "1pm", "2pm"));

        Ui ui = new Ui();
        TaskList taskList = new TaskList();
        Storage storage = new Storage();
        storage.createNewData(ui);
        try {
            Command deleteAllLessonsCommand = new DeleteLessonCommand();
            deleteAllLessonsCommand.execute(ui, taskList, lessonList, storage);
            assertTrue(lessonList.isEmpty());
        } catch (DukeException | IOException e) {
            // fail when the lesson list has any items
            fail();
        }
    }

    @Test
    public void testDeleteLesson() {
        LessonList lessonList = new LessonList();
        lessonList.addLesson(new Lesson("lesson 1", "tue", "2pm", "6pm"));
        lessonList.addLesson(new Lesson("lesson 2", "thu", "1pm", "2pm"));

        Ui ui = new Ui();
        TaskList taskList = new TaskList();
        Storage storage = new Storage();
        storage.createNewData(ui);
        try {
            Command deleteLessonCommand = new DeleteLessonCommand(0);
            deleteLessonCommand.execute(ui, taskList, lessonList, storage);
            assertEquals(1, lessonList.getSize());
        } catch (DukeException | IOException e) {
            // fail when there are more or tasks in the lesson list than there should be (should be 1 item)
            fail();
        }
    }

    @Test
    public void testDeleteOobLesson() {

        LessonList lessonList = new LessonList();
        lessonList.addLesson(new Lesson("lesson 1", "tue", "2pm", "6pm"));
        lessonList.addLesson(new Lesson("lesson 2", "thu", "1pm", "2pm"));

        TaskList taskList = new TaskList();
        Ui ui = new Ui();
        Storage storage = new Storage();
        storage.createNewData(ui);

        Command deleteOobLessonCommand = new DeleteLessonCommand(4);
        assertThrows(DukeException.class, () -> deleteOobLessonCommand.execute(ui, taskList, lessonList, storage));
    }
}
