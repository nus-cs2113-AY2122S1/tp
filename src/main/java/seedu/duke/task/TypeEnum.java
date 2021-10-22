package seedu.duke.task;

import seedu.duke.exception.InvalidTaskTypeException;

public enum TypeEnum {
    TODO, DEADLINE, EVENT, LESSON;

    @Override
    public String toString() {
        return super.toString().toLowerCase();
    }

    public static TypeEnum getTaskType(String taskString) throws InvalidTaskTypeException {
        for (TypeEnum type : values()) {
            if (taskString.equalsIgnoreCase(type.name())) {
                return type;
            }
        }
        throw new InvalidTaskTypeException(taskString);
    }
}
