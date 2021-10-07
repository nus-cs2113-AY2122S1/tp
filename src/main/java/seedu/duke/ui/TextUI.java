package seedu.duke.ui;

import java.io.PrintStream;
import java.util.Scanner;
import static seedu.duke.common.Messages.LOGO;
import static seedu.duke.common.Messages.WELCOME_MESSAGE;

public class TextUI {
    private final Scanner in;
    private final PrintStream out;
    private static String prefix = "> ";

    public TextUI() {
        in = new Scanner(System.in);
        out = System.out;
        print(LOGO);
        print(WELCOME_MESSAGE);
    }

    public String read() {
        out.print(prefix);
        String input = in.nextLine();
        return input;
    }

    public void print(String message) {
        out.println(message);
    }

}
