package seedu.duke.local;

import seedu.duke.task.PriorityEnum;
import seedu.duke.task.RecurrenceEnum;
import seedu.duke.task.Task;
import seedu.duke.task.TypeEnum;
import seedu.duke.task.reminder.Reminder;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;

public class TaskLine {
    private String type = null;

    private String description;
    private String priority;
    private String recurrence;

    public TaskLine(Task task) {
        type = task.getTaskType().toString();
        description = task.getDescription();
        priority = task.getPriority().toString();
        recurrence = task.getRecurrence().toString();
    }

    public String getString() {
        return type + "|" + description + "|" + priority + "|" + recurrence;
    }

    public TaskLine() {
    }

    public void updateTime(long userTime) {

    }

    public void updateMessage(String message) {

    }
}
