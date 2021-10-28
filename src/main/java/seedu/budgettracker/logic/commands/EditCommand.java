package seedu.budgettracker.logic.commands;

public abstract class EditCommand extends Command {
    public static final String COMMAND_WORD = "edit";

    public abstract void execute(boolean isLoadingStorage);
}
