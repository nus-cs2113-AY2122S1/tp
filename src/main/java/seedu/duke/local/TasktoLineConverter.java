package seedu.duke.local;

import seedu.duke.local.TaskLine;
import seedu.duke.task.Task;

public class TasktoLineConverter {
    public static TaskLine convertTaskToLine(Task task) {
        return new TaskLine(task);
    }
}
