package seedu.tp.task.reminder;

public class ReminderInformation {
    private boolean reminderDone;
    private long userMinute = 10;
    private long userDay = 0;
    private String message = "Reminder! 10 min before the following task:\n";

    public ReminderInformation(boolean reminderDone, long minute, long day, String message) {
        this.reminderDone = reminderDone;
        this.userMinute = minute;
        this.userDay = day;
        this.message = message;
    }

    public String getDone() {
        if (reminderDone) {
            return "T";
        } else {
            return "F";
        }
    }

    public String getMessage() {
        return message;
    }

    public String getUserMinute() {
        return Long.toString(userMinute);
    }

    public String getUserTime() {
        return Long.toString(userDay);
    }
}
