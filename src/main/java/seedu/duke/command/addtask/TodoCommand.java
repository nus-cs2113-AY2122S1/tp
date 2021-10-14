package seedu.duke.command.addtask;

import java.util.HashMap;
import seedu.duke.exception.GetTaskFailedException;
import seedu.duke.task.Task;
import seedu.duke.task.factory.TodoFactory;

public class TodoCommand extends TaskCommand {

    public TodoCommand(HashMap<String, String> commandArguments) {
        super(commandArguments);
    }

    @Override
    Task createTask() throws GetTaskFailedException {
        return TodoFactory.getTodo(commandArguments);
    }

}
