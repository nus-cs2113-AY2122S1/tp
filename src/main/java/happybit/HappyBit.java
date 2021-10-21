package happybit;

import happybit.exception.HaBitStorageException;
import happybit.goal.GoalList;
import happybit.state.State;
import happybit.storage.Storage;
import happybit.ui.PrintManager;

public class HappyBit {

    private Storage storage;
    private GoalList goalList;
    private PrintManager printManager;
    private State state;

    /**
     * Duke class constructor that also loads in tasks data from an external save file.
     *
     * @param filePath File path of the external save file
     */
    public HappyBit(String filePath, String fileDir) {
        printManager = new PrintManager();
        storage = new Storage(filePath, fileDir);
        goalList = new GoalList();
        state = new State(goalList, printManager, storage);
        loadData();
    }

    /**
     * Main method of HappyBit.
     * Creates a HappyBit class and runs it.
     *
     * @param args Not applicable.
     */
    public static void main(String[] args) {
        new HappyBit("data/habits.txt", "data").run();
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
        } catch (HaBitStorageException e) {
            printManager.showError(e.getMessage());
        }
    }

    /**
     * Exports data to an external storage.
     */
    private void exportData() {
        try {
            storage.export(goalList.getGoalList());
        } catch (HaBitStorageException e) {
            printManager.showError(e.getMessage());
        }
    }

    /**
     * Executes the main body of HappyBit.
     */
    private void run() {
        state.startupState();
        state.handleState();
        exportData();
        printManager.showGoodbye();
    }

}
