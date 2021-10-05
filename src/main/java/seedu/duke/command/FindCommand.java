package seedu.duke.command;

import seedu.duke.lesson.LessonList;
import seedu.duke.task.TaskList;
import seedu.duke.ui.Ui;

public class FindCommand extends Command {
    private String findType;
    private String keyword;

    public FindCommand(String findType, String keyword) {
        this.findType = findType;
        this.keyword = keyword;
    }

    public void execute(Ui ui, TaskList tasklist, LessonList lessonList) {

    }
}
