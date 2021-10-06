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

    protected static final String PRODUCT_LOGO = "███████ ████████  ██████  ███    ██ ██   ██ ███████"
            + "     ██   ██ ██████  \n██         ██    ██    ██ ████   ██ ██  ██  ██           ██ ██  ██   ██ \n"
            + "███████    ██    ██    ██ ██ ██  ██ █████   ███████       ███   ██   ██ \n"
            + "     ██    ██    ██    ██ ██  ██ ██ ██  ██       ██      ██ ██  ██   ██ \n"
            + "███████    ██     ██████  ██   ████ ██   ██ ███████     ██   ██ ██████  ";
    
    protected static final String BYE_MESSAGE = "██████  ██    ██ ███████        ██  \n"
            + "██   ██  ██  ██  ██          ██  ██ \n"
            + "██████    ████   █████           ██ \n"
            + "██   ██    ██    ██          ██  ██ \n"
            + "██████     ██    ███████        ██ ";

    
    
    protected static final List<String> commands = Arrays.asList("help", "add_ex", "del_ex",
            "list_ex", "total_ex", "add_in", "del_in", "list_in", "total_in");

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
    
    public void listExpense(ArrayList<Object> entries) {
        int i = 1;
        for (Object entry:entries) {
            if (entry instanceof Expense) {
                System.out.println(i);
                System.out.println(": ");
                System.out.println(entry);
                i++;
            }
        }  
    }
    
    public void listIncome(ArrayList<Object> entries) {
        int i = 1;
        for (Object entry:entries) {
            if (entry instanceof Income) {
                System.out.println(i);
                System.out.println(": ");
                System.out.println(entry);
                i++;
            }
        }
    }
    
    public void printTotalExpense(ArrayList<Entry> entries) {
        int totalExpense = 0;
        for (Entry entry:entries) {
            if (entry instanceof Expense) {
                totalExpense += entry.getValue();
            }
        }
        System.out.printf("Your total expense is: %d\n",totalExpense);
    }
    
    public void printTotalIncome(ArrayList<Entry> entries) {
        int totalIncome = 0;
        for (Entry entry:entries) {
            if (entry instanceof Income) {
                totalIncome += entry.getValue();
            }
        }
        System.out.printf("Your total income is: %d\n",totalIncome);
    }
    
    public void printExpenseAdded() {
        System.out.println("Your most recent spending: ");
    }
    
    public void printExpenseDeleted() {
        System.out.println(".... has been removed");
    }
    
    public void printIncomeAdded() {
        System.out.println("Your most recent earning: ");
    }
    
    public void printIncomeDeleted() {
        System.out.println(".... has been removed");
    }
    
    public void printHelp() {
        for (String command:commands) {
            System.out.println(command + ": ");
        }
    }
    
    public void printBye() {
        printLine();
        System.out.println(BYE_MESSAGE);
        printLine();
    }

}