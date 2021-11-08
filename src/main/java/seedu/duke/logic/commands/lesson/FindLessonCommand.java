package seedu.duke.logic.commands.lesson;

import seedu.duke.logic.commands.Command;
import seedu.duke.model.lesson.LessonList;
import seedu.duke.model.module.ModuleList;
import seedu.duke.model.task.TaskList;
import seedu.duke.storage.Storage;
import seedu.duke.ui.Ui;

//@@author richwill28
public class FindLessonCommand extends Command {
    private final String keyword;

    public FindLessonCommand(String keyword) {
        this.keyword = keyword;
    }

    @Override
    public void execute(Ui ui, Storage storage, TaskList taskList, LessonList lessonList, ModuleList moduleList) {
        ui.printLessonsWithKeyword(lessonList, keyword);
    }
}
