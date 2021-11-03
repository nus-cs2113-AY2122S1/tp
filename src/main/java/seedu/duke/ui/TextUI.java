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
    private final Scanner IN;
    private final PrintStream OUT;
    private static final String PREFIX = "> ";

    /**
     * Constructor for TextUI class.
     */
    public TextUI() {
        IN = new Scanner(System.in);
        OUT = System.out;
        print(LOGO);
        print(WELCOME_MESSAGE);
    }

    /**
     * Reads in user input to the application.
     * @return User input
     */
    public String read() {
        OUT.print(PREFIX);
        String input = IN.nextLine();
        return input;
    }

    /**
     * Prints a message to the user.
     * @param message Message to be printed
     */
    public void print(String message) {
        OUT.println(message);
    }

    /**
     * Prints string representation of an item to user.
     * @param item Item to be printed
     */
    public void print(Item item) {
        OUT.println("  " + item);
    }

    /**
     * Prints a pair of message and string representation of item.
     * @param message Message to be printed
     * @param item Item to be printed
     */
    public void print(String message, Item item) {
        OUT.println(message);
        OUT.println("  " + item);
    }
}
