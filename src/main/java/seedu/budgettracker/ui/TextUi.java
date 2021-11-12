package seedu.budgettracker.ui;

import seedu.budgettracker.data.records.Budget;
import seedu.budgettracker.logic.commands.Command;
import seedu.budgettracker.logic.commands.FindCommand;
import seedu.budgettracker.logic.commands.StatYearCommand;
import seedu.budgettracker.data.AllRecordList;
import seedu.budgettracker.data.records.Category;
import seedu.budgettracker.data.records.Expenditure;
import seedu.budgettracker.data.records.Loan;
import seedu.budgettracker.storage.Storage;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Scanner;

import static java.lang.Math.round;
import static seedu.budgettracker.common.Messages.MESSAGE_EXIT;

public class TextUi {

    /**
     * A platform independent line separator.
     */
    public static final String LS = System.lineSeparator();

    private static final DecimalFormat df = new DecimalFormat("0.00");

    private static final String DIVIDER = "========================================================";
    private static final String LOGO = "  ____            _            _ _______             _\n"
            + " |  _ \\          | |          | |__   __|           | |\n"
            + " | |_) |_   _  __| | __ _  ___| |_ | |_ __ __ _  ___| | _____ _ __\n"
            + " |  _ <| | | |/ _` |/ _` |/ _ \\ __|| | '__/ _` |/ __| |/ / _ \\ '__|\n"
            + " | |_) | |_| | (_| | (_| |  __/ |_ | | | | (_| | (__|   <  __/ |\n"
            + " |____/ \\__,_|\\__,_|\\__, |\\___|\\__||_|_|  \\__,_|\\___|_|\\_\\___|_|\n"
            + "                     __/ |\n"
            + "                    |___/";

    private static final String buffer = "     ";
    public static final int percentageRepresentedByEachBar = 5;
    private static final String WARNING_DIVIDER = "************************************************";
    private final Scanner in;

    public TextUi() {
        in = new Scanner(System.in);
    }

    //@@author YEOWEIHNGWHYELAB
    public static void showWelcomeMessage() {
        DateTimeFormatter dateTime = DateTimeFormatter.ofPattern("HH:mm dd/MM/yyyy");
        LocalDateTime now = LocalDateTime.now();
        System.out.println();
        System.out.println(DIVIDER + LS + "Current Date and Time: "
                + dateTime.format(now) + LS);
        System.out.println("Database will be set to: " + now.getYear());
        Delay.wait(1000);

        System.out.println(DIVIDER + LS
                + "If you would like to change the database, "
                + "please use the \"year DATABASE_YEAR\" command");

        System.out.println();

        System.out.println("Loading... Please Wait");
        Delay.loadingBar(20);

        System.out.println(DIVIDER + LS
                + LOGO + LS
                + "    Hello! I'm Budget Tracker\n"
                + "    What entries do you wish to enter today?\n"
                + DIVIDER);
    }



    public static void csvStatus() {
        System.out.println("Your csv file have been successfully created in the data folder!");
        System.out.print(LS);
        System.out.println("Please have a look!");

        System.out.println(DIVIDER);
    }

    //@@author EdisonZhong17
    public static void showLoanReminder(int index, Loan dueLoan) {
        if (index == 1) {
            System.out.println("Loan Reminder:");
        }
        System.out.println(index + "."
                + "Your loan to "
                + dueLoan.getName() + ", "
                + "$" + df.format(dueLoan.getAmount()) + ", "
                + "on " + dueLoan.getDate()
                + " is due!!");
    }

    //@@author ZenoNexus
    public static void showExpenditureAddedMessage(Expenditure addedExpenditure, AllRecordList recordList) {
        assert addedExpenditure.getAmount() > 0 : "Expenditure added should be a positive value";

        System.out.println("Expenditure successfully added!");
        showExpenditureDetails(addedExpenditure, recordList);

        printDivider();
    }


    public static void showExpenditureEditedMessage(Expenditure newExpenditure, AllRecordList recordList) {
        assert newExpenditure.getAmount() > 0 : "Edited Expenditure should have a positive amount";

        System.out.println("Expenditure has been successfully edited!");
        System.out.println("New values: ");
        showExpenditureDetails(newExpenditure, recordList);

        printDivider();
    }


