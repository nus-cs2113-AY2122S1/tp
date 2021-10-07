package seedu.duke.storage;

import seedu.duke.items.Event;

import java.util.ArrayList;
import java.util.List;

public class EventEncoder {

    public static List<String> encodeEventsList(ArrayList<Event> eventsToSave) {
        List<String> encodedEvents = new ArrayList<>();
        eventsToSave.forEach(event -> encodedEvents.add(encodeEventToString(event)));
        return encodedEvents;
    }

    public static String encodeEventToString(Event event) {
        return event.getTitle() +
                " | " +
                event.getDescription() +
                " | " +
                event.getDateValue() +
                " | " +
                event.getVenue() +
                " | " +
                event.getBudget();
    }
}
