package seedu.duke.command;

import seedu.duke.task.TaskManager;

import java.util.Map;

public class ListCommand extends Command {
    public ListCommand(TaskManager taskManager, Map<String, String> commandOptions) {
    }

    @Override
    public CommandResult executeCommand() throws Exception {
        return null;
    }
}
