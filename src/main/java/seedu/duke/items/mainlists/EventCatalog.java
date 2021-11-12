package seedu.duke.items.mainlists;

import seedu.duke.items.Event;
import seedu.duke.items.Task;

import java.util.ArrayList;

public class EventCatalog extends ArrayList<Event> {

    private static EventCatalog eventCatalog = null;

    private EventCatalog() {
    }

    public static EventCatalog getInstance() {
        if (eventCatalog == null) {
            eventCatalog = new EventCatalog();
        }
        return eventCatalog;
    }

    public void sortCatalog() {
        eventCatalog.sort(Event.DateTimeComparator);
        for (Event event : eventCatalog) {
            event.getTaskList().sort(Task.DateTimeComparator);
        }
    }
}
