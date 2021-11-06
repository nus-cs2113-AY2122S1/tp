package seedu.budgettracker.logic.commands;

import seedu.budgettracker.data.records.exceptions.DuplicateBudgetException;
import seedu.budgettracker.logic.commands.exceptions.CommandException;
import seedu.budgettracker.ui.TextUi;

import static seedu.budgettracker.common.Messages.MESSAGE_INVALID_BUDGET_AMOUNT;

public class AddBudgetCommand extends AddCommand {

    public static final boolean IS_NOT_LOADING_STORAGE = false;

    public static final String MESSAGE_USAGE = ("Adds a budget record.\n"
            + "Parameters: -b a/AMOUNT m/[MONTH]\n"
            + "Note:\n"
            + " * If MONTH is not specified, current system month will be the default value.\n");


    private final double amount;
    private final int month;

    public AddBudgetCommand(double amount, int month) {
        this.amount = amount;
        this.month = month;
    }

    public void execute() throws CommandException {
        if (amount < 0) {
            throw new CommandException(MESSAGE_INVALID_BUDGET_AMOUNT);
        }
        try {
            allRecordList.addBudget(amount, month, IS_NOT_LOADING_STORAGE);
        } catch (DuplicateBudgetException e) {
            System.out.println(e.getMessage());
        }
        TextUi.showBudgetAddedMessage(amount, month);
    }

    public void execute(boolean isLoadingStorage) {
        try {
            allRecordList.addBudget(amount, month, isLoadingStorage);
        } catch (DuplicateBudgetException e) {
            System.out.println(e.getMessage());
        }
    }
}
