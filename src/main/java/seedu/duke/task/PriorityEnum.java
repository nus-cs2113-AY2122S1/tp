package seedu.duke.task;

import seedu.duke.exception.InvalidPriorityException;

//@@author SeanRobertDH
/**
 * {@link java.lang.Enum} class used to store the possible {@link seedu.duke.task.Task} priorities.
 */
public enum PriorityEnum {
    HIGH(2),
    MEDIUM(1),
    LOW(0);

    private final int priorityNumber;

    PriorityEnum(final int priorityNumber) {
        this.priorityNumber = priorityNumber;
    }

    /**
     * Returns the correspnding integer value for {@link seedu.duke.task.PriorityEnum}.
     *
     * @return <code>int</code> of {@link PriorityEnum}.
     */
    public int getValue() {
        return this.priorityNumber;
    }

    /**
     * Returns the name of {@link PriorityEnum} in lowercase.
     *
     * @return <code>String</code> of {@link PriorityEnum} name in lowercase.
     */
    @Override
    public String toString() {
        return super.toString().toLowerCase();
    }

    /**
     * Returns the Priority enum corresponding to <code>int priorityNumber</code>.
     *
     * @param priorityNumber the priority <code>int</code> corresponding to the {@link PriorityEnum} you want to obtain.
     * @return {@link PriorityEnum} enum corresponding to <code>int priorityNumber</code> argument.
     * @throws seedu.duke.exception.InvalidPriorityException if <code>priorityNumber</code> does not
     *     correspond to a valid {@link seedu.duke.task.PriorityEnum}.
     */
    public static PriorityEnum getPriority(int priorityNumber) throws InvalidPriorityException {
        for (PriorityEnum priority : PriorityEnum.values()) {
            if (priorityNumber == priority.getValue()) {
                return priority;
            }
        }
        throw new InvalidPriorityException(Integer.toString(priorityNumber));
    }

    /**
     * Returns the {@link PriorityEnum} corresponding to <code>String priority</code>.
     *
     * @param priority the priority <code>String</code> corresponding to the {@link PriorityEnum} you want to obtain.
     * @return {@link PriorityEnum} enum corresponding to <code>int priorityNumber</code> argument.
     * @throws seedu.duke.exception.InvalidPriorityException if <code>priority</code> does not
     *     correspond to a valid {@link seedu.duke.task.PriorityEnum}.
     */
    public static PriorityEnum getPriority(String priority) throws InvalidPriorityException {
        for (PriorityEnum priorityEnum : values()) {
            if (priority.equalsIgnoreCase(priorityEnum.name())) {
                return priorityEnum;
            }
        }
        throw new InvalidPriorityException(priority);
    }


}
