package seedu.duke.parser;

/**
 * To make sense of user commands by extracting keywords, descriptions and time/date.
 */
public class Parser {
    public static String getFirstWordFromCommand(String input) {
        return input.toLowerCase().split(" ")[0];
    }
}
