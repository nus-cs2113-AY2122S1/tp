package seedu.duke.command;

import seedu.duke.exception.DukeException;
import seedu.duke.lesson.Lesson;
import seedu.duke.lesson.LessonList;
import seedu.duke.storage.Storage;
import seedu.duke.task.TaskList;
import seedu.duke.ui.Ui;

public abstract class Command {
    public boolean isExit() {
        return false;
    }

    public abstract void execute(Ui ui, TaskList taskList, LessonList lessonList) throws DukeException;
}
