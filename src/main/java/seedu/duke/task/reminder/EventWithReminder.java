package seedu.duke.task.reminder;

import seedu.duke.task.PriorityEnum;
import seedu.duke.task.RecurrenceEnum;
import seedu.duke.task.type.Event;

import java.time.LocalDateTime;
import java.util.Calendar;
import java.util.Date;

public class EventWithReminder extends Event {

    static final RecurrenceEnum DEFAULT_RECURRENCE = RecurrenceEnum.NONE;

    Date startDate;
    Date endDate;
    RecurrenceEnum recurrence;

    Reminder reminder;

    public EventWithReminder(String description) {
        super(description);
    }

    public EventWithReminder(String description, PriorityEnum priority) {
        super(description, priority);
    }

    public EventWithReminder(String description, Date startDate, Date endDate) {
        super(description);
        reminder = new Reminder(startDate);
    }

    public EventWithReminder(String description, RecurrenceEnum recurrence) {
        super(description);
    }

    public EventWithReminder(String description, PriorityEnum priority, Date startDate, Date endDate) {
        super(description, priority);
        reminder = new Reminder(startDate);
    }

    public EventWithReminder(String description, Date startDate, Date endDate, RecurrenceEnum recurrence) {
        super(description);
        reminder = new Reminder(startDate);
    }

    public EventWithReminder(String description,
                             PriorityEnum priority,
                             Date startDate, Date endDate,
                             RecurrenceEnum recurrence) {
        super(description, priority);
        reminder = new Reminder(startDate);
    }

    public void displayReminder(LocalDateTime now) {
        reminder.printReminder(now, getTaskEntryDescription());
    }
}
