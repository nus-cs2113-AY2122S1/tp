package seedu.duke.command;

import java.util.HashMap;
import seedu.duke.exception.EmptyTasklistException;
import seedu.duke.task.TaskManager;

import java.util.Map;

public class ListCommand extends Command {
    private static final CommandEnum COMMAND = CommandEnum.LIST;

    public ListCommand(HashMap<String, String> commandArguments) {
        super(COMMAND, commandArguments);
    }

    @Override
    public CommandResult executeCommand() throws Exception {
        String message = "";

        try {
            message = TaskManager.listTasklist();
        } catch (EmptyTasklistException e) {
            message = e.toString();
        }

        return new CommandResult(message, false, false);
    }
}
