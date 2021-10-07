package seedu.duke;

import seedu.command.Command;
import seedu.parser.CommandParser;
import seedu.timetable.Timetable;
import seedu.ui.TextUi;

public class Duke {
    private static String path = "data/Modules.json";
    public static Timetable timetable = new Timetable(1);
    public static CommandParser commandParser = new CommandParser();

    public static void main(String[] args) {
        new Duke().setup();
    }

    private void setup() {
        TextUi.printWelcomeMessage();
        run();
    }

    private void run() {
        Command command;
        do {
            command = commandParser.parseCommand(TextUi.getCommand(), timetable);
            executeCommand(command);
        } while (!command.isExit());
    }

    private void executeCommand(Command command) {
        command.execute();
    }
}
