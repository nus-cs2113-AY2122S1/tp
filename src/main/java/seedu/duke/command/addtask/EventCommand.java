package seedu.duke.command.addtask;

import java.util.HashMap;
import seedu.duke.exception.GetTaskFailedException;
import seedu.duke.task.Task;
import seedu.duke.task.factory.EventFactory;

public class EventCommand extends TaskCommand {
    public EventCommand(HashMap<String, String> commandArguments) {
        super(commandArguments);
    }

    @Override
    Task createTask() throws GetTaskFailedException {
        return EventFactory.getEvent(commandArguments);
    }


}