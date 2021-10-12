package seedu.duke.command;

import java.util.HashMap;
import seedu.duke.task.TaskManager;

import java.util.Map;

/**
 * Represents a command.
 */
public abstract class Command {

    public TaskManager taskManager;
    public HashMap<String, String> commandArguments;

    public Command() {

    }

    public Command(TaskManager taskManager) {
        this.taskManager = taskManager;
    }

    public Command(TaskManager taskManager, HashMap<String, String> commandArguments) {
        this.taskManager = taskManager;
        this.commandArguments = commandArguments;
    }

    public abstract CommandResult executeCommand() throws Exception;

}
