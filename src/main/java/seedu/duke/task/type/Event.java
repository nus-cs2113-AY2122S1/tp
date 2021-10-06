package seedu.duke.task.type;

import net.fortuna.ical4j.model.Date;
import net.fortuna.ical4j.model.component.VEvent;
import seedu.duke.task.PriorityEnum;
import seedu.duke.task.TimedTask;

public class Event extends TimedTask {

    private Event() {
        super(new VEvent());
    }

    Event(String description, Date startDate) {
        this();
        setDescription(description);
        setDate(startDate);
    }

    Event(String description, PriorityEnum priority, Date startDate) {
        this(description, startDate);
        setPriority(priority);
    }

    Event(String description, Date startDate, Date endDate) {
        this(description, startDate);
        setEndDate(endDate);
    }

    Event(String description, PriorityEnum priority, Date startDate, Date endDate) {
        this(description, priority, startDate);
        setEndDate(endDate);
    }
}
