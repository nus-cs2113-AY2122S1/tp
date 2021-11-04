package seedu.duke.command.flags;

/**
 * Class used as flags to identify values entered by
 * user in {@link seedu.duke.command.addtask.EventCommand}
 * for Event creation in {@link seedu.duke.task.factory.EventFactory}.
 * Also inherits flags from {@link seedu.duke.command.flags.TaskFlag}.
 */
public class EventFlag extends TaskFlag {
    public static final String START_DATE = "start";
    public static final String END_DATE = "end";

    public static final String[] REQUIRED_FLAGS = {DESCRIPTION, START_DATE, END_DATE};
}
