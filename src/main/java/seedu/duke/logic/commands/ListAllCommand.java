package seedu.duke.logic.commands;

import seedu.duke.model.lesson.LessonList;
import seedu.duke.storage.Storage;
import seedu.duke.model.task.TaskList;
import seedu.duke.ui.Ui;

public class ListAllCommand extends ListCommand {
    public ListAllCommand() {
        this.isListAll = true;
    }

    public ListAllCommand(String period) {
        this.isListAll = false;
        this.period = period;
    }

    @Override
    public void execute(Ui ui, Storage storage, TaskList taskList, LessonList lessonList) {
        if (isListAll) {
            ui.printAllList(taskList, lessonList);
        } else {
            ui.printAllWithPeriod(taskList, lessonList, period);
        }
    }
}
