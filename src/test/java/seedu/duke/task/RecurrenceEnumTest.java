package seedu.duke.task;

import java.time.LocalDateTime;
import org.junit.jupiter.api.Test;
import seedu.duke.exception.ParseDateFailedException;
import seedu.duke.parser.DateParser;

import static org.junit.jupiter.api.Assertions.assertEquals;

//@@author SeanRobertDH
class RecurrenceEnumTest {
    @Test
    void getRecurredDate_validDate_expectRecurredDate() throws ParseDateFailedException {
        LocalDateTime dateTime = DateParser.stringToDate("10-04-2021 03:29");
        LocalDateTime recurred = RecurrenceEnum.NONE.getRecurredDate(dateTime);
        assertEquals(dateTime, recurred);

        recurred = RecurrenceEnum.DAILY.getRecurredDate(dateTime);
        assertEquals(DateParser.stringToDate("11-04-2021 03:29"), recurred);

        recurred = RecurrenceEnum.WEEKLY.getRecurredDate(dateTime);
        assertEquals(DateParser.stringToDate("17-04-2021 03:29"), recurred);

        recurred = RecurrenceEnum.MONTHLY.getRecurredDate(dateTime);
        assertEquals(DateParser.stringToDate("10-05-2021 03:29"), recurred);

        recurred = RecurrenceEnum.YEARLY.getRecurredDate(dateTime);
        assertEquals(DateParser.stringToDate("10-04-2022 03:29"), recurred);
    }

    @Test
    void getRecurredDate_monthlyRecurredJan_expectFeb28() throws ParseDateFailedException {
        LocalDateTime dateTime = DateParser.stringToDate("30-01-2021 03:29");
        LocalDateTime recurred = RecurrenceEnum.MONTHLY.getRecurredDate(dateTime);
        assertEquals(DateParser.stringToDate("28-02-2021 03:29"), recurred);
    }

    @Test
    void getRecurredDate_yearlyRecurredLeapYear_expectFeb28() throws ParseDateFailedException {
        LocalDateTime dateTime = DateParser.stringToDate("29-02-2020 03:29");
        LocalDateTime recurred = RecurrenceEnum.YEARLY.getRecurredDate(dateTime);
        assertEquals(DateParser.stringToDate("28-02-2021 03:29"), recurred);
    }
}