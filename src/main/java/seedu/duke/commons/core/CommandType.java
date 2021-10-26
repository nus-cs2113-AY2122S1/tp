package seedu.duke.commons.core;

//@@author richwill28
public enum CommandType {
    ADD,
    ALL,
    DELETE,
    DONE,
    EXIT,
    FIND,
    GRADE,
    INVALID,
    LESSON,
    LIST,
    MODULE,
    SET,
    TASK,
    MODULEINFO,
    HELP;

    /**
     * Determines the correct command type from the input parameter.
     *
     * @param param the input parameter
     * @return the command type of the input parameter
     */
    public static CommandType of(String param) {
        for (CommandType commandType : CommandType.values()) {
            if (param.equalsIgnoreCase(commandType.toString())) {
                return commandType;
            }
        }
        return INVALID;
    }
}
