package seedu.duke;

import seedu.duke.exceptions.DukeException;
import seedu.duke.exceptions.InsufficientParametersException;
import seedu.duke.exceptions.UnknownCommandException;
import seedu.duke.ingredients.Ingredient;
import seedu.duke.ingredients.IngredientList;
import seedu.duke.ui.UI;

public class Duke {

    private static UI ui;


    /**
     * Starts up the system by creating the UI and the IngredientList.
     */
    public static void initialize() {
        ui = new UI();
    }


    /**
     * Prints the exit message, then closes the program.
     */
    public static void exit() {
        ui.printGoodbye();
        System.exit(0);
    }


    /**
     * Runs the command parser and return the message.
     * @param command user's input command
     * @return result message
     */
    public static String runCommand(String command) {
        String msg;

        try {
            msg = Parser.parse(command);
            return msg;
        } catch (DukeException e) {
            return e.getMessage();
        }
    }

    public static void run() {
        boolean isExit = false;
        String command;
        String resultMsg;

        while (!isExit) {
            command = ui.getUserCommand();
            resultMsg = runCommand(command);

            isExit = Parser.isExit(command);

            if (!isExit) {
                ui.printCommandOutput(resultMsg);
            }
        }
    }

    public static void main(String[] args) {
        initialize();
        run();
        exit();
    }
}
