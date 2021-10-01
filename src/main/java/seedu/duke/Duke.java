package seedu.duke;

import seedu.command.Command;
import seedu.module.Module;
import seedu.online.NusMods;
import seedu.parser.CommandParser;
import seedu.storage.ModStorage;
import seedu.timetable.Class;
import seedu.timetable.TimeTable;
import seedu.ui.TextUi;

import java.io.IOException;


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
        setupTT();
        run();
    }

    // EXAMPLES FOR HOW TO ADD A CLASS INTO TIMETABLE FOR @POOPIES99
    private void setupTT() {
        try {
            Module module = NusMods.fetchModOnline("CG2271");
            timetable.addClass(new Class(module,1, module.getLesson(1,1)));
            timetable.addClass(new Class(module,1, module.getLesson(1,5)));
            Module u = NusMods.fetchModOnline("EG2401A");
            timetable.addClass(new Class(u,1, module.getLesson(1,3)));
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("You are seeing this because the example timetable cannot load when offline");
        }
    }

    private void run() {
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
