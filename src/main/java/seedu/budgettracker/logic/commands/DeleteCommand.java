package seedu.budgettracker.logic.commands;

/**
 * Command that deals with deleting Records to the RecordList.
 * Expected to be extended by DeleteBudget and DeleteExpenditure.
 */
public abstract class DeleteCommand extends Command {
    public static final String COMMAND_WORD = "delete";

    public static final String MESSAGE_USAGE = (DeleteSingleExpenditureCommand.MESSAGE_USAGE
            + System.lineSeparator()
            + DeleteBudgetCommand.MESSAGE_USAGE);

    public abstract void execute(boolean isLoadingStorage);
}
