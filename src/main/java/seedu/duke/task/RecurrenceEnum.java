package seedu.duke.task;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;
import seedu.duke.exception.InvalidRecurrenceException;

//@@author SeanRobertDH
/**
 * {@link java.lang.Enum} class used to store the possible {@link seedu.duke.task.Task} recurrences.
 */
public enum RecurrenceEnum {
    NONE(null),
    DAILY(ChronoUnit.DAYS),
    WEEKLY(ChronoUnit.WEEKS),
    MONTHLY(ChronoUnit.MONTHS),
    YEARLY(ChronoUnit.YEARS);

    private final ChronoUnit chronoUnit;

    RecurrenceEnum(ChronoUnit chronoUnit) {
        this.chronoUnit = chronoUnit;
    }

    /**
     * Returns the name of {@link RecurrenceEnum} in lowercase.
     *
     * @return <code>String</code> of {@link RecurrenceEnum} name in lowercase.
     */
    @Override
    public String toString() {
        return super.toString().toLowerCase();
    }

    /**
     * Returns a <code>List</code> of <code>LocalDateTimes</code> which is the next
     * <code>n</code> recurrences of <code>date</code> argument.
     * If recurrenceEnum is {@link #NONE}, an empty list is returned.
     *
     * @return <code>List&lt;String&gt;</code> of next <code>n</code> recurrences of <code>date</code>.
     */
    public List<LocalDateTime> getNextNRecurredDates(LocalDateTime date, int n) {
        ArrayList<LocalDateTime> recurredDates = new ArrayList<>(n);
        if (this == NONE) {
            return recurredDates;
        }
        for (int i = 1; i <= n; i++) {
            recurredDates.add(date.plus(i, chronoUnit));
        }
        return recurredDates;
    }

    /**
     * Returns the next recurred date of <code>date</code>.
     * If recurrenceEnum is {@link #NONE}, <code>date</code> is returned.
     *
     * @return Next recurrence of <code>date</code>.
     */
    public LocalDateTime getNextRecurredDate(LocalDateTime date) {
        if (this == NONE) {
            return date;
        }
        long i = 0;
        LocalDateTime recurredDate;
        do {
            recurredDate = date.plus(i++, chronoUnit);
        } while (recurredDate.isBefore(LocalDateTime.now()));

        return recurredDate;
    }

    /**
     * Returns the {@link RecurrenceEnum} corresponding to <code>String recurrence</code>.
     *
     * @param recurrence the recurrence <code>String</code> corresponding
     *     to the {@link RecurrenceEnum} you want to obtain.
     * @return {@link RecurrenceEnum} enum corresponding to <code>String recurrence</code> argument.
     * @throws InvalidRecurrenceException if <code>recurrence</code> does not
     *     correspond to a valid {@link RecurrenceEnum}.
     */
    public static RecurrenceEnum getRecurrence(String recurrence) throws InvalidRecurrenceException {
        for (RecurrenceEnum recurrenceEnum : values()) {
            if (recurrence.equalsIgnoreCase(recurrenceEnum.name())) {
                return recurrenceEnum;
            }
        }
        throw new InvalidRecurrenceException(recurrence);
    }


}
