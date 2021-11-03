package seedu.duke;

import seedu.duke.command.Command;
import seedu.duke.command.CommandResult;
import seedu.duke.storage.DataManager;
import seedu.duke.parser.CommandParser;
import seedu.duke.storage.FileCreator;
import seedu.duke.task.taskmanager.TaskManager;
import seedu.duke.ui.Ui;

public class Duke {

    private Ui ui;
    private TaskManager taskManager;

    public Duke() {
        ui = new Ui();

        FileCreator fileCreator = new FileCreator();
        DataManager dataManager = new DataManager(fileCreator);
        taskManager = new TaskManager(dataManager);
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

    public void startProgram() {

        ui.printLogo();

        Command userCommand;
        CommandResult commandResult;

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