    private static void showExpenditureDetails(Expenditure newExpenditure, AllRecordList recordList) {
        System.out.println("Description: " + newExpenditure.getDescription()
                + "\nAmount: $" + df.format(newExpenditure.getAmount())
                + "\nDate: " + newExpenditure.getDateString()
                + "\nCategory: " + newExpenditure.getCategoryString());

        int month = newExpenditure.getMonth();
        String monthString = getMonthString(month);
        double amount = recordList.getBudget(month).getAmount();
        double totalMonthExpenditureSpending = recordList.getTotalAmountSpent(month);

        spendingNotice(monthString, amount, totalMonthExpenditureSpending);
    }

    //@@author EdisonZhong17
    private static void showExpenditureDetailsForDelete1(Expenditure newExpenditure) {
        System.out.println("Description: " + newExpenditure.getDescription()
                + "\nAmount: $" + df.format(newExpenditure.getAmount())
                + "\nDate: " + newExpenditure.getDateString()
                + "\nCategory: " + newExpenditure.getCategoryString());
    }

    //@@author EdisonZhong17
    private static void showExpenditureDetailsForDelete2(Expenditure newExpenditure, AllRecordList recordList) {
        System.out.println("Description: " + newExpenditure.getDescription()
                + "\nAmount: $" + df.format(newExpenditure.getAmount())
                + "\nDate: " + newExpenditure.getDateString()
                + "\nCategory: " + newExpenditure.getCategoryString());

        System.out.println(DIVIDER);

        int month = newExpenditure.getMonth();
        String monthString = getMonthString(month);
        double amount = recordList.getBudget(month).getAmount();
        double totalMonthExpenditureSpending = recordList.getTotalAmountSpent(month);

        spendingNotice(monthString, amount, totalMonthExpenditureSpending);
    }

    //@@author YEOWEIHNGWHYELAB
    private static void spendingNotice(String monthString, double amount, double totalMonthExpenditureSpending) {
        System.out.println("Total Amount Spent in "
                + monthString
                + ": $" + df.format(totalMonthExpenditureSpending));

        double amountLeft = amount - totalMonthExpenditureSpending;

        double percentageLeft;
        if (amount == 0) {
            System.out.println("Your Budget is: $0.00");
            System.out.println("Have you forgotten to enter the budget first?");
        } else if (amountLeft > 0) {
            percentageLeft = (amountLeft / amount) * 100;
            System.out.print("Percentage of Budget Left: ");
            System.out.printf("%.2f", percentageLeft);
            System.out.println("%");
        } else {
            percentageLeft = (totalMonthExpenditureSpending / amount) * 100;
            System.out.print("You overspent your Budget by: ");
            System.out.printf("%.2f", percentageLeft);
            System.out.println("%");
        }

    }



    //@@author ZenoNexus
    public static void showBudgetAddedMessage(double amount, int month) {
        String monthString = getMonthString(month);
        System.out.println("Your budget of $"
                + df.format(amount)
                + " for "
                + monthString
                + " has been successfully set!"
                + LS
                + DIVIDER);
    }


    public static void showBudgetEditedMessage(Budget newBudget) {
        assert newBudget.getAmount() > 0 : "Edited Budget should have a positive amount";

        System.out.println("Budget has been successfully edited!");
        System.out.println("New values: ");
        showBudgetDetails(newBudget);

        printDivider();
    }

    //@@author EdisonZhong17
    private static void showBudgetDetails(Budget newBudget) {
        System.out.println("Amount: $" + df.format(newBudget.getAmount())
                + "\nMonth: " + newBudget.getMonth());
    }
    //@@author


    public static void showLoanAddedMessage(Loan newLoan, boolean isLoadingStorage) {
        if (isLoadingStorage) {
            return;
        }
        System.out.println("Loan successfully added!"
                + LS
                + newLoan.getName() + " owes you: $" + df.format(newLoan.getAmount())
                + LS
                + "Date of loan: " + newLoan.getDate()
                + LS
                + DIVIDER);

        if (newLoan.getAmount() <= 0.00) {
            System.out.println(WARNING_DIVIDER);
            System.out.println("You may have entered a negative amount or entered $0.00!");
            System.out.println("Please make the necessary edit before continuing!");
            System.out.println(WARNING_DIVIDER);
        }
    }

    public static void showLoanEditedMessage(Loan newLoan) {
        assert newLoan.getAmount() > 0 : "Edited Loan should have a positive amount";

        System.out.println("Loan has been successfully edited!");
        System.out.println("New values: ");
        showLoanDetails(newLoan);

        printDivider();
    }

