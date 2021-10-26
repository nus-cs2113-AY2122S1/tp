package seedu.duke.local;

import seedu.duke.task.type.Deadline;

public class DeadlineLine extends TaskLine{
    private String type;

    private String description;
    private String priority;
    private String recurrence;

    private String dueDate;

    private String reminderTime;

    private String reminderMessage;


    public DeadlineLine (Deadline task) {
        type = task.getTaskType().toString();
        description = task.getDescription();
        priority = task.getPriority().toString();
        recurrence = task.getRecurrence().toString();
        dueDate = task.getDueDate().toString();
        reminderTime = task.getReminderInformation().getUserTime();
        reminderMessage = task.getReminderInformation().getMessage();
    }

    public String getString() {
        return type + "|" + description + "|" + dueDate + "|"
                + priority + "|" + recurrence + "|"
                + reminderTime + "|" + reminderMessage;
    }
}
