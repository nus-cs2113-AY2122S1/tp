package seedu.unimods;

import seedu.command.AddCommand;
import seedu.command.Command;
import seedu.exceptions.AddException;
import seedu.exceptions.FetchException;
import seedu.exceptions.IntegerException;
import seedu.exceptions.ModuleExistException;
import seedu.parser.CommandParser;
import seedu.storage.TimetableStorage;
import seedu.timetable.Timetable;
import seedu.ui.TextUi;
import seedu.user.Profile;

import java.util.ArrayList;

public class UniMods {
    private static String path = "data/Modules.json";
    private static String timetablePath = "data/timetable.json";
    public static Timetable timetable;
    public static TimetableStorage timetableStorage;
    public static CommandParser commandParser = new CommandParser();
    public static ArrayList<Profile> profiles = new ArrayList<>();
    private static Profile profileInUse;

    public static void main(String[] args) {
        new UniMods().setup();
    }

    private void setup() {
        timetableStorage = new TimetableStorage(timetablePath);
        timetable = timetableStorage.loadSchedule();
        TextUi.printWelcomeMessage();
        profiles.add(new Profile("test user", "CEG", "2"));
        profileInUse = profiles.get(0);
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
        } catch (FetchException e) {
            e.printMessage();
        } catch (IntegerException e) {
            e.printMessage();
        } catch (ModuleExistException e) {
            e.printMessage();
        } catch (NullPointerException e) {
            e.printStackTrace();
        } catch (AddException e) {
            e.printMessage();
        }
    }

    public static Profile getProfileInUse() {
        return profileInUse;
    }
}
