package seedu.duke.logic.commands.module;

import org.junit.jupiter.api.Test;
import seedu.duke.DukeException;
import seedu.duke.model.lesson.LessonList;
import seedu.duke.model.module.ModuleList;
import seedu.duke.model.task.TaskList;
import seedu.duke.storage.Storage;
import seedu.duke.ui.Ui;

import static org.junit.jupiter.api.Assertions.assertThrows;

//@@author Roycius
public class DeleteModuleCommandTest {
    @Test
    public void deleteLesson_emptyList_exceptionThrown() {
        ModuleList moduleList = new ModuleList();
        Ui ui = new Ui();
        Storage storage = new Storage();
        TaskList taskList = new TaskList();
        LessonList lessonList = new LessonList();
        assertThrows(DukeException.class, () -> new DeleteModuleCommand("CS1010")
                .execute(ui, storage, taskList, lessonList, moduleList));
    }
}
