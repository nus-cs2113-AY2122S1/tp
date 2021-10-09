package seedu.duke;

import java.util.Scanner;

public class Ui {
    public static String readInput() {
        Scanner in = new Scanner(System.in);
        return in.nextLine();
    }

    public static void linebreak() {
        System.out.println("______________________________________________________________________");
    }

    public static void promptForDescription() {
        System.out.println("Please add an optional description for your item and press enter. ");
        linebreak();
    }
}
