package seedu.duke.command;

//@@author SeanRobertDH
public enum CommandEnum {
    BYE, HELP, TODO, DEADLINE, EVENT, LIST, DELETE, SORT, MODULE, INVALID;

    public static CommandEnum getCommand(String command) {
        for (CommandEnum commandEnum : CommandEnum.values()) {
            if (command.equalsIgnoreCase(commandEnum.name())) {
                return commandEnum;
            }
        }
        return CommandEnum.INVALID;
    }

    @Override
    public String toString() {
        return this.name().toLowerCase();
    }
}
