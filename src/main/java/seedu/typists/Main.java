package seedu.typists;


import seedu.typists.game.TimeModeGame;
import seedu.typists.parser.Parser;
import seedu.typists.ui.TextUi;

import static seedu.typists.common.Messages.SAMPLE_TEXT;

//solution below adapted from https://github.com/se-edu/addressbook-level2/
public class Main {
    /** Version info of the program. */
    public static final String VERSION = "Typist - Version 1.0";
    public static final int LINE_LENGTH = 10;
    TextUi uiBot;
    Parser parseBot;
    TimeModeGame gameBot;

    public Main() {
        this.uiBot = new TextUi();
        this.parseBot = new Parser();
        this.gameBot = new TimeModeGame();
    }

    public void runCommandLoopUntilExitCommand() {

    }

    public void exit() {

    }

    public void start() {
        uiBot.showWelcomeMessage(VERSION);
        gameBot.startGame(SAMPLE_TEXT,10, LINE_LENGTH);
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
        new Main().run();
    }
}
