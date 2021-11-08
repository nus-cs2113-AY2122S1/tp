package seedu.tp.task;

/**
 * {@link java.lang.Enum} class used to store the possible {@link seedu.tp.task.Task} types.
 */
public enum TypeEnum {
    TODO, DEADLINE, EVENT, LESSON;

    /**
     * Returns the name of {@link TypeEnum} in lowercase.
     *
     * @return <code>String</code> of {@link TypeEnum} name in lowercase.
     */
    @Override
    public String toString() {
        return super.toString().toLowerCase();
    }
}
