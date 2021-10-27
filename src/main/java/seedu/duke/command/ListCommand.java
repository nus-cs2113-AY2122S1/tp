package seedu.duke.command;

import java.util.Map;

import seedu.duke.exception.EmptyTasklistException;
import seedu.duke.exception.ListFormatException;
import seedu.duke.exception.MissingFilterArgumentException;
import seedu.duke.task.taskmanager.TaskManager;

//@@author APZH
public class ListCommand extends Command {

    private static final String USAGE = "list";

    public ListCommand(TaskManager taskManager, Map<String, String> commandArguments) {
        super(taskManager, commandArguments);
    }

    //@@author APZH
    @Override
    public CommandResult executeCommand() {
        String message = "";

        try {
            if (commandArguments.containsKey(MAIN_ARGUMENT)) {
                message = taskManager.listTaskRecurrence(commandArguments);
            } else {
                message = taskManager.listTasklistWithFilter(commandArguments);
            }
        } catch (EmptyTasklistException ete) {
            message = ete.toString();
        } catch (ListFormatException lfe) {
            message = lfe.toString();
        } catch (MissingFilterArgumentException mfae) {
            message = mfae.toString();
        }

        return new CommandResult(message, false, false);
    }

    @Override
    protected String getUsage() {
        return USAGE;
    }
}
