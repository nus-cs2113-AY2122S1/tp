package seedu.duke.commons.util;

import java.time.LocalDate;

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
}
