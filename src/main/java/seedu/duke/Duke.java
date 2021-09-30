package seedu.duke;

import seedu.command.Command;
import seedu.module.ModList;
import seedu.module.Module;
import seedu.parser.CommandParser;
import seedu.parser.NusModsParser;
import seedu.storage.ModStorage;
import seedu.timetable.Timetable;
import seedu.timetable.TimetableLesson;
import seedu.ui.TextUi;

import java.io.IOException;

public class Duke {
    private static String path = "data/Modules.json";
    public static Timetable timetable = new Timetable(1);
    public static CommandParser commandParser = new CommandParser();
    public ModList modList;
    public ModStorage modStorage;

    public static void main(String[] args) throws IOException, InterruptedException {
        new Duke().setup();
    }

    private void setup() throws IOException, InterruptedException {
        this.modList = new ModList();
        this.modStorage = new ModStorage(path);
        TextUi.printWelcomeMessage();
        NusModsParser.setup(modList);
        setupTT();
        //setupTT(modList);
        run();
    }


    // EXAMPLES FOR HOW TO ADD A CLASS INTO TIMETABLE FOR @POOPIES99
    private void setupTT() {
        Module module = NusModsParser.fetchModFromAPI("CG2271");
        timetable.addClass(new TimetableLesson(module,1, module.getLesson(1,1)));
        timetable.addClass(new TimetableLesson(module,1, module.getLesson(1,5)));
        Module u = NusModsParser.fetchModFromAPI("EG2401A");
        timetable.addClass(new TimetableLesson(u,1, module.getLesson(1,3)));
    }

    /*
    // SLOWER METHOD USING THE LOADED MODLIST
    private void setupTT(ModList modList) {
        Module module = modList.fetchMod("CG2271");
        timetable.addClass(new TimetableLesson(module,1, module.getLesson(1,1)));
        timetable.addClass(new TimetableLesson(module,1, module.getLesson(1,5)));
        Module u = modList.fetchMod("EG2401A");
        timetable.addClass(new TimetableLesson(u,1, module.getLesson(1,3)));
    }
    */

    private void run() {
        Command command;
        do {
            command = commandParser.parseCommand(TextUi.getCommand());
            executeCommand(command);
        } while (!command.isExit());
    }

    private void executeCommand(Command command) {
        command.setData(modList);
        command.execute();
    }
}
