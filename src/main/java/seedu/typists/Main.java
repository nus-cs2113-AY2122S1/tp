package seedu.typists;

<<<<<<< Updated upstream
import java.util.Scanner;

public class Main {
=======
import seedu.typists.game.Game;
import seedu.typists.game.TimeModeGame;
import seedu.typists.parser.Parser;
import seedu.typists.ui.TextUi;

import static seedu.typists.common.Messages.SAMPLE_TEXT;


//solution below adapted from https://github.com/se-edu/addressbook-level2/
public class Main {
    /** Version info of the program. */
    public static final String VERSION = "Typist - Version 1.0";

    public void start() {
        TextUi uiBot = new TextUi();
        Parser parseBot = new Parser();
        TimeModeGame gameBot = new TimeModeGame();
        uiBot.showWelcomeMessage(VERSION);
        gameBot.startGame(SAMPLE_TEXT, 5);
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

>>>>>>> Stashed changes
    /**
     * Main entry-point for the java.duke.Duke application.
     */
    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        System.out.println("What is your name?");

        Scanner in = new Scanner(System.in);
        System.out.println("Hello " + in.nextLine());
    }
}
