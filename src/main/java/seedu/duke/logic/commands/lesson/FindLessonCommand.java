package seedu.duke.logic.commands.lesson;

import seedu.duke.logic.commands.FindCommand;
import seedu.duke.model.lesson.LessonList;
import seedu.duke.storage.Storage;
import seedu.duke.model.task.TaskList;
import seedu.duke.ui.Ui;

public class FindLessonCommand extends FindCommand {
    public FindLessonCommand(String keyword) {
        this.keyword = keyword;
    }

    @Override
    public void execute(Ui ui, Storage storage, TaskList taskList, LessonList lessonList) {
        ui.printLessonsWithKeyword(lessonList, keyword);
    }
}
