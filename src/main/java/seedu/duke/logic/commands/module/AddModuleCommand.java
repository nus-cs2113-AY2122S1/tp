package seedu.duke.logic.commands.module;

import seedu.duke.Duke;
import seedu.duke.DukeException;
import seedu.duke.logic.commands.Command;
import seedu.duke.model.lesson.LessonList;
import seedu.duke.model.module.Module;
import seedu.duke.model.module.ModuleList;
import seedu.duke.model.task.TaskList;
import seedu.duke.storage.Storage;
import seedu.duke.ui.Ui;

import java.io.IOException;

//@@author ptejasv
public class AddModuleCommand extends Command {
    private final String moduleCode;

    public AddModuleCommand(String moduleCode) {
        this.moduleCode = moduleCode;
    }

    @Override
    public void execute(Ui ui, Storage storage, TaskList taskList, LessonList lessonList, ModuleList moduleList)
            throws DukeException, IOException {
        Module newModule = Duke.fullModuleList.findModule(moduleCode);
        moduleList.addModule(newModule);
        storage.saveData(moduleList);
        ui.printModuleAdded(newModule, moduleList.getSize());
    }
}
