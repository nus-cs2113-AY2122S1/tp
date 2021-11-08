package seedu.duke.logic.commands.module;

import java.io.IOException;

import seedu.duke.DukeException;
import seedu.duke.logic.commands.Command;
import seedu.duke.model.lesson.LessonList;
import seedu.duke.model.module.Module;
import seedu.duke.model.module.ModuleList;
import seedu.duke.model.task.TaskList;
import seedu.duke.storage.Storage;
import seedu.duke.ui.Ui;

import static seedu.duke.Duke.fullModuleList;

//@@author ptejasv
public class AddModuleCommand extends Command {
    private final String moduleCode;
    private final String moduleGrade;

    public AddModuleCommand(String moduleCode, String moduleGrade) {
        this.moduleCode = moduleCode;
        this.moduleGrade = moduleGrade;
    }

    @Override
    public void execute(Ui ui, Storage storage, TaskList taskList, LessonList lessonList, ModuleList moduleList)
            throws DukeException, IOException {
        Module newModule = fullModuleList.findModule(moduleCode);
        moduleList.addModule(newModule, moduleGrade);
        storage.saveData(moduleList);
        ui.printModuleAdded(newModule, moduleList.getSize());
    }
}
