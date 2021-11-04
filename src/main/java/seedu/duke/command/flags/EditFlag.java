package seedu.duke.command.flags;

/**
 * Class used as flags to identify values entered by
 * user in {@link seedu.duke.command.EditCommand}. Because
 * {@link seedu.duke.task.Task} <code>Description</code> is usually
 * {@link seedu.duke.command.Command#MAIN_ARGUMENT}, in {@link seedu.duke.command.EditCommand}
 * it is replaced by {@link #DESCRIPTION}.
 */
public class EditFlag {
    public static final String DESCRIPTION = "description";
}
