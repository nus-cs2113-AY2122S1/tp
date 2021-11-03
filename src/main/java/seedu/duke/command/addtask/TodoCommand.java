package seedu.duke.command.addtask;

import java.util.Map;

import seedu.duke.command.flags.TodoFlag;
import seedu.duke.parser.DateParser;
import seedu.duke.task.factory.TaskFactory;
import seedu.duke.task.factory.TodoFactory;
import seedu.duke.task.taskmanager.TaskManager;

//@@author SeanRobertDH
public class TodoCommand extends TaskCommand {

    private static String USAGE = "todo <description> [--%s %s]";

    public TodoCommand(TaskManager taskManager, Map<String, String> commandArguments) {
        super(taskManager, commandArguments);
    }

    @Override
    TaskFactory setTaskFactory() {
        return new TodoFactory(commandArguments);
    }

    @Override
    protected String getTaskUsage() {
        return String.format(USAGE, TodoFlag.DO_ON_DATE, DateParser.getDefaultDateFormat());
    }

}
