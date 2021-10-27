package seedu.duke.ui;

import seedu.duke.data.Item;

import java.io.PrintStream;
import java.util.Scanner;
import static seedu.duke.common.Messages.LOGO;
import static seedu.duke.common.Messages.WELCOME_MESSAGE;

//@@author exetr
/**
 * Class encapsulating input and output of the application.
 */
public class TextUI {
    private final Scanner in;
    private final PrintStream out;
    private static String prefix = "> ";

    /**
     * Constructor for TextUI class.
     */
    public TextUI() {
        in = new Scanner(System.in);
        out = System.out;
        print(LOGO);
        print(WELCOME_MESSAGE);
    }

    /**
     * Reads in user input to the application.
     * @return User input
     */
    public String read() {
        out.print(prefix);
        String input = in.nextLine();
        return input;
    }

    /**
     * Prints a message to the user.
     * @param message Message to be printed
     */
    public void print(String message) {
        out.println(message);
    }

    /**
     * Prints string representation of an item to user.
     * @param item Item to be printed
     */
    public void print(Item item) {
        out.println("  " + item);
    }

    /**
     * Prints a pair of message and string representation of item.
     * @param message Message to be printed
     * @param item Item to be printed
     */
    public void print(String message, Item item) {
        out.println(message);
        out.println("  " + item);
    }
}
