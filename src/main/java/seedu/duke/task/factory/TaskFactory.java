package seedu.duke.task.factory;

import java.util.HashMap;
import seedu.duke.exception.GetTaskFailedException;
import seedu.duke.log.Log;
import seedu.duke.command.flags.TaskFlag;
import seedu.duke.exception.InvalidTaskTypeException;
import seedu.duke.task.Task;
import seedu.duke.task.TypeEnum;

public class TaskFactory {
    private static String TASK = "Task";

    public static Task getTask(HashMap<String, String> flags) throws GetTaskFailedException {
        try {
            String taskTypeString = flags.get(TaskFlag.TYPE);
            TypeEnum taskType = TypeEnum.getTaskType(taskTypeString);

            switch (taskType) {
            case TODO:
                return TodoFactory.getTodo(flags);
            case EVENT:
                return EventFactory.getEvent(flags);
            case DEADLINE:
                return DeadlineFactory.getDeadline(flags);
            default:
            }
        } catch (InvalidTaskTypeException itte) {
            Log.getLogger(TaskFactory.class).severe(itte.getMessage());
        } catch (GetTaskFailedException gtfe) {
            Log.getLogger(TaskFactory.class).warning(gtfe.getMessage());
        }
        throw new GetTaskFailedException(TASK);
    }
}
