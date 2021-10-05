package seedu.duke;

import seedu.duke.command.Command;
import seedu.duke.exception.GetJackDException;
import seedu.duke.lists.WorkoutList;
import seedu.duke.parser.Parser;
import seedu.duke.parser.TaskManager;
import seedu.duke.ui.Ui;

import java.util.Scanner;

public class Duke {
    private final WorkoutList workouts;
    private final Ui ui;

    public Duke() {
        workouts = new WorkoutList();
        ui = new Ui();
    }

    /**
     * Main entry-point for the java.duke.Duke application.
     */
    public static void main(String[] args) {
        Ui.printWelcomeMessage();
        new Duke().run();
        Ui.printByeMessage();
    }

    /**
     * Runs the program until user enters the exit command (bye).
     */
    private void run() {
        Scanner input = new Scanner(System.in);

        while (!Command.isExit()) {
            String userInput = input.nextLine();
            String command = Parser.getFirstWordFromCommand(userInput);

            try {
                TaskManager.parseAndExecuteUserCommand(command, userInput);
            } catch (GetJackDException e) {
                Ui.printErrorMessage(e);
            }
        }
    }
}

