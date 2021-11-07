package ui.card;

import data.Player;
import ui.Menu;
import ui.game.DifficultMenu;
import ui.game.EasyMenu;
import ui.game.GameCommandType;
import ui.game.GameMenu;
import ui.main.GameMainCommandType;
import ui.main.GameMainMenu;
import utils.Errors;
import utils.IO;
import utils.StringParser;
import utils.message.Strings;

import java.util.NoSuchElementException;
import java.util.Scanner;

/**
 * The card menu of the CodeHunt System.
 */
public class CardMenu extends Menu {
    /**
     * The {@code Scanner} for the menu to consume input.
     */
    private final Scanner in;

    /**
     * The parser with the rest of the command to be consumed by the menu.
     */
    private final StringParser parser;

    /**
     * Creates a new instance of the card menu.
     */
    public CardMenu(Scanner in, StringParser parser) {
        this.in = in;
        this.parser = parser;
    }

    @Override
    public void enter() {
        welcome();
        help();
        try {
            while (true) {
                prompt();
                String command = in.nextLine();
                StringParser parser = new StringParser(command);
                CardCommandType commandType = CardCommandType.getCommandType(parser.nextToken());
                if (commandType == null) {
                    Errors.print(parser.getString(), Strings.ERR_UNKNOWN_COMMAND);
                    continue;
                }
                if (parser.hasMoreTokens()) {
                    Errors.print(parser.getRemaining(), Strings.ERR_UNEXPECTED_INPUT);
                    continue;
                }
                switch (commandType) {
                case LIST: {
                    System.out.println(Strings.LIST_CARD_MESSAGE);
                    listCards();
                    break;
                }
                case DELETE: {
                    deleteCard();
                    break;
                }
                case FIND: {
                    findCard();
                    break;
                }
                case HELP: {
                    help();
                    break;
                }
                case BACK: {
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

    private void findCard() {
        int cardId = askCardId();
        if (cardId != -1) {
            Player.findCard(cardId);
        }

    }

    private void deleteCard() {
        int cardId = askCardId();
        if (cardId != -1) {
            Player.deleteCard(cardId);
        }
    }

    private int askCardId() {
        int cardId = IO.readInt(in, Strings.ASK_CARD_INDEX);
        while (cardId != -1) {
            if (cardId > 0 && cardId <= Player.getCardSize()) {
                return cardId;
            } else {
                System.out.println(Strings.ERR_INVALID_CARD_ID);
            }
            cardId = IO.readInt(in, Strings.ASK_CARD_INDEX);
        }
        return -1;
    }

    /**
     * Displays the welcome message.
     */
    private void welcome() {
        System.out.println(Strings.CARD_WELCOME_MESSAGE);
        System.out.println();
    }

    /**
     * Displays the prompt of the menu.
     */
    private void prompt() {
        System.out.print(Strings.CARD_PROMPT);
        System.out.flush();
    }

    /**
     * Display holding cards.
     */
    private void listCards() {
        Player.showCollectedCards();
    }

    /**
     * Displays available commands and their corresponding details.
     */
    private void help() {
        for (CardCommandType commandType : CardCommandType.values()) {
            System.out.printf("%-12s%s\n", commandType.getCommand(), commandType.getInfo());
        }
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
