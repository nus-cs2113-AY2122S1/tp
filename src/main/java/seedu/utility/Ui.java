package seedu.utility;

import seedu.entry.Expense;
import seedu.entry.Income;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Ui {
    private Scanner in;

    private static final String PRODUCT_LOGO = "███████ ████████  ██████  ███    ██ ██   ██ ███████"
            + "     ██   ██ ██████  \n██         ██    ██    ██ ████   ██ ██  ██  ██           ██ ██  ██   ██ \n"
            + "███████    ██    ██    ██ ██ ██  ██ █████   ███████       ███   ██   ██ \n"
            + "     ██    ██    ██    ██ ██  ██ ██ ██  ██       ██      ██ ██  ██   ██ \n"
            + "███████    ██     ██████  ██   ████ ██   ██ ███████     ██   ██ ██████  ";
    
    private static final String BYE_MESSAGE = "██████  ██    ██ ███████        ██  \n"
            + "██   ██  ██  ██  ██          ██  ██ \n"
            + "██████    ████   █████           ██ \n"
            + "██   ██    ██    ██          ██  ██ \n"
            + "██████     ██    ███████        ██ ";
    
    private static final String HELP_COMMAND_MESSAGE = "This is a list of commands and their format!";
    private static final String LISTING_EXPENSE_MESSAGE = "Below is a list of all of your recent spending!";
    private static final String LISTING_INCOME_MESSAGE = "Below is a list of all of your recent earnings!";
    private static final String EMPTY_INCOME_MESSAGE = "You have not entered any income!";
    private static final String EMPTY_EXPENSE_MESSAGE = "You have not spent anything!";

    private static final String HELP_FORMAT = "List Out All Commands: help";
    private static final String ADD_EXPENSE_FORMAT = "Adding Expense: add_ex d/DESCRIPTION a/AMOUNT";
    private static final String DEL_EXPENSE_FORMAT = "Deleting Expense: del_ex i/INDEX";
    private static final String LIST_EXPENSE_FORMAT = "Listing Expense: list_ex";
    private static final String TOTAL_EXPENSE_FORMAT = "Show Total Expense: total_ex";
    private static final String ADD_INCOME_FORMAT = "Adding Income: add_in d/DESCRIPTION a/AMOUNT";
    private static final String DEL_INCOME_FORMAT = "Deleting Income: del_in i/INDEX";
    private static final String LIST_INCOME_FORMAT = "Listing Income: list_in";
    private static final String TOTAL_INCOME_FORMAT = "Show Total Income: total_in";
    private static final String END_FORMAT = "To Terminate The Program: end";


    private static final List<String> commands = Arrays.asList(HELP_FORMAT, ADD_EXPENSE_FORMAT, DEL_EXPENSE_FORMAT,
            LIST_EXPENSE_FORMAT, TOTAL_EXPENSE_FORMAT, ADD_INCOME_FORMAT, DEL_INCOME_FORMAT, LIST_INCOME_FORMAT,
            TOTAL_INCOME_FORMAT, END_FORMAT);



    /**
     * Initialises the Ui system of the program with a Scanner object that is able to read user inputs.
     */
    public Ui() {
        this.in = new Scanner(System.in);
    }

    /**
     * Reads a new command from the user through the standard input.
     */
    public String readCommand() {
        return in.nextLine().trim();
    }
    
    /**
     * Prints the welcoming message for users that have entered the program.
     */
    public void printWelcome() {
        printLine();
        System.out.println(PRODUCT_LOGO);
        printLine();
        System.out.println("Type something!");
    }

    private void printLine() {
        for (int i = 0; i <= 100; i++) {
            System.out.print("-");
        }
        System.out.println(" ");
    }

    /**
     * Prints the filtered list of expenses in the financial tracker to the standard output.
     *
     * @param expenses The list of expenses in the financial tracker.
     */ 
    public void listExpense(ArrayList<Expense> expenses) {
        printLine();
        if (expenses.isEmpty()) {
            System.out.println(EMPTY_EXPENSE_MESSAGE);
            printLine();
            return;
        }
        System.out.println(LISTING_EXPENSE_MESSAGE);
        printLine();
        int i = 1;

        for (Expense expense:expenses) {
            System.out.print(i + ": ");
            System.out.println(expense);
            i++;
        }
        printLine();
    }

    /**
     * Prints the filtered list of incomes in the financial tracker to the standard output.
     *
     * @param incomes The list of incomes in the financial tracker.
     */    
    public void listIncome(ArrayList<Income> incomes) {
        printLine();
        if (incomes.isEmpty()) {
            System.out.println(EMPTY_INCOME_MESSAGE);
            printLine();
            return;
        }
        System.out.println(LISTING_INCOME_MESSAGE);
        printLine();
        int i = 1;

        for (Income income:incomes) {
            System.out.print(i + ": ");
            System.out.println(income);
            i++;
        }
        printLine();
    }
    
    /**
     * Prints the total expense of all expenses in the financial tracker to the standard output.
     *
     * @param totalExpense The total value of the expenses in the financial tracker.
     */
    public void printTotalExpense(double totalExpense) {
        assert totalExpense >= 0;
        printLine();
        System.out.printf("Your total expense is: $%.2f\n", totalExpense);
        printLine();
    }

    /**
     * Prints the total income of the financial tracker to the standard output.
     *
     * @param totalIncome The total value of the incomes in the financial tracker.
     */
    public void printTotalIncome(double totalIncome) {
        assert totalIncome >= 0;
        printLine();
        System.out.printf("Your total income is: $%.2f\n", totalIncome);
        printLine();
    }

    /**
     * Prints the feedback message for adding an expense to the financial tracker.
     *
     * @param expense The expense to be added to the financial tracker.
     */
    public void printExpenseAdded(Expense expense) {
        printLine();
        System.out.println("Your most recent spending: ");
        System.out.println(expense);
        printLine();
    }

    /**
     * Prints the feedback message for deleting an expense from the financial tracker.
     *
     * @param expense The expense to be deleted from the financial tracker.
     */
    public void printExpenseDeleted(Expense expense) {
        printLine();
        System.out.println("You removed this: ");
        System.out.println(expense);
        printLine();
    }

    /**
     * Prints the feedback message for adding an income to the financial tracker.
     *
     * @param income The income to be added to the financial tracker.
     */
    public void printIncomeAdded(Income income) {
        printLine();
        System.out.println("Your most recent earning: ");
        System.out.println(income);
        printLine();
    }

    /**
     * Prints the feedback message for deleting an income from the financial tracker.
     *
     * @param income The income to be deleted from the financial tracker.
     */
    public void printIncomeDeleted(Income income) {
        printLine();
        System.out.println("You removed this: ");
        System.out.println(income);
        printLine();
    }

    /**
     * Prints the list of commands and their respective format to the standard output.
     */
    public void printHelp() {
        printLine();
        System.out.println(HELP_COMMAND_MESSAGE);
        printLine();
        for (String command:commands) {
            System.out.println(command);
        }
        printLine();
    }

    /**
     * Prints the termination message of the STONKS XD program.
     */
    public void printBye() {
        printLine();
        System.out.println(BYE_MESSAGE);
        printLine();
    }

    /**
     * Prints the error message as feedback through the standard output.
     * 
     * @param errorMessage The error message to be printed out due to certain exceptions or invalid inputs.
     */
    public void printError(String errorMessage) {
        printLine();
        System.out.println(errorMessage);
        printLine();
    }

}