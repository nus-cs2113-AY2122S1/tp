package seedu.duke.command.flags;

/**
 * Class used as flags to identify values entered by
 * user in {@link seedu.duke.command.addtask.TodoCommand}
 * for Todo creation in {@link seedu.duke.task.factory.TodoFactory}.
 * Also inherits flags from {@link seedu.duke.command.flags.TaskFlag}.
 */
public class TodoFlag extends TaskFlag {
    public static final String DO_ON_DATE = "doOn";

    public static final String[] REQUIRED_FLAGS = {DESCRIPTION};
}
