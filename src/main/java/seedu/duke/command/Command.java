package seedu.duke.command;

import seedu.duke.task.TaskManager;

/**
 * Represents a command.
 */
public abstract class Command {

    /**
     * {@code taskManager} represents and contains the tasklist that the command will be executing upon.
     */
    public TaskManager taskManager;
    /**
     * {@code commandArguments} represents the arguments for the command.
     */
    public String commandArguments;

    /**
     * Empty default constructor for {@code Command} subclasses that do not take in any parameters.
     * Example of such subclasses would include: InvalidCommand, HelpCommand, TerminateCommand.
     */
    public Command() {

    }

    /**
     * Constructor for commands that only require {@code TaskManager} object as a parameter.
     * Example of such subclasses would include: ListCommand.
     */
    public Command(TaskManager taskManager) {
        this.taskManager = taskManager;
    }

    /**
     * Constructor for commands that requires both {@code TaskManager} object and command arguments as parameters.
     * Example of such subclasses would include: AddToDoCommand, AddDeadlineCommand, AddEventCommand,
     * SetTaskDoneCommand, DeleteTaskCommand, FindTaskCommand.
     */
    public Command(TaskManager taskManager, String commandArguments) {
        this.taskManager = taskManager;
        this.commandArguments = commandArguments;
    }

    /**
     * Executes the command and returns the result.
     *
     * @return the command result of the execution.
     * @throws Exception if the command is executed by the abstract {@Command} parent class, which is not allowed.
     */
    public abstract CommandResult executeCommand() throws Exception;

}
