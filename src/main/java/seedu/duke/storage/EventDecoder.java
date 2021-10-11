package seedu.duke.storage;

import seedu.duke.Parser;
import seedu.duke.items.Event;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class EventDecoder {

    private static final int INDEX_OF_TITLE = 0;
    private static final int INDEX_OF_STATUS = 1;
    private static final int INDEX_OF_DESCRIPTION = 2;
    private static final int INDEX_OF_DATETIME = 3;
    private static final int INDEX_OF_VENUE = 4;
    private static final int INDEX_OF_BUDGET = 5;

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
        String eventStatus = eventDetails[INDEX_OF_STATUS];
        String eventDescription = eventDetails[INDEX_OF_DESCRIPTION];
        LocalDateTime eventDateTime = Parser.convertDateTime(eventDetails[INDEX_OF_DATETIME]);
        String eventVenue = eventDetails[INDEX_OF_VENUE];
        double eventBudget = Double.parseDouble(eventDetails[INDEX_OF_BUDGET]);

        Event event = new Event(eventTitle, eventDescription, eventDateTime, eventVenue, eventBudget);
        if (eventStatus.equals("X")) {
            event.markAsDone();
        }
        return event;
    }
}
