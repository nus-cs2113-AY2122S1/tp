package seedu.duke.command;

import seedu.duke.task.TaskManager;

import java.util.Map;

public class AddCommand extends Command {
    public AddCommand(TaskManager taskManager, Map<String, String> commandArguments) {
        super(taskManager, commandArguments);
    }

    @Override
    public CommandResult executeCommand() throws Exception {

        // To help u visualise the commandArguments (Flag -> value)
        String flagsToArguments = "Printing add command arguments (flags) below:\n";

        for (String flag : commandArguments.keySet()) {
            flagsToArguments += flag + " = " + commandArguments.get(flag) + "\n";
        }

        return new CommandResult(flagsToArguments, false, false);
    }
}
