package utilities.parser;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Calendar;
import java.util.Date;

//@@author alvintan01

/**
 * Contains the parser for date objects.
 */

public class DateParser {
    public static final String INPUT_DATE_FORMAT = "d-M-yyyy";
    public static final String OUTPUT_DATE_FORMAT = "dd-MM-yyyy";

    /**
     * Helps to parse a string to a LocalDate object.
     *
     * @param date String date to be converted.
     * @return LocalDate object representing date.
     * @throws ParseException If date is invalid.
     */
    public static Date stringToDate(String date) throws ParseException {
        try {
            DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern(INPUT_DATE_FORMAT);
            dateTimeFormatter.parse(date); // To check if date was in valid format
        } catch (DateTimeParseException e) {
            throw new ParseException("Unknown date", 0);
        }
        return new SimpleDateFormat(INPUT_DATE_FORMAT).parse(date);
    }

    /**
     * Helps to parse a LocalDate object to string.
     *
     * @param date Date object to be converted to string.
     * @return String value of date.
     */
    public static String dateToString(Date date) {
        return new SimpleDateFormat(OUTPUT_DATE_FORMAT).format(date);
    }

    /**
     * Helps to remove time from date object.
     *
     * @param date Date object which time will be removed.
     * @return Date object without time.
     */
    public static Date removeTime(Date date) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.set(Calendar.HOUR_OF_DAY, 0);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.SECOND, 0);
        cal.set(Calendar.MILLISECOND, 0);
        return cal.getTime();
    }
}
