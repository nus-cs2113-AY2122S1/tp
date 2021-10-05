package seedu.duke;

import seedu.ui.TextUi;

import java.util.Scanner;

public class Duke {
    private TextUi textUi;

    public Duke() {
        this.textUi = new TextUi();
    }

    /**
     * Main entry-point for the java.duke.Duke application.
     */
    public static void main(String[] args) {
        new Duke();
    }
}
