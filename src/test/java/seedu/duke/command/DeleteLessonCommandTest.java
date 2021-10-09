package seedu.duke.command;

import org.junit.jupiter.api.Test;
import seedu.duke.exception.DukeException;
import seedu.duke.lesson.Lesson;
import seedu.duke.lesson.LessonList;
import seedu.duke.storage.Storage;
import seedu.duke.task.TaskList;
import seedu.duke.ui.Ui;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

class DeleteLessonCommandTest {
    @Test
    public void testDeleteAllLessons() {
        Ui ui = new Ui();
        TaskList taskList = new TaskList();
        LessonList lessonList = new LessonList();
        Storage storage = new Storage();

        lessonList.addLesson(new Lesson("lesson 1", "tue", "2pm", "6pm"));
        lessonList.addLesson(new Lesson("lesson 2", "thu", "1pm", "2pm"));

        try {
            Command deleteAllLessonsCommand = new DeleteLessonCommand();
            deleteAllLessonsCommand.execute(ui, taskList, lessonList, storage);
            assertTrue(lessonList.isEmpty());
        } catch (DukeException e) {
            fail();
        }
    }

    @Test
    public void testDeleteLesson() {
        Ui ui = new Ui();
        TaskList taskList = new TaskList();
        LessonList lessonList = new LessonList();
        Storage storage = new Storage();

        lessonList.addLesson(new Lesson("lesson 1", "tue", "2pm", "6pm"));
        lessonList.addLesson(new Lesson("lesson 2", "thu", "1pm", "2pm"));

        try {
            Command deleteLessonCommand = new DeleteLessonCommand(0);
            deleteLessonCommand.execute(ui, taskList, lessonList, storage);
            assertEquals(1, lessonList.getSize());
        } catch (DukeException e) {
            fail();
        }
    }

    @Test
    public void testDeleteOobLesson() {
        Ui ui = new Ui();
        TaskList taskList = new TaskList();
        LessonList lessonList = new LessonList();
        Storage storage = new Storage();

        lessonList.addLesson(new Lesson("lesson 1", "tue", "2pm", "6pm"));
        lessonList.addLesson(new Lesson("lesson 2", "thu", "1pm", "2pm"));

        Command deleteOobLessonCommand = new DeleteLessonCommand(4);
        assertThrows(DukeException.class, () -> deleteOobLessonCommand.execute(ui, taskList, lessonList, storage));
    }
}
