package seedu.unimods;

import seedu.command.Command;
import seedu.exceptions.AddException;
import seedu.exceptions.EditException;
import seedu.exceptions.FetchException;
import seedu.exceptions.IntegerException;
import seedu.exceptions.ModuleExistException;
import seedu.exceptions.ProfileException;
import seedu.parser.CommandParser;
import seedu.storage.ProfileStorage;
import seedu.storage.TimetableStorage;
import seedu.timetable.Timetable;
import seedu.ui.TextUi;
import seedu.user.Profile;

public class UniMods {
    private static final String timetablePath = "data/timetable.json";
    public static Timetable timetable;
    public static TimetableStorage timetableStorage;
    public static ProfileStorage profileStorage;
    public static CommandParser commandParser = new CommandParser();
    private static Profile profileInUse;

    /**
     * Main function that creates a UniMods instance that immediately setups and runs.
     *
     * @param args array of command-line arguments for application.
     */
    public static void main(String[] args) {
        new UniMods().setup();
    }

    /**
     * Setups UniMods by loading timetable, setting up profile, printing a welcome message before running the
     * main application.
     */
    private void setup() {
        timetableStorage = new TimetableStorage(timetablePath);
        profileStorage = new ProfileStorage();
        timetable = timetableStorage.loadSchedule();
        setupProfile();
        TextUi.printWelcomeMessage();
        run();
    }

    /**
     * Attempts to load profile. If profile does not exist, then prompts user to create the profile.
     */
    private void setupProfile() {
        try {
            profileInUse = profileStorage.loadProfile();
        } catch (ProfileException e) {
            TextUi.printProfileException(e);
            String name = TextUi.getCommand(TextUi.NAME_PROMPT);
            String major = TextUi.getCommand(TextUi.MAJOR_PROMPT);
            String year = TextUi.getCommand(TextUi.YEAR_PROMPT);
            profileInUse = new Profile(name,major,year);
        }
    }

    /**
     * Main running loop of UniMods. Prompts user for commands and executes them until an exit command is entered.
     */
    private void run() {
        Command command;
        do {
            command = commandParser.parseCommand(TextUi.getCommand(TextUi.COMMAND_PROMPT), timetable);
            executeCommand(command);
            timetableStorage.save(timetable);
            profileStorage.save(profileInUse);
        } while (!command.isExit());
    }

    /**
     * Executes the given command by calling its execute function.
     *
     * @param command Command to be executed.
     */
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
