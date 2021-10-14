package seedu.typists.common;

import static java.lang.Math.min;

/**
 * Error rate calculator.
 */
public class Error {
    public int lengthDifference(String content, String typed) {
        return Math.abs(content.length() - typed.length());
    }

    public int characterDifference(String content, String typed) {
        int charDiff = 0;
        for (int i = 0; i < min(content.length(), typed.length()); i++) {
            charDiff += (content.charAt(i) != typed.charAt(i) ? 1 : 0);
        }
        return charDiff + lengthDifference(content, typed);
    }

    public double errorRate(String content, String typed) {
        return (double) characterDifference(content, typed) / content.length();
    }
}
