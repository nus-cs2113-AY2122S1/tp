package seedu.duke.command;

import seedu.duke.lesson.LessonList;
import seedu.duke.task.TaskList;
import seedu.duke.ui.Ui;

public class ListCommand extends Command {
    private String listType;
    private String period;

    public ListCommand(String listType, String period) {
        this.listType = listType;
        this.period = period;
    }

    public void execute(Ui ui, TaskList tasklist, LessonList lessonList) {

    }
}
