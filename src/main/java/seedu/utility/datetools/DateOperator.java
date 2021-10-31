package seedu.utility.datetools;

import seedu.entry.Entry;

import java.time.LocalDate;
import java.util.function.Predicate;

public abstract class DateOperator {
    public static boolean isBetweenStartAndEndDates(LocalDate startDate, LocalDate endDate, Entry item) {
        LocalDate date = item.getDate();
        return ((date.isAfter(startDate) || date.isEqual(startDate))
                && (date.isBefore(endDate) || date.isEqual(endDate)));
    }

    public static boolean isValidDateRange(LocalDate startDate, LocalDate endDate) {
        return ((startDate.isBefore(endDate) || startDate.isEqual(endDate)));
    }

    public static boolean isSameYear(int inputYear, Entry item) {
        return item.getDate().getYear() == inputYear;
    }

    public static boolean isSameMonth(int inputMonth, Entry item) {
        return item.getDate().getMonthValue() == inputMonth;
    }

    public static Predicate<Entry> entryDateInRange(LocalDate startDate, LocalDate endDate) {
        return item -> isBetweenStartAndEndDates(startDate, endDate, item);
    }

    public static Predicate<Entry> sameEntryMonth(int inputMonth) {
        return item -> isSameMonth(inputMonth, item);
    }

    public static Predicate<Entry> sameEntryYear(int inputYear) {
        return item -> isSameYear(inputYear, item);
    }
}
