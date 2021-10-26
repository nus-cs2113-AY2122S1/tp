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

//@@author rebchua39
public class SetGradeCommand extends Command {
    private String moduleCode;
    private String grade;

    public SetGradeCommand(String moduleCode, String grade) {
        this.moduleCode = moduleCode;
        this.grade = grade;
    }

    @Override
    public void execute(Ui ui, Storage storage, TaskList taskList, LessonList lessonList, ModuleList moduleList)
            throws DukeException, IOException {
        Module module = moduleList.getModule(moduleCode);
        module.setGrade(grade);
        assert (module.getGrade() != null) : "module should have a valid grade";
        storage.saveData(moduleList);
        ui.printModuleWithGradeChanged(module);
    }
}
