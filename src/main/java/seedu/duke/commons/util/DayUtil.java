package seedu.duke.commons.util;

import seedu.duke.commons.core.Messages;
import seedu.duke.commons.util.exceptions.InvalidDayException;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

//@@author Roycius
public class DayUtil {
    public static String getToday() {
        LocalDate currentDate = LocalDate.now();
        String currentDayOfWeek = currentDate.getDayOfWeek().toString();
        return currentDayOfWeek.substring(0, 3).toLowerCase();
    }

    public static String getTomorrow() {
        LocalDate currentDate = LocalDate.now().plusDays(1);
        String currentDayOfWeek = currentDate.getDayOfWeek().toString();
        return currentDayOfWeek.substring(0, 3).toLowerCase();
    }

    public static boolean isToday(String period) {
        return period.equalsIgnoreCase("today");
    }

    public static boolean isTomorrow(String period) {
        return period.equalsIgnoreCase("tomorrow");
    }

    public static int compareDay(String d1, String d2) throws InvalidDayException {
        Map<String, Integer> order = new HashMap<>();
        order.put("Monday", 1);
        order.put("Tuesday", 2);
        order.put("Wednesday", 3);
        order.put("Thursday", 4);
        order.put("Friday", 5);
        order.put("Saturday", 6);
        order.put("Sunday", 7);

        boolean isInvalidDay = !order.containsKey(d1) || !order.containsKey(d2);
        if (isInvalidDay) {
            throw new InvalidDayException(Messages.ERROR_INVALID_DAY);
        }

        int p1 = order.get(d1);
        int p2 = order.get(d2);
        return Integer.compare(p1, p2);
    }
}
