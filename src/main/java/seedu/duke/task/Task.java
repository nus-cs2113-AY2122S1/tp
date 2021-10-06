package seedu.duke.task;

import net.fortuna.ical4j.model.Date;
import net.fortuna.ical4j.model.Property;
import net.fortuna.ical4j.model.component.CalendarComponent;
import net.fortuna.ical4j.model.property.Description;
import net.fortuna.ical4j.model.property.Priority;

public abstract class Task {
    static final PriorityEnum DEFAULT_TASK_PRIORITY = PriorityEnum.MEDIUM;

    private CalendarComponent calenderComponent;

    protected Task(CalendarComponent calenderComponent){
        this.calenderComponent = calenderComponent;
    }

    protected Object getProperty(String property){
        return calenderComponent.getProperties().getProperty(property);
    }

    protected void setProperty(Property property){
        calenderComponent.getProperties().add(property);
    }

    public void setDescription(String description){
        setProperty(new Description(description));
    }

    public String getDescription(String description){
        return (String) getProperty(Property.DESCRIPTION);
    }

    public void setPriority(){
        setPriority(DEFAULT_TASK_PRIORITY);
    }
    public void setPriority(PriorityEnum priorityEnum){
        int priority = priorityEnum.getValue();
        setProperty(new Priority(priority));
    }

    public int getPriority(){
        return (int) getProperty(Property.PRIORITY);
    }

    public abstract Date getDate();

    public abstract void setDate(Date date);
}
