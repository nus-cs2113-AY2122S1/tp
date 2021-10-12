package seedu.duke;

import seedu.command.Command;
import seedu.exceptions.AddException;
import seedu.parser.CommandParser;
import seedu.storage.ModStorage;
import seedu.storage.TimetableStorage;
import seedu.timetable.Timetable;
import seedu.ui.TextUi;

public class Duke {
    private static String path = "data/Modules.json";
    private static String timetablePath = "data/timetable.json";
    public static Timetable timetable;
    public static CommandParser commandParser = new CommandParser();

    public static void main(String[] args) {
        new Duke().setup();
    }

    private void setup() {
        timetable = new TimetableStorage(timetablePath).loadSchedule();
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
        try {
            command.execute();
        } catch(AddException e) {
            e.printMessage();
        }
    }
}
