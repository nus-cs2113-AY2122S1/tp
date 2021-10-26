package seedu.duke.local;

import seedu.duke.task.type.Todo;

public class TodoLine extends TaskLine{
    private String type;

    private String description;
    private String priority;
    private String recurrence;

    private String doOnDate;

    private String reminderTime;

    private String reminderMessage;


    public TodoLine (Todo task) {
        super();
        type = task.getTaskType().toString();
        description = task.getDescription();
        priority = task.getPriority().toString();
        recurrence = task.getRecurrence().toString();
        if (task.getDoOnDate() == null) {
            doOnDate = null;
            reminderTime = null;
            reminderMessage = null;
        } else {
            doOnDate = task.getDoOnDate().toString();
            reminderTime = task.getReminderInformation().getUserTime();
            reminderMessage = task.getReminderInformation().getMessage();
        }
    }

    public String getString() {
        return type + "|" + description + "|" + doOnDate + "|"
                + priority + "|" + recurrence + "|"
                +reminderTime + "|" + reminderMessage;
    }

    public void updateTime(long userTime) {
        reminderTime = Long.toString(userTime);
    }

    public void updateMessage(String message) {
        reminderMessage = message;
    }
}
