package seedu.duke.storage;

import net.fortuna.ical4j.model.Date;
import net.fortuna.ical4j.model.Property;
import net.fortuna.ical4j.model.component.CalendarComponent;
import net.fortuna.ical4j.model.property.Description;
import net.fortuna.ical4j.model.property.Priority;
import net.fortuna.ical4j.model.property.Uid;
import seedu.duke.task.PriorityEnum;

public abstract class Task {
    static final PriorityEnum DEFAULT_TASK_PRIORITY = PriorityEnum.MEDIUM;

    private CalendarComponent calenderComponent;

    protected Task(CalendarComponent calenderComponent) {
        this.calenderComponent = calenderComponent;
        setProperty(new Uid());
        setPriority(DEFAULT_TASK_PRIORITY);
    }

    protected Object getProperty(String property) {
        return calenderComponent.getProperties().getProperty(property);
    }

    protected void setProperty(Property property) {
        calenderComponent.getProperties().add(property);
    }

    public void setDescription(String description) {
        setProperty(new Description(description));
    }

    public String getDescription() {
        Description description = (Description) getProperty(Property.DESCRIPTION);
        return description.getValue();
    }

    public void setPriority(PriorityEnum priorityEnum) {
        int priority = priorityEnum.getValue();
        setProperty(new Priority(priority));
    }

    public int getPriority() {
        Priority priority = (Priority) getProperty(Property.PRIORITY);
        return priority.getLevel();
    }

    public abstract Date getDate();

    public abstract void setDate(Date date);
}
