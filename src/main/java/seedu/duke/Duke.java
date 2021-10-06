package seedu.duke;

import java.util.Scanner;


public class Duke {

    private Storage storage;
    private TaskList tasksList = new TaskList();
    private Ui ui;

    /**
     * Constructor for Duke class.
     *
     * @param filePath The file containing the tasks.
     */
    public Duke(String filePath) {
        this.storage = new Storage(filePath);
        try {
            this.tasksList = storage.load();
        } catch (Storage.DukeException e) {
            ui.showMessages(e.getMessage());
        }
    }

    public static void main(String[] args) {
        new Duke("Duke.txt").run();
    }

    /** Run the Duke */
    public void run() {
        startDuke();
        runCommand();
        exitDuke();
    }

    private void startDuke() {
        this.ui = new Ui();
        ui.showWelcomeMessage();
    }

    private void exitDuke() {
        ui.showGoodbyeMessage();
        try {
            storage.write(tasksList);
        } catch (Storage.DukeException e) {
            ui.showMessages(e.getMessage());
        }
        System.exit(0);
    }

    /** The process of running Duke */
    private void runCommand() {
        Command command;
        do {
            String userCommandText = ui.getUserCommand();
            command = new Parser().parseCommand(userCommandText);
            CommandResult result = executeCommand(command);
            ui.showResultToUser(result);

        } while (!ExitCommand.isExit(command));
    }

    /**
     * Return the result of the executed command.
     *
     * @param command the command class based on user input.
     * @return the result of executed command.
     */
    private CommandResult executeCommand(Command command) {
        command.setData(tasksList);
        CommandResult result = command.execute();
        return result;
    }

}
