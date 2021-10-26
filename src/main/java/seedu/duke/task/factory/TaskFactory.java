package seedu.duke.task.factory;

import java.util.Map;
import seedu.duke.command.flags.TaskFlag;
import seedu.duke.exception.GetTaskFailedException;
import seedu.duke.exception.InvalidPriorityException;
import seedu.duke.exception.InvalidRecurrenceException;
import seedu.duke.exception.RequiredArgmentNotProvidedException;
import seedu.duke.parser.TaskParser;
import seedu.duke.task.PriorityEnum;
import seedu.duke.task.RecurrenceEnum;
import seedu.duke.task.Task;
import seedu.duke.task.TypeEnum;

//TaskFactory Template
public abstract class TaskFactory {
    private final TypeEnum taskType;
    private final String[] requiredFlags;
    final Map<String, String> flags;

    String description;
    PriorityEnum priorityEnum;
    RecurrenceEnum recurrenceEnum;

    TaskFactory(TypeEnum taskType, String[] requiredFlags, Map<String, String> flags) {
        this.taskType = taskType;
        this.requiredFlags = requiredFlags;
        this.flags = flags;
    }

    //Template Method Pattern
    public Task getTask() throws GetTaskFailedException {
        try {
            checkForRequiredArguments(flags);

            String priority = flags.get(TaskFlag.PRIORITY);
            String recurrence = flags.get(TaskFlag.RECURRENCE);

            description = flags.get(TaskFlag.DESCRIPTION);
            priorityEnum = TaskParser.getPriorityEnum(priority);
            recurrenceEnum = TaskParser.getRecurrenceEnum(recurrence);

            setAdditionalVariables();

            return decideConstructor();

        } catch (RequiredArgmentNotProvidedException ranpe) {
            throw new GetTaskFailedException(ranpe.getMessage());
        } catch (InvalidPriorityException ipe) {
            throw new GetTaskFailedException(ipe.getMessage());
        } catch (InvalidRecurrenceException ire) {
            throw new GetTaskFailedException(ire.getMessage());
        } catch (GetTaskFailedException gtfe) {
            throw new GetTaskFailedException(gtfe.getMessage());
        }
    }

    private void checkForRequiredArguments(Map<String, String> flags) throws RequiredArgmentNotProvidedException {
        for (String requiredArgument : requiredFlags) {
            String requiredFlag = flags.get(requiredArgument);
            if (requiredFlag == null) {
                throw new RequiredArgmentNotProvidedException(requiredArgument, taskType.toString());
            }
        }
    }

    abstract void setAdditionalVariables() throws GetTaskFailedException;

    abstract Task decideConstructor();
}
