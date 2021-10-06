package seedu.duke.commands;

public class DeleteCommand extends Command {
    public String[] tasksToDelete;

    public DeleteCommand() {
    }

    public CommandResult execute() {
        return new CommandResult("DeleteCommand");
    }
}
