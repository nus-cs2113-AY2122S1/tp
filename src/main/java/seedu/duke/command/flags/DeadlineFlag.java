package seedu.duke.command.flags;

import java.util.ArrayList;
import java.util.List;

/**
 * Class used as flags to identify values entered by
 * user in {@link seedu.duke.command.addtask.DeadlineCommand}
 * for Deadline creation in {@link seedu.duke.task.factory.DeadlineFactory}.
 * Also inherits flags from {@link seedu.duke.command.flags.TaskFlag}.
 */
public class DeadlineFlag extends TaskFlag {
    public static final String DUE_DATE = "due";

    public static final String[] REQUIRED_FLAGS = {DESCRIPTION, DUE_DATE};

    @Override
    public List<String> getAllEditFlags() {
        List<String> editFlags = new ArrayList<>();
        editFlags.addAll(super.getAllEditFlags());
        editFlags.add(DUE_DATE);
        return editFlags;
    }
}
