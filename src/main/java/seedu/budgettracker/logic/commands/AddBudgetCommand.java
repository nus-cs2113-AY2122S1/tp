package seedu.budgettracker.logic.commands;

import seedu.budgettracker.data.records.exceptions.DuplicateBudgetException;
import seedu.budgettracker.logic.commands.exceptions.CommandException;
import seedu.budgettracker.ui.TextUi;

import static seedu.budgettracker.common.Messages.MESSAGE_AMOUNT_EXCEEDED;
import static seedu.budgettracker.common.Messages.MESSAGE_INVALID_AMOUNT;

/**
 * This class is responsible for the adding of budget.
 */
public class AddBudgetCommand extends AddCommand {

    public static final boolean IS_NOT_LOADING_STORAGE = false;

    public static final String MESSAGE_USAGE = ("Adds a budget record.\n"
            + "Parameters: -b a/AMOUNT m/[MONTH]\n"
            + "Note:\n"
            + " * If MONTH is not specified, current system month will be the default value.\n");


    private final double amount;
    private final int month;

    /**
     * Constructor of AddBudgetCommand and it assigns the value to the attributes
     * of this class - amount & month.
     *
     * @param amount Budget limit for selected month.
     * @param month The month which the budget limit is imposed on.
     */
    public AddBudgetCommand(double amount, int month) {
        this.amount = amount;
        this.month = month;
    }

    /**
     * Adds a budget to the record list during runtime.
     *
     * @throws CommandException to catch invalid amounts entered by user.
     */
    public void execute() throws CommandException {
        if (amount <= 0) {
            throw new CommandException(MESSAGE_INVALID_AMOUNT);
        }
        if (amount > 1000000000) {
            throw new CommandException(MESSAGE_AMOUNT_EXCEEDED);
        }
        try {
            allRecordList.addBudget(amount, month, IS_NOT_LOADING_STORAGE);
            TextUi.showBudgetAddedMessage(amount, month);
        } catch (DuplicateBudgetException e) {
            System.out.println(e.getMessage());
            TextUi.printDivider();
        }
    }

    public void execute(boolean isLoadingStorage) {
        if (amount == 0) {
            return;
        }
        try {
            allRecordList.addBudget(amount, month, isLoadingStorage);
        } catch (DuplicateBudgetException e) {
            System.out.println(e.getMessage());
        }
    }
}
