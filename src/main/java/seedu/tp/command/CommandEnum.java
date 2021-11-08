package seedu.tp.command;

//@@author SeanRobertDH
/**
 * {@link java.lang.Enum} class used to store the names of all commands.
 */
public enum CommandEnum {
    BYE, HELP, TODO, DEADLINE, EVENT, LIST, DELETE, REMINDER, SORT, MODULE, EDIT, BROWSE, INVALID, UPCOMING;

    /**
     * Returns the {@link seedu.tp.command.CommandEnum} corresponding to String command.
     * If command does not match any command, {@link #INVALID} is returned.
     *
     * @param command <code>String</code> of name of {@link seedu.tp.command.CommandEnum} to return.
     * @return {@link seedu.tp.command.CommandEnum} enum corresponding
     *     to <code>String command</code> {@link seedu.tp.command.CommandEnum}.
     */
    public static CommandEnum getCommand(String command) {
        for (CommandEnum commandEnum : CommandEnum.values()) {
            if (command.equalsIgnoreCase(commandEnum.name())) {
                return commandEnum;
            }
        }
        return CommandEnum.INVALID;
    }

    /**
     * Returns the name of {@link seedu.tp.command.CommandEnum} in lowercase.
     *
     * @return <code>String</code> of {@link seedu.tp.command.CommandEnum} enum name in lowercase.
     */
    @Override
    public String toString() {
        return this.name().toLowerCase();
    }
}
