package seedu.duke.parser;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.time.format.DateTimeParseException;
import java.time.temporal.ChronoField;
import seedu.duke.exception.ParseDateFailedException;
import seedu.duke.log.Log;

//@@author SeanRobertDH
/**
 * Parser Class. Contains methods pertaining to parsing {@link java.time.LocalDateTime}.
 */
public class DateParser {
    private static final String DEFAULT_DATE_FORMAT = "dd-MM-yyyy HH:mm";

    private static final String[] ACCEPTED_DATE_FORMATS
        = {"d-M-[yyyy][yy]_H-m", "d-M-[yyyy][yy]_H", "d-M_H-m", "d-M_H", "d_H-m", "d_H", "H-m", "H"};
    private static final String YEAR_CHAR_REPRESENTATION = "y";

    private static final String WHITESPACE_REGEX = "\\s+";

    private static final String DEFAULT_SEPARATOR = "-";

    private static final String DEFAULT_SPACE = "_";

    private static int HALF_HOUR = 30;
    private static int HOUR = 60;

    private static final String LOG_FORMATTED_DATE_MESSAGE = "Formatted date String is: ";


    private static final String[] ACCEPTED_SEPARATORS
        = {":", "~", "\\.", "\\/", "\\|", "\\\\"};

    private static LocalDateTime removeUnitsOfTimeBelowHour(LocalDateTime dateTime) {
        int minutes = dateTime.getMinute();
        dateTime = dateTime.minusMinutes(minutes);
        int seconds = dateTime.getSecond();
        dateTime = dateTime.minusSeconds(seconds);
        int nanos = dateTime.getNano();
        dateTime = dateTime.minusNanos(nanos);
        return dateTime;
    }

    /**
     * Returns <code>dateTime</code> rounded up to the nearest hour.
     *
     * @param dateTime {@link java.time.LocalDateTime} to round up
     * @return {@link java.time.LocalDateTime} rounded up to the nearest hour.
     */
    public static LocalDateTime roundUpHour(LocalDateTime dateTime) {
        dateTime = removeUnitsOfTimeBelowHour(dateTime);
        return dateTime.plusHours(1);
    }

    /**
     * Returns <code>dateTime</code> as a <code>String</code>.
     *
     * @param dateTime {@link java.time.LocalDateTime} that is to be converted to <code>String</code>.
     * @return <code>dateTime</code> parsed using {@link #DEFAULT_DATE_FORMAT}.
     */
    public static String dateToString(LocalDateTime dateTime) {
        DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern(DEFAULT_DATE_FORMAT);
        return dateFormat.format(dateTime);
    }

    /**
     * Returns <code>dateTime</code> as a {@link java.time.LocalDateTime}.
     *
     * @param dateTime <code>String</code> that is to be converted to {@link java.time.LocalDateTime}.
     * @return <code>dateTime</code> parsed using the appropriate date format in {@link #ACCEPTED_DATE_FORMATS}.
     * @throws ParseDateFailedException when <code>dateTime</code> matches none
     *     of the date formats in {@link #ACCEPTED_DATE_FORMATS}.
     */
    public static LocalDateTime stringToDate(String dateTime) throws ParseDateFailedException {
        String formattedDate = formatDateString(dateTime);
        Log.info(LOG_FORMATTED_DATE_MESSAGE + formattedDate);
        for (String dateFormatString : ACCEPTED_DATE_FORMATS) {
            try {
                DateTimeFormatter dateFormat = getDateTimeFormatter(dateFormatString);
                return LocalDateTime.parse(formattedDate, dateFormat);
            } catch (DateTimeParseException dtpe) {
                continue;
            }
        }
        throw new ParseDateFailedException(getDefaultDateFormat());
    }

    /**
     * Returns the parsed <code>dateTime</code> String to match
     * the format in {@link #ACCEPTED_DATE_FORMATS}.
     *
     * @param dateTime Unformatted datetime String.
     * @return formatted datetime String.
     */
    private static String formatDateString(String dateTime) {
        for (String separator : ACCEPTED_SEPARATORS) {
            dateTime = dateTime.replaceAll(separator, DEFAULT_SEPARATOR);
        }
        if (hasDateAndTime(dateTime)) {
            dateTime = dateTime.replaceFirst(WHITESPACE_REGEX, DEFAULT_SPACE);
        }
        dateTime = dateTime.replaceAll(WHITESPACE_REGEX, "");
        return dateTime;
    }

    private static boolean hasDateAndTime(String date) {
        return date.split(WHITESPACE_REGEX).length >= 2;
    }

    /**
     * Returns the <code>DateTimeFormatter</code> from the <code>dateFormatString</code>.
     *
     * @param dateFormatString the date format as a string.
     * @return <code>DateTimeFormatter</code> with defaults that are not specified in
     * <code>dateFormatString</code> initialised.
     */
    private static DateTimeFormatter getDateTimeFormatter(String dateFormatString) {
        DateTimeFormatterBuilder builder = new DateTimeFormatterBuilder()
            .appendPattern(dateFormatString);
        if (!dateFormatString.contains(YEAR_CHAR_REPRESENTATION)) {
            builder = builder.parseDefaulting(ChronoField.YEAR, LocalDateTime.now().getYear());
        }
        return builder
            .parseDefaulting(ChronoField.MONTH_OF_YEAR, LocalDateTime.now().getMonthValue())
            .parseDefaulting(ChronoField.DAY_OF_MONTH, LocalDateTime.now().getDayOfMonth())
            .toFormatter();
    }

    public static String getDefaultDateFormat() {
        return DEFAULT_DATE_FORMAT;
    }
}
