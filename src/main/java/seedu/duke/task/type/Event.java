package seedu.duke.task.type;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.Calendar;
import java.util.Date;
import seedu.duke.task.PriorityEnum;
import seedu.duke.task.RecurrenceEnum;
import seedu.duke.task.Task;

public class Event extends Task {

    static final RecurrenceEnum DEFAULT_RECURRENCE = RecurrenceEnum.NONE;

    Date startDate;
    Date endDate;
    RecurrenceEnum recurrence;

    public Event(String description) {
        super(description);
        this.startDate = Calendar.getInstance().getTime();
        this.endDate = Calendar.getInstance().getTime();
        this.recurrence = DEFAULT_RECURRENCE;
    }

    public Event(String description, PriorityEnum priority) {
        super(description, priority);
        this.startDate = Calendar.getInstance().getTime();
        this.endDate = Calendar.getInstance().getTime();
        this.recurrence = DEFAULT_RECURRENCE;
    }

    public Event(String description, Date startDate, Date endDate) {
        super(description);
        this.startDate = startDate;
        this.endDate = endDate;
        this.recurrence = DEFAULT_RECURRENCE;
    }

    public Event(String description, RecurrenceEnum recurrence) {
        super(description);
        this.startDate = Calendar.getInstance().getTime();
        this.endDate = Calendar.getInstance().getTime();
        this.recurrence = recurrence;
    }

    public Event(String description, PriorityEnum priority, Date startDate, Date endDate) {
        super(description, priority);
        this.startDate = startDate;
        this.endDate = endDate;
        this.recurrence = DEFAULT_RECURRENCE;
    }

    public Event(String description, Date startDate, Date endDate, RecurrenceEnum recurrence) {
        super(description);
        this.startDate = startDate;
        this.endDate = endDate;
        this.recurrence = recurrence;
    }

    public Event(String description, PriorityEnum priority, Date startDate, Date endDate, RecurrenceEnum recurrence) {
        super(description, priority);
        this.startDate = startDate;
        this.endDate = endDate;
        this.recurrence = recurrence;
    }

    @Override
    public String getTaskEntryDescription() {
        return super.getTaskEntryDescription() + " (startDate: " + getDateAsString(this.startDate) + " - "
                + "endDate: " + getDateAsString(this.endDate) + ")";
    }

    @Override
    public void displayReminder(LocalDateTime now) {

    }

    public String getDateAsString(Date date) {
        DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy hh:mm:ss");
        String strDate = dateFormat.format(date);
        return strDate;
    }
}
