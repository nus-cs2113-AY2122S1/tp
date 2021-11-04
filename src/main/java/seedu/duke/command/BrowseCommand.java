package seedu.duke.command;

import seedu.duke.exception.BrowseFailException;
import seedu.duke.exception.InvalidTaskIndexException;
import seedu.duke.parser.CommandParser;
import seedu.duke.task.Task;
import seedu.duke.task.taskmanager.TaskManager;
import seedu.duke.task.type.Lesson;
import seedu.duke.utility.ExternalHelper;

import java.net.URI;
import java.util.Map;

public class BrowseCommand extends Command {
    private static final String USAGE = "-> Launching a link tagged to a task: browse <index>";

    public BrowseCommand(TaskManager taskManager, Map<String, String> commandArguments) {
        super(taskManager, commandArguments);
    }

    @Override
    protected String getUsage() {
        return USAGE;
    }

    @Override
    public CommandResult executeCommand() throws Exception {
        String message;
        try {
            String mainArgument = getMainArgument();
            if (mainArgument == null) {
                throw new NullPointerException();
            }
            int index = CommandParser.parseTaskIndex(mainArgument) - 1;
            assert taskManager != null;
            Task task = taskManager.getFilteredTask(index);
            URI link = ((Lesson) task).getLink();
            ExternalHelper.browseUri(link);
            message = link.toString();
        } catch (NullPointerException npe) {
            message = USAGE;
        } catch (ClassCastException cce) {
            message = cce.getMessage();
        } catch (InvalidTaskIndexException itie) {
            message = itie.getMessage();
        } catch (BrowseFailException bfe) {
            message = bfe.getMessage();
        }
        return new CommandResult(message, false);
    }
}
