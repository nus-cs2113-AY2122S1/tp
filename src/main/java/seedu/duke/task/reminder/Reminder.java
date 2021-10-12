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

    public void checkAndPrintReminder(LocalDateTime now, String task) {
        if (reminderTime.isAfter(now.minusSeconds(30)) && reminderTime.isBefore(now.plusSeconds(30))) {
            System.out.println("Reminder! 10 min before the following task:");
            //System.out.println("\t" + task);
            this.reminderDone = true;
        }
    }

    public String testOut(LocalDateTime now, String task) {
        if (reminderTime.isAfter(now.minusSeconds(30)) && reminderTime.isBefore(now.plusSeconds(30))) {
            this.reminderDone = true;
            return ("Reminder! 10 min before the following task:");
        }
        return "";
    }

    public void printReminder(LocalDateTime now, String task, RecurrenceEnum recurrence) {
        if (!reminderDone) {
            switch (recurrence) {
            case NONE:
                checkAndPrintReminder(now, task);
            case DAILY:
                checkAndPrintReminder(now, task);
                setRecurReminderTime(reminderTime.plusDays(1));
            case WEEKLY:
                checkAndPrintReminder(now, task);
                setRecurReminderTime(reminderTime.plusWeeks(1));
            case MONTHLY:
                checkAndPrintReminder(now, task);
                setRecurReminderTime(reminderTime.plusMonths(1));
            case YEARLY:
                checkAndPrintReminder(now, task);
                setRecurReminderTime(reminderTime.plusYears(1));
            }
        }
    }

    public String testReminder(LocalDateTime now, String task, RecurrenceEnum recurrence) {
        if (!reminderDone) {
            switch (recurrence) {
            case NONE:
                return testOut(now, task);
            case DAILY:
                setRecurReminderTime(reminderTime.plusDays(1));
                return testOut(now, task);
            case WEEKLY:
                setRecurReminderTime(reminderTime.plusWeeks(1));
                return testOut(now, task);
            case MONTHLY:
                setRecurReminderTime(reminderTime.plusMonths(1));
                return testOut(now, task);
            case YEARLY:
                setRecurReminderTime(reminderTime.plusYears(1));
                return testOut(now, task);
            }
        }
        return "";
    }
}
