package seedu.duke.finance;

import seedu.duke.ingredient.IngredientList;
import seedu.duke.ingredient.IngredientUI;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;

public class FinanceParser {

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

    public void loadFinanceFromStorage(FinanceList finances, Finance account) {
        finances.financeList.add(account);
        finances.totalAccount += account.getAccount();
    }

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

    public void listFinance(FinanceList finances) {
        if (finances.financeList.size() < 1) {
            IngredientUI.printEmptyListMessage();
            return;
        }
        assert finances.financeList.size() > 0 : "Finance list should not be empty";

        FinanceUI.printFinanceListMessage(finances);
    }

    public void showFinance(FinanceList finances) {
        FinanceUI.printTotalAccount(finances.totalAccount);
    }

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
