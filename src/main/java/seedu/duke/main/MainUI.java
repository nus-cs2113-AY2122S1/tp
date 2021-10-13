package seedu.duke.main;

public class MainUI {

    public static void printSingleLine() {
        System.out.println("--------------------");
    }

    public static void printWelcomeMessage() {
        printSingleLine();
        System.out.println(
                " ___        _                          _     \n"
                + "| _ \\___ __| |_ __ _ _  _ _ _ __ _ _ _| |_  \n"
                + "|   / -_|_-<  _/ _` | || | '_/ _` | ' \\  _| \n"
                + "|_|_\\___/__/\\__\\__,_|\\_,_|_| \\__,_|_||_\\__| \n"
                + " ___         _    _                         \n"
                + "| _ )_  _ __| |__| |_  _                    \n"
                + "| _ \\ || / _` / _` | || |                   \n"
                + "|___/\\_,_\\__,_\\__,_|\\_, |                   \n"
                + "                    |__/                    \n");
        System.out.println("Hello! Welcome to Restaurant Buddy :D");
        System.out.println("How may I help you today?");
        printSingleLine();
    }

    public static void printGoodbyeMessage() {
        printSingleLine();
        System.out.println("Thank you. Goodbye!");
        printSingleLine();
    }

    public static void printStorageSaved() {
        System.out.println("Storage saved successfully.");
        System.out.println("See you again!");
    }

    public static void printWrongCommandMessage() {
        printSingleLine();
        System.out.println("I do not recognise the input.");
        System.out.println("Please try again!");
        printSingleLine();
    }

}
