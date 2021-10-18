package seedu.duke;

import seedu.command.Command;
import seedu.exceptions.AddException;
import seedu.exceptions.IntegerException;
import seedu.module.Module;
import seedu.parser.CommandParser;
import seedu.storage.TimetableStorage;
import seedu.timetable.Timetable;
import seedu.ui.TextUi;
import seedu.user.Profile;

import java.util.ArrayList;

public class Duke {
    private static String path = "data/Modules.json";
    private static String timetablePath = "data/timetable.json";
    public static Timetable timetable;
    public static TimetableStorage timetableStorage;
    public static CommandParser commandParser = new CommandParser();
    public static ArrayList<Profile> profiles = new ArrayList<>();
    private static Profile profileInUse;

    public static void main(String[] args) {
        // assert false : "dummy";
        new Duke().setup();
    }

    private void setup() {
        timetableStorage = new TimetableStorage(timetablePath);
        timetable = timetableStorage.loadSchedule();
        TextUi.printWelcomeMessage();
        profiles.add(new Profile("test user", "CEG", "2"));
        profileInUse = profiles.get(0);
        profileInUse.getRecord().addModuleToRecord(new Module("CS1010"),'A');
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
        try {
            command.execute();
        } catch (AddException e) {
            e.printMessage();
        } catch (IntegerException e) {
            e.printMessage();
        }
    }

    public static Profile getProfileInUse() {
        return profileInUse;
    }
}
