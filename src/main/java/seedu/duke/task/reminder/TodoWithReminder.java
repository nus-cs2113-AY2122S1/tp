package seedu.duke.task.reminder;

import seedu.duke.task.PriorityEnum;
import seedu.duke.task.RecurrenceEnum;
import seedu.duke.task.type.Todo;

import java.time.LocalDateTime;
import java.util.Date;

public class TodoWithReminder extends Todo {
    static final RecurrenceEnum DEFAULT_RECURRENCE = RecurrenceEnum.NONE;

    Date doOn;
    RecurrenceEnum recurrence;
    Reminder reminder;

    public TodoWithReminder(String description) {
        super(description);
    }

    public TodoWithReminder(String description, PriorityEnum priority) {
        super(description, priority);
    }

    public TodoWithReminder(String description, Date doOn) {
        super(description);
        reminder = new Reminder(doOn);
    }

    public TodoWithReminder(String description, RecurrenceEnum recurrence) {
        super(description);
    }

    public TodoWithReminder(String description, PriorityEnum priority, Date doOn) {
        super(description, priority);
        reminder = new Reminder(doOn);
    }

    public TodoWithReminder(String description, Date doOn, RecurrenceEnum recurrence) {
        super(description);
        reminder = new Reminder(doOn);
    }

    public TodoWithReminder(String description, PriorityEnum priority, Date doOn, RecurrenceEnum recurrence) {
        super(description, priority);
        reminder = new Reminder(doOn);
    }

    public void displayReminder(LocalDateTime now) {
        reminder.printReminder(now, getTaskEntryDescription());
    }
}
