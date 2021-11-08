package seedu.tp.task.reminder;

import seedu.tp.task.RecurrenceEnum;

import java.time.LocalDateTime;

public class Reminder {
    private static final long BUFFER_SECOND = 30;
    private static final long RECURRENCE_INCREMENT = 1;
    private LocalDateTime taskTime;
    private LocalDateTime reminderTime;
    private boolean reminderDone;
    private long userMinute = 10;
    private long userDay = 0;
    private String message = "Reminder! 10 min before the following task:";

    public Reminder() {
        this.reminderDone = false;
    }

    public Reminder(LocalDateTime time) {
        this.taskTime = time;
        this.reminderTime = taskTime.minusMinutes(userMinute);
        setReminderDone();
    }

    public void setUserMinute(long minute) {
        this.userMinute = minute;
        updateReminderTime();
    }

    public void setUserDay(long day) {
        this.userDay = day;
        updateReminderTime();
    }

    public void updateReminderTime() {
        this.reminderTime = taskTime.minusMinutes(userMinute);
        this.reminderTime = taskTime.minusDays(userDay);
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public void setRecurReminderTime(LocalDateTime newReminderTime) {
        this.reminderTime = newReminderTime;
        this.reminderDone = false;
    }

    public void setReminderDone() {
        LocalDateTime now = LocalDateTime.now();
        if (now.isAfter(this.reminderTime.plusSeconds(BUFFER_SECOND))) {
            this.reminderDone = true;
        } else {
            this.reminderDone = false;
        }
    }

    public String getMessage(LocalDateTime now, String task) {
        if (!reminderDone) {
            if (reminderTime.isAfter(now.minusSeconds(BUFFER_SECOND))
                    && reminderTime.isBefore(now.plusSeconds(BUFFER_SECOND))) {
                this.reminderDone = true;
                return (message + "\n" + "\t" + task + "\n");
            }
        }
        return "";
    }

    public String getRecurrenceMessage(LocalDateTime now, String task, RecurrenceEnum recurrence) {
        String reminderMessage = "";
        switch (recurrence) {
        case NONE:
            reminderMessage = getMessage(now, task);
            break;
        case DAILY:
            reminderMessage = getMessage(now, task);
            setRecurReminderTime(reminderTime.plusDays(RECURRENCE_INCREMENT));
            break;
        case WEEKLY:
            reminderMessage = getMessage(now, task);
            setRecurReminderTime(reminderTime.plusWeeks(RECURRENCE_INCREMENT));
            break;
        case MONTHLY:
            reminderMessage = getMessage(now, task);
            setRecurReminderTime(reminderTime.plusMonths(RECURRENCE_INCREMENT));
            break;
        case YEARLY:
            reminderMessage = getMessage(now, task);
            setRecurReminderTime(reminderTime.plusYears(RECURRENCE_INCREMENT));
            break;
        default:
            break;
        }
        return reminderMessage;
    }
}
