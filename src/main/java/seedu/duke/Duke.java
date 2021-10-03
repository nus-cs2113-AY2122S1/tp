package seedu.duke;

import seedu.command.Command;
import seedu.module.Module;
import seedu.online.NusMods;
import seedu.parser.CommandParser;
import seedu.storage.ModStorage;
import seedu.timetable.Class;
import seedu.timetable.TimeTable;
import seedu.ui.TextUi;

public class Duke {
    private static String path = "data/Modules.json";
    public static TimeTable timetable = new TimeTable(1);
    public static CommandParser commandParser = new CommandParser();
    public ModStorage modStorage;

    public static void main(String[] args) {
        new Duke().setup();
    }

    private void setup() {
        this.modStorage = new ModStorage(path);
        TextUi.printWelcomeMessage();
        run();
    }

    private void run() {
        Command command;
        do {
            command = commandParser.parseCommand(TextUi.getCommand(), timetable.getSemester());
            executeCommand(command);
        } while (!command.isExit());
    }

    private void executeCommand(Command command) {
        command.execute();
    }
}
