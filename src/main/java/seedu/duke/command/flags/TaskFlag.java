package seedu.duke.command.flags;

import java.util.Arrays;
import java.util.List;
import seedu.duke.command.Command;

public abstract class TaskFlag {
    public static final String DESCRIPTION = Command.MAIN_ARGUMENT;
    public static final String RECURRENCE = "recur";
    public static final String PRIORITY = "priority";

    public static final String EDIT_DESCRIPTION = "description";

    public List<String> getAllEditFlags() {
        return Arrays.asList(EDIT_DESCRIPTION, RECURRENCE, PRIORITY);
    }
}
