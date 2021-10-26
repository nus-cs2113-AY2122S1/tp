package seedu.duke.logic.commands.lesson;

import org.junit.jupiter.api.Test;
import seedu.duke.DukeException;
import seedu.duke.model.lesson.Lesson;
import seedu.duke.model.lesson.LessonList;
import seedu.duke.model.module.ModuleList;
import seedu.duke.storage.Storage;
import seedu.duke.model.task.TaskList;
import seedu.duke.ui.Ui;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.fail;

class DeleteLessonCommandTest {
    @Test
    public void deleteLesson_lessonToDelete_lessonDeleted() {
        LessonList lessonList = new LessonList();
        lessonList.addLesson(new Lesson("lesson 1", "Tuesday", "12:00 PM", "02:00 PM", "-"));
        lessonList.addLesson(new Lesson("lesson 2", "Thursday", "03:00 PM", "05:00 PM", "https://example.com/"));

        Ui ui = new Ui();
        Storage storage = new Storage();
        storage.createNewDataFile(ui, "LESSON");
        TaskList taskList = new TaskList();
        ModuleList moduleList = new ModuleList();
        try {
            new DeleteLessonCommand(1).execute(ui, storage, taskList, lessonList, moduleList);
            assertEquals(1, lessonList.getSize());
            new DeleteLessonCommand(0).execute(ui, storage, taskList, lessonList, moduleList);
            assertEquals(0, lessonList.getSize());
        } catch (DukeException | IOException e) {
            fail(); // fail when there are more or tasks in the lesson list than there should be (should be 1 item)
        }
    }

    @Test
    public void deleteLesson_indexOutOfBounds_exceptionThrown() {
        LessonList lessonList = new LessonList();
        lessonList.addLesson(new Lesson("lesson 1", "Tuesday", "12:00 PM", "02:00 PM", "-"));
        lessonList.addLesson(new Lesson("lesson 2", "Thursday", "03:00 PM", "05:00 PM", "-"));

        Ui ui = new Ui();
        Storage storage = new Storage();
        storage.createNewDataFile(ui, "LESSON");
        TaskList taskList = new TaskList();
        ModuleList moduleList = new ModuleList();

        assertThrows(DukeException.class, () -> new DeleteLessonCommand(2)
                .execute(ui, storage, taskList, lessonList, moduleList));
    }
}
