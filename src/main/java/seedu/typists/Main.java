package seedu.typists;

import seedu.typists.commands.NewGame;
import seedu.typists.exception.FaultyInputException;
import seedu.typists.exception.InvalidStringInputException;
import seedu.typists.parser.Parser;
import seedu.typists.storage.StorageFile;
import seedu.typists.ui.TextUi;

import java.io.IOException;
import java.util.NoSuchElementException;


//solution below adapted from https://github.com/se-edu/addressbook-level2/
public class Main {
    /** Version info of the program. */
    public static final String VERSION = "Typist - Version 1.0";
    TextUi uiBot;
    NewGame game;
    StorageFile storage;

    public void start() {
        this.uiBot = new TextUi();
        this.game = new NewGame();
        this.storage = new StorageFile();
        uiBot.showWelcomeMessage(VERSION);
    }

    public static void executeCommand(Parser c, NewGame game, StorageFile storage) throws InvalidStringInputException {
        switch (c.getCommand()) {
        case "bye":
            break;
        case "new":
            game.beginNewGame();
            break;
        default:
            break;
        }
        //storage.writeToFile(tasks);
    }

    public void runCommandLoopUntilExitCommand() {
        boolean isExit = false;
        while (!isExit) {
            try {
                String fullCommand = uiBot.readCommand();
                uiBot.showLine(); // show the divider line ("_______")
                Parser c = new Parser(fullCommand);
                c.parse();
                executeCommand(c,game,storage);
                isExit = c.getIsExit();
            } catch (StringIndexOutOfBoundsException e) {
                uiBot.showText("OOPS!!! The description after this command word cannot be empty.");
            } catch (IndexOutOfBoundsException e) {
                uiBot.showText("OOPS!!! It's out of range.");
            } catch (NumberFormatException e) {
                uiBot.showText("OOPS!!! Input after done/delete must be a number.");
            } catch (FaultyInputException e) {
                uiBot.showText(e.getMessage());
            } catch (InvalidStringInputException e) {
                e.printStackTrace();
            } catch (NoSuchElementException e) {
                e.printStackTrace();
            } finally {
                uiBot.showLine();
            }
        }
    }

    public void exit() {
        uiBot.showBye();
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
