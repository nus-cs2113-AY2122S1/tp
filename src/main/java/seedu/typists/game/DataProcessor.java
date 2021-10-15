package seedu.typists.game;

import java.util.ArrayList;

import static seedu.typists.Main.LINE_LENGTH;
import static seedu.typists.common.Utils.getWordLineFromStringArray;

public class DataProcessor {
    private final ArrayList<String[]> checkerWordLines;
    private final ArrayList<String[]> userWordLines;
    public final int totalTime;

    public DataProcessor(TimeModeGame tg) {
            //ArrayList<String[]> checkerWordLines, ArrayList<String> userText, int lineLength, int totalTime){
        this.checkerWordLines = tg.wordLines;
        this.userWordLines = getWordLineFromStringArray(tg.inputLines);
        this.totalTime = tg.gameTime;
    }

    public int getErrorWordCount() {
        int errorWordCount = 0;
        int i = 0;
        for (String[] sa : userWordLines) {
            String[] checker = checkerWordLines.get(i);
            int ii = 0;
            for (String s : sa) {
                if (!s.equals(checker[ii])) {
                    errorWordCount++;
                }
                ii++;
            }
            i++;
        }
        return errorWordCount;
    }

    public int getTotalWordTyped() {
        return userWordLines.size() * LINE_LENGTH;
    }

    public double getWPM() {
        return getTotalWordTyped()/ ((double)totalTime/60);
    }


    public void getWrongKeyStrokes(ArrayList<String> checkerText, ArrayList<String> userText){
        int totalErrorCount = 0;
        for (int i = 0; i < userText.size(); i++) {
            totalErrorCount += getLineWrongKeyStrokes(checkerText.get(i), userText.get(i));
        }
        System.out.println("Total number of wrong keystrokes: " + totalErrorCount);
    }

    public int getLineWrongKeyStrokes(String checker, String answer) {
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
