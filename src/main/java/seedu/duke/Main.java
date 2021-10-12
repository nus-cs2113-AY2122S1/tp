package seedu.duke;

import seedu.duke.commands.ByeCommand;
import seedu.duke.commands.Command;
import seedu.duke.commands.CommandResult;
import seedu.duke.exercise.ExerciseList;
import seedu.duke.food.FoodList;
import seedu.duke.parser.Parser;
import seedu.duke.profile.Profile;
import seedu.duke.storage.Decoder;
import seedu.duke.storage.Storage;
import seedu.duke.storage.exceptions.UnableToReadFileException;
import seedu.duke.ui.Ui;


/**
 * Main class of Fitbot.
 * Initialises the application and starts interaction with user.
 */
public class Main {

    private ExerciseList exerciseItems;
    private FoodList foodItems;
    private Profile profile;
    private Ui ui;
    private Storage storage;


    /**
     * Entry point of the application.
     */
    public static void main(String[] args) {
        new Main().run(args);
    }

    /**
     * Runs the application until command is given to exit it.
     **/
    private void run(String[] args) {
        start();
        enterTaskModeUntilByeCommand();
        exit();
    }

    /**
     * Initialises the application by creating the required objects and (to be implemented) loading data from the
     * storage file, then showing the welcome message.
     */
    private void start() {
        this.storage = new Storage();
        this.ui = new Ui();
        try {
            this.profile = storage.loadProfileFile();
            this.foodItems = storage.loadFoodListFile();
            this.exerciseItems = storage.loadExerciseListFile();
        } catch (UnableToReadFileException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    /**
     * Reads the user input and executes appropriate command.
     * Runs indefinitely until user inputs the Bye command.
     */
    private void enterTaskModeUntilByeCommand() {
        Command command;
        do {
            String userInput = ui.getUserInput();
            command = new Parser().parseCommand(userInput);
            CommandResult result = executeCommand(command);
            ui.formatMessageFramedWithDivider(result.toString());
        } while (!ByeCommand.isBye(command));
    }

    /**
     * Executes the given Command and (to be implemented) calls for storage operation if required.
     *
     * @param command Command to be executed
     * @return CommandResult representing result of execution of the command
     */
    private CommandResult executeCommand(Command command) {
        command.setData(this.profile, this.exerciseItems, this.foodItems);
        CommandResult result = command.execute();
        return result;
    }

    /**
     * Exits the application.
     */
    private void exit() {
        System.exit(0);
    }


}
