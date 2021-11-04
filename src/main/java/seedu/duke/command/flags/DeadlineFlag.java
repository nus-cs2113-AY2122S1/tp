package seedu.duke.command.flags;

/**
 * Class used as flags to identify values entered by
 * user in {@link seedu.duke.command.addtask.DeadlineCommand}
 * for Deadline creation in {@link seedu.duke.task.factory.DeadlineFactory}.
 * Also inherits flags from {@link seedu.duke.command.flags.TaskFlag}.
 */
public class DeadlineFlag extends TaskFlag {
    public static final String DUE_DATE = "due";

    public static final String[] REQUIRED_FLAGS = {DESCRIPTION, DUE_DATE};
}
