package seplanner.ui;

import static java.lang.System.out;

public class UiInvalid extends Ui {
    public static void printFindModNull() {
        out.println("There is an error in your inputs: This module does not exist.");
    }

    public static void printFindUniNull() {
        out.println("There is an error in your inputs: This university does not exist.");
    }

    public static void printParseException(String errorMessage, String format, boolean isPrintFormat) {
        out.println("There is an error in your inputs: " + errorMessage + "\n");
        if (isPrintFormat) {
            printFormat(format);
        }
    }

    public static void printFormat(String format) {
        out.println("Follow the following format: ");
        out.println(format);
    }
}
