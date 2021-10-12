package seedu.duke.command;

import java.util.HashMap;
import seedu.duke.exception.EmptyTasklistException;
import seedu.duke.task.TaskManager;

import java.util.Map;

public class ListCommand extends Command {

    public ListCommand(TaskManager taskManager, HashMap<String, String> commandArguments) {
        super(taskManager, commandArguments);
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
