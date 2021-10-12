package seedu.duke.storage;

import org.junit.jupiter.api.Test;
import seedu.duke.Parser;
import seedu.duke.items.Event;

import java.lang.reflect.Array;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class EventEncoderTest {

    @Test
    void encodeEventsList_listOfTwoEvents_expectListOfTwoStrings() {
        // Setting up
        LocalDateTime event1DateTime = Parser.convertDateTime("19-02-2022 2000");
        Event event1 = new Event("Peppa Pig's Concert",
                "Asia world tour", event1DateTime,
                "Indoor Stadium", 1000.90);
        event1.markAsDone();
        LocalDateTime event2DateTime = Parser.convertDateTime("20-02-2022 2030");
        Event event2 = new Event("Funfair",
                "For charity", event2DateTime,
                "Parade square", 2000.10);
        ArrayList<Event> eventsList = new ArrayList<>();
        eventsList.add(event1);
        eventsList.add(event2);
        List<String> encodedEventsList = EventEncoder.encodeEventsList(eventsList);

        // Check number of items in encodedEventsList
        assertEquals(2, encodedEventsList.size());

        // Check the event is saved in the correct String format
        String expectedResult1 = "Peppa Pig's Concert | X | Asia world tour | 19-02-2022 2000 | Indoor Stadium | 1000.9";
        String expectedResult2 = "Funfair |   | For charity | 20-02-2022 2030 | Parade square | 2000.1";
        assertEquals(expectedResult1, encodedEventsList.get(0));
        assertEquals(expectedResult2, encodedEventsList.get(1));
    }
}