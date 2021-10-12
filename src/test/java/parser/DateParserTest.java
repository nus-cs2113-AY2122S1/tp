package parser;

import org.junit.jupiter.api.Test;

import java.util.Date;
import java.text.ParseException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class DateParserTest {

    @Test
    public void stringToDate_validDate_expectValid() {
        try {
            // Depreciated Date Initialisation
            // Year = actualYear - 1900 [2021 - 1900 = 121]
            // Month = 0-11 [8 = Sep]
            Date date = new Date(121, 8, 13); // Mon Sep 13 00:00:00 SGT 2021
            Date parsedDate = DateParser.stringToDate("13-9-2021");
            assertEquals(date, parsedDate);
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void stringToDate_invalidDate_exceptionThrown() {
        assertThrows(ParseException.class, () -> DateParser.stringToDate("99-99-2021"));
    }

    @Test
    public void dateToString_validString_expectValid() {
        // Depreciated Date Initialisation
        // Year = actualYear - 1900 [2021 - 1900 = 121]
        // Month = 0-11 [8 = Sep]
        Date date = new Date(121, 8, 13); // Mon Sep 13 00:00:00 SGT 2021
        String parsedStr = DateParser.dateToString(date);
        assertEquals("13-09-2021", parsedStr);
    }
}
