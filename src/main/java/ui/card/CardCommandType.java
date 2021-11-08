package ui.card;

import utils.message.Strings;

public enum CardCommandType {
    /**
     * Command "list".
     */
    LIST("list", "Show your package of all holding."),

    /**
     * Command "delete".
     */
    DELETE("delete", "Delete an unwanted card in your package."),

    /**
     * Command "find".
     */
    FIND("find", "Search cards by card index."),

    /**
     * Command "help".
     */
    HELP("help", "Display a list of available commands."),

    /**
     * Command "back".
     */
    BACK("back", "Back to game menu.");

    /**
     * The command corresponding to the command type.
     */
    private final String command;

    /**
     * Information of the command.
     */
    private final String info;

    CardCommandType(String command, String info) {
        this.command = command.toLowerCase();
        this.info = info;
    }

    /**
     * Get command type from a string.
     *
     * @param command the command as a string
     * @return the corresponding command type if there is a match; {@code null} otherwise
     */
    public static CardCommandType getCommandType(String command) {
        for (CardCommandType commandType : CardCommandType.values()) {
            if (command.toLowerCase().equals(commandType.getCommand())) {
                return commandType;
            }
        }
        return null;
    }

    public String getCommand() {
        return command;
    }

    public String getInfo() {
        return info;
    }
}
