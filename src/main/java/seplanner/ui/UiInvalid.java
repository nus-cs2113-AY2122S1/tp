package seplanner.ui;

import static java.lang.System.out;

public class UiInvalid extends Ui {
    public static void printFindModNull() {
        out.println("No modules found");
    }

    public static void printFindUniNull() {
        out.println("No universities found");
    }

    public static void printParseException(String errorMessage) {
        out.println("There is an error in your inputs: " + errorMessage);
    }

    public static void printFormat(String format) {
        out.println("Follow the following format: ");
        out.println(format);
    }
}
