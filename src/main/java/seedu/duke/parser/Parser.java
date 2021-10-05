package seedu.duke.parser;

public class Parser {

    /**
     * Parses a {@code userInput} and returns a {@code Command} to be executed.
     *
     * @param taskManager {@code TaskManager} object that contains the tasklist to be executed upon.
     * @param userInput   full raw user input to determine the {@code Command} to be executed.
     * @return {@code Command} to be executed.
     */

    /*
    public Command parseCommand(TaskManager taskManager, String userInput) {
        String[] inputArguments = userInput.split("\\s+", 2);
        String command = inputArguments[0];
        String commandArguments = "";

        if (inputArguments.length == 2) {
            commandArguments = inputArguments[1];
        }

        switch (command) {
        case TERMINATE_CMD:
            return new TerminateCommand();
        case HELP_CMD:
            return new HelpCommand();
        case LIST_CMD:
            return new ListCommand(taskManager);
        case ADD_TODO_CMD:
            return new AddToDoCommand(taskManager, commandArguments);
        case ADD_DEADLINE_CMD:
            return new AddDeadlineCommand(taskManager, commandArguments);
        case ADD_EVENT_CMD:
            return new AddEventCommand(taskManager, commandArguments);
        case SET_TASK_DONE_CMD:
            return new SetTaskDoneCommand(taskManager, commandArguments);
        case DELETE_TASK_CMD:
            return new DeleteTaskCommand(taskManager, commandArguments);
        case FIND_TASK_CMD:
            return new FindTaskCommand(taskManager, commandArguments);
        default:
            return new InvalidCommand();
        }
    }*/


}
