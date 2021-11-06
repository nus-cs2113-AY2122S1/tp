package seedu.duke.finance;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;

/**
 * Contains all Finance related methods.
 * Deals with the user's Finance related input commands.
 */
public class FinanceParser {

    /**
     * Adds a new account to the Finance list.
     *
     * @param command User's command in ArrayList format.
     * @param finances User's finance list.
     */
    public void addFinance(String[] command, FinanceList finances) {
        try {
            Finance newAccount = new Finance(LocalDate.parse(command[1]), command[2]);

            finances.financeList.add(newAccount);
            finances.totalAccount += newAccount.getAccount();

            FinanceUI.printAddFinanceMessage(newAccount);

        } catch (ArrayIndexOutOfBoundsException e) {
            seedu.duke.finance.FinanceUI.printInvalidCommandSyntaxMessage();
        } catch (DateTimeParseException e) {
            FinanceUI.printInvalidCommandSyntaxMessage();
        }
    }

    /**
     * Adds a new account to the finance list without printing any messages.
     *
     * @param finances User's finance list.
     * @param account Account to be added.
     */
    public void loadFinanceFromStorage(FinanceList finances, Finance account) {
        finances.financeList.add(account);
        finances.totalAccount += account.getAccount();
    }

    /**
     * Removes an existing account from the menu.
     *
     * @param command User's command in ArrayList format.
     * @param finances User's finance list.
     */
    public void deleteFinance(String[] command, FinanceList finances) {
        try {
            int deletedFinanceIndex = Integer.parseInt(command[1]) - 1;

            Finance deletedAccount = finances.financeList.get(deletedFinanceIndex);
            finances.financeList.remove(deletedFinanceIndex);
            finances.totalAccount -= deletedAccount.getAccount();

            FinanceUI.printRemoveFinanceMessage(deletedAccount);

        } catch (ArrayIndexOutOfBoundsException e) {
            FinanceUI.printInvalidCommandSyntaxMessage();
        } catch (IndexOutOfBoundsException e) {
            FinanceUI.printInvalidIndexMessage();
        } catch (NumberFormatException e) {
            FinanceUI.printInvalidCommandSyntaxMessage();
        }
    }

    /**
     * Prints a list of all the accounts in the finance list.
     *
     * @param finances Finance list to be printed.
     */
    public void listFinance(FinanceList finances) {
        if (finances.financeList.size() < 1) {
            FinanceUI.printEmptyListMessage();
            return;
        }
        assert finances.financeList.size() > 0 : "Finance list should not be empty";

        FinanceUI.printFinanceListMessage(finances);
    }

    /**
     * Prints the total account .
     *
     * @param finances Finance list to be computed to get the total account.
     */
    public void showFinance(FinanceList finances) {
        FinanceUI.printTotalAccount(finances.totalAccount);
    }

    /**
     * Edits the number of an existing account in the finance list.
     *
     * @param command User's command in ArrayList format.
     * @param finances User's finance list.
     */
    public void editFinance(String[] command, FinanceList finances) {
        try {
            int editedFinanceIndex = Integer.parseInt(command[1]) - 1;
            Finance editedAccount = finances.financeList.get(editedFinanceIndex);
            finances.totalAccount -= editedAccount.getAccount();
            editedAccount.setAccount(command[2]);
            finances.totalAccount += editedAccount.getAccount();
            FinanceUI.printEditFinanceMessage(editedAccount);
        } catch (ArrayIndexOutOfBoundsException e) {
            FinanceUI.printInvalidCommandSyntaxMessage();
        } catch (IndexOutOfBoundsException e) {
            FinanceUI.printInvalidIndexMessage();
        } catch (NumberFormatException e) {
            FinanceUI.printInvalidCommandSyntaxMessage();
        }
    }
}
