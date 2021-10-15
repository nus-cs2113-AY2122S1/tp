package seedu.duke.logic.commands.lesson;

import org.junit.jupiter.api.Test;
import seedu.duke.DukeException;
import seedu.duke.logic.commands.Command;
import seedu.duke.logic.commands.lesson.DeleteLessonCommand;
import seedu.duke.model.lesson.Lesson;
import seedu.duke.model.lesson.LessonList;
import seedu.duke.storage.Storage;
import seedu.duke.model.task.TaskList;
import seedu.duke.ui.Ui;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

class DeleteLessonCommandTest {
    @Test
    public void deleteLesson_lessonToDelete_lessonDeleted() {
        LessonList lessonList = new LessonList();
        lessonList.addLesson(new Lesson("lesson 1", "tue", "2pm", "6pm"));
        lessonList.addLesson(new Lesson("lesson 2", "thu", "1pm", "2pm"));

        Ui ui = new Ui();
        TaskList taskList = new TaskList();
        Storage storage = new Storage();
        storage.createNewData(ui);
        try {
            Command deleteLessonCommand = new DeleteLessonCommand(0);
            deleteLessonCommand.execute(ui, storage, taskList, lessonList);
            assertEquals(1, lessonList.getSize());
        } catch (DukeException | IOException e) {
            // fail when there are more or tasks in the lesson list than there should be (should be 1 item)
            fail();
        }
    }

    @Test
    public void deleteLesson_indexOutOfBounds_exceptionThrown() {
        LessonList lessonList = new LessonList();
        lessonList.addLesson(new Lesson("lesson 1", "tue", "2pm", "6pm"));
        lessonList.addLesson(new Lesson("lesson 2", "thu", "1pm", "2pm"));

        TaskList taskList = new TaskList();
        Ui ui = new Ui();
        Storage storage = new Storage();
        storage.createNewData(ui);

        Command deleteOobLessonCommand = new DeleteLessonCommand(4);
        assertThrows(DukeException.class, () -> deleteOobLessonCommand.execute(ui, storage, taskList, lessonList));
    }
}
