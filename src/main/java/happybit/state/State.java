package happybit.state;

import happybit.exception.HaBitUiException;
import happybit.goal.GoalList;
import happybit.storage.Storage;
import happybit.ui.PrintManager;
import happybit.ui.UiMain;
import happybit.ui.UiStartup;

public class State {

    protected GoalList goalList;
    protected PrintManager printManager;
    protected Storage storage;

    public State(GoalList goalList, PrintManager printManager, Storage storage) {
        this.goalList = goalList;
        this.printManager = printManager;
        this.storage = storage;
    }

    /**
     * Alternates between the 2 states.
     */
    public void handleState() {
        if (mainState()) {
            startupState();
            handleState();
        }
    }

    /**
     * Manages the start-up interface.
     */
    public void startupState() {
        try {
            UiStartup uiStartup = new UiStartup();
            uiStartup.run();
        } catch (HaBitUiException e) {
            printManager.showError(e.getMessage());
        }
    }

    /*
     * NOTE : ==================================================================
     * The following are private methods that are used to implement SLAP for the
     * above public methods. These methods are positioned at the bottom to better
     * visualise the actual methods that can be called from outside this class.
     * =========================================================================
     */

    /**
     * Manages the main menu interface.
     *
     * @return Boolean of whether the isReturn flag is set.
     */
    private boolean mainState() {
        boolean isReturn = false;
        UiMain uiMain = new UiMain(goalList, printManager, storage);
        isReturn = uiMain.run();
        return isReturn;
    }

}
