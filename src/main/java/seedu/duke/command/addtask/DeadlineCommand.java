package seedu.duke.command.addtask;

import java.util.HashMap;
import seedu.duke.exception.GetTaskFailedException;
import seedu.duke.task.Task;
import seedu.duke.task.factory.DeadlineFactory;

public class DeadlineCommand extends TaskCommand {

    public DeadlineCommand(HashMap<String, String> commandArguments) {
        super(commandArguments);
    }

    @Override
    Task createTask() throws GetTaskFailedException {
        return DeadlineFactory.getDeadline(commandArguments);
    }
}
