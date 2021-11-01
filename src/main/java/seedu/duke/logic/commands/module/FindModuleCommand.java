package seedu.duke.logic.commands.module;

import seedu.duke.DukeException;
import seedu.duke.logic.commands.Command;
import seedu.duke.model.lesson.LessonList;
import seedu.duke.model.module.ModuleList;
import seedu.duke.model.task.TaskList;
import seedu.duke.storage.Storage;
import seedu.duke.ui.Ui;

import java.io.IOException;

//@@author richwill28
public class FindModuleCommand extends Command {
    private final String moduleCode;
    private final boolean isVerbose;

    public FindModuleCommand(String moduleCode, boolean isVerbose) {
        this.moduleCode = moduleCode;
        this.isVerbose = isVerbose;
    }

    @Override
    public void execute(Ui ui, Storage storage, TaskList taskList, LessonList lessonList, ModuleList moduleList)
            throws DukeException, IOException {
        ui.printModuleInfo(moduleCode, isVerbose);
    }
}
