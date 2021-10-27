package seedu.typists.game;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

import static seedu.typists.common.Messages.MESSAGE_TIME_GAME_END;
import static seedu.typists.common.Utils.*;

public class TimeModeGame extends Game {
    protected static final Logger logger = Logger.getLogger("Foo");
    protected int gameTime;
    protected final ArrayList<String> wordLists;
    protected int wordsPerLine;

    public double realGameTime;

    public TimeModeGame(String targetWordSet, int wordsPerLine) {
        super();
        assert targetWordSet != null : "text passed into Time Game should not be null.";
        this.wordLists = splitStringIntoWordList(targetWordSet);
        this.userLines = new ArrayList<>();
        this.displayedLines = new ArrayList<>();
        this.gameTime = getGameTime();
        this.wordsPerLine = wordsPerLine;
    }

    public int getGameTime() {
        Scanner in = new Scanner(System.in);
        ui.printScreen("Enter how long (in seconds) you want the game to run: ");
        try {
            return Integer.parseInt(in.nextLine());
        } catch (NumberFormatException e) {
            System.out.println("Not a Number!");
            return getGameTime();
        }
    }


    public void runGame() {
        Scanner in = new Scanner(System.in);
        List<String> inputs = new ArrayList<>();

        if (ui.readyToStartTimer()) {
            int currentRow = 1;
            long beginTime = getTimeNow();
            boolean timeOver = false;

            while (!timeOver) {
                long currTime = getTimeNow() - beginTime;
                if (currTime >= gameTime * 1000L) {
                    realGameTime = (double) currTime / 1000;
                    timeOver = true;
                } else {
                    String[] display = getDisplayLines(currentRow);
                    ui.printLine(display);
                    displayedLines.add(display);
                    inputs.add(in.nextLine());
                    currentRow++;
                }
            }

            updateUserLines(inputs);
            endGame();
        }
    }

    public void endGame() {
        ui.printEnd(MESSAGE_TIME_GAME_END);
        double overshoot = realGameTime - gameTime;
        assert overshoot >= 0;
        if (overshoot > 0) {
            ui.printOvershoot(overshoot);
        }
    }

    public String[] getDisplayLines (int row) {
        int startIndex = (row - 1) * wordsPerLine;
        assert startIndex >= 0 : "word index should be non-negative";
        String[] line = new String[wordsPerLine];
        try {
            for (int i = 0; i < wordsPerLine; i++) {
                line[i] = wordLists.get(startIndex + i);
            }
        } catch (IndexOutOfBoundsException e) {
            logger.log(Level.WARNING, "exceed content maximum.");
        }
        return line;
    }

    public void gameSummary() {
        HashMap<String, Object> summary = handleSummary(displayedLines, userLines, realGameTime, "Word-limited");
        handleStorage(summary);
    }


    public void updateUserLines(List<String> stringArray) {
        userLines = getWordLineFromStringArray(stringArray);
    }
}

