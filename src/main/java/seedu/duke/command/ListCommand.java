package seedu.duke.command;

import java.util.HashMap;
import java.util.Map;

import seedu.duke.exception.EmptyTasklistException;
import seedu.duke.exception.ListFormatException;
import seedu.duke.exception.MissingFilterArgumentException;
import seedu.duke.task.TaskManager;

//@@author APZH
public class ListCommand extends Command {
    private static final CommandEnum COMMAND = CommandEnum.LIST;

    public ListCommand(Map<String, String> commandArguments) {
        super(COMMAND, commandArguments);
    }

    //@@author APZH
    @Override
    public CommandResult executeCommand() {
        String message = "";

        try {
            message = TaskManager.listTasklist(commandArguments);
        } catch (EmptyTasklistException ete) {
            message = ete.toString();
        } catch (ListFormatException lfe) {
            message = lfe.toString();
        } catch (MissingFilterArgumentException mfae) {
            message = mfae.toString();
        }

        return new CommandResult(message, false, false);
    }
}
