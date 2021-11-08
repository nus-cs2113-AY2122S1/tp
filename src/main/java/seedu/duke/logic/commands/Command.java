package seedu.duke.logic.commands;

import java.io.IOException;

import seedu.duke.DukeException;
import seedu.duke.model.lesson.LessonList;
import seedu.duke.model.module.ModuleList;
import seedu.duke.model.task.TaskList;
import seedu.duke.storage.Storage;
import seedu.duke.ui.Ui;

//@@author rebchua39
public abstract class Command {
    public boolean isExit() {
        return false;
    }

    public abstract void execute(Ui ui, Storage storage, TaskList taskList, LessonList lessonList,
            ModuleList moduleList) throws DukeException, IOException;
}
