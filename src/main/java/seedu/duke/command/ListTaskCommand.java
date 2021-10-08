package seedu.duke.command;

import seedu.duke.lesson.LessonList;
import seedu.duke.task.TaskList;
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
    public void execute(Ui ui, TaskList taskList, LessonList lessonList) {
        if (isListAll) {
            ui.printTaskList(taskList);
        } else {
            ui.printTasksWithPeriod(taskList, period);
        }
    }
}
