package seedu.typists.parser;


import seedu.typists.game.DataProcessor;
import seedu.typists.game.Game;
import seedu.typists.game.TimeModeGame;

import static seedu.typists.Main.LINE_LENGTH;

public class newGameCommand extends Command {
    @Override
    public void runCommand() {

    }

    public void newTimeModeGame() {
         Game newGame = new TimeModeGame(content,getContent(), LINE_LENGTH);
         DataProcessor p = new DataProcessor(newGame);
    }

    public void startTimeLimitGame() {
        uiBot.printClock();
        TimeModeGame g = new TimeModeGame(content.getContent(), LINE_LENGTH);

        uiBot.showSummary(
                p.getErrorWordCount(),
                p.getErrorPercentage(),
                p.getErrorWords(),
                p.getWordPerMinute(),
                p.getTotalWordTyped(),
                p.totalTime
        );
    }
}
