package seedu.duke.command;

import seedu.duke.command.addtask.DeadlineCommand;
import seedu.duke.command.addtask.EventCommand;
import seedu.duke.command.addtask.TodoCommand;

//@@author SeanRobertDH
public class HelpCommand extends Command {

    private static final String HEADER = "List of commands: \n";
    private static final char NEW_LINE = '\n';
    private static final String ADDITIONAL_INFORMATION = "...\n"
        + "<--flag argument> specifies a required argument\n"
        + "[--flag argument] specifies an optional argument";

    private static final Command[] COMMANDS_TO_LIST =
        {new ByeCommand(), new DeleteCommand(null, null), new ListCommand(null, null), new SortCommand(null, null),
            new DeadlineCommand(null, null), new EventCommand(null, null), new TodoCommand(null, null),
        new EditCommand(null, null)};

    @Override
    public CommandResult executeCommand() throws Exception {
        String message = HEADER;
        for (Command command : COMMANDS_TO_LIST) {
            message += command.getUsage() + NEW_LINE;
        }
        message += ADDITIONAL_INFORMATION;
        return new CommandResult(message, false, false);
    }

    @Override
    protected String getUsage() {
        return null;
    }
}
