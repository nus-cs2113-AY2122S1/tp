package seedu.duke.commons.util;

import java.time.format.DateTimeParseException;
import java.time.LocalTime;

import seedu.duke.commons.util.exceptions.InvalidTimeException;

//@@author richwill28
public class TimeUtil {
    /**
     * Compare two input times.
     *
     * @param t1 time 1
     * @param t2 time 2
     * @return -1 if t1 is before t2, 0 if t1 == t2, and 1 if t1 is after t2
     * @throws InvalidTimeException if input times are inmvalid
     */
    public static int compareTime(String t1, String t2) throws InvalidTimeException {
        try {
            LocalTime time1 = LocalTime.parse(t1);
            LocalTime time2 = LocalTime.parse(t2);
            if (time1.isBefore(time2)) {
                return -1;
            } else if (time1.isAfter(time2)) {
                return 1;
            } else {
                return 0;
            }
        } catch (DateTimeParseException e) {
            throw new InvalidTimeException(e.getMessage());
        }
    }
}
