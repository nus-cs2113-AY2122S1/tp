package seedu.duke;

import seedu.duke.command.Command;
import seedu.duke.exception.GetJackDException;
import seedu.duke.lists.ExerciseList;
import seedu.duke.lists.RoutineList;
import seedu.duke.parser.Parser;
import seedu.duke.parser.TaskManager;
import seedu.duke.ui.Ui;

import java.util.Scanner;

public class Duke {
    private final ExerciseList exerciseList;
    private final RoutineList routineList;
    private final Ui ui;

    public Duke() {
        exerciseList = new ExerciseList();
        routineList = new RoutineList();
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
        Scanner in = new Scanner(System.in);

        while (!Command.isExit()) {
            String input = Parser.getUserInput(in);
            String command = Parser.getFirstWordFromCommand(input);

            try {
                TaskManager.parseUserCommand(command, input);
            } catch (GetJackDException e) {
                Ui.printErrorMessage(e);
            }
        }
    }
}

