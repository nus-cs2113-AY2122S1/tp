package seedu.duke.parser;

import java.util.Scanner;

/**
 * To make sense of user commands by extracting keywords, descriptions and time/date
 */
public class Parser {
    public static String getUserInput(Scanner in) {
        String input;
        input = in.nextLine();

        return input;
    }

    /**
     * @param input is the command given by the user
     * @return the lowercase form of the first word present in the command
     */
    public static String getFirstWordFromCommand(String input) {
        return input.toLowerCase().split(" ")[0];
    }
}
