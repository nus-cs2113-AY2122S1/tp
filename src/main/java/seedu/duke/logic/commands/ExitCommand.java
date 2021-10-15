package seedu.duke.logic.commands;

import seedu.duke.model.lesson.LessonList;
import seedu.duke.storage.Storage;
import seedu.duke.model.task.TaskList;
import seedu.duke.ui.Ui;

public class ExitCommand extends Command {
    @Override
    public boolean isExit() {
        return true;
    }

    @Override
    public void execute(Ui ui, Storage storage, TaskList taskList, LessonList lessonList) {

    }
}
