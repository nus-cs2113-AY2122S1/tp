package seedu.situs.ui;

import java.util.Scanner;
//@@author nishantrai-nus

public class UI {

    Scanner line = new Scanner(System.in);

    public static final String DIVIDER = "____________________________________________________\n";
    private static final String WELCOME_MESSAGE = DIVIDER
            + "Welcome to SITUS!\n"
            + "What would you like to do first?\n"
            + "To see what I can do, use \"help\"\n";
    private static final String GOODBYE_MESSAGE = DIVIDER + "Okay, see you soon! Goodbye.\n" + DIVIDER;


    public UI() {
        System.out.print(WELCOME_MESSAGE);
    }

    public static void printGoodbye() {
        System.out.print(GOODBYE_MESSAGE);
    }

    public static void printCommandOutput(String commandOutput) {
        System.out.print(DIVIDER + commandOutput + "\n" + DIVIDER);
    }

    public static void printCommandOutputWithoutTopDivider(String commandOutput) {
        System.out.print(commandOutput + "\n" + DIVIDER);
    }

    public String getUserCommand() {
        return line.nextLine().trim().toLowerCase();
    }
}
