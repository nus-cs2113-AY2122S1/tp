package seedu.duke.storage;

import org.junit.jupiter.api.Test;
import seedu.duke.Parser;
import seedu.duke.items.Event;

import java.lang.reflect.Parameter;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class EventDecoderTest {

    @Test
    void decodeEventsList_listOfTwoEventsAsStrings_expectListOfTwoEvents() {
        // Setting up
        String encodedEvent1 = "event1 | X |  | 19-02-2022 2030 | Singapore | 1000.10";
        String encodedEvent2 = "event2 |   |  | 19-03-2022 2040 | Singapore | 1000.20";
        List<String> encodedEventList = new ArrayList<>();
        encodedEventList.add(encodedEvent1);
        encodedEventList.add(encodedEvent2);
        ArrayList<Event> decodedEventsList = EventDecoder.decodeEventsList(encodedEventList);

        LocalDateTime expectedDateTime = Parser.convertDateTime("19-02-2022 2030");

        // Check number of items in decodedEventsList
        assertEquals(2, decodedEventsList.size());

        // Check fields in an event from the decoded events list are correctly added
        assertEquals("event1", decodedEventsList.get(0).getTitle());
        assertTrue(decodedEventsList.get(0).getIsDone());
        assertEquals("", decodedEventsList.get(0).getDescription());
        assertEquals(expectedDateTime, decodedEventsList.get(0).getDateTime());
        assertEquals("Singapore", decodedEventsList.get(0).getVenue());
        assertEquals(1000.1, decodedEventsList.get(0).getBudget());
    }
}
