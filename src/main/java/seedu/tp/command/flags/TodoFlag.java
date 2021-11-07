package seedu.tp.command.flags;

import java.util.ArrayList;
import java.util.List;

/**
 * Class used as flags to identify values entered by
 * user in {@link seedu.tp.command.addtask.TodoCommand}
 * for Todo creation in {@link seedu.tp.task.factory.TodoFactory}.
 * Also inherits flags from {@link seedu.tp.command.flags.TaskFlag}.
 */
public class TodoFlag extends TaskFlag {
    public static final String DO_ON_DATE = "doOn";

    public static final String[] REQUIRED_FLAGS = {DESCRIPTION};

    @Override
    public List<String> getAllEditFlags() {
        List<String> editFlags = new ArrayList<>();
        editFlags.addAll(super.getAllEditFlags());
        editFlags.add(DO_ON_DATE);
        return editFlags;
    }
}
