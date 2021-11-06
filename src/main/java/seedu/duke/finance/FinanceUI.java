package seedu.duke.finance;

import seedu.duke.main.MainUI;


/**
 * Print messages to interact with the users.
 */
public class FinanceUI {

    /**
     * Print a message to the user whenever an account is added.
     *
     * @param newAccount Finance added.
     */
    public static void printAddFinanceMessage(Finance newAccount) {
        MainUI.printSingleLine();
        System.out.println(" Got it. This account is added:");
        System.out.println("   Finance Date: " + newAccount.getDate());
        System.out.println("   Account: " + newAccount.getAccount());
        MainUI.printSingleLine();
    }

    /**
     * Prints a message to the user whenever there is an invalid attempt of wrong syntax of command.
     */
    public static void printInvalidCommandSyntaxMessage() {
        MainUI.printSingleLine();
        System.out.println(" Invalid command syntax!");
        MainUI.printSingleLine();
    }

    /**
     * Prints a message to the user whenever there is an invalid attempt of invalid index to delete an account.
     */
    public static void printInvalidIndexMessage() {
        MainUI.printSingleLine();
        System.out.println(" That account does not exist!");
        MainUI.printSingleLine();
    }

    /**
     * Prints a message to the user whenever an account is removed.
     *
     * @param deletedAccount Finance removed.
     */
    public static void printRemoveFinanceMessage(Finance deletedAccount) {
        MainUI.printSingleLine();
        System.out.println(" Got it. This account was deleted:");
        System.out.println("   " + deletedAccount.getDate() + "  $" + deletedAccount.getAccount());
        MainUI.printSingleLine();
    }

    public static void printFinanceListMessage(FinanceList finances) {
        MainUI.printSingleLine();
        System.out.println(" Here are the accounts in your list:");
        for (int i = 0; i < finances.financeList.size(); i++) {
            System.out.println("   " + (i + 1) + ". " + finances.financeList.get(i));
        }
        MainUI.printSingleLine();
    }

    /**
     * Print a message to the user whenever there is an attempt to print an empty finance list.
     */
    public static void printEmptyListMessage() {
        MainUI.printSingleLine();
        System.out.println(" No accounts found.");
        MainUI.printSingleLine();
    }

    /**
     * Print a message to the user to show the total account of the finance list.
     */
    public static void printTotalAccount(double totalAccount) {
        MainUI.printSingleLine();
        System.out.println(" Here is the total account you have: $" + totalAccount);
        MainUI.printSingleLine();
    }

    /**
     * Print a message to the user whenever an account is edited.
     *
     * @param editedAccount the account is edited
     */
    public static void printEditFinanceMessage(Finance editedAccount) {
        MainUI.printSingleLine();
        System.out.println(" Got it. This account is edited:");
        System.out.println("   Finance Date: " + editedAccount.getDate());
        System.out.println("   Account: " + editedAccount.getAccount());
        MainUI.printSingleLine();
    }
}
