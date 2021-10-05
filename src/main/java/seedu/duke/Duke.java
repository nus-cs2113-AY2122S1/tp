package seedu.duke;

import java.util.Scanner;

public class Duke {
    /**
     * Main entry-point for the java.duke.Duke application.
     */
    public static void main(String[] args) {
        String logo = "   _____         _______        _     \n" +
                "  / ____|       |__   __|      | |    \n" +
                " | |     ___  _ __ | | ___  ___| |__  \n" +
                " | |    / _ \\| '_ \\| |/ _ \\/ __| '_ \\ \n" +
                " | |___| (_) | | | | |  __/ (__| | | |\n" +
                "  \\_____\\___/|_| |_|_|\\___|\\___|_| |_|\n" +
                "                                      \n" +
                "                                      ";
        System.out.println("Hello from\n" + logo);
        System.out.println("What is your name?");

        Scanner in = new Scanner(System.in);
        System.out.println("Hello " + in.nextLine());
    }
}
