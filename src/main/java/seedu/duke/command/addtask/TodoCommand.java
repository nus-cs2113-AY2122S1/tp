package seedu.duke.command.addtask;

import java.util.Map;

import seedu.duke.command.CommandEnum;
import seedu.duke.exception.GetTaskFailedException;
import seedu.duke.task.Task;
import seedu.duke.task.factory.TodoFactory;

//@@author SeanRobertDH
public class TodoCommand extends TaskCommand {
    private static final CommandEnum COMMAND = CommandEnum.TODO;

    public TodoCommand(Map<String, String> commandArguments) {
        super(COMMAND, commandArguments);
    }

    @Override
    Task createTask() throws GetTaskFailedException {
        return TodoFactory.getTodo(commandArguments);
    }

}
