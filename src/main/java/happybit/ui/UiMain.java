package happybit.ui;

import happybit.command.Command;
import happybit.exception.HaBitCommandException;
import happybit.exception.HaBitParserException;
import happybit.exception.HaBitStorageException;
import happybit.goal.GoalList;
import happybit.goal.GoalType;
import happybit.parser.MainParser;
import happybit.storage.Storage;

import java.util.ArrayList;
import java.util.Scanner;

public class UiMain extends UiManager {

    private static final String SLEEP_LOGO = "              .-.,\n"
            + "      .------;,' |     ____  __    ____  ____  ____ \n"
            + "    ,;......: |  |    / ___)(  )  (  __)(  __)(  _ \\\n"
            + "   |          |.';    \\___ \\/ (_/\\ ) _)  ) _)  ) __/\n"
            + "   ;'''''''''';       (____/\\____/(____)(____)(__)  \n";

    private static final String FOOD_LOGO = "   ^    ^^    ^    \n"
            + " ------;;;;------      ____  __    __  ____ \n"
            + "|______|;;|______|    (  __)/  \\  /  \\(    \\\n"
            + "  |    |;;|    |       ) _)(  O )(  O )) D (\n"
            + "   '.__|;;|__.'       (__)  \\__/  \\__/(____/\n";

    private static final String EXERCISE_LOGO = "            _( } \n"
            + "   -=  _  <<  \\       ____  _  _  ____  ____   ___  __  ____  ____ \n"
            + "      `.\\__/`/\\\\     (  __)( \\/ )(  __)(  _ \\ / __)(  )/ ___)(  __)\n"
            + " -=     '--'\\\\  `     ) _)  )  (  ) _)  )   /( (__  )( \\___ \\ ) _) \n"
            + "      -=     \\)      (____)(_/\\_)(____)(__\\_) \\___)(__)(____/(____)\n";

    private static final String STUDY_LOGO = "       _______ \n"
            + "      /      /,       ____  ____  _  _  ____  _  _ \n"
            + "     /      //       / ___)(_  _)/ )( \\(    \\( \\/ )\n"
            + "    /______//        \\___ \\  )(  ) \\/ ( ) D ( )  / \n"
            + "   (______(/         (____/ (__) \\____/(____/(__/ \n";

    private static final String MESSAGE_RETURN = "Press enter to return to command mode...\n";
    private static final String MESSAGE_DUE_HABITS = "These are the habit(s) that you have yet to complete:";
    private static final String HABIT_DETAILS = "%1$s) %2$s" + LS;
    private static final int WAIT_TIME_IN_SECONDS = 3;

    protected GoalList goalList;
    protected PrintManager printManager;
    protected Storage storage;
    protected boolean isExit;

    public UiMain(GoalList goalList, PrintManager printManager, Storage storage) {
        this.goalList = goalList;
        this.printManager = printManager;
        this.storage = storage;
        this.isExit = false;
        loadData();
    }

    /**
     * Main function to execute main application interface.
     */
    public void run() {
        resetDisplay();
        processInput();
    }

    /**
     * Getter for whether the application should exit.
     *
     * @return True if isExit flag is set, false otherwise.
     */
    public boolean getIsExit() {
        return this.isExit;
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
            this.goalList = this.storage.load();
        } catch (HaBitStorageException e) {
            printManager.printError(e.getMessage());
        }
    }

    /**
     * Reads in user input and performs the corresponding command.
     */
    private void processInput() {
        String userInput;
        Scanner in = new Scanner(System.in);
        boolean isExit = false;
        boolean isReturn = false;
        while (!isExit && !isReturn) {
            userInput = readUserInput(in);
            try {
                Command command = MainParser.parse(userInput);
                command.runCommand(goalList, printManager, storage);
                isExit = isExitCommand(command);
                isReturn = isReturnCommand(command);
                checkFlags(isExit, isReturn);
            } catch (HaBitParserException | HaBitCommandException e) {
                printManager.printError(e.getMessage());
                waitApp(WAIT_TIME_IN_SECONDS);
                resetDisplay();
            }
        }
        this.isExit = isExit;
    }

    /**
     * Reads in the user input.
     *
     * @param in Scanner that obtains the user input.
     * @return String of the user input.
     */
    private String readUserInput(Scanner in) {
        System.out.print("Command: ");
        String userInput = in.nextLine();
        System.out.print(LS);
        return userInput;
    }

    /**
     * Checks if the exit command was called by the user.
     *
     * @param command Command called by the user.
     * @return Boolean of whether the exit command was called.
     */
    private boolean isExitCommand(Command command) {
        return command.isExit();
    }

    /**
     * Checks if the return command was called by the user.
     *
     * @param command Command called by the user.
     * @return Boolean of whether the return command was called.
     */
    private boolean isReturnCommand(Command command) {
        return command.isReturn();
    }

    /**
     * Executes waitForEnter if both flags are not set.
     *
     * @param isExit   True if exit flag is set.
     * @param isReturn True if return flag is set.
     */
    private void checkFlags(boolean isExit, boolean isReturn) {
        if (!isExit && !isReturn) {
            waitForEnter();
        }
    }

    /**
     * Wait for user to press Enter.
     */
    private void waitForEnter() {
        System.out.println(LS + MESSAGE_RETURN);
        Scanner in = new Scanner(System.in);
        String userInput = null;
        while (userInput == null) {
            userInput = in.nextLine();
        }
        resetDisplay();
    }

    /**
     * Clears the console and re-prints the title and due habits.
     */
    private void resetDisplay() {
        clearConsoleScreen();
        printTitle();
        printDueHabits();
    }

    /**
     * Prints the title depending on goalType (if a goal has been set).
     */
    private void printTitle() {
        GoalType goalType = goalList.getChosenGoalType();
        switch (goalType) {
        case SLEEP:
            System.out.print(BAR + SLEEP_LOGO + BAR);
            break;
        case FOOD:
            System.out.print(BAR + FOOD_LOGO + BAR);
            break;
        case EXERCISE:
            System.out.print(BAR + EXERCISE_LOGO + BAR);
            break;
        case STUDY:
            System.out.print(BAR + STUDY_LOGO + BAR);
            break;
        default:
            printLogo();
        }
    }

    /**
     * Prints the list of habits to be marked as done.
     */
    private void printDueHabits() {
        ArrayList<String> dueHabits = this.goalList.listDueHabits();
        if (!dueHabits.isEmpty()) {
            System.out.println(MESSAGE_DUE_HABITS);
            for (int listIndex = 0; listIndex < dueHabits.size(); listIndex++) {
                System.out.printf(HABIT_DETAILS, (listIndex + 1), dueHabits.get(listIndex));
            }
            System.out.println();
        }
    }

}
