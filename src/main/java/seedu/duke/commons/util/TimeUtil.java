package seedu.duke.commons.util;

import seedu.duke.commons.core.Messages;
import seedu.duke.commons.util.exceptions.TimeParseException;

public class TimeUtil {
    /**
     * Parses 12-hour clock format to 24-hour clock format.
     *
     * @param clock 12-hour clock
     * @return 24-hour clock
     * @throws TimeParseException if input clock is invalid
     */
    public static String parseTwelveHourClock(String clock) throws TimeParseException {
        if (clock.length() != 8) {
            throw new TimeParseException(Messages.ERROR_INVALID_TIME_FORMAT);
        }

        String hour = clock.substring(0, 2);
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

        if (clock.charAt(2) != ':') {
            throw new TimeParseException(Messages.ERROR_INVALID_TIME_FORMAT);
        }

        String minute = clock.substring(3, 5);
        int minuteToInt;
        try {
            minuteToInt = Integer.parseInt(minute);
        } catch (NumberFormatException e) {
            throw new TimeParseException(Messages.ERROR_INVALID_TIME_FORMAT);
        }
        if (minuteToInt < 0 || minuteToInt > 59) {
            throw new TimeParseException(Messages.ERROR_INVALID_TIME_FORMAT);
        }

        String period = clock.substring(6, 8);
        if (period.equals("PM")) {
            hourToInt += 12;
        }

        return (hourToInt < 10 ? "0" : "") + hourToInt + ":" + (minuteToInt < 10 ? "0" : "") + minuteToInt;
    }
}
