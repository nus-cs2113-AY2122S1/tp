package seedu.typists.parser;

import seedu.typists.game.DataProcessor;
import seedu.typists.game.Game;
import seedu.typists.game.TimeModeGame;

import static seedu.typists.Main.LINE_LENGTH;
import static seedu.typists.Main.content;

public class TimeGameCommand implements Command {

    @Override
    public void run() {
        startTimeLimitGame();
    }

    public void startTimeLimitGame() {
        Game newGame = new TimeModeGame(content.getContent(), LINE_LENGTH);
        newGame.runGame();
        //need to be modified with data processor
        DataProcessor p = new DataProcessor((TimeModeGame) newGame);
    }
}
