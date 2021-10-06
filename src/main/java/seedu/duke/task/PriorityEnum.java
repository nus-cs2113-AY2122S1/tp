package seedu.duke.task;

import java.util.Map;
import seedu.duke.exception.PriorityEnumDoesNotExistException;

import static java.util.Arrays.stream;
import static java.util.stream.Collectors.toMap;

public enum PriorityEnum {
    //Values from https://www.kanzaki.com/docs/ical/priority.html
    HIGH(1),
    MEDIUM(5),
    LOW(9);

    private static final int LOWEST_PRIORITY_INT = 9;
    private static final int HIGHEST_PRIORITY_INT = 1;

    private final int priorityNumber;

    PriorityEnum(final int priorityNumber) {
        this.priorityNumber = priorityNumber;
    }

    int getValue() {
        return this.priorityNumber;
    }

    /**
     * Returns the name of enum in lowercase.
     *
     * @return <code>String</code> of {@link PriorityEnum} enum name in lowercase.
     */
    @Override
    public String toString() {
        return super.toString().toLowerCase();
    }

    /**
     * Returns the Priority enum corresponding to int argument.
     *
     * @return {@link PriorityEnum} enum corresponding to <code>int priorityNumber</code> argument.
     */
    public static PriorityEnum getEnum(int priorityNumber) throws PriorityEnumDoesNotExistException {
        if (priorityNumber > LOWEST_PRIORITY_INT || priorityNumber < HIGHEST_PRIORITY_INT) {
            throw new PriorityEnumDoesNotExistException();
        }
        for (PriorityEnum priority : PriorityEnum.values()) {
            if (priorityNumber <= priority.getValue()) {
                return priority;
            }
        }
        throw new PriorityEnumDoesNotExistException();
    }
}
