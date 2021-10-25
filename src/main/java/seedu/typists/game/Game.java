package seedu.typists.game;

import seedu.typists.ui.SummaryUI;
import seedu.typists.ui.TextUi;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public abstract class Game {
    protected final TextUi ui;

    public Game() {
        this.ui = new TextUi();
    }

    public long getTimeNow() {
        return System.currentTimeMillis();
    }

    public double duration(long startTime, long endTime) {
        return (double) (endTime - startTime) / 1000;
    }

    public abstract void runGame();

    public void handleSummary(ArrayList<String[]> expectedInput, ArrayList<String[]> actualInput, double timeElapsed, String gameMode) {
        HashMap<String, Object> summary = SummaryManager.generateSummary(expectedInput, actualInput, timeElapsed, gameMode);
        SummaryUI.displaySummary(summary);
    }

    public void handleStorage() {

    }
}
