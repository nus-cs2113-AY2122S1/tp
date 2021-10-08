package seedu.duke.commands;

/**
 * Command that deals with adding Records to the RecordList.
 * Expected to be extended by AddBudget and AddExpenditure.
 */
public abstract class AddCommand extends Command {
    public static final String COMMAND_WORD = "add";
}
