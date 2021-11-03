package seedu.duke.command;

import seedu.duke.command.addtask.DeadlineCommand;
import seedu.duke.command.addtask.EventCommand;
import seedu.duke.command.addtask.ModuleCommand;
import seedu.duke.command.addtask.TodoCommand;

//@@author SeanRobertDH
public class HelpCommand extends Command {

    private static final String SEPARATOR = "......................................................................\n";
    private static final String HEADER = "[!] Please kindly refer below for the list of commands available:\n"
            + SEPARATOR;
    private static final String NEW_LINE = System.lineSeparator();
    private static final String ADDITIONAL_INFORMATION = SEPARATOR
        + "* <--flag argument> specifies a required argument\n"
        + "* [--flag argument] specifies an optional argument\n"
        + SEPARATOR;

    private static final Command[] COMMANDS_TO_LIST = {
        new ByeCommand(),
        new DeadlineCommand(null, null),
        new EventCommand(null, null),
        new TodoCommand(null, null),
        new ModuleCommand(null, null),
        new BrowseCommand(null, null),
        new ListCommand(null, null),
        new SortCommand(null, null),
        new DeleteCommand(null, null),
        new EditCommand(null, null),
        new ReminderCommand(null, null)
    };

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
