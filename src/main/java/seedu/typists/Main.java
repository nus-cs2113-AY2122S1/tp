package seedu.typists;

import seedu.typists.parser.Parser;
import seedu.typists.ui.TextUi;


//solution below adapted from https://github.com/se-edu/addressbook-level2/
public class Main {
    /** Version info of the program. */
    public static final String VERSION = "Typist - Version 1.0";
    TextUi uiBot;
    Parser parseBot;

    public void start() {
        this.uiBot = new TextUi();
        this.parseBot = new Parser();
        uiBot.showWelcomeMessage(VERSION);
    }

    public void runCommandLoopUntilExitCommand() {

    }

    public void exit() {

    }

    /** Runs the program until termination.  */
    public void run() {
        start();
        runCommandLoopUntilExitCommand();
        exit();
    }

    /**
     * Main entry-point for the java.duke.Duke application.
     */
    public static void main(String[] args) {
        assert true;
        new Main().run();
    }


}
