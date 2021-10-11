package seedu.typists;

import seedu.typists.parser.Parser;
import seedu.typists.ui.TextUi;


//solution below adapted from https://github.com/se-edu/addressbook-level2/
public class Main {
    /** Version info of the program. */
    public static final String VERSION = "Typist - Version 1.0";
    TextUi UiBot;
    Parser ParseBot;

    public void start() {
        this.UiBot = new TextUi();
        this.ParseBot = new Parser();
        UiBot.showWelcomeMessage(VERSION);
    }

    /** Runs the program until termination.  */
    public void run() {
        start();
//        runCommandLoopUntilExitCommand();
//        exit();
    }

    /**
     * Main entry-point for the java.duke.Duke application.
     */
    public static void main(String[] args) {
        new Main().run();
    }
}
