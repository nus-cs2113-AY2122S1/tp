package seedu.duke.logic.commands.module;

import org.junit.jupiter.api.Test;
import seedu.duke.Duke;
import seedu.duke.DukeException;
import seedu.duke.model.lesson.LessonList;
import seedu.duke.model.module.ModuleList;
import seedu.duke.model.task.TaskList;
import seedu.duke.storage.Storage;
import seedu.duke.ui.Ui;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.fail;

//@@author ptejasv
class AddModuleCommandTest {
    @Test
    public void addModule_invalidModuleCode_exceptionThrown() {
        TaskList taskList = new TaskList();
        Ui ui = new Ui();
        Storage storage = new Storage();
        storage.createNewDataFile(ui, "MODULE");
        LessonList lessonList = new LessonList();
        ModuleList moduleList = new ModuleList();
        Duke duke = new Duke(); // ensures that AddModuleCommand can find the static variable fullModuleList

        assertThrows(DukeException.class, () -> new AddModuleCommand("invalidCode")
                .execute(ui, storage, taskList, lessonList, moduleList));
    }

    @Test
    public void addModule_validModuleCode_noExceptions() {
        TaskList taskList = new TaskList();
        Ui ui = new Ui();
        Storage storage = new Storage();
        storage.createNewDataFile(ui, "MODULE");
        LessonList lessonList = new LessonList();
        ModuleList moduleList = new ModuleList();
        Duke duke = new Duke(); // ensures that AddModuleCommand can find the static variable fullModuleList

        try {
            new AddModuleCommand("CS2113T").execute(ui, storage, taskList, lessonList, moduleList);
            assertEquals(1, moduleList.getSize());
        } catch (DukeException | IOException e) {
            fail();
        }
    }
}
