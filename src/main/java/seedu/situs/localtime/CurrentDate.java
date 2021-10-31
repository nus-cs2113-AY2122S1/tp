package seedu.situs.localtime;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
//@@author datn02

public class CurrentDate {
    private static final String DATE_FORMAT = "dd/MM/yyyy";
    private static LocalDate currentDate;
    private static DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern(DATE_FORMAT);

    /**
     * Sets the current date to system's date at initialization.
     *
     */
    public static void setCurrentDate() {
        currentDate = LocalDate.now();
    }


    /**
     * Gets current date in the format specified by DATE_FORMAT constant.
     *
     * @return string representation of the current date
     */
    public static String getCurrentFormattedDate() {
        // failsafe
        if (currentDate == null) {
            currentDate = LocalDate.now();
        }

        return currentDate.format(dateFormat);
    }

    /**
     * Gets the current date in <code>LocalDate</code> variable.
     *
     * @return the current date in <code>LocalDate</code> variable
     */
    public static LocalDate getCurrentDate() {
        // failsafe
        if (currentDate == null) {
            currentDate = LocalDate.now();
        }

        return currentDate;
    }

    /**
     * Overwrites the current date as inputted by the user.
     *
     * @param newDate the new date to be overwritten in the system
     */
    public static void overwriteCurrentDate(LocalDate newDate) {
        currentDate = newDate;
    }
}
