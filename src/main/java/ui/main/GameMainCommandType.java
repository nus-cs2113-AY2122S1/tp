package ui.main;

import utils.message.Strings;

public enum GameMainCommandType {
    /**
     * Command "newGame".
     */
    NEWGAME("newGame", "Start a new game."),
    /**
     * Command "continue".
     */
    CONTINUE("continue", "Continue the game stored previously."),
    /**
     * Command "help".
     */
    HELP("help", "Display a list of available commands."),

    /**
     * Command "about".
     */
    ABOUT("about", String.format("Display information about %s.", Strings.APP_NAME_SHORT)),

    /**
     * Command "exit".
     */
    EXIT("exit", String.format("Exit %s.", Strings.APP_NAME_SHORT));
    /**
     * The command corresponding to the command type.
     */
    private final String command;

    /**
     * Information of the command.
     */
    private final String info;

    GameMainCommandType(String command, String info) {
        this.command = command.toLowerCase();
        this.info = info;
    }

    /**
     * Get command type from a string.
     *
     * @param command the command as a string
     * @return the corresponding command type if there is a match; {@code null} otherwise
     */
    public static GameMainCommandType getCommandType(String command) {
        for (GameMainCommandType commandType : GameMainCommandType.values()) {
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
