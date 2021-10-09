package seedu.duke.command;

import seedu.duke.exception.EmptyTasklistException;
import seedu.duke.task.TaskManager;

import java.util.Map;

public class ListCommand extends Command {

    public ListCommand(TaskManager taskManager, Map<String, String> commandOptions) {
        super(taskManager, commandOptions);
    }

    @Override
    public CommandResult executeCommand() throws Exception {
        String message = "";

        try {
            message = taskManager.listTasklist();
        } catch (EmptyTasklistException e) {
            message = e.toString();
        }

        return new CommandResult(taskManager, message, false, false);
    }
}
