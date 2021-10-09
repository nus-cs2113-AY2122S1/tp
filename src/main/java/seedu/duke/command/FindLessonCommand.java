package seedu.duke.command;

import seedu.duke.lesson.LessonList;
import seedu.duke.storage.Storage;
import seedu.duke.task.TaskList;
import seedu.duke.ui.Ui;

public class FindLessonCommand extends FindCommand {
    public FindLessonCommand(String keyword) {
        this.keyword = keyword;
    }

    @Override
    public void execute(Ui ui, TaskList taskList, LessonList lessonList, Storage storage) {
        ui.printLessonsWithKeyword(lessonList, keyword);
    }
}
