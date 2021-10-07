package seedu.duke.ui;

import java.io.PrintStream;
import java.util.Scanner;

public class TextUI {
    private final Scanner in;
    private final PrintStream out;

    public TextUI() {
        in = new Scanner(System.in);
        out = System.out;
    }

    public String read() {
        return in.nextLine();
    }

    public void print(String message) {
        out.println(message);
    }
}
