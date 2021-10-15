package seedu.typists.commands;

import seedu.typists.exception.FaultyInputException;
import seedu.typists.exception.InvalidStringInputException;
import seedu.typists.parser.Parser;
import seedu.typists.parser.TextParser;
import seedu.typists.ui.TextUi;

import java.util.ArrayList;

import static seedu.typists.parser.StringParser.splitString;

public class NewGame {

    private ArrayList<String> game1;
    private int gameIndex;
    TextUi uiGame;

    public NewGame() {
        this.game1 = new ArrayList<>(100);
        this.gameIndex = 0;
        this.uiGame = new TextUi();
    }

    public int getTotalSentence() {
        return game1.size();
    }

    public void beginNewGame() throws InvalidStringInputException {
        String content1 = "The greatest glory in living lies not in never falling.";
        game1 = splitString(content1," ");
        uiGame.showText(content1);
        boolean isExit = false;
        while (!isExit) {
            uiGame.showText(game1.get(gameIndex));
            String fullCommand = uiGame.readCommand();
            TextParser c =  new TextParser(fullCommand, game1.get(gameIndex));
            isExit = c.getIsExit();
            gameIndex += 1;
            uiGame.printGameMode1Progress(gameIndex,getTotalSentence());
            if (gameIndex == getTotalSentence()) {
                uiGame.printSuccess();
                break;
            }
        }
    }
}