    private static void showLoanDetails(Loan newLoan) {
        System.out.println("Debtor: " + newLoan.getName()
                + "\nAmount: $" + df.format(newLoan.getAmount())
                + "\nDate: " + newLoan.getDate());
    }

    //@@author LS-Young
    public static String getMonthString(int month) {
        String monthString = null;
        switch (month) {
        case 1:
            monthString = "January";
            break;
        case 2:
            monthString = "February";
            break;
        case 3:
            monthString = "March";
            break;
        case 4:
            monthString = "April";
            break;
        case 5:
            monthString = "May";
            break;
        case 6:
            monthString = "June";
            break;
        case 7:
            monthString = "July";
            break;
        case 8:
            monthString = "August";
            break;
        case 9:
            monthString = "September";
            break;
        case 10:
            monthString = "October";
            break;
        case 11:
            monthString = "November";
            break;
        case 12:
            monthString = "December";
            break;
        default:
            break;
        }
        return monthString;
    }

    //@@author YEOWEIHNGWHYELAB
    public static void showRecordsListView(AllRecordList records, int month, boolean isListAll, Category category) {
        if (isListAll) {
            for (int i = 1; i <= 12; i++) {
                printRecordList(records, i, category);
            }
        } else {
            printRecordList(records, month, category);
        }
    }

    //@@author LS-Young
    /**
     * print out the record list.
     * @param records the record list of budget, expenditures and loans
     * @param i the month of the record list
     * @param category the category to be listed
     */
    private static void printRecordList(AllRecordList records, int i, Category category) {
        String monthString = getMonthString(i);
        double totalSpending = 0.0;
        double currentMonthBudget = records.getBudget(i).getAmount();
        ArrayList<Expenditure> currentMonthRecordList = records.getExpenditureRecords(i);

        for (Expenditure expenditure : currentMonthRecordList) {
            if (expenditure.getCategory() == category || category == Category.ALL) {
                totalSpending += expenditure.getAmount();
            }
        }

        String budget = "";
        boolean printInfo = true;

        if (currentMonthBudget == 0) {
            budget = " Budget has not been allocated!";
            printInfo = false;
        } else {
            budget = records.getBudget(i).toString();
        }

        recordListPrinter(records, i, category, monthString, totalSpending, currentMonthBudget, budget, printInfo);
    }

    //@@author YEOWEIHNGWHYELAB
    private static void recordListPrinter(AllRecordList records, int i, Category category,
                                          String monthString, double totalSpending, double currentMonthBudget,
                                          String budget, boolean printInfo) {
        if (totalSpending > currentMonthBudget && currentMonthBudget > 0 && printInfo) {
            System.out.println("You are spending too much for " + monthString + "!");
            double percentage = (totalSpending / currentMonthBudget) * 100;
            System.out.printf("%.2f", percentage);
            System.out.println("% of your overall budget has been spent");
        } else if (printInfo) {
            System.out.println("You did not overspend for " + monthString + ", Good JOB!");
            double percentage = (totalSpending / currentMonthBudget) * 100;
            System.out.printf("%.2f", percentage);
            System.out.println("% of your overall budget has been spent");
        }

        getMonthListView(records, i, monthString, budget, category);
    }
    //@@author

    //@@author LS-Young
    /**
     * Names/Descriptions longer than 30characters may get truncated.
     */
    private static void getMonthListView(AllRecordList list, int month, String monthString,
                                         String budget, Category category) {
        budgetExpenditurePrinter(list, month, monthString, budget, category);
        loanPrinter(list, month);
        printDivider();
    }

    private static void loanPrinter(AllRecordList list, int month) {
        System.out.println("Your loans: ");
        if (list.getLoanListSize(month) > 0) {
            System.out.printf("%-20.20s  %-20.20s %-20.20s%n", "  Debtor name", "   | Amount", "   | Date ");
            printEnumeratedLoanList(list.getLoanRecords(month));
        } else {
            System.out.println("No Loan records yet.");
        }
    }

    private static void budgetExpenditurePrinter(AllRecordList list, int month,
                                                 String monthString, String budget, Category category) {
        System.out.println("Your budget for " + monthString + ":" + budget + LS
                + "Your expenditures:");

        if (list.getMonthNumberOfExpenditures(month) > 0) {
            System.out.printf("%-30.30s %-20.20s %-20.20s %-20.20s%n", "  Description", "| Amount",
                    "| Date ", "| Category");
            printEnumeratedExpenditureList(list.getExpenditureRecords(month), category);
        } else {
            System.out.println("No Expenditure records yet.");
        }
    }

