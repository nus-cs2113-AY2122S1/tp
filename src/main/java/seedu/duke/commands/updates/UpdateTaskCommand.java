package seedu.duke.commands.updates;

import seedu.duke.Ui;
import seedu.duke.commands.Command;
import seedu.duke.commands.CommandResult;
import seedu.duke.items.Task;

import java.time.LocalDateTime;

public class UpdateTaskCommand extends Command {
    private static final int INDEX_OF_TITLE = 0;
    private static final int INDEX_OF_DESCRIPTION = 1;

    private final String[] updates;
    private final Task taskToBeUpdated;
    private final LocalDateTime dateTime;

    public UpdateTaskCommand(String[] updates, Task taskToBeUpdated, LocalDateTime dateTime) {
        this.updates = updates;
        this.taskToBeUpdated = taskToBeUpdated;
        this.dateTime = dateTime;
    }

    public CommandResult execute() {
        if (!updates[INDEX_OF_TITLE].equalsIgnoreCase("")) {
            taskToBeUpdated.setTitle(updates[INDEX_OF_TITLE]);
        } else if (dateTime != null) {
            taskToBeUpdated.setDateTime(dateTime);
        } else if (!updates[INDEX_OF_DESCRIPTION].equalsIgnoreCase("")) {
            taskToBeUpdated.setDescription(updates[INDEX_OF_DESCRIPTION]);
        }
        Ui.postUpdateMessage(taskToBeUpdated.getEvent());
        return new CommandResult(Ui.updateExitMessage());
    }


}
