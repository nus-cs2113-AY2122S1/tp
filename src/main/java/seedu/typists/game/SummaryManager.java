package seedu.typists.game;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class SummaryManager {

    public static HashMap<String, Object> generateSummary(
            ArrayList<String[]> expectedInput, ArrayList<String[]> actualInput, double timeElapsed, String gameMode) {

        HashMap<String, Object> summary = new HashMap<>();
        summary.put("timeElapsed", timeElapsed);
        summary.put("gameMode", gameMode);
        summary.put("errorWordCount", calculateErrorWordCount(actualInput, expectedInput));
        summary.put("correctWordCount", calculateCorrectWordCount(actualInput, expectedInput));
        summary.put("totalWordCount", calculateTotalWordCount(expectedInput));
        summary.put("errorWordPercentage", calculateErrorWordsPercentage(actualInput, expectedInput));
        summary.put("correctWordPercentage", calculateCorrectWordsPercentage(actualInput, expectedInput));
        summary.put("wordPerMinute", calculateWPM(timeElapsed, actualInput, expectedInput));
        summary.put("errorWords", getErrorWords(actualInput, expectedInput));
        return summary;
    }

    private static List<String> getErrorWords(ArrayList<String[]> actualInput, ArrayList<String[]> expectedInput) {
        List<String> errorWords = new ArrayList<String>();

        for (String[] userLine : actualInput)
            for (String userWord : userLine) {

                if (expectedInput.isEmpty()) {
                    break;
                }

                int result = expectedInput.indexOf(userWord);

                if (result == -1) {
                    String errorWord = userWord;
                    errorWords.add(userWord);
                } else {
                    expectedInput.remove(result);
                }

            }

        return errorWords;
    }

    private static Double calculateCorrectWordsPercentage(
            ArrayList<String[]> actualInput, ArrayList<String[]> expectedInput) {
        int correctWordCount = calculateCorrectWordCount(actualInput, expectedInput);
        int totalWordCount = calculateTotalWordCount(expectedInput);
        double correctWordsPercentage = ((double) correctWordCount / (double) totalWordCount) * 100;
        return correctWordsPercentage;
    }

    private static Double calculateWPM(Double timeElapsed, ArrayList<String[]> actualInput, ArrayList<String[]> expectedInput) {
        int correctWordCount = calculateCorrectWordCount(actualInput, expectedInput);
        double wordPerMinute = (double) correctWordCount / timeElapsed;
        return wordPerMinute;
    }

    private static int calculateCorrectWordCount(ArrayList<String[]> actualInput, ArrayList<String[]> expectedInput) {
        int correctWordCount = 0;

        for (String[] userLine : actualInput) {
            for (String userWord : userLine) {

                if (expectedInput.isEmpty()) {
                    break;
                }

                int result = expectedInput.indexOf(userWord);

                if (result == -1) {
                    continue;
                } else {
                    expectedInput.remove(result);
                    correctWordCount++;
                }

            }
        }

        return correctWordCount;

    }

    private static int calculateErrorWordCount(ArrayList<String[]> actualInput, ArrayList<String[]> expectedInput) {
        int correctWordCount = calculateCorrectWordCount(actualInput, expectedInput);
        assert correctWordCount >= 0;
        int totalWordCount = calculateTotalWordCount(expectedInput);
        assert correctWordCount >= 0;
        assert totalWordCount >= correctWordCount;
        int errorWordCount = totalWordCount - correctWordCount;
        return errorWordCount;
    }

    private static Double calculateErrorWordsPercentage(ArrayList<String[]> actualInput, ArrayList<String[]> expectedInput) {
        int errorWordCount = calculateErrorWordCount(actualInput, expectedInput);
        int totalWordCount = calculateTotalWordCount(expectedInput);
        double errorWordsPercentage = ((double) errorWordCount / (double) totalWordCount) * 100;
        return errorWordsPercentage;
    }

    private static int calculateTotalWordCount(ArrayList<String[]> expectedInput) {
        int totalWordCount = expectedInput.size();
        return totalWordCount;
    }


}
