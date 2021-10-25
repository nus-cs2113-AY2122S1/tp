package happybit.ui;

import happybit.command.Command;
import happybit.exception.HaBitCommandException;
import happybit.exception.HaBitParserException;
import happybit.exception.HaBitStorageException;
import happybit.goal.GoalList;
import happybit.goal.GoalType;
import happybit.parser.Parser;
import happybit.storage.Storage;

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

    private static final String RETURN_MESSAGE = "Press enter to return to command mode...\n";

    protected GoalList goalList;
    protected PrintManager printManager;
    protected Storage storage;

    public UiMain(GoalList goalList, PrintManager printManager, Storage storage) {
        this.goalList = goalList;
        this.printManager = printManager;
        this.storage = storage;
        loadData();
        this.goalList.setRecurringTasks();
    }

    /**
     * Main function to execute main application interface.
     *
     * @return Boolean of whether the isReturn flag is set.
     */
    public boolean run() {
        resetDisplay();
        return processInput();
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
            printManager.showError(e.getMessage());
        }
    }

    /**
     * Reads in user input and performs the corresponding command.
     *
     * @return Boolean of whether the isReturn flag is set.
     */
    private boolean processInput() {
        String userInput;
        Scanner in = new Scanner(System.in);
        boolean isExit = false;
        boolean isReturn = false;
        while (!isExit && !isReturn) {
            userInput = readUserInput(in);
            try {
                Command command = Parser.parse(userInput);
                command.runCommand(goalList, printManager, storage);
                isExit = isExitCommand(command);
                isReturn = isReturnCommand(command);
                checkFlags(isExit, isReturn);
            } catch (HaBitParserException | HaBitCommandException e) {
                printManager.showError(e.getMessage());
                waitApp(2);
                resetDisplay();
            }
        }
        return isReturn;
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
        System.out.println(LS + RETURN_MESSAGE);
        Scanner in = new Scanner(System.in);
        String userInput = null;
        while (userInput == null) {
            userInput = in.nextLine();
        }
        resetDisplay();
    }

    /**
     * Clears the console and re-prints the title.
     */
    private void resetDisplay() {
        clearConsoleScreen();
        printTitle();
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

    // More work to be done next week before v2.0

    private void printChosenGoal() {
        if (goalList.getChosenGoalIndex() == -1) {
            System.out.println("You may choose to view a goal with 'goal <GOAL_INDEX>'.");
        } else {
            printHabitList();
        }
    }

    private void printHabitList() {

    }

    private void printProgressBar() {

    }
}
