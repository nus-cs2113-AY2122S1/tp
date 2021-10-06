package seedu.duke.storage;

import seedu.duke.items.Event;

import java.util.ArrayList;
import java.util.List;

public class EventDecoder {

    private static final String EVENT_DATA_ARGS_DELIMITER = "\\s*\\|\\s*";

    private static final int INDEX_OF_TITLE = 0;
    private static final int INDEX_OF_DATETIME = 1;
    private static final int INDEX_OF_VENUE = 2;
    private static final int INDEX_OF_DESCRIPTION = 3;
    private static final int INDEX_OF_BUDGET = 4;

    public static ArrayList<Event> decodeEventList(List<String> encodedEventList) {
        ArrayList<Event> decodedEvents = new ArrayList<>();
        for (String encodedEvent : encodedEventList) {
            decodedEvents.add(decodeEventFromString(encodedEvent));
        }
        return decodedEvents;
    }

    public static Event decodeEventFromString(String encodedEvent) {
        String[] eventDetails = encodedEvent.trim().split(EVENT_DATA_ARGS_DELIMITER);
        String eventTitle = eventDetails[INDEX_OF_TITLE];
        String eventDateTime = eventDetails[INDEX_OF_DATETIME];
        String eventVenue = eventDetails[INDEX_OF_VENUE];
        String eventDescription = eventDetails[INDEX_OF_DESCRIPTION];
        String eventBudget = eventDetails[INDEX_OF_BUDGET];

        return new Event(eventTitle, eventDateTime, eventVenue, eventDescription, eventBudget);
    }
}
