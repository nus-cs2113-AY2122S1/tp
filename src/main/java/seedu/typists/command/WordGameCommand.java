package seedu.typists.command;

import seedu.typists.exception.IncompleteCommandException;
import seedu.typists.exception.InvalidCommandException;
import seedu.typists.game.Game;
import seedu.typists.game.WordLimitGame;

import java.util.ArrayList;

import static seedu.typists.Main.content;

public class WordGameCommand extends GameCommand {
    private static final String WORD_SIGNIFIER = "-word";

    @Override
    public Game createGame(ArrayList<String> args, boolean isReady, boolean setContent) {
        //ui.printKeyBoard
        try {
            int wordLimit = getNumber(args, WORD_SIGNIFIER);
            if (setContent) {
                content.setContent();
            }
            return new WordLimitGame(wordLimit, content.getContent(), 5, isReady);
        } catch (NumberFormatException e) {
            System.out.println("Number of words be a number");
        } catch (InvalidCommandException e) {
            //won't come here.
        }
        return null;
    }

}
