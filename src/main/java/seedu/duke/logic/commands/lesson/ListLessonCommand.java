package seedu.duke.logic.commands.lesson;

import seedu.duke.logic.commands.Command;
import seedu.duke.model.lesson.LessonList;
import seedu.duke.storage.Storage;
import seedu.duke.model.task.TaskList;
import seedu.duke.ui.Ui;

public class ListLessonCommand extends Command {
    private final String period;

    public ListLessonCommand(String period) {
        this.period = period;
    }

    @Override
    public void execute(Ui ui, Storage storage, TaskList taskList, LessonList lessonList) {
        if (period.isBlank()) {
            ui.printLessonList(lessonList);
        } else {
            ui.printLessonsWithPeriod(lessonList, period);
        }
    }
}
