package happybit;

import happybit.goal.GoalList;
import happybit.state.State;
import happybit.storage.Storage;
import happybit.ui.PrintManager;

import java.util.concurrent.TimeUnit;

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
     * Executes the main body of HappyBit.
     */
    private void run() {
        state.handleState();
        printManager.printExit();
        wait3Seconds();
    }

    private void wait3Seconds() {
        try {
            TimeUnit.SECONDS.sleep(3);
        } catch (InterruptedException e) {
            // Do nothing
        }
    }

}
