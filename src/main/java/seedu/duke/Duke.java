package seedu.duke;

import java.io.IOException;
import seedu.duke.command.Command;
import seedu.duke.command.CommandEnum;
import seedu.duke.command.CommandResult;
import seedu.duke.local.DataManager;
import seedu.duke.parser.CommandParser;
import seedu.duke.storage.FileCreator;
import seedu.duke.task.reminder.ReminderManager;
import seedu.duke.task.taskmanager.TaskManager;
import seedu.duke.ui.Ui;

public class Duke {

    private final Ui ui;
    private ReminderManager reminderManager;
    private TaskManager taskManager;

    public Duke() {
        ui = new Ui();

        DataManager dataManager = new DataManager();
        taskManager = new TaskManager(dataManager);

        reminderManager = new ReminderManager();
    }

    public CommandResult runCommand(Command userCommand) {
        CommandResult commandResult = null;
        try {
            commandResult = userCommand.executeCommand();
        } catch (Exception e) {
            commandResult = new CommandResult(e.toString(), false, false);
        }
        return commandResult;
    }

    public String checkReminder() {
        return reminderManager.sendReminder(taskManager);
    }

    public void startProgram() {

        ui.printLogo();

        Command userCommand;
        CommandResult commandResult = null;

        do {

            String userInput = ui.readInput();

            userCommand = CommandParser.parseCommand(taskManager, userInput);

            commandResult = runCommand(userCommand);

            ui.printMessage(commandResult.getMessage());

        } while (commandResult.getIsExited() != true);
    }

    public static void main(String[] args) {
        Duke duke = new Duke();
        duke.startProgram();
    }
}
