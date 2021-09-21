package seedu.ui;

import seedu.parser.NusModsParser;

import java.util.Scanner;

public class TextUi {

    public static Scanner in = new Scanner(System.in);

    public static final String LINE = "____________________________________________________________________________\n";

    public static String getCommand() {
        return in.nextLine();
    }

    public static void printExitMessage() {
        System.out.print(LINE + "> Bye friend!\n> See you again! :)\n" + LINE);
    }

    public static void printInvalidCommandMessage() {
        System.out.print(LINE + "> Sorry friend, I don't know what that means. :/\n" + LINE);
    }

    public static void printSearchResults(String searchTerm) {
        NusModsParser.parseSearch(searchTerm);
    }

    public static void printErrorMessage() {
        System.out.println("Error occurred.");
    }
}
