package seedu.typists.game;

import org.w3c.dom.Text;
import seedu.typists.ui.TextUi;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.Timer;
import java.util.TimerTask;

import static seedu.typists.common.Messages.SAMPLE_TEXT;

public class TimeModeGame extends Game {
    private ArrayList<String> displayLines;
    private ArrayList<String> inputLines;
    private final TextUi uiBot;

    public TimeModeGame() {
        this.uiBot = new TextUi();
        boolean is_gameEnd = false;
        displayLines = new ArrayList <>();
        inputLines = new ArrayList <>();
    }

    /** Currently still a dummy */
    public String getTargetWordSet() {
        return SAMPLE_TEXT;
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

    public void startGame(String targetWordSet, int timeLimitSeconds, int wordsPerLine) {
        Scanner in = new Scanner(System.in);
        //refacter out this line later, pass in the arraylist to statGame
        displayLines = uiBot.getDisplayWordLine(targetWordSet, wordsPerLine);

        if (readyToStartTimer()) {
            int i = 0;
            long beginTime = System.currentTimeMillis();
            boolean timeOver = false;
            long currTime = 0;
            inputLines = new ArrayList <>();
            while (!timeOver) {
                currTime = System.currentTimeMillis() - beginTime;
                if (currTime >= timeLimitSeconds * 1000L) {
                    timeOver = true;
                } else {
                    System.out.println(displayLines.get(i));
                    String userInput = in.nextLine();
                    inputLines.add(userInput);
                    i++;
                }
            }
            int displayTime = (int) currTime / 1000;
            System.out.println("Game Finished. Total time taken: " + displayTime + "seconds. ");

            compareData(displayLines, inputLines);
        }
    }


    public void compareData(ArrayList<String> checker, ArrayList<String> answer){
        int totalErrorCount = 0;
        for (int i = 0; i < answer.size(); i++) {
            totalErrorCount += compareOneLine(checker.get(i), answer.get(i));
        }
        System.out.println("Total number of wrong keystrokes: " + totalErrorCount);
    }

    public int compareOneLine(String checker, String answer) {
        int errorCount = 0;
        for (int i = 0; i < checker.length(); i++) {
            try {
                if (checker.charAt(i) != answer.charAt(i)) {
                    errorCount++;
                }
            } catch (StringIndexOutOfBoundsException e) {
                errorCount++;
            }
        }
        return errorCount;
    }

}
