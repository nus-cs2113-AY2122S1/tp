package seedu.duke.parser;

import java.time.LocalDateTime;
import org.junit.jupiter.api.Test;
import seedu.duke.exception.ParseDateFailedException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

//@@author SeanRobertDH
class DateParserTest {
    @Test
    void getStringAsDate_validArguments0_expectLocalDateTime() throws ParseDateFailedException {
        LocalDateTime dateTime = DateParser.stringToDate("10-04-2021 03:29");
        assertEquals(10, dateTime.getDayOfMonth());
        assertEquals(4, dateTime.getMonthValue());
        assertEquals(2021, dateTime.getYear());
        assertEquals(3, dateTime.getHour());
        assertEquals(29, dateTime.getMinute());
    }

    @Test
    void getStringAsDate_validArguments1_expectLocalDateTime() throws ParseDateFailedException {
        LocalDateTime dateTime = DateParser.stringToDate("10-04-23 03");
        assertEquals(10, dateTime.getDayOfMonth());
        assertEquals(4, dateTime.getMonthValue());
        assertEquals(2023, dateTime.getYear());
        assertEquals(3, dateTime.getHour());

        assertEquals(0, dateTime.getMinute());
    }

    @Test
    void getStringAsDate_validArguments2_expectLocalDateTime() throws ParseDateFailedException {
        LocalDateTime dateTime = DateParser.stringToDate("10-04 03");
        assertEquals(10, dateTime.getDayOfMonth());
        assertEquals(4, dateTime.getMonthValue());
        assertEquals(LocalDateTime.now().getYear(), dateTime.getYear());
        assertEquals(3, dateTime.getHour());

        assertEquals(0, dateTime.getMinute());
    }

    @Test
    void getStringAsDate_validArguments3_expectLocalDateTime() throws ParseDateFailedException {
        LocalDateTime dateTime = DateParser.stringToDate("3 03:29");
        assertEquals(3, dateTime.getDayOfMonth());
        assertEquals(LocalDateTime.now().getMonthValue(), dateTime.getMonthValue());
        assertEquals(LocalDateTime.now().getYear(), dateTime.getYear());
        assertEquals(3, dateTime.getHour());

        assertEquals(29, dateTime.getMinute());
    }

    @Test
    void getStringAsDate_validArguments4_expectLocalDateTime() throws ParseDateFailedException {
        LocalDateTime dateTime = DateParser.stringToDate("03:29");
        assertEquals(LocalDateTime.now().getDayOfMonth(), dateTime.getDayOfMonth());
        assertEquals(LocalDateTime.now().getMonthValue(), dateTime.getMonthValue());
        assertEquals(LocalDateTime.now().getYear(), dateTime.getYear());
        assertEquals(3, dateTime.getHour());

        assertEquals(29, dateTime.getMinute());
    }

    @Test
    void getStringAsDate_validArgumentsWithDifferentFormat0_expectLocalDateTime() throws ParseDateFailedException {
        LocalDateTime dateTime = DateParser.stringToDate("10/4/2021    03:29");
        assertEquals(10, dateTime.getDayOfMonth());
        assertEquals(4, dateTime.getMonthValue());
        assertEquals(2021, dateTime.getYear());
        assertEquals(3, dateTime.getHour());
        assertEquals(29, dateTime.getMinute());
    }

    @Test
    void getStringAsDate_validArgumentsWithDifferentFormat1_expectLocalDateTime() throws ParseDateFailedException {
        LocalDateTime dateTime = DateParser.stringToDate("10|4|03      03.29");
        assertEquals(10, dateTime.getDayOfMonth());
        assertEquals(4, dateTime.getMonthValue());
        assertEquals(2003, dateTime.getYear());
        assertEquals(3, dateTime.getHour());
        assertEquals(29, dateTime.getMinute());
    }

    @Test
    void getStringAsDate_invalidArguments0_expectParseDateFailedException() {
        assertThrows(ParseDateFailedException.class,
            () -> DateParser.stringToDate("10-69-2021 03:29"));
    }

    @Test
    void getStringAsDate_invalidDate_expectDateCorrected() throws ParseDateFailedException {
        LocalDateTime dateTime = DateParser.stringToDate("29-2-2021 03:29");
        assertEquals(28, dateTime.getDayOfMonth());
        assertEquals(2, dateTime.getMonthValue());
        assertEquals(2021, dateTime.getYear());
        assertEquals(3, dateTime.getHour());
        assertEquals(29, dateTime.getMinute());
    }
}