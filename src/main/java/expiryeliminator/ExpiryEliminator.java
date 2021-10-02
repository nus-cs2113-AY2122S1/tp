package expiryeliminator;

import java.util.Scanner;

import expiryeliminator.parser.Parser;
import expiryeliminator.ui.Ui;

public class ExpiryEliminator {
    private static Parser parser;
    private static Ui ui;

    /**
     * Initialises the ExpiryEliminator application.
     */
    public ExpiryEliminator() {
        ui = new Ui();
    }

    /**
     * Runs the program.
     */
    public void run() {
        ui.showGreeting();
        final String userInput = ui.getUserInput();
        System.out.println("Hello " + userInput);
    }

    /**
     * Main entry-point for the ExpiryEliminator application.
     */
    public static void main(String[] args) {
        new ExpiryEliminator().run();
    }
}
