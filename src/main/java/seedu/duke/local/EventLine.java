package seedu.duke.local;

import seedu.duke.task.type.Event;

public class EventLine extends TaskLine{
    public String type;

    public String description;
    public String priority;
    public String recurrence;

    public String startDate;
    public String endDate;

    public String reminderTime;

    public String reminderMessage;


    public EventLine (Event task) {
        super();
        type = task.getTaskType().toString();
        description = task.getDescription();
        priority = task.getPriority().toString();
        recurrence = task.getRecurrence().toString();
        startDate = task.getStartDate().toString();
        endDate = task.getEndDate().toString();
        reminderTime = task.getReminderInformation().getUserTime();
        reminderMessage = task.getReminderInformation().getMessage();
    }

    public String getString() {
        return type + "|" + description + "|" + startDate + "|" + endDate + "|"
                + priority + "|" + recurrence + "|"
                +reminderTime + "|" + reminderMessage;
    }
}
