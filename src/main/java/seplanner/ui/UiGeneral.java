package seplanner.ui;

import seplanner.constants.Constants;

import static java.lang.System.out;

// @@author titustortoiseturtle1999
public class UiGeneral extends Ui {
    public static void printWelcome() {
        printGlobe();
        printLogo();
    }

    public static void printExit() {
        printBye();
        printPlane();
    }

    public static void promptInput() {
        out.print("Enter a command: ");
    }

    public static void printLineSeparator() {
        out.println(Constants.LINE_SEPARATOR);
    }
}
