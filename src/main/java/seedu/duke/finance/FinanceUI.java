package seedu.duke.finance;

import seedu.duke.main.MainUI;

public class FinanceUI {
    public static void printAddFinanceMessage(Finance newAccount) {
        MainUI.printSingleLine();
        System.out.println(" Got it. This account is added:");
        System.out.println("   Finance Date: " + newAccount.getDate());
        System.out.println("   Account: " + newAccount.getAccount());
        MainUI.printSingleLine();
    }

    public static void printInvalidCommandSyntaxMessage() {
        MainUI.printSingleLine();
        System.out.println(" Invalid command syntax!");
        MainUI.printSingleLine();
    }

    public static void printInvalidIndexMessage() {
        MainUI.printSingleLine();
        System.out.println(" That account does not exist!");
        MainUI.printSingleLine();
    }

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
    public static void printEmptyListMessage() {
        MainUI.printSingleLine();
        System.out.println(" No accounts found.");
        MainUI.printSingleLine();
    }

    public static void printTotalAccount(double totalAccount) {
        MainUI.printSingleLine();
        System.out.println(" Here is the total account you have: $" + totalAccount);
        MainUI.printSingleLine();
    }

    public static void printEditFinanceMessage(Finance editedAccount) {
        MainUI.printSingleLine();
        System.out.println(" Got it. This account is edited:");
        System.out.println("   Finance Date: " + editedAccount.getDate());
        System.out.println("   Account: " + editedAccount.getAccount());
        MainUI.printSingleLine();
    }
}
