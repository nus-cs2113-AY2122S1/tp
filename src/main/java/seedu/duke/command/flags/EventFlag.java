package seedu.duke.command.flags;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

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

    @Override
    public List<String> getAllEditFlags() {
        List<String> editFlags = new ArrayList<>();
        editFlags.addAll(super.getAllEditFlags());
        editFlags.addAll(Arrays.asList(START_DATE, END_DATE));
        return editFlags;
    }
}
