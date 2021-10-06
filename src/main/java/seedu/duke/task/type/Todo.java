package seedu.duke.task.type;

import net.fortuna.ical4j.model.Date;
import net.fortuna.ical4j.model.Property;
import net.fortuna.ical4j.model.component.VToDo;
import net.fortuna.ical4j.model.property.DtEnd;
import seedu.duke.task.PriorityEnum;
import seedu.duke.task.Task;
import seedu.duke.task.TimedTask;

public class Todo extends TimedTask {

    private Todo() {
        super(new VToDo());
    }

    Todo(String description) {
        this();
        setDescription(description);
    }

    Todo(String description, PriorityEnum priority) {
        this(description);
        setPriority(priority);
    }

    Todo(String description, Date startDate) {
        this(description);
        setDate(startDate);
    }

    Todo(String description, PriorityEnum priority, Date startDate) {
        this(description, priority);
        setDate(startDate);
    }

    Todo(String description, Date startDate, Date endDate) {
        this(description, startDate);
        setEndDate(endDate);
    }

    Todo(String description, PriorityEnum priority, Date startDate, Date endDate) {
        this(description, priority, startDate);
        setEndDate(endDate);
    }
}
