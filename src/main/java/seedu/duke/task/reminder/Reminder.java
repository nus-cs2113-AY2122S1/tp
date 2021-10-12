package seedu.duke.task.reminder;

import seedu.duke.task.RecurrenceEnum;

import java.time.LocalDateTime;
import java.util.Date;
import java.sql.Timestamp;

public class Reminder {
    private LocalDateTime taskTime;
    private LocalDateTime reminderTime;
    private boolean reminderDone;

    public Reminder() {
        this.reminderDone = false;
    }

    public Reminder(Date time) {
        this.taskTime = new Timestamp(time.getTime()).toLocalDateTime();
        this.reminderTime = taskTime.minusMinutes(10);
        setReminderDone();
    }

    public void setRecurReminderTime(LocalDateTime newReminderTime) {
        this.reminderTime = newReminderTime;
        this.reminderDone = false;
    }

    public void setReminderDone() {
        LocalDateTime now = LocalDateTime.now();
        if (now.isAfter(this.reminderTime.plusSeconds(30))) {
            this.reminderDone = true;
        } else {
            this.reminderDone = false;
        }
    }
/*
    public void checkAndPrintReminder(LocalDateTime now, String task) {
        if (reminderTime.isAfter(now.minusSeconds(30)) && reminderTime.isBefore(now.plusSeconds(30))) {
            System.out.println("Reminder! 10 min before the following task:");
            //System.out.println("\t" + task);
            this.reminderDone = true;
        }
    }
    */

    public String getMessage(LocalDateTime now, String task) {
        if (!reminderDone) {
            if (reminderTime.isAfter(now.minusSeconds(30)) && reminderTime.isBefore(now.plusSeconds(30))) {
                this.reminderDone = true;
                return ("Reminder! 10 min before the following task:\n");
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
            setRecurReminderTime(reminderTime.plusDays(1));
            break;
        case WEEKLY:
            reminderMessage = getMessage(now, task);
            setRecurReminderTime(reminderTime.plusWeeks(1));
            break;
        case MONTHLY:
            reminderMessage = getMessage(now, task);
            setRecurReminderTime(reminderTime.plusMonths(1));
            break;
        case YEARLY:
            reminderMessage = getMessage(now, task);
            setRecurReminderTime(reminderTime.plusYears(1));
            break;
        }
        return reminderMessage;
    }
}
