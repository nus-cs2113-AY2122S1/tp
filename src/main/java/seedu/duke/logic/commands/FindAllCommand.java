package seedu.duke.logic.commands;

import seedu.duke.model.lesson.LessonList;
import seedu.duke.storage.Storage;
import seedu.duke.model.task.TaskList;
import seedu.duke.ui.Ui;

public class FindAllCommand extends FindCommand {
    public FindAllCommand(String keyword) {
        this.keyword = keyword;
    }

    @Override
    public void execute(Ui ui, Storage storage, TaskList taskList, LessonList lessonList) {
        ui.printAllWithKeyword(taskList, lessonList, keyword);
    }
}
