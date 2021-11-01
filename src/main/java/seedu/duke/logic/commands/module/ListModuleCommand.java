package seedu.duke.logic.commands.module;

import seedu.duke.DukeException;
import seedu.duke.logic.commands.Command;
import seedu.duke.model.lesson.LessonList;
import seedu.duke.model.module.ModuleList;
import seedu.duke.model.task.TaskList;
import seedu.duke.storage.Storage;
import seedu.duke.ui.Ui;

import java.io.IOException;

//@@author Roycius
public class ListModuleCommand extends Command {
    private final String argument;

    public ListModuleCommand(String argument) {
        this.argument = argument;
    }

    @Override
    public void execute(Ui ui, Storage storage, TaskList taskList, LessonList lessonList, ModuleList moduleList)
            throws DukeException, IOException {
        if (moduleList.isEmpty()) {
            ui.printMessage("There are no modules in the list.");
        } else if (argument.equalsIgnoreCase("verbose")) {
            ui.printModulesWithDetails(moduleList);
        } else if (argument.isEmpty()) {
            ui.printModuleList(moduleList);
        }
    }
}
