package seedu.duke.ui;

import seedu.duke.data.RecordList;

import java.util.Scanner;

public class TextUi {

    /**
     * A platform independent line separator.
     */
    public static final String LS = System.lineSeparator();

    private static final String DIVIDER = "========================================================";

    private static final String MESSAGE_EXIT = "Bye, see you again soon!";

    private final Scanner in;

    public TextUi() {
        in = new Scanner(System.in);
    }

    public static void showRecordsListView(RecordList budgetList) {
        int budgetListLength = RecordList.numberOfRecords;
        for (int i = 0; i < budgetListLength; i += 1) {
            budgetList.printRecord(i);
        }
    }

    public static void showWelcomeMessage() {
        System.out.println(DIVIDER
                + LS
                + "    Hello! I'm Budget Tracker\n"
                + "    Which entries do you wish to enter today?\n"
                + DIVIDER);
    }

    public static void showExitMessage() {
        System.out.println(MESSAGE_EXIT + LS + DIVIDER);
    }

    public static void showInvalidCommandMessage() {
        System.out.println("☹ Sorry, I did not understand your command.");
        System.out.println(DIVIDER);
    }

    /*
     * ===========================================
     *           ERROR MESSAGES
     * ===========================================
     */

    /**
     * Reads the text entered by the user.
     *
     * @return command (full input) entered by the user.
     */
    public String getUserInput() {
        String input = in.nextLine();
        System.out.println(DIVIDER);
        return input;
    }
}
