package seedu.duke;

import seedu.command.Command;
import seedu.parser.CommandParser;
import seedu.ui.TextUi;

public class Duke {
    /**
     * Main entry-point for the java.duke.Duke application.
     */

    public static CommandParser commandParser = new CommandParser();

    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        System.out.println("What is your name?");

        new Duke().run();
    }

    public void run() {
        Command command;
        do {
            command = commandParser.parseCommand(TextUi.getCommand());
            executeCommand(command);
        } while (!command.isExit());
    }

    private void executeCommand(Command command) {
        command.execute();
    }
}
