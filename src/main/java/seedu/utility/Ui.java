package seedu.utility;

import seedu.entry.Entry;
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
    
    public static final String HELP_COMMAND_MESSAGE = "This is a list of commands and their format!";
    private static final String LISTING_EXPENSE_MESSAGE = "Below is a list of all of your recent spending!";
    private static final String LISTING_INCOME_MESSAGE = "Below is a list of all of your recent earnings!";

    private static final String HELP_FORMAT = "List out all commands: help:)";
    private static final String ADD_EXPENSE_FORMAT = "Adding Expense: add_ex d/DESCRIPTION a/AMOUNT";
    private static final String DEL_EXPENSE_FORMAT = "Deleting Expense: del_ex i/INDEX";
    private static final String LIST_EXPENSE_FORMAT = "Listing Expense: list_ex";
    private static final String TOTAL_EXPENSE_FORMAT = "Show Total Expense: total_ex";
    private static final String ADD_INCOME_FORMAT = "Adding Income: add_in d/DESCRIPTION a/AMOUNT";
    private static final String DEL_INCOME_FORMAT = "Deleting Income: del_in i/INDEX";
    private static final String LIST_INCOME_FORMAT = "Listing Income: list_in";
    private static final String TOTAL_INCOME_FORMAT = "Show Total Income: total_in";



    private static final List<String> commands = Arrays.asList(HELP_FORMAT, ADD_EXPENSE_FORMAT, DEL_EXPENSE_FORMAT,
            LIST_EXPENSE_FORMAT, TOTAL_EXPENSE_FORMAT, ADD_INCOME_FORMAT, DEL_INCOME_FORMAT, LIST_INCOME_FORMAT,
            TOTAL_INCOME_FORMAT);


    
    
    public Ui() {
        this.in = new Scanner(System.in);
    }

    public String readCommand() {
        return in.nextLine().trim();
    }

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
    
    public void listExpense(ArrayList<Entry> entries) {
        printLine();
        System.out.println(LISTING_EXPENSE_MESSAGE);
        printLine();
        int i = 1;
        for (Entry entry:entries) {
            if (entry instanceof Expense) {
                System.out.print(i);
                System.out.print(": ");
                System.out.println(entry);
                i++;
            }
        }
        printLine();

    }

    public void emptyList() {
        printLine();
        System.out.println("Your list is empty");
        printLine();
    }
    
    public void listIncome(ArrayList<Entry> entries) {
        printLine();
        System.out.println(LISTING_INCOME_MESSAGE);
        printLine();
        int i = 1;
        for (Entry entry:entries) {
            if (entry instanceof Income) {
                System.out.print(i);
                System.out.print(": ");
                System.out.println(entry);
                i++;
            }
        }
        printLine();

    }
    
    public void printTotalExpense(double totalExpense) {
        System.out.printf("Your total expense is: %f\n", totalExpense);
        printLine();
    }
    
    public void printTotalIncome(double totalIncome) {
        System.out.printf("Your total income is: %f\n", totalIncome);
        printLine();
    }
    
    public void printExpenseAdded(Expense expense) {
        printLine();
        System.out.println("Your most recent spending: ");
        System.out.println(expense);
        printLine();

    }

    public void printExpenseDeleted(Expense expense) {
        printLine();
        System.out.println("You removed this: ");
        System.out.println(expense);
        printLine();

    }
    
    public void printIncomeAdded(Income income) {
        printLine();
        System.out.println("Your most recent earning: ");
        System.out.println(income);
        printLine();

    }

    public void printIncomeDeleted(Income income) {
        printLine();
        System.out.println("You removed this: ");
        System.out.println(income);
        printLine();

    }
    
    public void printHelp() {
        printLine();
        System.out.println(HELP_COMMAND_MESSAGE);
        printLine();
        for (String command:commands) {
            System.out.println(command);
        }
        printLine();
    }
    
    public void printBye() {
        printLine();
        System.out.println(BYE_MESSAGE);
        printLine();
    }

    public void printError(String message) {
        printLine();
        System.out.println(message);
        printLine();
    }

}