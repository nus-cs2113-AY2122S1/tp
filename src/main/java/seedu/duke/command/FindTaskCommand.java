package seedu.duke.command;

import seedu.duke.lesson.LessonList;
import seedu.duke.task.TaskList;
import seedu.duke.ui.Ui;

public class FindTaskCommand extends FindCommand {
    public FindTaskCommand(String keyword) {
        this.keyword = keyword;
    }

    @Override
    public void execute(Ui ui, TaskList taskList, LessonList lessonList) {
        ui.printTasksWithKeyword(taskList, keyword);
    }
}
