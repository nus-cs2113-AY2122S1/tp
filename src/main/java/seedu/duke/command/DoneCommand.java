package seedu.duke.command;

public class DoneCommand extends Command {
    int taskIndex;

    public DoneCommand(int taskIndex) {
        this.taskIndex = taskIndex;
    }
}
