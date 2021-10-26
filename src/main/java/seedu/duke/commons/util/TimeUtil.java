package seedu.duke.commons.util;

import seedu.duke.commons.core.Messages;
import seedu.duke.commons.util.exceptions.TimeParseException;

//@@author richwill28
public class TimeUtil {
    /**
     * Parses 12-hour time format to 24-hour time format.
     *
     * @param time 12-hour time
     * @return 24-hour time
     * @throws TimeParseException if input time is invalid
     */
    public static String parseTwelveHourTime(String time) throws TimeParseException {
        if (time.length() != 8) {
            throw new TimeParseException(Messages.ERROR_INVALID_TIME_FORMAT);
        }

        String hour = time.substring(0, 2);
        int hourToInt;
        try {
            hourToInt = Integer.parseInt(hour);
        } catch (NumberFormatException e) {
            throw new TimeParseException(Messages.ERROR_INVALID_TIME_FORMAT);
        }
        if (hourToInt < 1 || hourToInt > 12) {
            throw new TimeParseException(Messages.ERROR_INVALID_TIME_FORMAT);
        }
        if (hourToInt == 12) {
            hourToInt -= 12;
        }

        if (time.charAt(2) != ':') {
            throw new TimeParseException(Messages.ERROR_INVALID_TIME_FORMAT);
        }

        String minute = time.substring(3, 5);
        int minuteToInt;
        try {
            minuteToInt = Integer.parseInt(minute);
        } catch (NumberFormatException e) {
            throw new TimeParseException(Messages.ERROR_INVALID_TIME_FORMAT);
        }
        if (minuteToInt < 0 || minuteToInt > 59) {
            throw new TimeParseException(Messages.ERROR_INVALID_TIME_FORMAT);
        }

        String period = time.substring(6, 8);
        if (period.equals("PM")) {
            hourToInt += 12;
        }

        return (hourToInt < 10 ? "0" : "") + hourToInt + ":" + (minuteToInt < 10 ? "0" : "") + minuteToInt;
    }
}
