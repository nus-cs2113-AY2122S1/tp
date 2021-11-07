package happybit.habit;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Calendar;
import java.util.Date;

public class HabitDateManager {

    /**
     * Converts date into a string.
     *
     * @param date Date to be converted.
     * @return String format of a date.
     */
    public static String dateToString(Date date) {
        SimpleDateFormat formatter = new SimpleDateFormat("ddMMyyyy");
        return formatter.format(date);
    }

    /**
     * 'Type-casting' a Date to a LocalDate.
     *
     * @param date Date to be 'type-casted'.
     * @return LocalDate that has been 'type-casted' from Date.
     */
    public static LocalDate convertDateToLocalDate(Date date) {
        return date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
    }

    /**
     * Sets the hours, minute, seconds, milliseconds of a date to the start of the day.
     *
     * @param date Date to set to start of the day.
     * @return Date corresponding to start of the day.
     */
    public static Date getStartOfDay(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);
        return calendar.getTime();
    }

    /**
     * Sets the hours, minute, seconds, milliseconds of a date to the end of the day.
     *
     * @param date Date to set to end of the day.
     * @return Date corresponding to end of the day.
     */
    public static Date getEndOfDay(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.set(Calendar.HOUR_OF_DAY, 23);
        calendar.set(Calendar.MINUTE, 59);
        calendar.set(Calendar.SECOND, 59);
        calendar.set(Calendar.MILLISECOND, 999);
        return calendar.getTime();
    }

    /**
     * Gets the start of the next day.
     *
     * @param date Date used to get the next day.
     * @return Date of the start of the next day.
     */
    public static Date getStartOfNextDay(Date date) {
        Date currStartOfDay = getStartOfDay(date);
        return addDaysToDate(currStartOfDay, 1);
    }

    /**
     * Adds number of dates equal to the interval to the base date.
     *
     * @param baseDate Date to have days added to.
     * @param days     Number of days to be added to base date.
     * @return Date with days added.
     */
    public static Date addDaysToDate(Date baseDate, int days) {
        LocalDate baseLocalDate = convertDateToLocalDate(baseDate);
        LocalDate nextLocalDate = baseLocalDate.plusDays(days);
        return convertLocalDateToDate(nextLocalDate);
    }

    /*
     * NOTE : ==================================================================
     * The following are private methods that are used to implement SLAP for the
     * above public methods. These methods are positioned at the bottom to better
     * visualise the actual methods that can be called from outside this class.
     * =========================================================================
     */

    /**
     * 'Type-casting' a LocalDate to a Date.
     *
     * @param localDate LocalDate to be 'type-casted'.
     * @return Date that has been 'type-casted' from LocalDate.
     */
    private static Date convertLocalDateToDate(LocalDate localDate) {
        return Date.from(localDate.atStartOfDay().atZone(ZoneId.systemDefault()).toInstant());
    }


}
