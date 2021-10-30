package seedu.budgettracker.logic.commands;

import seedu.budgettracker.ui.TextUi;

public class AddBudgetCommand extends AddCommand {

    public static final String MESSAGE_USAGE = ("Adds a budget record.\n"
            + "Parameters: -b a/AMOUNT m/MONTH");

    private final double amount;
    private final int month;

    public AddBudgetCommand(double amount, int month) {
        if (amount < 0.00) {
            this.amount = 0.00;
        } else {
            this.amount = amount;
        }
        this.month = month;
    }

    public void execute(boolean isLoadingStorage) {
        allRecordList.addBudget(amount, month, isLoadingStorage);
        TextUi.showBudgetAddedMessage(amount, isLoadingStorage);
    }
}
