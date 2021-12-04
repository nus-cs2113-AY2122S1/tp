package seedu.duke;

import seedu.duke.data.DataManager;
import seedu.duke.logic.LogicManager;
import seedu.duke.logic.commands.CommandResult;
import seedu.duke.state.StartState;
import seedu.duke.storage.Storage;
import seedu.duke.storage.StorageManager;
import seedu.duke.storage.exceptions.UnableToWriteFileException;
import seedu.duke.ui.Ui;


/**
 * Main class of Fitbot.
 * Initialises the application and starts interaction with user.
 */
public class Main {
    private DataManager dataManager;
    private Ui ui;
    private StorageManager storageManager;
    private LogicManager logicManager;
    private StartState startState;



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
        checkAndCreateProfile();
        enterTaskModeUntilByeCommand();
        exit();
    }

    //@@author tttyyzzz
    /**
     * Checks the attributes of profile in dataManager.
     */
    private void checkAndCreateProfile() {
        dataManager.setProfile(startState.checkAndCreateProfile());
    }
    //@@author

    /**
     * Initialises the application by creating the required objects and loading data from the
     * storage file, then showing the welcome message.
     */
    private void start() {
        this.storageManager = new StorageManager();
        this.ui = new Ui();
        this.dataManager = storageManager.loadAll();
        this.logicManager = new LogicManager(storageManager, dataManager);
        this.startState = new StartState(dataManager.getProfile(), storageManager, ui);
        ui.printStartMessage(
                dataManager.getProfile().checkProfileComplete(),
                dataManager.getProfile().checkProfilePresent());
    }

    /**
     * Reads the user input and executes appropriate command.
     * Runs indefinitely until user inputs the Bye command.
     */
    private void enterTaskModeUntilByeCommand() {
        CommandResult result;
        do {
            String userInput = ui.getUserInput();
            result = logicManager.execute(userInput);
            ui.formatMessageFramedWithDivider(result.toString());
        } while (!result.isBye());
    }


    /**
     * Exits the application.
     */
    private void exit() {
        System.exit(0);
    }

}
