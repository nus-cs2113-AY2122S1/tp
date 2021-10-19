package seedu.duke.ui;


import seedu.duke.data.AllRecordList;
import seedu.duke.data.RecordList;
import seedu.duke.data.records.Expenditure;

import java.time.LocalDate;
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

    public static void showExpenditureAddedMessage(Expenditure addedExpenditure, boolean isLoadingStorage) {
        if (!isLoadingStorage) {
            System.out.println("Expenditure successfully added!"
                    + LS
                    + "Description: " + addedExpenditure.getDescription()
                    + "\nAmount: $" + addedExpenditure.getAmount()
                    + "\nDate: " + addedExpenditure.getDate());
            System.out.println(DIVIDER);
        }
    }

    public static void showBudgetAddedMessage(double amount, boolean isLoadingStorage) {
        if (!isLoadingStorage) {
            System.out.println("Your budget of "
                    + amount
                    + " for this month is successfully added!"
                    + LS
                    + DIVIDER);
        }
    }

    public static String getMonth(int month) {
        String Month = null;
        switch (month) {
        case 1:
            Month = "January";
            break;
        case 2:
            Month = "February";
            break;
        case 3:
            Month = "March";
            break;
        case 4:
            Month = "April";
            break;
        case 5:
            Month = "May";
            break;
        case 6:
            Month = "June";
            break;
        case 7:
            Month = "July";
            break;
        case 8:
            Month = "August";
            break;
        case 9:
            Month = "September";
            break;
        case 10:
            Month = "October";
            break;
        case 11:
            Month = "November";
            break;
        case 12:
            Month = "December";
            break;
        default:
            break;
        }
        return Month;
    }

    public static void showRecordsListView(AllRecordList list, int month, boolean isListAll) {
        int i = 1;

        if (isListAll) {
            for (month = 1; month <= 12; month += 1) {
                String Month = getMonth(month);
                if (list.checkOverspending(month)) {
                    System.out.println("You are spending too much for " + Month + " !");
                }
                String budget = (list.getBudget(month).getRawValue() > 0) ? list.getBudget(month).toString(): " Not Set Yet";
                System.out.println("Your budget for " + Month + ":" + budget + LS
                        + "Your expenditures:");
                if (list.getMonthListSize(month) > 0) {
                System.out.printf("%-20.20s  %-20.20s %-20.20s%n", "  Description", "   | Amount", "   | Date ");
                for (Expenditure a : list.getExpenditureRecords(month)) {
                    System.out.println(i + "." + a);
                    i++;
                }
                } else {
                    System.out.println(" None ");
                }
                System.out.println(DIVIDER);

                i = 1;
            }
        } else {
            String Month = getMonth(month);
            if (list.checkOverspending(month)) {
                System.out.println("You are spending too much for " + Month + " !");
            }
            String budget = (list.getBudget(month).getRawValue() > 0) ? list.getBudget(month).toString(): "Not Set Yet";
            System.out.println("Your budget for " + Month + ":" + budget + LS
                    + "Your expenditures:");
            if (list.getMonthListSize(month) > 0) {
                System.out.printf("%-20.20s  %-20.20s %-20.20s%n", "  Description", "   | Amount", "   | Date ");
                for (Expenditure a : list.getExpenditureRecords(month)) {
                    System.out.println(i + "." + a);
                    i++;
                }
            } else {
                System.out.println(" None ");
            }
            System.out.println(DIVIDER);
        }
    }

    public static void showExpenditureDeletedMessage(int indexOfDeletedExpenditure, Expenditure deletedExpenditure) {
        System.out.println("Successfully deleted Expenditure " + indexOfDeletedExpenditure + "." + deletedExpenditure);
        System.out.println(DIVIDER);
    }

    public static void showBudgetDeletedMessage() {
        System.out.println("Successfully deleted Budget for this month!");
        System.out.println("Now the budget amount is 0.00!");
        System.out.println(DIVIDER);
    }

    public static void showExitMessage() {
        System.out.println(MESSAGE_EXIT + LS + DIVIDER);
    }

    /*
     * ===========================================
     *           ERROR MESSAGES
     * ===========================================
     */

    public static void showInvalidCommandMessage(String feedbackToUser) {
        System.out.println(feedbackToUser);
        System.out.println(DIVIDER);
    }

    /**
     * Prints out the divider line.
     */
    public static void printDivider() {
        System.out.println(DIVIDER);
    }

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
