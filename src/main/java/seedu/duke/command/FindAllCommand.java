package seedu.duke.command;

import seedu.duke.lesson.LessonList;
import seedu.duke.task.TaskList;
import seedu.duke.ui.Ui;

public class FindAllCommand extends FindCommand {
    public FindAllCommand(String keyword) {
        this.keyword = keyword;
    }

    @Override
    public void execute(Ui ui, TaskList taskList, LessonList lessonList) {
        ui.printAllWithKeyword(taskList, lessonList, keyword);
    }
}
