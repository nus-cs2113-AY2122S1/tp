package seedu.duke;

import seedu.duke.ui.TextUI;

import java.util.Scanner;

public class Duke {
    private static TextUI ui;

    /**
     * Main entry-point for the java.duke.Duke application.
     */

    public static void main(String[] args) {
        ui = new TextUI();
        String userInput = ui.read();
        ui.print(userInput);
    }
}
