package seedu.duke.main;

public class MainUI {

    public static void printWelcomeMessage() {
        printSingleLine();
        System.out.println("Hello! Welcome to Restaurant Buddy :D");
        printSingleLine();
    }

    public static void printGoodbyeMessage() {
        printSingleLine();
        System.out.println("Thank you. Goodbye!");
        printSingleLine();
    }

    public static void printStorageSaved() {
        printSingleLine();
        System.out.println("Storage saved successfully.");
        System.out.println("See you again!");
        printSingleLine();
    }

    public static void printSingleLine() {
        System.out.println("--------------------");
    }

    public static void printWrongCommandMessage() {
        System.out.println("I do not recognise the input.");
        System.out.println("Please try again!");
    }
}
