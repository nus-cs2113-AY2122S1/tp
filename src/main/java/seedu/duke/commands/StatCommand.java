package seedu.duke.commands;

import seedu.duke.data.records.Category;

public abstract class StatCommand extends Command {
    public static final String COMMAND_WORD = "stat";

    public abstract void execute(boolean isLoadingStorage);
}
