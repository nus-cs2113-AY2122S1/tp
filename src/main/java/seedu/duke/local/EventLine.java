package seedu.duke.local;

import seedu.duke.task.reminder.ReminderManager;
import seedu.duke.task.type.Event;

import java.time.format.DateTimeFormatter;

public class EventLine extends TaskLine {
    public String type;

    public String description;
    public String priority;
    public String recurrence;

    public String startDate;
    public String endDate;

    public String reminderTime;

    public String reminderMessage;


    public EventLine(Event task) {
        super();
        type = task.getTaskType().toString();
        description = task.getDescription();
        priority = task.getPriority().toString();
        recurrence = task.getRecurrence().toString();
        startDate = task.getStartDate().format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm"));
        endDate = task.getEndDate().format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm"));
        reminderTime = task.getReminderInformation().getUserTime();
        reminderMessage = task.getReminderInformation().getMessage();
    }

    public String getString(int taskIndex) {
        String line = type + " " + description + " --priority " + priority;
        if (!recurrence.equals("none")) {
            line += " --recur " + recurrence;
        }
        if (startDate != null) {
            line += " --start " + startDate + " --end " + endDate
                    + System.lineSeparator() + getReminderString(taskIndex);
        }
        return line;
    }

    public String getReminderString(int taskIndex) {
        return "reminder " + taskIndex
                + " --time " + reminderTime
                + " --message " + reminderMessage;
    }

    public void updateTime(long userTime) {
        reminderTime = Long.toString(userTime);
    }

    public void updateMessage(String message) {
        reminderMessage = message;
    }
}
