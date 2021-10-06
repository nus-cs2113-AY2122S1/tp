package seedu.duke.command;

import seedu.duke.task.TaskManager;

import java.util.Map;

/**
 * Represents a command.
 */
public abstract class Command {

    public TaskManager taskManager;
    public Map<String, String> commandArguments;

    public Command() {

    }

    public Command(TaskManager taskManager) {
        this.taskManager = taskManager;
    }

    public Command(TaskManager taskManager, Map<String, String> commandArguments) {
        this.taskManager = taskManager;
        this.commandArguments = commandArguments;
    }

    public abstract CommandResult executeCommand() throws Exception;

}
