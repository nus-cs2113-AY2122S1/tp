package happybit;

import happybit.command.Command;
import happybit.exception.HBLoadException;
import happybit.exception.HappyBitException;
import happybit.goal.GoalList;
import happybit.storage.Storage;
import happybit.ui.Ui;

import java.util.Scanner;

public class HappyBit {

    private Storage storage;
    private GoalList goalList;
    private Ui ui;

    /**
     * Duke class constructor that also loads in tasks data
     * from an external save file
     *
     * @param filePath File path of the external save file
     */
    public HappyBit(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        loadData();
    }

    /**
     * Main method of HappyBit.
     * Creates a HappyBit class and runs it.
     *
     * @param args Not applicable.
     */
    public static void main(String[] args) {
        new HappyBit("happybit.txt").run();
    }

    /*
     * NOTE : ==================================================================
     * The following are private methods that are used to implement SLAP for the
     * above public methods. These methods are positioned at the bottom to better
     * visualise the actual methods that can be called from outside this class.
     * =========================================================================
     */

    /**
     * Loads in data from an external storage.
     */
    private void loadData() {
        try {
            goalList = storage.load();
        } catch (HBLoadException e) {
            ui.showError(e.getMessage());
            goalList = new GoalList();
        }
    }

    /**
     * Executes the main body of HappyBit.
     */
    private void run() {
        ui.showWelcome();
        handleUserInput();
        ui.showGoodbye();
    }

    /**
     * Takes in the user input and performs the relevant commands.
     */
    private void handleUserInput() {
        String userInput;
        boolean isExit = false;
        Scanner in = new Scanner(System.in);
        while (!isExit) {
            userInput = in.nextLine().strip().replaceAll("\\s+"," ");
            try {
                Command command = Parser.parse(userInput);
                command.runCommand(goalList, ui, storage);
                isExit = command.isExit();
            } catch (HappyBitException e) {
                ui.showError(e.getMessage());
            }
        }
    }

}
