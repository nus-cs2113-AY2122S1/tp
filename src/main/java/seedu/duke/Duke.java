package seedu.duke;

import seedu.command.Command;
import seedu.module.LessonType;
import seedu.module.ModList;
import seedu.module.Module;
import seedu.parser.CommandParser;
import seedu.parser.NusModsParser;
import seedu.storage.ModStorage;
import seedu.timetable.Class;
import seedu.timetable.TimeTable;
import seedu.ui.TextUi;

import java.io.IOException;

public class Duke {
    /**
     * Main entry-point for the java.duke.Duke application.
     */

    private static String path = "data/Modules.json";
    public static TimeTable timetable = new TimeTable(1);
    public static CommandParser commandParser = new CommandParser();
    public ModList modList;
    public ModStorage modStorage;

    public static void main(String[] args) throws IOException, InterruptedException {
        new Duke().setup();
    }

    private void setup() throws IOException, InterruptedException {
        this.modList = new ModList();
        this.modStorage = new ModStorage(path);
        TextUi.printWelcomeMessage(checkForSave());
        NusModsParser.setup(modList);
        setupTT();
        run();

    }

    // EXAMPLES FOR HOW TO ADD A CLASS INTO TIMETABLE FOR @POOPIES99
    private void setupTT() {
        Module module = NusModsParser.fetchMod("CG2271");
        timetable.addClass(new Class(module,1, module.getLesson(1,1)));
        timetable.addClass(new Class(module,1, module.getLesson(1,5)));
        Module u = NusModsParser.fetchMod("EG2401A");
        timetable.addClass(new Class(u,1, module.getLesson(1,3)));
    }

    /**
     * Function that checks if a save file exists, and creates one if it does not.
     * @return true if save does not exist, false otherwise
     */
    private boolean checkForSave() {
        boolean hasSave = false;
        try {
            hasSave = modStorage.setupSave();
        } catch (SecurityException e) {
            e.printStackTrace();
        } catch (ModStorage.FileErrorException e) {
            e.printStackTrace();
        }
        return hasSave;
    }

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
