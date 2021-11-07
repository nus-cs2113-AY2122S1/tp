package seedu.tp.nusmods;

import seedu.tp.exception.ImpossibleError;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.Month;
import java.time.Year;
import java.time.temporal.ChronoUnit;
import java.util.stream.IntStream;

import static java.time.temporal.TemporalAdjusters.firstInMonth;

//@@author SuibianP
/**
 * Enumeration class for NUS semesters.
 */
public enum Semester {
    S1, S2, ST1, ST2;

    public static Semester fromInt(int n) {
        // n is 1-based, as in NUSMods data
        return Semester.values()[n - 1];
    }

    /**
     * Get the semester which the date is in.
     * @param date A date to be queried
     * @return The semester of the date
     */
    public static Semester getSemester(LocalDate date) {
        for (Semester s : Semester.values()) {
            if (ChronoUnit.DAYS.between(s.getStartingMonday(), date) <= s.getWeekLength() * 7) {
                return s;
            }
        }
        throw new ImpossibleError();
    }

    public static Semester getSemester() {
        return getSemester(LocalDate.now());
    }

    /**
     * Get the standard number of weeks in a particular semester.
     * @return The number of weeks in the semester
     */
    public long getWeekLength() {
        switch (this) {
        case S1:
            return 18;
        case S2:
            return 17;
        case ST1:
        case ST2:
        default:
            return 6;
        }
    }

    /**
     * Convert academic weeks to real weeks, accounting for recess weeks, etc.
     * @param acadWeeks An array of academic weeks
     * @return An array of real weeks
     */
    public static int[] acadWeeksToRealWeeks(int[] acadWeeks) {
        return IntStream.of(acadWeeks).map(i -> i > 6 ? i + 1 : i).toArray();
    }

    /**
     * Get the starting Monday of a semester in a year.
     * @param year A year
     * @return The starting Monday
     */
    public LocalDate getStartingMonday(Year year) {
        switch (this) {
        case S1:
            return year.atMonth(Month.AUGUST).atDay(1).with(firstInMonth(DayOfWeek.MONDAY));
            // second Monday of August
        case S2:
            return year.atMonth(Month.JANUARY).atDay(1).with(firstInMonth(DayOfWeek.MONDAY));
            // second Monday of January
        case ST1:
            return S2.getStartingMonday(year).plusWeeks(17);
            // S2 + 17 week
        case ST2:
            return ST1.getStartingMonday(year).plusWeeks(6);
            // ST1 + 6 week
        default:
            throw new ImpossibleError();
        }
    }

    public LocalDate getStartingMonday() {
        return getStartingMonday(Year.now());
    }
}
