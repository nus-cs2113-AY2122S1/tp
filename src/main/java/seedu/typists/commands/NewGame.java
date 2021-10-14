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
    private static int game_index;
    TextUi uiGame;

    public NewGame() {
        this.game1 = new ArrayList<>(100);
        this.game_index = 0;
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
            uiGame.showText(game1.get(game_index));
            String fullCommand = uiGame.readCommand();
            TextParser c =  new TextParser(fullCommand, game1.get(game_index));
            isExit = c.getIsExit();
            game_index += 1;
            uiGame.printGameMode1Progress(game_index,getTotalSentence());
            if (game_index == getTotalSentence()) {
                uiGame.printSuccess();
                break;
            }
        }
    }

}
