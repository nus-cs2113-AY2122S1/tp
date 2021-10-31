package seedu.duke.storage;

import org.junit.jupiter.api.Test;
import seedu.duke.exceptions.DukeException;
import seedu.duke.items.Event;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class EventDecoderTest {

    @Test
    void decodeEventFromString_stringValidFormat_expectOneEvent() throws DukeException {
        // Setting up
        String encodedEvent = "e | Peppa Pig's Concert | X | Asia world tour | 19-02-2022 2000 "
                + "| Indoor Stadium | 1000.9";
        Event decodedEvent = EventDecoder.decodeEventFromString(encodedEvent);

        String expectedTitle = "Peppa Pig's Concert";
        assertEquals(expectedTitle, decodedEvent.getTitle());
        String expectedStatus = "X";
        assertEquals(expectedStatus, decodedEvent.getStatusIcon());
    }
}
