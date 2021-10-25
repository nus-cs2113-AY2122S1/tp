package seedu.duke.task;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import seedu.duke.exception.InvalidRecurrenceException;

//@@author SeanRobertDH
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
     * Returns the name of enum in lowercase.
     *
     * @return <code>String</code> of {@link RecurrenceEnum} enum name in lowercase.
     */
    @Override
    public String toString() {
        return super.toString().toLowerCase();
    }

    public LocalDateTime getRecurredDate(LocalDateTime date) {
        if (this == NONE) {
            return date;
        }
        return date.plus(1, chronoUnit);
    }

    public static RecurrenceEnum getRecurrence(String recurrence) throws InvalidRecurrenceException {
        for (RecurrenceEnum recurrenceEnum : values()) {
            if (recurrence.equalsIgnoreCase(recurrenceEnum.name())) {
                return recurrenceEnum;
            }
        }
        throw new InvalidRecurrenceException(recurrence);
    }
}
