package seedu.duke.task.reminder;

import seedu.duke.task.PriorityEnum;
import seedu.duke.task.RecurrenceEnum;
import seedu.duke.task.type.Deadline;

import java.time.LocalDateTime;
import java.util.Calendar;
import java.util.Date;

public class DeadlineWithReminder extends Deadline {
    static final RecurrenceEnum DEFAULT_RECURRENCE = RecurrenceEnum.NONE;

    Date dueDate;
    RecurrenceEnum recurrence;
    
    Reminder reminder;

    public DeadlineWithReminder(String description) {
        super(description);
    }

    public DeadlineWithReminder(String description, PriorityEnum priority) {
        super(description, priority);
    }
    
    public DeadlineWithReminder(String description, Date doOn) {
        super(description);
        reminder = new Reminder(doOn);
    }

    public DeadlineWithReminder(String description, RecurrenceEnum recurrence) {
        super(description);
    }

    public DeadlineWithReminder(String description, PriorityEnum priority, Date doOn) {
        super(description, priority);
        reminder = new Reminder(doOn);
    }

    public DeadlineWithReminder(String description, Date doOn, RecurrenceEnum recurrence) {
        super(description);
        reminder = new Reminder(doOn);
    }

    public DeadlineWithReminder(String description, PriorityEnum priority, Date doOn, RecurrenceEnum recurrence) {
        super(description, priority);
        reminder = new Reminder(doOn);
    }

    public void displayReminder(LocalDateTime now) {
        reminder.printReminder(now, getTaskEntryDescription());
    }
}
