package utilities.parser;

import org.junit.jupiter.api.Test;

import java.util.Date;
import java.text.ParseException;
import java.util.GregorianCalendar;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

//@@author RemusTeo

public class DateParserTest {

    @Test
    public void stringToDate_validDate_expectValid() {
        try {
            Date date = new GregorianCalendar(2021, 8, 13).getTime();
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
        Date date = new GregorianCalendar(2021, 8, 13).getTime();
        String parsedStr = DateParser.dateToString(date);
        assertEquals("13-09-2021", parsedStr);
    }

    @Test
    public void removeTime_validDate_expectValid() {
        Date date = new GregorianCalendar(2021, 8, 13, 10, 10, 10).getTime();
        Date dateWithoutTime = DateParser.removeTime(date);
        Date expectedDate = new GregorianCalendar(2021, 8, 13).getTime();
        assertEquals(expectedDate, dateWithoutTime);
    }

}
