package seedu.typists.common;

import static java.lang.Math.abs;
import static java.lang.Math.min;

/**
 * Error rate calculator.
 */
public class Error {
    public int wordLengthDifference(String word, String typed) {
        return Math.abs(word.length() - typed.length());
    }

    public int wordCharacterDifference(String word, String typed) {
        int charDiff = 0;
        for (int i = 0; i < min(word.length(), typed.length()); i++) {
            charDiff += (word.charAt(i) != typed.charAt(i) ? 1 : 0);
        }
        return charDiff + wordLengthDifference(word, typed);
    }

    public double wordErrorRate(String word, String typed) {
        return (double) wordCharacterDifference(word, typed) / word.length();
    }

    public int sentenceLengthDifference(String[] content, String[] typed) {
        return abs(content.length - typed.length);
    }

    public double sentenceErrorRate(String[] content, String[] typed) {
        double temp = 0.0;
        for (int i = 0; i < min(content.length, typed.length); i++) {
            temp += wordErrorRate(content[i], typed[i]);
        }
        return temp / (double) min(content.length, typed.length);
    }

    public int wrongWordCount(String[] content, String[] typed) {
        int temp = 0;
        for (int i = 0; i < min(content.length, typed.length); i++) {
            temp += (content[0].equals(content[1]) ? 1 : 0);
        }
        return temp;
    }
}
