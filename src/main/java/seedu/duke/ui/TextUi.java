package seedu.duke.ui;


import seedu.duke.data.RecordList;
import seedu.duke.data.records.Expenditure;

import java.util.Scanner;

public class TextUi {

    /**
     * A platform independent line separator.
     */
    public static final String LS = System.lineSeparator();

    private static final String DIVIDER = "========================================================";
    private static final String LOGO = "  ____            _            _ _______             _\n"
            + " |  _ \\          | |          | |__   __|           | |\n"
            + " | |_) |_   _  __| | __ _  ___| |_ | |_ __ __ _  ___| | _____ _ __\n"
            + " |  _ <| | | |/ _` |/ _` |/ _ \\ __|| | '__/ _` |/ __| |/ / _ \\ '__|\n"
            + " | |_) | |_| | (_| | (_| |  __/ |_ | | | | (_| | (__|   <  __/ |\n"
            + " |____/ \\__,_|\\__,_|\\__, |\\___|\\__||_|_|  \\__,_|\\___|_|\\_\\___|_|\n"
            + "                     __/ |\n"
            + "                    |___/";

    private static final String MESSAGE_EXIT = "Bye, see you again soon!";

    private final Scanner in;

    public TextUi() {
        in = new Scanner(System.in);
    }

    //    public static void showRecordsListView(RecordList budgetList) {
    //        int budgetListLength = RecordList.numberOfRecords;
    //        for (int i = 0; i < budgetListLength; i += 1) {
    //            System.out.println(budgetList.getRecord(i));
    //        }
    //    }

    public static void showWelcomeMessage() {
        System.out.println(DIVIDER + LS
                + LOGO + LS
                + "    Hello! I'm Budget Tracker\n"
                + "    What entries do you wish to enter today?\n"
                + DIVIDER);
    }

    public static void showExpenditureAddedMessage(Expenditure addedExpenditure) {
        System.out.println("Successfully added. Here's the added expenditure: ");
        System.out.println("   " + addedExpenditure);
    }

    public static void showRecordsListView(RecordList list) {
        int i = 1;
        System.out.println(DIVIDER + LS
                 + "Your budget for this month:" + list.getBudget() + LS
                 + "Your expenditures:");
        for (Expenditure a: list.getExpenditureRecords()) {
            System.out.println(i + "." + a);
            i++;
        }
        System.out.println(DIVIDER);
    }

    public static void showExpenditureDeletedMessage(int indexOfDeletedExpenditure, Expenditure deletedExpenditure) {
        System.out.println("Successfully deleted Expenditure" + indexOfDeletedExpenditure + "." + deletedExpenditure);
    }

    public static void showBudgetDeletedMessage() {
        System.out.println("Successfully deleted Budget for this month!");
        System.out.println("Now the budget amount is 0.00!");
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
