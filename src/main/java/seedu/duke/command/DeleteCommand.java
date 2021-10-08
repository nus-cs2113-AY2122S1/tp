package seedu.duke.command;

import seedu.duke.lesson.LessonList;
import seedu.duke.task.TaskList;
import seedu.duke.ui.Ui;

public abstract class DeleteCommand extends Command {
    protected boolean isDeleteAll;
}
