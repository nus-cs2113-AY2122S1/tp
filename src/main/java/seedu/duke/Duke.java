package seedu.duke;

import seedu.command.Command;
import seedu.parser.CommandParser;
import seedu.storage.TimetableStorage;
import seedu.timetable.Timetable;
import seedu.ui.TextUi;

public class Duke {
    private static String path = "data/Modules.json";
    private static String timetablePath = "data/timetable.json";
    public static Timetable timetable;
    public static TimetableStorage timetableStorage;
    public static CommandParser commandParser = new CommandParser();

    public static void main(String[] args) {
        new Duke().setup();
    }

    private void setup() {
        timetableStorage = new TimetableStorage(timetablePath);
        timetable = timetableStorage.loadSchedule();
        TextUi.printWelcomeMessage();
        run();
    }

    private void run() {
        Command command;
        do {
            command = commandParser.parseCommand(TextUi.getCommand(), timetable);
            executeCommand(command);
            timetableStorage.save(timetable);
        } while (!command.isExit());
    }

    private void executeCommand(Command command) {
        command.execute();
    }
}
