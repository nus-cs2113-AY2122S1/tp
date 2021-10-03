package seedu.duke.ui;

import seedu.duke.exception.GetJackDException;

/**
 *
 */
public class Ui {
    public static void printLineSeparator() {
        String line = "________________________________________________________";
        System.out.println(line);
    }

    public static void printWelcomeMessage() {

        String logo = "\n" +
                "   ______             _          _____                   __        _   ______    \n" +
                " .' ___  |           / |_       |_   _|                 [  |  _   | | |_   _ `.  \n" +
                "/ .'   \\_|   .---.  `| |-'        | |    ,--.    .---.   | | / ]  \\_|   | | `. \\ \n" +
                "| |   ____  / /__\\\\  | |      _   | |   `'_\\ :  / /'`\\]  | '' <         | |  | | \n" +
                "\\ `.___]  | | \\__.,  | |,    | |__' |   // | |, | \\__.   | |`\\ \\       _| |_.' / \n" +
                " `._____.'   '.__.'  \\__/    `.____.'   \\'-;__/ '.___.' [__|  \\_]     |______.'  \n" +
                "                                                                                 \n";
        System.out.println("Welcome to\n" + logo);

        printLineSeparator();
        System.out.println("What is your name?");
        printLineSeparator();
    }

    public static void printByeMessage() {
        printLineSeparator();
        System.out.println("Bye. Hope you get your desired body soon and have a great day!");
        printLineSeparator();

    }

    public static void printErrorMessage(GetJackDException e) {
        printLineSeparator();
        System.out.println(e.getMessage());
        printLineSeparator();
    }
}
