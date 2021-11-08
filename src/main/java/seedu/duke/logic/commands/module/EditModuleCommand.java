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

//@@author rebchua39
public class EditModuleCommand extends Command {
    private final String moduleCode;
    private final String moduleGrade;

    public EditModuleCommand(String moduleCode, String moduleGrade) {
        this.moduleCode = moduleCode;
        this.moduleGrade = moduleGrade;
    }

    @Override
    public void execute(Ui ui, Storage storage, TaskList taskList, LessonList lessonList, ModuleList moduleList)
            throws DukeException, IOException {
        Module module = moduleList.getModule(moduleCode);
        module.setGrade(moduleGrade);
        storage.saveData(moduleList);
        ui.printModuleGradeChanged(module);
    }
}
