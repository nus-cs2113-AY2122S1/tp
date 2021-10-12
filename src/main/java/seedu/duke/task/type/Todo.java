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

public class Todo extends Task {

    static final RecurrenceEnum DEFAULT_RECURRENCE = RecurrenceEnum.NONE;

    Date doOn;
    RecurrenceEnum recurrence;

    private Reminder reminder;

    public Todo(String description) {
        super(description);
        this.doOn = Calendar.getInstance().getTime();
        this.recurrence = DEFAULT_RECURRENCE;
    }

    public Todo(String description, PriorityEnum priority) {
        super(description, priority);
        this.doOn = Calendar.getInstance().getTime();
        this.recurrence = DEFAULT_RECURRENCE;
    }

    public Todo(String description, Date doOn) {
        super(description);
        this.doOn = doOn;
        this.recurrence = DEFAULT_RECURRENCE;
        reminder = new Reminder(doOn);
    }

    public Todo(String description, RecurrenceEnum recurrence) {
        super(description);
        this.doOn = Calendar.getInstance().getTime();
        this.recurrence = recurrence;
    }

    public Todo(String description, PriorityEnum priority, Date doOn) {
        super(description, priority);
        this.doOn = doOn;
        this.recurrence = DEFAULT_RECURRENCE;
        reminder = new Reminder(doOn);
    }

    public Todo(String description, Date doOn, RecurrenceEnum recurrence) {
        super(description);
        this.doOn = doOn;
        this.recurrence = recurrence;
        reminder = new Reminder(doOn);
    }

    public Todo(String description, PriorityEnum priority, Date doOn, RecurrenceEnum recurrence) {
        super(description, priority);
        this.doOn = doOn;
        this.recurrence = recurrence;
        reminder = new Reminder(doOn);
    }

    @Override
    public String getTaskEntryDescription() {
        return super.getTaskEntryDescription() + " (doOn: " + getDateAsString(this.doOn) + ")";
    }

    @Override
    public void displayReminder(LocalDateTime now) {
        reminder.printReminder(now, getTaskEntryDescription(), recurrence);
    }

    public String reminderForTest(LocalDateTime now) {
        return reminder.testReminder(now, getTaskEntryDescription(), recurrence);
    }

    public boolean needReminder() {
        return (reminder != null);
    }

    public String getDateAsString(Date date) {
        DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy hh:mm:ss");
        String strDate = dateFormat.format(date);
        return strDate;
    }

}
