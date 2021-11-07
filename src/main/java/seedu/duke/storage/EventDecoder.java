package seedu.duke.storage;

import seedu.duke.exceptions.DukeException;
import seedu.duke.parser.Parser;
import seedu.duke.items.Event;

import java.time.LocalDateTime;

public class EventDecoder {

    // Index 0 is reserved for indicator for event/task
    private static final int INDEX_OF_TITLE = 1;
    private static final int INDEX_OF_STATUS = 2;
    private static final int INDEX_OF_DESCRIPTION = 3;
    private static final int INDEX_OF_DATETIME = 4;
    private static final int INDEX_OF_VENUE = 5;
    private static final int INDEX_OF_BUDGET = 6;

    protected static Event decodeEventFromString(String encodedEvent) throws DukeException {
        String[] eventDetails = encodedEvent.trim().split(Event.EVENT_DATA_ARGS_DELIMITER);
        String eventTitle = eventDetails[INDEX_OF_TITLE];
        String eventStatus = eventDetails[INDEX_OF_STATUS];
        String eventDescription = eventDetails[INDEX_OF_DESCRIPTION];
        LocalDateTime eventDateTime = Parser.convertDateTimeForLoading(eventDetails[INDEX_OF_DATETIME]);
        String eventVenue = eventDetails[INDEX_OF_VENUE];
        double eventBudget = Double.parseDouble(eventDetails[INDEX_OF_BUDGET]);

        Event event = new Event(eventTitle, eventDescription, eventDateTime, eventVenue, eventBudget);
        if (eventStatus.equals("X")) {
            event.markAsDone();
        }
        return event;
    }
}
