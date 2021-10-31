package seedu.duke.logic.commands.module;

import java.io.IOException;

import seedu.duke.DukeException;
import seedu.duke.logic.commands.Command;
import seedu.duke.model.lesson.LessonList;
import seedu.duke.model.module.ModuleList;
import seedu.duke.model.task.TaskList;
import seedu.duke.storage.Storage;
import seedu.duke.ui.Ui;

import static seedu.duke.logic.parser.ParserUtil.isVerbose;

//@@author Roycius
public class ListModuleCommand extends Command {
    private final String parameter;

    public ListModuleCommand(String parameter) {
        this.parameter = parameter;
    }

    @Override
    public void execute(Ui ui, Storage storage, TaskList taskList, LessonList lessonList, ModuleList moduleList)
            throws DukeException, IOException {
        ui.printModuleList(moduleList);
    }
}
