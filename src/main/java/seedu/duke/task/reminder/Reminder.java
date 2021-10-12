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

    public void setReminderDone() {
        LocalDateTime now = LocalDateTime.now();
        if (now.isAfter(this.reminderTime)) {
            this.reminderDone = true;
        } else {
            this.reminderDone = false;
        }
    }

    public void printReminder(LocalDateTime now, String task) {
        if (!reminderDone) {
            if (reminderTime.isAfter(now.minusSeconds(30)) && reminderTime.isBefore(now.plusSeconds(30))) {
                System.out.println("Reminder! 10 min before the following task:");
                System.out.println("\t" + task);
                this.reminderDone = true;
            }
        }
    }
}
