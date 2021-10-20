package seedu.utility;

import seedu.entry.Entry;
import seedu.entry.Expense;
import seedu.entry.ExpenseCategory;
import seedu.entry.Income;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
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

    public static final String SEPARATOR_LINE = "-------------------------------------------------------------------"
            + "----------------------------------";
    private final String newLine = System.lineSeparator();


    private static final String HELP_FORMAT = "List Out All Commands: help";
    private static final String ADD_EXPENSE_FORMAT = "Adding Expense: add_ex d/DESCRIPTION a/AMOUNT c/CATEGORY";
    private static final String DEL_EXPENSE_FORMAT = "Deleting Expense: del_ex i/INDEX";
    private static final String LIST_EXPENSE_FORMAT = "Listing Expense: list_ex";
    private static final String TOTAL_EXPENSE_FORMAT = "Show Total Expense: total_ex";
    private static final String ADD_INCOME_FORMAT = "Adding Income: add_in d/DESCRIPTION a/AMOUNT c/CATEGORY";
    private static final String DEL_INCOME_FORMAT = "Deleting Income: del_in i/INDEX";
    private static final String LIST_INCOME_FORMAT = "Listing Income: list_in";
    private static final String TOTAL_INCOME_FORMAT = "Show Total Income: total_in";
    private static final String EXPENSE_BETWEEN_FORMAT = "Show Total Expense between 2 dates" 
            + ": btw_ex s/START_DATE e/END_DATE";
    private static final String INCOME_BETWEEN_FORMAT = "Show Total Income between 2 dates"
            + ": btw_in s/START_DATE e/END_DATE";
    private static final String END_FORMAT = "To Terminate The Program: end";
    private static final String FIND_FORMAT = "To Find Using Date: find YYYY-MM-DD\n"
            + "To Find Based On Keyword: find KEYWORD";
    private static final String BALANCE_FORMAT = "To Display Total Balance: balance";

    private static final List<String> commands = Arrays.asList(HELP_FORMAT, ADD_EXPENSE_FORMAT, DEL_EXPENSE_FORMAT,
            LIST_EXPENSE_FORMAT, TOTAL_EXPENSE_FORMAT, EXPENSE_BETWEEN_FORMAT, ADD_INCOME_FORMAT, DEL_INCOME_FORMAT, 
            LIST_INCOME_FORMAT, TOTAL_INCOME_FORMAT, FIND_FORMAT, BALANCE_FORMAT, INCOME_BETWEEN_FORMAT, END_FORMAT);



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
        System.out.println(Messages.TYPE_SOMETHING_MESSAGE);
    }

    private void printLine() {
        System.out.println(SEPARATOR_LINE);
    }

    /**
     * Prints the filtered list of expenses in the financial tracker to the standard output.
     *
     * @param expenses The list of expenses in the financial tracker.
     */
    public void listExpense(ArrayList<Expense> expenses) {
        printLine();
        if (expenses.isEmpty()) {
            printEmptyExpenseListMessage();
        } else {
            printExpenseList(expenses);
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
            printEmptyIncomeListMessage();
        } else {
            printIncomeList(incomes);
        }
        printLine();
    }

    public void listFind(ArrayList<Entry> filteredEntries) {
        printLine();
        if (filteredEntries.isEmpty()) {
            System.out.println(Messages.SEARCH_NO_MATCH_MESSAGE);
        } else {
            printFilteredList(filteredEntries);
        }
        printLine();
    }
    
    private void printEmptyIncomeListMessage() {
        System.out.println(Messages.EMPTY_INCOME_MESSAGE);
    }

    private void printEmptyExpenseListMessage() {
        System.out.println(Messages.EMPTY_EXPENSE_MESSAGE);
    }

    private void printFilteredList(ArrayList<Entry> filteredEntries) {
        assert filteredEntries.size() > 0;
        System.out.println(Messages.FOUND_LIST_MESSAGE);
        printLine();

        int i = 1;
        for (Entry entry : filteredEntries) {
            System.out.print(i + ": " + entry + newLine);
            i++;
        }
    }
    
    private void printIncomeList(ArrayList<Income> incomes) {
        assert incomes.size() > 0;
        System.out.println(Messages.LISTING_INCOME_MESSAGE);
        printLine();

        int i = 1;
        for (Income income : incomes) {
            System.out.print(i + ": " + income + newLine);
            i++;
        }
    }

    private void printExpenseList(ArrayList<Expense> expenses) {
        assert expenses.size() > 0;
        System.out.println(Messages.LISTING_EXPENSE_MESSAGE);
        printLine();

        int i = 1;
        for (Expense expense : expenses) {
            System.out.print(i + ": " + expense + newLine);
            i++;
        }
    }
    
    /**
     * Prints the total expense of all expenses in the financial tracker to the standard output.
     *
     * @param totalExpense The total value of the expenses in the financial tracker.
     */
    public void printTotalExpense(double totalExpense) {
        assert totalExpense >= 0;
        printLine();
        System.out.printf("Your total expense is: $%.2f" + newLine, totalExpense);
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
        System.out.printf("Your total income is: $%.2f" + newLine, totalIncome);
        printLine();
    }

    public void printBalance(double balance) {
        printLine();
        System.out.printf("Your current balance is: $%.2f" + newLine, balance);
        printLine();
    }
    
    /**
     * Prints the feedback message for adding an expense to the financial tracker.
     *
     * @param expense The expense to be added to the financial tracker.
     */
    public void printExpenseAdded(Expense expense) {
        printLine();
        System.out.println("Your most recent spending: " + newLine + expense);
        printLine();
    }

    /**
     * Prints the feedback message for deleting an expense from the financial tracker.
     *
     * @param expense The expense to be deleted from the financial tracker.
     */
    public void printExpenseDeleted(Expense expense) {
        printLine();
        System.out.println("You removed this: " + newLine + expense);
        printLine();
    }

    /**
     * Prints the feedback message for adding an income to the financial tracker.
     *
     * @param income The income to be added to the financial tracker.
     */
    public void printIncomeAdded(Income income) {
        printLine();
        System.out.println("Your most recent earning: " + newLine + income);
        printLine();
    }

    /**
     * Prints the feedback message for deleting an income from the financial tracker.
     *
     * @param income The income to be deleted from the financial tracker.
     */
    public void printIncomeDeleted(Income income) {
        printLine();
        System.out.println("You removed this: " + newLine + income);
        printLine();
    }


    public void printTotalExpenseBetween(double totalExpense, LocalDate start, LocalDate end) {
        printLine();
        if (totalExpense == 0) {
            printNoExpenseBetweenMessage(start, end);
        } else {
            printExpenseBetweenMessage(totalExpense, start, end);
        }
        printLine();
    }


    public void printTotalIncomeBetween(double totalExpense, LocalDate start, LocalDate end) {
        printLine();
        if (totalExpense == 0) {
            printNoIncomeBetweenMessage(start, end);
        } else {
            printIncomeBetweenMessage(totalExpense, start, end);
        }
        printLine();
    }

    private void printExpenseBetweenMessage(double totalExpense, LocalDate start, LocalDate end) {
        String startString = start.format(DateTimeFormatter.ofPattern("dd MMM yyy"));
        String endString = end.format(DateTimeFormatter.ofPattern("dd MMM yyy"));
        System.out.printf("Your total expense between %s and %s is $%.2f", startString, endString, totalExpense);
        System.out.print(newLine);
    }

    private void printNoExpenseBetweenMessage(LocalDate start, LocalDate end) {
        String startString = start.format(DateTimeFormatter.ofPattern("dd MMM yyy"));
        String endString = end.format(DateTimeFormatter.ofPattern("dd MMM yyy"));
        System.out.printf("You do not have any expense between %s and %s", startString, endString);
        System.out.print(newLine);
    }

    private void printIncomeBetweenMessage(double totalExpense, LocalDate start, LocalDate end) {
        String startString = start.format(DateTimeFormatter.ofPattern("dd MMM yyy"));
        String endString = end.format(DateTimeFormatter.ofPattern("dd MMM yyy"));
        System.out.printf("Your total income between %s and %s is $%.2f", startString, endString, totalExpense);
        System.out.print(newLine);
    }

    private void printNoIncomeBetweenMessage(LocalDate start, LocalDate end) {
        String startString = start.format(DateTimeFormatter.ofPattern("dd MMM yyy"));
        String endString = end.format(DateTimeFormatter.ofPattern("dd MMM yyy"));
        System.out.printf("You do not have any income between %s and %s", startString, endString);
        System.out.print(newLine);

    }

    /**
     * Prints the list of commands and their respective format to the standard output.
     */
    public void printHelp() {
        printLine();
        System.out.println(Messages.HELP_COMMAND_MESSAGE);
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
        System.out.println(Messages.BYE_MESSAGE);
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

    /**
     * Prints a message to user through standard output confirming all entries have been cleared.
     */
    public void printAllEntriesCleared() {
        printLine();
        System.out.println(Messages.ALL_DATA_CLEARED);
        printLine();
    }
    
    public void printGraph(String graph) {
        printLine();
        System.out.println(graph);
        printLine();
    }

    public void printBudgetWarning(String month, String budgetName, double currAmount, double limit) {
        printLine();
        System.out.printf("You are almost reaching the %s %s budget: $%.2f/$%.2f", month, budgetName, currAmount, limit);
        System.out.print(newLine);
        System.out.println("Would you like to readjust your " + month + " " + budgetName + " budget?");
        printLine();
    }

    public void printBudgetExceeded(String month, String budgetName, double currAmount, double limit) {
        printLine();
        System.out.printf("You have exceeded the %s %s budget: $%.2f/$%.2f", month, budgetName, currAmount, limit);
        System.out.print(newLine);
        System.out.println("Would you like to readjust your " + month + " " + budgetName + " budget?");
        printLine();
    }

    public void printBudgetSetConfirmation(double amount, ExpenseCategory category) {
        printLine();
        System.out.printf("%s budget has been set to $%.2f", category.toString(), amount);
        System.out.print(newLine);
        printLine();
    }

    public void printBudget(ExpenseCategory category, double budgetLimit) {
        printLine();
        System.out.printf("Current %s limit is $%.2f", category.toString(), budgetLimit);
        System.out.print(newLine);
        printLine();
    }
}
