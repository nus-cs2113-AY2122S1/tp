package seedu.duke.local;

import seedu.duke.local.TaskLine;
import seedu.duke.task.Task;
import seedu.duke.task.type.Deadline;
import seedu.duke.task.type.Event;
import seedu.duke.task.type.Todo;

public class TasktoLineConverter {
    public static TaskLine convertTaskToLine(Task task) {
        switch (task.getTaskType()) {
        case TODO:
            return new TodoLine((Todo) task);
        case DEADLINE:
            return new DeadlineLine((Deadline) task);
        case EVENT:
            return new EventLine((Event) task);
        default:
            return new TaskLine(task);
        }
    }
}
