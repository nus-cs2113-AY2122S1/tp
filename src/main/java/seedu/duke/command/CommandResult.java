package seedu.duke.command;

import seedu.duke.task.TaskManager;

/**
 * Represents the result of a command execution.
 */
public class CommandResult {

    private TaskManager taskManager;
    private boolean isModified;
    private boolean isExited;
    private String message;

    public CommandResult(String message, boolean isModified, boolean isExited) {
        this.isModified = isModified;
        this.isExited = isExited;
        this.message = message;
    }

    public CommandResult(TaskManager taskManager, String message, boolean isModified, boolean isExited) {
        this.taskManager = taskManager;
        this.isModified = isModified;
        this.isExited = isExited;
        this.message = message;
    }

    public TaskManager getTaskManager() {
        return this.taskManager;
    }

    public String getMessage() {
        return this.message;
    }

    public boolean getIsModified() {
        return this.isModified;
    }

    public boolean getIsExited() {
        return this.isExited;
    }

}
