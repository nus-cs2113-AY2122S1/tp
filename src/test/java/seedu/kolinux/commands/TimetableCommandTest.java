package seedu.kolinux.commands;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import seedu.kolinux.exceptions.KolinuxException;

import static org.junit.jupiter.api.Assertions.assertEquals;



public class TimetableCommandTest {

    private static final String[] VALID_ADD_TUTORIAL_ARGUMENTS = new String[] {"CS1231", "lec", "friday",
            "1700", "1800"};
    private static final String[] VALID_DELETE_TUTORIAL_ARGUMENTS = new String[] {"CS1231", "lec", "friday", "1700"};
    private static final String[] VALID_UPDATE_TUTORIAL_ARGUMENTS = new String[] {"CS1231", "lec", "friday", "1700",
            "monday", "1800"};
    private static final String INVALID_COMMAND_MESSAGE = "Ensure command has one of the following formats:\n"
            +
            "1. timetable add LESSON_TYPE/MODULE_CODE/DAY/START_TIME\n"
            +
            "2. timetable view\n"
            +
            "3. timetable update MODULE_CODE/LESSON_TYPE/OLD_DAY/NEW_DAY/NEW_START_TIME\n"
            +
            "4. timetable delete MODULE_CODE/LESSON_TYPE/DAY\n"
            +
            "5. timetable clear";

    private static final String[] CODE = {"CS1231"};

    @BeforeEach
    public void clearBefore() throws KolinuxException {
        TimetableCommand clear = new TimetableCommand("clear",null);
        clear.executeCommand();
    }

    @AfterEach
    public void clearAfter() throws KolinuxException {
        TimetableCommand clear = new TimetableCommand("clear",null);
        clear.executeCommand();
    }

    @Test
    public void executeCommand_validLesson_lessonAdded() throws KolinuxException {
        ModuleCommand moduleCommand = new ModuleCommand("store", CODE);
        moduleCommand.executeCommand();
        TimetableCommand addCommand = new TimetableCommand("add", VALID_ADD_TUTORIAL_ARGUMENTS);
        assertEquals("CS1231 LEC has been added to timetable",
                addCommand.executeCommand().getFeedbackToUser());
    }

    @Test
    public void executeDeleteCommand_validLesson_lessonDeleted() throws KolinuxException {
        ModuleCommand moduleCommand = new ModuleCommand("store", CODE);
        moduleCommand.executeCommand();
        TimetableCommand addCommand = new TimetableCommand("add", VALID_ADD_TUTORIAL_ARGUMENTS);
        addCommand.executeCommand();
        TimetableCommand deleteCommand = new TimetableCommand("delete", VALID_DELETE_TUTORIAL_ARGUMENTS);
        assertEquals("CS1231 LEC 1700 friday has been deleted from timetable",
                deleteCommand.executeCommand().getFeedbackToUser());
    }

    @Test
    public void executeUpdateCommand_validLesson_lessonUpdated() throws KolinuxException {
        ModuleCommand moduleCommand = new ModuleCommand("store", CODE);
        moduleCommand.executeCommand();
        TimetableCommand addCommand = new TimetableCommand("add", VALID_ADD_TUTORIAL_ARGUMENTS);
        addCommand.executeCommand();
        TimetableCommand updateCommand = new TimetableCommand("update", VALID_UPDATE_TUTORIAL_ARGUMENTS);
        assertEquals("CS1231 LEC has been updated",
                updateCommand.executeCommand().getFeedbackToUser());

    }

    @Test
    public void executeViewCommand_validCommand_timetableViewed() throws KolinuxException {
        TimetableCommand viewCommand = new TimetableCommand("view", null);
        assertEquals("Timetable has been printed above",
               viewCommand.executeCommand().getFeedbackToUser());
    }

    @Test
    public void executeInvalidCommand_invalidCommand_errorMessage() throws KolinuxException {
        TimetableCommand viewCommand = new TimetableCommand("hello", null);
        assertEquals(INVALID_COMMAND_MESSAGE, viewCommand.executeCommand().getFeedbackToUser());
    }


}
