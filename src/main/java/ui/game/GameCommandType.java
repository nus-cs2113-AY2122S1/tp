package ui.game;

import ui.main.GameMainCommandType;
import utils.message.Strings;

public enum GameCommandType {
    /**
     * Command "start".
     */
    START("start", "Start and then choose your difficulty level."),

    /**
     * Command "check".
     */
    CHECK("check", "Check Current Game Progress."),

    /**
     * Command "card".
     */
    CARD("card", "Manage your holding cards. You can view coding knowledge here!"),

    /**
     * Command "help".
     */
    HELP("help", "Display a list of available commands."),

    /**
     * Command "back".
     */
    BACK("back", "Back to main menu.");

    /**
     * The command corresponding to the command type.
     */
    private final String command;

    /**
     * Information of the command.
     */
    private final String info;

    GameCommandType(String command, String info) {
        this.command = command.toLowerCase();
        this.info = info;
    }

    /**
     * Get command type from a string.
     *
     * @param command the command as a string
     * @return the corresponding command type if there is a match; {@code null} otherwise
     */
    public static GameCommandType getCommandType(String command) {
        for (GameCommandType commandType : GameCommandType.values()) {
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
