package seedu.duke.task;

import seedu.duke.exception.InvalidRecurrenceException;

public enum RecurrenceEnum {
    NONE, DAILY, WEEKLY, MONTHLY, YEARLY;

    /**
     * Returns the name of enum in lowercase.
     *
     * @return <code>String</code> of {@link RecurrenceEnum} enum name in lowercase.
     */
    @Override
    public String toString() {
        return super.toString().toLowerCase();
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
