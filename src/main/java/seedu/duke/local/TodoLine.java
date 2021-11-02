package seedu.duke.local;

import seedu.duke.task.type.Todo;

import java.time.format.DateTimeFormatter;

public class TodoLine extends TaskLine {
    private String type;

    private String description;
    private String priority;
    private String recurrence;

    private String doOnDate;

    private String reminderTime;

    private String reminderMessage;


    public TodoLine(Todo task) {
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
            doOnDate = task.getDoOnDate().format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm"));
            reminderTime = task.getReminderInformation().getUserTime();
            reminderMessage = task.getReminderInformation().getMessage();
        }
    }

    public String getString(int taskIndex) {
        String line = type + " " + description + " --priority " + priority;
        if (!recurrence.equals("none")) {
            line += " --recur " + recurrence;
        }
        if (doOnDate != null) {
            line += " --doOn " + doOnDate + System.lineSeparator() + getReminderString(taskIndex);
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
