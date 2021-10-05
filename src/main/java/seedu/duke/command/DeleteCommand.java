package seedu.duke.command;

import seedu.duke.lesson.LessonList;
import seedu.duke.task.TaskList;
import seedu.duke.ui.Ui;

public class DeleteCommand extends Command {
    private String deleteType;
    private int index;
    private String deleteAllType;

    public DeleteCommand(String deleteType, int index) {
        this(deleteType, index, "");
    }

    public DeleteCommand(String deleteType, int index, String deleteAllType) {
        this.deleteType = deleteType;
        this.index = index;
        this.deleteAllType = deleteAllType;
    }

    public void execute(Ui ui, TaskList tasklist, LessonList lessonList) {

    }
}
