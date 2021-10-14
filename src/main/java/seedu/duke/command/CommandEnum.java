package seedu.duke.command;

public enum CommandEnum {
    BYE, HELP, LIST, TODO, DEADLINE, EVENT, INVALID;

    @Override
    public String toString() {
        return super.toString().toLowerCase();
    }

    public static CommandEnum getCommand(String command) {
        if (command == null) {
            return CommandEnum.INVALID;
        }
        for (CommandEnum commandEnum : values()) {
            if (command.equalsIgnoreCase(commandEnum.name())) {
                return commandEnum;
            }
        }
        return CommandEnum.INVALID;
    }
}
