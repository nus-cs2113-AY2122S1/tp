package seedu.duke.parser;

import org.junit.jupiter.api.Test;
import seedu.duke.exceptions.syntax.IllegalDateTimeException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

//@@author nvbinh15

/**
 * A test class for Parser.
 */
class ParserTest {

    static Parser parser = new Parser();

    /**
     * Checks format date time with valid input.
     *
     * @throws IllegalDateTimeException If the input datetime is illegal.
     */
    @Test
    void formatDateTime_legalDateTime_dateTimeOfOtherFormat() throws IllegalDateTimeException {
        String inputDateTime = "01-10-2021 2359";
        String expectedDateTime = "Oct 01 2021 23:59";
        assertEquals(expectedDateTime, parser.formatDateTime(inputDateTime));
    }

    /**
     * Checks IllegalDateTimeException thrown when invalid input is passed into formatDatetime.
     */
    @Test
    void formatDateTime_illegalDateTime_illegalDateTimeException() {
        String illegalDateTime = "01/01/2021 2359";
        assertThrows(IllegalDateTimeException.class, () -> parser.formatDateTime(illegalDateTime));
    }

}