    private static void printEnumeratedExpenditureList(ArrayList<Expenditure> monthExpenditureList, Category category) {
        for (int i = 0; i < monthExpenditureList.size(); i++) {
            Expenditure currentExpenditure = monthExpenditureList.get(i);
            if (currentExpenditure.getCategory() == category || category == Category.ALL) {
                System.out.println(currentExpenditure.toString(i));
            }
        }
    }

    private static void printEnumeratedLoanList(ArrayList<Loan> monthLoanList) {
        for (int i = 0; i < monthLoanList.size(); i++) {
            Loan currentLoan = monthLoanList.get(i);
            System.out.println(i + 1 + "." + currentLoan);
        }
    }

    //@@author EdisonZhong17
    public static void showSingleExpenditureDeletedMessage(int index, Expenditure delExe, AllRecordList allRecordList) {
        System.out.println("Successfully deleted Expenditure " + index + ":");
        showExpenditureDetails(delExe, allRecordList);
        System.out.println(DIVIDER);
    }

    //@@author EdisonZhong17
    public static void showSingleLoanDeletedMessage(int indexOfLoan, Loan deletedLoan) {
        System.out.println("Successfully deleted Loan " + indexOfLoan + ":");
        showLoanDetails(deletedLoan);
        System.out.println(DIVIDER);
    }

    //@@author EdisonZhong17
    public static void showAllExpenditureDeletedMessage() {
        System.out.println("Successfully deleted all Expenditures in this month!");
        System.out.println(DIVIDER);
    }

    //@@author EdisonZhong17
    public static void showAllLoanDeletedMessage() {
        System.out.println("Successfully deleted all Loans in this month!");
        System.out.println(DIVIDER);
    }

    //@@author EdisonZhong17
    public static void showMultiExpenditureDelMessage(int index1, int index2, Expenditure delExe, AllRecordList list) {
        System.out.println("Successfully deleted Expenditure " + index1 + ":");
        if (index1 != index2) {
            showExpenditureDetailsForDelete1(delExe);
        } else {
            showExpenditureDetailsForDelete2(delExe, list);
        }
        System.out.println(DIVIDER);
    }

    //@@author EdisonZhong17
    public static void showMultipleLoanDeletedMessage(int index, Loan deletedLoan) {
        System.out.println("Successfully deleted Loan " + index + ":");
        showLoanDetails(deletedLoan);
        System.out.println(DIVIDER);
    }

    //@@author EdisonZhong17
    public static void showBudgetDeletedMessage() {
        System.out.println("Successfully deleted the Budget for this month!");
        System.out.println("Now the budget amount is $0.00!");
        System.out.println(DIVIDER);
    }

    //@@author YEOWEIHNGWHYELAB
    public static void drawVerticalPercentage(double[] barPercentage) {
        System.out.println("Percentage of Money Spent");
        System.out.println(buffer + "JAN FEB MAR APR MAY JUN JUL AUG SEP OCT NOV DEC ");
        for (int i = 10; i > 0; i--) {
            System.out.printf("%-5s", (i * 10) + "%");
            printVerticalPercentageRow(barPercentage, i);
            System.out.println();
        }
        printDivider();
    }



    public static void printExplosion() {
        System.out.println("              _.-^^---....,,---_\n"
                + "           _--                  --_\n"
                + "          <          Overspent!       >)\n"
                + "           \\._                   _./\n"
                + "              ```--. . , ; .--'''\n"
                + "                    | |   |\n"
                + "                 .-=||  | |=-.\n"
                + "                 `-=#$%&%$#=-'\n"
                + "                    | ;  :|\n"
                + "           _____.,-#%&$@%#&#~,._____");
    }



    public static void drawSingleOverallGraph(double barPercentage) {
        System.out.println("Percentage of Money Spent throughout the year");
        System.out.print(LS);

        System.out.print("Percentage: ");
        System.out.printf("%.2f", barPercentage);
        System.out.print("%");
        System.out.print(LS);

        if (barPercentage > 100) {
            printExplosion();
        }

        for (int i = 20; i > 0; i--) {
            if (i == 20) {
                System.out.print((i * 5) + "%     ");
            } else if (i == 1) {
                System.out.print((i * 5) + "%       ");
            } else {
                System.out.print((i * 5) + "%      ");
            }

            if (barPercentage < i * 5) {
                System.out.print("            .....");
            } else {
                System.out.print("            #####");
            }

            System.out.print(LS);
        }

        printDivider();
    }



