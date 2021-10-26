package seedu.duke.command;

import seedu.duke.exception.EmptySortCriteriaException;
import seedu.duke.exception.EmptyTasklistException;
import seedu.duke.task.TaskManager;

import java.util.HashMap;
import java.util.Map;

//@@author APZH
public class SortCommand extends Command {

    private static final CommandEnum COMMAND = CommandEnum.SORT;

    public SortCommand(Map<String, String> commandArguments) {
        super(COMMAND, commandArguments);
    }

    //@@author APZH
    @Override
    public CommandResult executeCommand() throws Exception {
        String message = "";

        try {
            message = TaskManager.sortTasklist(commandArguments);
        } catch (EmptyTasklistException ete) {
            message = ete.toString();
        } catch (EmptySortCriteriaException esce) {
            message = esce.toString();
        }

        return new CommandResult(message, true, false);
    }

}
