package ui.main;

import java.util.Scanner;
import java.util.NoSuchElementException;

import data.Player;
import storage.Storage;
import ui.game.GameMenu;
import utils.IO;
import utils.message.Strings;
import ui.Menu;
import utils.Errors;
import utils.StringParser;

import java.util.logging.Logger;


/**
 * The main menu of the CodeHunt system.
 *
 * @author Wang Ruisi
 */
public class GameMainMenu extends Menu {
    /**
     * The {@code Scanner} for the menu to consume input.
     */
    private final Scanner in;


    /**
     * Creates a new instance of the main menu.
     */
    public GameMainMenu(Scanner in) {
        this.in = in;
    }

    public void enter() {
        welcome();
        help();
        try {
            while (true) {
                prompt();
                String command = in.nextLine();
                if (command.isBlank()) {
                    continue;
                }
                StringParser parser = new StringParser(command);
                GameMainCommandType commandType = GameMainCommandType.getCommandType(parser.nextToken());
                if (commandType == null) {
                    Errors.print(parser.getString(), Strings.ERR_UNKNOWN_COMMAND);
                    continue;
                }
                switch (commandType) {
                case NEWGAME: {
                    Storage.loadPlayer();
                    GameMenu gameMenu = new GameMenu(in, parser);
                    gameMenu.enter();
                    help();
                    break;
                }
                case CONTINUE: {
                    String playerId = IO.readString(in, Strings.MAIN_PLAYER_ID_ENTER_PROMPT).trim();
                    assert playerId != null : "Nothing is inputted!!";
                    Storage.loadPlayer(playerId);
                    GameMenu gameMenu = new GameMenu(in, parser);
                    gameMenu.enter();
                    help();
                    break;
                }
                case HELP: {
                    if (parser.hasMoreTokens()) {
                        Errors.print(parser.getRemaining(), Strings.ERR_UNEXPECTED_INPUT);
                        continue;
                    }
                    help();
                    break;
                }
                case ABOUT: {
                    if (parser.hasMoreTokens()) {
                        Errors.print(parser.getRemaining(), Strings.ERR_UNEXPECTED_INPUT);
                        continue;
                    }
                    about();
                    break;
                }
                case EXIT: {
                    if (parser.hasMoreTokens()) {
                        Errors.print(parser.getRemaining(), Strings.ERR_UNEXPECTED_INPUT);
                        continue;
                    }
                    if (Player.getName() != null) {
                        Storage.savePlayer();
                    }
                    exit(true);
                    return;
                }
                default: {
                    break;
                }
                }
            }
        } catch (NoSuchElementException e) {
            exit(false);
        }
    }

    /**
     * Displays the welcome message.
     */
    private void welcome() {
        System.out.println(Strings.MAIN_WELCOME_MESSAGE);
        System.out.println();
    }

    /**
     * Displays the prompt of the menu.
     */
    private void prompt() {
        System.out.print(Strings.MAIN_PROMPT);
        System.out.flush();
    }

    /**
     * The handler for command "help". Displays help message consisting of commands
     * available and their corresponding details.
     */
    private void help() {
        for (GameMainCommandType commandType : GameMainCommandType.values()) {
            System.out.printf("%-12s%s\n", commandType.getCommand(), commandType.getInfo());
        }
        System.out.println();
    }

    /**
     * The handler for command "about". Displays information about the system.
     */
    private void about() {
        System.out.println(Strings.MAIN_ABOUT_MESSAGE);
        System.out.println();
    }

    /**
     * The handler for command "exit". Does all necessary cleanups before the exit.
     * Note that the exit does not happen here. It is done by the {@code return}
     * statement under the {@link #enter()} method.
     *
     * @param promptToSave whether to prompt to save the current state or not
     */
    public void exit(boolean promptToSave) {
        // TODO: Implement "prompt to save" functionality
        System.out.println(Strings.MAIN_EXIT_MESSAGE);
        System.out.println();
    }
}


