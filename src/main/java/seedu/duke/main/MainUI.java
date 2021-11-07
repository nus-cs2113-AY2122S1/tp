//@@author kairoskoh

package seedu.duke.main;

public class MainUI {

    public static void printSingleLine() {
        System.out.println("---------------------------------------------");
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
        System.out.println(" Hello! Welcome to Restaurant Buddy :D");
        System.out.println(" How may I help you today?");
        printSingleLine();
    }

    public static void printGoodbyeMessage() {
        printSingleLine();
        System.out.println(" Thank you. Goodbye!");
        printSingleLine();
    }

    public static void printStorageSaved() {
        printSingleLine();
        System.out.println(" Storage saved successfully.");
        System.out.println(" See you again!");
        printSingleLine();
    }

    public static void printWrongCommandMessage() {
        printSingleLine();
        System.out.println(" I do not recognise the input.");
        System.out.println(" Please try again!");
        printSingleLine();
    }

    public static void printHelpMessage() {
        printSingleLine();
        System.out.println("Hello! You seem like you need a hand to get started.");
        System.out.println("To interact with Restaurant Buddy, type in any of the following commands and press Enter!");
        System.out.println("The words that are CAPITALISED are the parameters that you can change.");
        printSingleLine();
        System.out.println("- Employee Commands:");
        System.out.println("add-employee/EMPLOYEE_NAME/PHONE_NUMBER/EMPLOYMENT_STATUS/SALARY");
        System.out.println("remove-employee/EMPLOYEE_INDEX");
        System.out.println("list-employee");
        printSingleLine();
        System.out.println("- Menu Commands:");
        System.out.println("add-dish/DISH_NAME/PRICE");
        System.out.println("remove-dish/DISH_INDEX");
        System.out.println("edit-dish/DISH_INDEX/NEW_PRICE");
        System.out.println("discount-dish/DISH_INDEX/DISCOUNT(%)");
        System.out.println("list-dish");
        printSingleLine();
        System.out.println("- Ingredient Commands:");
        System.out.println("add-ingredient/INGREDIENT_NAME/QUANTITY");
        System.out.println("remove-ingredient/INGREDIENT_INDEX");
        System.out.println("list-ingredient");
        printSingleLine();
        System.out.println("- Finance Commands:");
        System.out.println("add-finance/DATE/ACCOUNT");
        System.out.println("remove-finance/FINANCE_INDEX");
        System.out.println("edit-finance/FINANCE_INDEX/NEW_ACCOUNT");
        System.out.println("show-finance");
        System.out.println("list-finance");
        printSingleLine();
        System.out.println("To exit the program safely, please type in the command:");
        System.out.println("bye");
        printSingleLine();
        System.out.println("If you require more help, please refer to the User Guide on Github.");
        System.out.println("Let's get started at once! Feel free to try out the commands :D");
        printSingleLine();
    }
}
