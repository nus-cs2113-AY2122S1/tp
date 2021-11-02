package seedu.duke.local;

import seedu.duke.task.type.Deadline;

import java.time.format.DateTimeFormatter;

public class DeadlineLine extends TaskLine {
    private String type;

    private String description;
    private String priority;
    private String recurrence;

    private String dueDate;

    private String reminderTime;

    private String reminderMessage;


    public DeadlineLine(Deadline task) {
        type = task.getTaskType().toString();
        description = task.getDescription();
        priority = task.getPriority().toString();
        recurrence = task.getRecurrence().toString();
        dueDate = task.getDueDate().format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm"));
        reminderTime = task.getReminderInformation().getUserTime();
        reminderMessage = task.getReminderInformation().getMessage();
    }

    public String getString(int taskIndex) {
        String line = type + " " + description + " --priority " + priority;
        if (!recurrence.equals("none")) {
            line += " --recur " + recurrence;
        }
        if (dueDate != null) {
            line += " --due " + dueDate + System.lineSeparator() + getReminderString(taskIndex);
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
