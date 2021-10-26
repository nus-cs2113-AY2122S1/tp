package seedu.duke.logic.commands.module;

import seedu.duke.DukeException;
import seedu.duke.logic.commands.Command;
import seedu.duke.model.lesson.LessonList;
import seedu.duke.model.module.Module;
import seedu.duke.model.module.ModuleList;
import seedu.duke.model.task.TaskList;
import seedu.duke.storage.Storage;
import seedu.duke.ui.Ui;

import java.io.IOException;

//@@author Roycius
public class DeleteModuleCommand extends Command {
    private final String moduleCode;

    public DeleteModuleCommand(String moduleCode) {
        this.moduleCode = moduleCode;
    }

    @Override
    public void execute(Ui ui, Storage storage, TaskList taskList, LessonList lessonList, ModuleList moduleList)
            throws DukeException, IOException {
        Module deletedModule = moduleList.getModule(moduleCode);
        moduleList.deleteModule(moduleCode);
        storage.saveData(moduleList);
        ui.printModuleDeleted(deletedModule, moduleList.getSize());
    }
}
