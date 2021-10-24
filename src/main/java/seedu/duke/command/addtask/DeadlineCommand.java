package seedu.duke.command.addtask;

import java.util.HashMap;
import java.util.Map;

import seedu.duke.command.CommandEnum;
import seedu.duke.exception.GetTaskFailedException;
import seedu.duke.task.Task;
import seedu.duke.task.factory.DeadlineFactory;

public class DeadlineCommand extends TaskCommand {
    private static final CommandEnum COMMAND = CommandEnum.DEADLINE;

    public DeadlineCommand(Map<String, String> commandArguments) {
        super(COMMAND, commandArguments);
    }

    @Override
    Task createTask() throws GetTaskFailedException {
        return DeadlineFactory.getDeadline(commandArguments);
    }
}
