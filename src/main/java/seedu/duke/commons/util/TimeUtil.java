package seedu.duke.commons.util;

import java.time.LocalTime;
import java.time.format.DateTimeParseException;

import seedu.duke.commons.util.exceptions.InvalidTimeException;

public class TimeUtil {
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
