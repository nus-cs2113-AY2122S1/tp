package seedu.typists.game;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;


public class SummaryManager {

    public static HashMap<String, Object> generateSummary(
            ArrayList<String[]> expectedInput, ArrayList<String[]> actualInput, double timeElapsed, String gameMode) {

        assert timeElapsed >= 0;
        assert expectedInput != null;
        assert actualInput != null;
        assert ((gameMode.equals("Word-limited")) || (gameMode.equals("Time-limited")));

        HashMap<String, Object> summary = new HashMap<>();
        summary.put("timeElapsed", timeElapsed);
        summary.put("gameMode", gameMode);
        summary.put("errorWordCount", calculateErrorWordCount(actualInput, expectedInput));
        summary.put("correctWordCount", calculateCorrectWordCount(actualInput, expectedInput));
        summary.put("totalWordCount", calculateTotalWordCount(expectedInput));
        summary.put("errorWordPercentage", calculateErrorWordsPercentage(actualInput, expectedInput));
        summary.put("correctWordPercentage", calculateCorrectWordsPercentage(actualInput, expectedInput));
        summary.put("wordsPerMinute", calculateWPM(timeElapsed, actualInput, expectedInput));
        summary.put("errorWords", getErrorWords(actualInput, expectedInput));
        return summary;
    }

    private static ArrayList<String> getErrorWords(
            ArrayList<String[]> actualInput, ArrayList<String[]> expectedInput) {

        ArrayList<String> errorWords = new ArrayList<>();
        ArrayList<String> mergedExpectedInput = mergeArrays(expectedInput);

        if (actualInput.isEmpty()) {
            return mergedExpectedInput;
        }

        ArrayList<String> mergedActualInput = mergeArrays(actualInput);

        int count = 0;
        for (String expectedWord : mergedExpectedInput) {

            if (mergedActualInput.isEmpty()) {
                break;
            }

            int result = mergedActualInput.indexOf(expectedWord);

            if (result == -1) {
                String errorWord = expectedWord;
                errorWords.add(errorWord);
            } else {
                mergedActualInput.remove(result);
            }
            count++;
        }

        if (count != calculateTotalWordCount(expectedInput)) {
            for (int i = count; i < calculateTotalWordCount(expectedInput); i++) {
                errorWords.add(mergedExpectedInput.get(i));
            }
        }

        return errorWords;
    }

    private static int calculateCorrectWordCount(ArrayList<String[]> actualInput, ArrayList<String[]> expectedInput) {
        int correctWordCount = 0;

        ArrayList<String> mergedExpectedInput = mergeArrays(expectedInput);
        if (actualInput.isEmpty()) {
            return correctWordCount;
        }
        ArrayList<String> mergedActualInput = mergeArrays(actualInput);

        for (String userWord : mergedActualInput) {

            if (mergedExpectedInput.isEmpty()) {
                break;
            }

            int result = mergedExpectedInput.indexOf(userWord);

            if (result == -1) {
                continue;
            } else {
                mergedExpectedInput.remove(result);
                correctWordCount++;
            }

        }

        return correctWordCount;
    }

    private static Double calculateCorrectWordsPercentage(
            ArrayList<String[]> actualInput, ArrayList<String[]> expectedInput) {
        int correctWordCount = calculateCorrectWordCount(actualInput, expectedInput);
        int totalWordCount = calculateTotalWordCount(expectedInput);
        double correctWordsPercentage = ((double) correctWordCount / (double) totalWordCount) * 100;
        return correctWordsPercentage;
    }

    private static Double calculateWPM(
            Double timeElapsed, ArrayList<String[]> actualInput, ArrayList<String[]> expectedInput) {
        int correctWordCount = calculateCorrectWordCount(actualInput, expectedInput);
        double wordPerMinute = ((double) correctWordCount / timeElapsed) * 60;
        return wordPerMinute;
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

    private static Double calculateErrorWordsPercentage(
            ArrayList<String[]> actualInput, ArrayList<String[]> expectedInput) {
        int errorWordCount = calculateErrorWordCount(actualInput, expectedInput);
        int totalWordCount = calculateTotalWordCount(expectedInput);
        double errorWordsPercentage = ((double) errorWordCount / (double) totalWordCount) * 100;
        return errorWordsPercentage;
    }

    private static int calculateTotalWordCount(ArrayList<String[]> expectedInput) {
        ArrayList<String> mergedExpectedInput = mergeArrays(expectedInput);
        int totalWordCount = mergedExpectedInput.size();
        return totalWordCount;
    }

    private static ArrayList<String> mergeArrays(ArrayList<String[]> arrayToMerge) {
        ArrayList<String> mergedArr = new ArrayList<>();

        for (String[] array : arrayToMerge) {
            ArrayList<String> tempArr = new ArrayList<>(Arrays.asList(array));
            mergedArr.addAll(tempArr);
        }

        return mergedArr;
    }

}
