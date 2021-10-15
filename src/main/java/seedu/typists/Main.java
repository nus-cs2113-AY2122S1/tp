package seedu.typists;


import seedu.typists.game.DataProcessor;
import seedu.typists.game.TimeModeGame;
import seedu.typists.parser.Parser;
import seedu.typists.ui.TextUi;

import static seedu.typists.common.Messages.SAMPLE_TEXT;

//solution below adapted from https://github.com/se-edu/addressbook-level2/
public class Main {
    /** Version info of the program. */
    public static final String VERSION = "Typist - Version 1.0";
    public static final int LINE_LENGTH = 10;
    /** game time in seconds */
    public static final int GAME_TIME = 15;
    TextUi uiBot;
    Parser parseBot;

    public Main() {
        this.uiBot = new TextUi();
        this.parseBot = new Parser();
    }

    public void runCommandLoopUntilExitCommand() {

    }

    public void exit() {

    }

    public void start() {
        uiBot.showWelcomeMessage(VERSION);
        TimeModeGame g = new TimeModeGame(SAMPLE_TEXT, GAME_TIME, LINE_LENGTH);
        DataProcessor p =  new DataProcessor(g);
        uiBot.showSummary(p.getErrorWordCount(), p.getWPM(), p.getTotalWordTyped(), p.totalTime);
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
