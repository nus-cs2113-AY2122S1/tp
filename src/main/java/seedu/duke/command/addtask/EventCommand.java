package seedu.duke.command.addtask;

import java.util.Map;

import seedu.duke.command.CommandEnum;
import seedu.duke.exception.GetTaskFailedException;
import seedu.duke.task.Task;
import seedu.duke.task.factory.EventFactory;

//@@author SeanRobertDH
public class EventCommand extends TaskCommand {
    private static final CommandEnum COMMAND = CommandEnum.EVENT;

    public EventCommand(Map<String, String> commandArguments) {
        super(COMMAND, commandArguments);
    }

    @Override
    Task createTask() throws GetTaskFailedException {
        return EventFactory.getEvent(commandArguments);
    }


}