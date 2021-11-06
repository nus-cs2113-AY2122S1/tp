package seedu.budgettracker.logic.commands;

/**
 * Command that deals with adding Records to the RecordList.
 * Expected to be extended by AddBudget and AddExpenditure.
 */
public abstract class AddCommand extends Command {
    public static final String COMMAND_WORD = "add";

    public static final String MESSAGE_USAGE = (AddExpenditureCommand.MESSAGE_USAGE
            + System.lineSeparator()
            + AddBudgetCommand.MESSAGE_USAGE
            + System.lineSeparator()
            + AddLoanCommand.MESSAGE_USAGE);

    public abstract void execute(boolean isLoadingStorage);
}
