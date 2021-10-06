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
}
