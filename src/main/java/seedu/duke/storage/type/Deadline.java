package seedu.duke.storage.type;

import net.fortuna.ical4j.model.Date;
import net.fortuna.ical4j.model.Property;
import net.fortuna.ical4j.model.component.VToDo;
import net.fortuna.ical4j.model.property.Due;
import seedu.duke.task.PriorityEnum;
import seedu.duke.storage.Task;

public class Deadline extends Task {

    private Deadline() {
        super(new VToDo());
    }

    Deadline(String description, Date dueDate) {
        this();
        setDescription(description);
        setDate(dueDate);
    }

    Deadline(String description, PriorityEnum priority, Date startDate) {
        this(description, startDate);
        setPriority(priority);
    }

    public void setDate(Date dueDate) {
        Due due = new Due(dueDate);
        setProperty(due);
    }

    public Date getDate() {
        Due due = (Due) getProperty(Property.DUE);
        return due.getDate();
    }

}
