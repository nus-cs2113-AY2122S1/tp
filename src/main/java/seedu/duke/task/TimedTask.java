package seedu.duke.task;

import net.fortuna.ical4j.model.Date;
import net.fortuna.ical4j.model.Property;
import net.fortuna.ical4j.model.component.CalendarComponent;
import net.fortuna.ical4j.model.property.DtEnd;
import net.fortuna.ical4j.model.property.DtStart;

public abstract class TimedTask extends Task {
    protected TimedTask(CalendarComponent calenderComponent) {
        super(calenderComponent);
    }

    public Date getDate() {
        return (Date) getProperty(Property.DTSTART);
    }

    public void setDate(Date dateStart) {
        DtStart dtStart = new DtStart(dateStart);
        setProperty(dtStart);
    }

    public Date getEndDate() {
        return (Date) getProperty(Property.DTEND);
    }

    public void setEndDate(Date dateEnd) {
        DtEnd dtEnd = new DtEnd(dateEnd);
        setProperty(dtEnd);
    }
}