    private static void printVerticalPercentageRow(double[] barPercentage, int i) {
        for (double v : barPercentage) {
            if ((i * 10) <= v) {
                System.out.print(" #  ");
            } else {
                System.out.print("    ");
            }
        }
    }



    //@@author ZenoNexus
    public static void displayStats(int month, double[] categoryPercentage, double monthBudget,
                                    String topCategory, double topCategorySpending) {
        String monthString = getMonthString(month);
        System.out.println("Here are the statistics for the month of " + monthString + ":");
        drawHorizontalGraph(categoryPercentage);
        System.out.print(LS);
        System.out.println("Your budget: $" + df.format(monthBudget));
        System.out.println("The category you spent the most on: " + topCategory);
        System.out.println("The amount you spent on this category: $" + df.format(topCategorySpending));
        printDivider();
    }


    public static void drawHorizontalGraph(double[] barPercentage) {
        printHorizontalGraphHeader();
        for (Category category : Category.values()) {
            System.out.printf("%-14s", category.toString());
            int categoryIndex = category.ordinal();
            double percentageToPrint = barPercentage[categoryIndex];
            printHorizontalGraphRow(percentageToPrint);
        }
    }

    private static void printHorizontalGraphHeader() {
        System.out.printf("%-14s", " ");
        System.out.printf("%-6s", "0%");
        for (int i = 1; i <= 10; i++) {
            System.out.printf("%-6s", (i * 10) + "%");
        }
        System.out.println();
    }

    //@@author YEOWEIHNGWHYELAB
    public static void printYearStatus(int statusMessage) {
        System.out.print("The data base have been switched to: ");
        System.out.println(statusMessage);

        System.out.println("You may work on the " + statusMessage + " database!");

        System.out.println(DIVIDER);
    }

    //@@author ZenoNexus
    private static void printHorizontalGraphRow(double percentageToPrint) {
        int totalNumberOfBars = 20;
        long numberOfBarsToPrint = round(percentageToPrint / percentageRepresentedByEachBar);
        DecimalFormat df = new DecimalFormat("###.##");
        for (int i = 0; i < totalNumberOfBars; i++) {
            String barToPrint;
            if (i < numberOfBarsToPrint) {
                barToPrint = "###";
            } else {
                barToPrint = "   ";
            }
            System.out.print(barToPrint);
        }
        System.out.print("     " + df.format(percentageToPrint) + "%");
        System.out.println();
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

    //@@author YEOWEIHNGWHYELAB
    public static void statsIntro(AllRecordList recordList) {
        StatYearCommand command = new StatYearCommand(1);
        command.setAllRecordList(recordList);

        command.overallStatisticsIntro();

        Delay.wait(200);
        System.out.println();
        Delay.loadingBar(20);
    }



    public static void printAvailableDataBase(Storage dataBase) {
        System.out.println("These are the data base you currently have!");
        dataBase.directoryListAllFiles();
    }



    public static void printExpenditureLoanFoundInMonth(ArrayList<Expenditure> matchedExpenditureList,
                                                        ArrayList<Loan> matchedLoanList,
                                                        int sizeOfMatchedExpenditureList,
                                                        int sizeOfMatchedLoanList) {
        if (sizeOfMatchedExpenditureList == 0) {
            System.out.println("No Expenditures found for this month");
        } else {
            System.out.println("Here are the Expenditures we found!");
            System.out.println(FindCommand.TITLE_DIVIDER);
            for (int j = 0; j < sizeOfMatchedExpenditureList; j += 1) {
                System.out.println(matchedExpenditureList.get(j).toString(j));
            }
        }

        System.out.println(FindCommand.EXPENDITURE_LOAN_DIVIDER);

        if (sizeOfMatchedLoanList == 0) {
            System.out.println("No Loan found for this month");
        } else {
            System.out.println("Here are the Loan we found!");
            System.out.println(FindCommand.TITLE_DIVIDER);
            for (int j = 0; j < sizeOfMatchedLoanList; j += 1) {
                System.out.print((j + 1) + ". ");
                System.out.println(matchedLoanList.get(j).toString());
                System.out.print(LS);
            }
        }

        System.out.println(FindCommand.DIVIDER);
    }

    //@@author jyxhazcake
    /**
     * Prints a message out to the user with dividers.
     * @param messages the message shown to the user
     */
    public static void showUser(String... messages) {
        for (String message: messages) {
            System.out.println(message);
        }
        printDivider();
    }

    //@@author ZenoNexus
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
