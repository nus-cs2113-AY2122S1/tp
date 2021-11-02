package seedu.unimods;

import seedu.command.Command;
import seedu.exceptions.AddException;
import seedu.exceptions.EditException;
import seedu.exceptions.IntegerException;
import seedu.exceptions.FetchException;
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
    public static ProfileStorage profileStorage;
    public static CommandParser commandParser = new CommandParser();
    public static ArrayList<Profile> profiles = new ArrayList<>();
    private static Profile profileInUse;

    public static void main(String[] args) {
        new UniMods().setup();
    }

    private void setup() {
        timetableStorage = new TimetableStorage(timetablePath);
        profileStorage = new ProfileStorage();
        timetable = timetableStorage.loadSchedule();
        TextUi.printWelcomeMessage();
        profileInUse = profileStorage.loadProfile();
        run();
    }

    private void run() {
        Command command;
        do {
            command = commandParser.parseCommand(TextUi.getCommand(), timetable);
            executeCommand(command);
            timetableStorage.save(timetable);
            profileStorage.save(profileInUse);
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
        } catch (EditException e) {
            e.printMessage();
        }
    }

    public static Profile getProfileInUse() {
        return profileInUse;
    }
}
