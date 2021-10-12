package seedu.duke.task.type;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.Calendar;
import java.util.Date;
import seedu.duke.task.PriorityEnum;
import seedu.duke.task.RecurrenceEnum;
import seedu.duke.task.Task;
import seedu.duke.task.reminder.Reminder;

public class Deadline extends Task {

    static final RecurrenceEnum DEFAULT_RECURRENCE = RecurrenceEnum.NONE;

    Date dueDate;
    RecurrenceEnum recurrence;

    private Reminder reminder;

    public Deadline(String description) {
        super(description);
        this.dueDate = Calendar.getInstance().getTime();
        this.recurrence = DEFAULT_RECURRENCE;
    }

    public Deadline(String description, PriorityEnum priority) {
        super(description, priority);
        this.dueDate = Calendar.getInstance().getTime();
        this.recurrence = DEFAULT_RECURRENCE;
    }

    public Deadline(String description, Date doOn) {
        super(description);
        this.dueDate = doOn;
        this.recurrence = DEFAULT_RECURRENCE;
        reminder = new Reminder(doOn);
    }

    public Deadline(String description, RecurrenceEnum recurrence) {
        super(description);
        this.dueDate = Calendar.getInstance().getTime();
        this.recurrence = recurrence;
    }

    public Deadline(String description, PriorityEnum priority, Date doOn) {
        super(description, priority);
        this.dueDate = doOn;
        this.recurrence = DEFAULT_RECURRENCE;
        reminder = new Reminder(doOn);
    }

    public Deadline(String description, Date doOn, RecurrenceEnum recurrence) {
        super(description);
        this.dueDate = doOn;
        this.recurrence = recurrence;
        reminder = new Reminder(doOn);
    }

    public Deadline(String description, PriorityEnum priority, Date doOn, RecurrenceEnum recurrence) {
        super(description, priority);
        this.dueDate = doOn;
        this.recurrence = recurrence;
        reminder = new Reminder(doOn);
    }

    @Override
    public String getTaskEntryDescription() {
        return super.getTaskEntryDescription() + " (dueDate: " + getDateAsString(this.dueDate) + ")";
    }

    @Override
    public void displayReminder(LocalDateTime now) {
        reminder.printReminder(now, getTaskEntryDescription(), recurrence);
    }

    @Override
    public boolean needReminder() {
        return (reminder != null);
    }

    public String getDateAsString(Date date) {
        DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy hh:mm:ss");
        String strDate = dateFormat.format(date);
        return strDate;
    }

    public String reminderForTest(LocalDateTime now) {
        return reminder.testReminder(now, getTaskEntryDescription(), recurrence);
    }
}
