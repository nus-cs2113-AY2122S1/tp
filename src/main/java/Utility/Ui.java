package Utility;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Ui {
    protected static final String newLogo = "███████ ████████  ██████  ███    ██ ██   ██ ███████     ██   ██ ██████  \n" +
            "██         ██    ██    ██ ████   ██ ██  ██  ██           ██ ██  ██   ██ \n" +
            "███████    ██    ██    ██ ██ ██  ██ █████   ███████       ███   ██   ██ \n" +
            "     ██    ██    ██    ██ ██  ██ ██ ██  ██       ██      ██ ██  ██   ██ \n" +
            "███████    ██     ██████  ██   ████ ██   ██ ███████     ██   ██ ██████  ";
    
    protected static final String endMessage = "██████  ██    ██ ███████        ██  \n" +
            "██   ██  ██  ██  ██          ██  ██ \n" +
            "██████    ████   █████           ██ \n" +
            "██   ██    ██    ██          ██  ██ \n" +
            "██████     ██    ███████        ██ ";

    
    
    protected static final List<String> commands = Arrays.asList("help", "add_ex", "del_ex",
            "list_ex", "total_ex", "add_in", "del_in", "list_in", "total_in");
    
    public static String readCommand() {
        Scanner in = new Scanner(System.in);
        return in.nextLine().trim();
    }

    public static void printWelcome() {
        printLine();
        System.out.println(newLogo);
        printLine();
        System.out.println("Type something!");
    }

    private static void printLine() {
        for(int i = 0; i <= 100; i++) {
            System.out.print("-");
        }
        System.out.println(" ");
    }
    
    /*
    
        Idea is to use polymorphism to mix a list of income/expense, and print out using instanceof Income/expense
        for listExpense and listIncome respectively.
        
        Likewise for printTotalIncome/Expense it uses instance of to filter out each specified class and sum up.
        
        For print help make a custom string array to print out "command: PURPOSE" for all possible commands.
    
    */
    
    
    public static void listExpense(ArrayList<Object> entries) {
//        int i = 1;
//        for(Object Entry:entries) {
//            if(Entry instanceof Expense) {
//                System.out.println(i);
//                System.out.println(": ");
//                System.out.println(Entry);
//                i++;
//            }
//        }  
    }
    
    public static void listIncome(ArrayList<Object> entries) {
//        int i = 1;
//        for(Object Entry:entries) {
//            if(Entry instanceof Income) {
//                System.out.println(i);
//                System.out.println(": ");
//                System.out.println(Entry);
//                i++;
//            }
//        }
    }
    
    public static void printTotalExpense(ArrayList<Object> entries) {
//        int totalExpense = 0;
//        for(Object Entry:entries) {
//            if(Entry instanceof Expense) {
//                totalExpense += Expense.value();
//            }
//        }
//        System.out.printf("Your total expense is: %d\n",totalExpense);
    }
    
    public static void printTotalIncome(ArrayList<Object> entries) {
//        int totalIncome = 0;
//        for(Object Entry:entries) {
//            if(Entry instanceof Income) {
//                totalIncome += Income.value();
//            }
//        }
//        System.out.printf("Your total income is: %d\n",totalIncome);
    }
    
    public static void printExpenseAdded() {
        System.out.println("Your most recent spending: ");
    }
    
    public static void printExpenseDeleted() {
        System.out.println(".... has been removed");
    }
    
    public static void printIncomeAdded() {
        System.out.println("Your most recent earning: ");
    }
    
    public static void printIncomeDeleted() {
        System.out.println(".... has been removed");
    }
    
    public static void printHelp() {
        for(String command:commands) {
            System.out.println(command + ": ");
        }
    }
    
    public static void printBye() {
        printLine();
        System.out.println(endMessage);
        printLine();
    }

}