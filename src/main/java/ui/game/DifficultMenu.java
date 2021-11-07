package ui.game;

import data.Player;
import data.game.GuessingNumGame;
import data.game.HangmanGame;
import data.game.QuizGame;
import data.game.TreasureHuntGame;
import ui.Menu;
import utils.Errors;
import utils.IO;
import utils.StringParser;
import utils.message.Strings;

import java.util.Scanner;

/**
 * The difficult game mode menu of the CodeHunt game.
 */
public class DifficultMenu extends Menu {
    /**
     * The {@code Scanner} for the menu to consume input.
     */
    private final Scanner in;

    /**
     * The parser with the rest of the command to be consumed by the menu.
     */
    private final StringParser parser;

    /**
     * Creates a new instance of the difficult menu.
     */
    public DifficultMenu(Scanner in, StringParser parser) {
        this.in = in;
        this.parser = parser;
    }

    @Override
    public void enter() {
        welcome();

        switch (Player.printDifficultRecord()) {
        case "empty":
            if (startHangMan() == 3) {
                return;
            }
            if (startQuizGame() == 3) {
                return;
            }
            if (startGuessNum() == 3) {
                return;
            }
            if (startTreasureHunt() == 3) {
                return;
            }
            break;
        case "Hang Man":
            if (startQuizGame() == 3) {
                return;
            }
            if (startGuessNum() == 3) {
                return;
            }
            if (startTreasureHunt() == 3) {
                return;
            }
            break;
        case "Knowledge Quiz":
            if (startGuessNum() == 3) {
                return;
            }
            if (startTreasureHunt() == 3) {
                return;
            }
            break;
        case "Guess Number":
            if (startTreasureHunt() == 3) {
                return;
            }
            break;
        default:
            break;
        }

        System.out.println(Strings.FINISH_DIFFICULT_MODE);

    }

    /**
     * Displays the welcome message and the background.
     */
    private void welcome() {
        System.out.println(Strings.DIFFICULT_LOGO);
        System.out.println(Strings._GAME_WELCOME_MESSAGE);
        System.out.println();
    }

    private int startHangMan() {
        int cardId;

        while (true) {
            System.out.println(Strings.HANG_MAN_START);
            HangmanGame hangmanGame = new HangmanGame();
            cardId = hangmanGame.execute(false);
            Player.winCard(cardId);

            if (cardId != 0) { // Win the Game
                Player.addDifficultGameRecord(hangmanGame);

                System.out.print(hangmanGame.getName());
                int playStatus = setPlayStatus();
                if (playStatus != 2) {
                    return playStatus;
                }
            } else {
                System.out.print(hangmanGame.getName());
                int playStatus = setPlayStatusLoss();
                if (playStatus == 3) {
                    return playStatus;
                }
            }
        }
    }

    private int startGuessNum() {
        int cardId;

        while (true) {
            System.out.println(Strings.GUESS_NUM_START);
            GuessingNumGame guessingNumGame = new GuessingNumGame();
            cardId = guessingNumGame.execute(false);
            Player.winCard(cardId);

            if (cardId != 0) { // Win the Game
                Player.addDifficultGameRecord(guessingNumGame);

                System.out.print(guessingNumGame.getName());
                int playStatus = setPlayStatus();
                if (playStatus != 2) {
                    return playStatus;
                }
            } else {
                System.out.print(guessingNumGame.getName());
                int playStatus = setPlayStatusLoss();
                if (playStatus == 3) {
                    return playStatus;
                }
            }
        }
    }

    private int startQuizGame() {
        int cardId;

        while (true) {
            System.out.println(Strings.QUIZ_START);
            QuizGame quizGame = new QuizGame("2");
            cardId = quizGame.execute(false);
            Player.winCard(cardId);

            if (cardId != 0) { // Win the Game
                Player.addDifficultGameRecord(quizGame);

                System.out.print(quizGame.getName());
                int playStatus = setPlayStatus();
                if (playStatus != 2) {
                    return playStatus;
                }
            } else {
                System.out.print(quizGame.getName());
                int playStatus = setPlayStatusLoss();
                if (playStatus == 3) {
                    return playStatus;
                }
            }
        }
    }

    private int startTreasureHunt() {
        int cardId;

        while (true) {
            System.out.println(Strings.TREASURE_HUNT_START);
            TreasureHuntGame treasureHuntGame = new TreasureHuntGame();
            cardId = treasureHuntGame.execute(false);
            Player.winCard(cardId);

            if (cardId != 0) { // Win the Game
                Player.addDifficultGameRecord(treasureHuntGame);

                System.out.print(treasureHuntGame.getName());
                int playStatus = setPlayStatus();
                if (playStatus != 2) {
                    return playStatus;
                }
            } else {
                System.out.print(treasureHuntGame.getName());
                int playStatus = setPlayStatusLoss();
                if (playStatus == 3) {
                    return playStatus;
                }
            }
        }
    }

    private int setPlayStatus() {
        int playStatus;

        while (true) {
            playStatus = IO.readInt(in, Strings.GAME_END_SIGN_WIN);
            if (playStatus == 1 || playStatus == 2 || playStatus == 3) {
                break;
            }
            Errors.print(Integer.toString(playStatus), Strings.ERR_PLAY_INVALID_NUMBER);
        }

        return playStatus;
    }

    private int setPlayStatusLoss() {
        int playStatus;

        while (true) {
            playStatus = IO.readInt(in, Strings.GAME_END_SIGN_LOSS);
            if (playStatus == 1 || playStatus == 3) {
                break;
            }
            Errors.print(Integer.toString(playStatus), Strings.ERR_PLAY_INVALID_NUMBER);
        }

        return playStatus;
    }
}


