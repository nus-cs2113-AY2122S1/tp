package seedu.typists.game;

import seedu.typists.ui.TextUi;

import java.util.ArrayList;

public abstract class Game {
    protected final TextUi ui;

    public ArrayList<String[]> displayedLines;
    public ArrayList<String[]> userLines;

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
    public abstract void store();
    public abstract void getSummary();
}
