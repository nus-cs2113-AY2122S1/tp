package seedu.duke.command.flags;

import seedu.duke.command.Command;

public abstract class TaskFlag {
    public static final String DESCRIPTION = Command.MAIN_ARGUMENT;
    public static final String RECURRENCE = "recur";
    public static final String PRIORITY = "priority";
}
