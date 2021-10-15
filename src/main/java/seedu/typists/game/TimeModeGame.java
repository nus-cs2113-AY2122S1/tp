package seedu.typists.game;

import java.util.ArrayList;
import java.util.Scanner;

import seedu.typists.ui.TextUi;
import static seedu.typists.common.Utils.getWordLine;

public class TimeModeGame extends Game {
    private final TextUi ui;

    protected final ArrayList<String[]> wordLines;
    protected final int gameTime;

    protected ArrayList<String> inputLines;
    protected int displayTime;

    public TimeModeGame(String targetWordSet, int gameTime, int wordsPerLine) {
        this.gameTime = gameTime;
        ui = new TextUi();
        inputLines = new ArrayList <>();
        wordLines = getWordLine(targetWordSet, wordsPerLine);
        startGame();
    }

    public boolean readyToStartTimer () {
        Scanner in = new Scanner(System.in);
        String command = "";
        while(!command.equals("yes")) {
            System.out.println("Do you want to start now?");
            command = in.nextLine();

        }
        return true;
    }

    public void startGame() {
        Scanner in = new Scanner(System.in);

        if (readyToStartTimer()) {
            int i = 0;
            long beginTime = System.currentTimeMillis();
            boolean timeOver = false;
            long realGameTime = 0;

            while (!timeOver) {
                long currTime = System.currentTimeMillis() - beginTime;
                if (currTime >= gameTime * 1000L) {
                    timeOver = true;
                    realGameTime = currTime;
                } else {
                    ui.printLine(wordLines.get(i));
                    String userInput = in.nextLine();
                    inputLines.add(userInput);
                    i++;
                }
            }
            displayTime = (int) realGameTime / 1000;
            System.out.println("Game Finished. Total time taken: " + displayTime + "seconds. ");
        }
    }
}
