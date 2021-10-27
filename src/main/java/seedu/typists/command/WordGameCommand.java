package seedu.typists.command;

import seedu.typists.command.Command;
import seedu.typists.game.WordLimitGame;

import java.util.ArrayList;

import static seedu.typists.Main.content;
import static seedu.typists.Main.uiBot;

public class WordGameCommand implements Command {
    private static final String CONTENT_SIGNIFIER = "-c";

    @Override
    public void run(ArrayList<String> args) {
        if (args.contains(CONTENT_SIGNIFIER)) {
            content.setContent();
        }
        startWordLimitGame();
    }

    public void startWordLimitGame() {
        uiBot.printKeyboard();
        new WordLimitGame(content.getContent(), 5).runGame();
    }
}
