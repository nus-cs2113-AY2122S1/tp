package seedu.duke;

import java.util.List;

public class CommandResult extends Command{
    public final String feedback;

    /** The list of tasks that was produced by the command */
    public final Task relevantTask;

    public final int size;

    public final List<Task> relevantTasks;

    /**
     * Constructor of CommandResult.
     *
     * @param feedback the command executed message.
     */
    public CommandResult(String feedback) {
        this.feedback = feedback;
        relevantTask = null;
        size = 0;
        relevantTasks = null;
    }

    /**
     * Constructor of CommandResult.
     *
     * @param feedback the command executed message.
     * @param relevantTask the command executed task to display.
     */
    public CommandResult(String feedback, Task relevantTask) {
        this.feedback = feedback;
        this.relevantTask = relevantTask;
        size = 0;
        relevantTasks = null;
    }

    /**
     * Constructor of CommandResult.
     *
     * @param feedback the command executed message.
     * @param relevantTask the command executed task to display.
     * @param totalNumber the total number of the tasks in the list.
     */
    public CommandResult(String feedback, Task relevantTask, int totalNumber) {
        this.feedback = feedback;
        this.relevantTask = relevantTask;
        this.size = totalNumber;
        this.relevantTasks = null;
    }

    /**
     * Constructor of CommandResult.
     *
     * @param feedback the command executed message.
     * @param tasksList the command executed tasks list to display.
     * @param totalNumber the total number of the tasks in the list.
     */
    public CommandResult(String feedback, List<Task> tasksList, int totalNumber) {
        this.feedback = feedback;
        this.relevantTask = null;
        this.size = totalNumber;
        this.relevantTasks = tasksList;
    }

    /**
     * Constructor of CommandResult.
     *
     * @param feedback the command executed message.
     * @param tasksList the command executed tasks list to display.
     */
    public CommandResult(String feedback, List<Task> tasksList) {
        this.feedback = feedback;
        this.relevantTask = null;
        size = 0;
        this.relevantTasks = tasksList;
    }
}
