package seedu.duke.logic.commands.task;

import seedu.duke.logic.commands.ListCommand;
import seedu.duke.model.lesson.LessonList;
import seedu.duke.storage.Storage;
import seedu.duke.model.task.TaskList;
import seedu.duke.ui.Ui;

public class ListTaskCommand extends ListCommand {
    public ListTaskCommand() {
        this.isListAll = true;
    }

    public ListTaskCommand(String period) {
        this.isListAll = false;
        this.period = period;
    }

    @Override
    public void execute(Ui ui, Storage storage, TaskList taskList, LessonList lessonList) {
        if (isListAll) {
            ui.printTaskList(taskList);
        } else {
            ui.printTasksWithPeriod(taskList, period);
        }
    }
}
