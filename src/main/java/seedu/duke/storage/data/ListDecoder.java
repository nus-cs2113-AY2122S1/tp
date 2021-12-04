package seedu.duke.storage.data;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * An abstract class that inherits date parsing functionality for lists.
 */
public abstract class ListDecoder {

    protected static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern("dd-MM-yyyy");
    protected static final DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern("dd-MM-yyyy HHmm");

    protected static LocalDate parseDate(String date) {
        return LocalDate.parse(date, DATE_FORMATTER);
    }

    protected static LocalDateTime parseDateTime(String dateTime) {
        return LocalDateTime.parse(dateTime, DATE_TIME_FORMATTER);
    }

    protected static boolean isWithinPastTenYears(LocalDate date) {
        //After 10 years ago from today
        LocalDate lowerLimit = LocalDate.now().minusYears(10);
        //Before tomorrow (today)
        LocalDate upperLimit = LocalDate.now().plusDays(1);
        return date.isAfter(lowerLimit) && date.isBefore(upperLimit);
    }

    protected static boolean isWithinNextYear(LocalDate date) {
        //After the past 7 days (today)
        LocalDate lowerLimit = LocalDate.now().minusDays(7);
        //Before 1 year from today
        LocalDate upperLimit = LocalDate.now().plusYears(1);
        return date.isAfter(lowerLimit) && !date.isAfter(upperLimit);
    }
}
