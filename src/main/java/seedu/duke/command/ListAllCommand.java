package seedu.duke.command;

import seedu.duke.exception.DukeException;
import seedu.duke.lesson.LessonList;
import seedu.duke.task.TaskList;
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
    public void execute(Ui ui, TaskList taskList, LessonList lessonList) {
        if (isListAll) {
            ui.printAllList(taskList, lessonList);
        } else {
            ui.printAllWithPeriod(taskList, lessonList, period);
        }
    }
}
