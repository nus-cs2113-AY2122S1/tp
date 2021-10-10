package seedu.duke.storage;

import seedu.duke.Parser;
import seedu.duke.items.Event;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class EventDecoder {

    private static final int INDEX_OF_TITLE = 0;
    private static final int INDEX_OF_DATETIME = 1;
    private static final int INDEX_OF_VENUE = 2;
    private static final int INDEX_OF_DESCRIPTION = 3;
    private static final int INDEX_OF_BUDGET = 4;

    public static ArrayList<Event> decodeEventsList(List<String> encodedEventList) {
        ArrayList<Event> decodedEvents = new ArrayList<>();
        for (String encodedEvent : encodedEventList) {
            decodedEvents.add(decodeEventFromString(encodedEvent));
        }
        return decodedEvents;
    }

    private static Event decodeEventFromString(String encodedEvent) {
        String[] eventDetails = encodedEvent.trim().split(Event.EVENT_DATA_ARGS_DELIMITER);
        String eventTitle = eventDetails[INDEX_OF_TITLE];
        LocalDateTime eventDateTime = Parser.convertDateTime(eventDetails[INDEX_OF_DATETIME]);
        String eventVenue = eventDetails[INDEX_OF_VENUE];
        String eventDescription = eventDetails[INDEX_OF_DESCRIPTION];
        int eventBudget = Integer.parseInt(eventDetails[INDEX_OF_BUDGET]);

        return new Event(eventTitle, eventDescription, eventDateTime, eventVenue, eventBudget);
    }
}
