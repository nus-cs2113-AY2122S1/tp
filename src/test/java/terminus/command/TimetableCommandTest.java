package terminus.command;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.IOException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import terminus.exception.InvalidArgumentException;
import terminus.exception.InvalidCommandException;
import terminus.module.ModuleManager;
import terminus.parser.LinkCommandParser;
import terminus.parser.MainCommandParser;
import terminus.ui.Ui;

public class TimetableCommandTest {

    private MainCommandParser mainCommandParser;
    private LinkCommandParser linkCommandParser;
    private ModuleManager moduleManager;
    private Ui ui;

    private final String tempModule1 = "test1";

    @BeforeEach
    void setUp() {
        this.mainCommandParser = MainCommandParser.getInstance();
        this.linkCommandParser = LinkCommandParser.getInstance();
        this.linkCommandParser.setModuleName(tempModule1);
        this.moduleManager = new ModuleManager();
        moduleManager.addModule(tempModule1);
    }

    @Test
    void execute_viewWeekly_success()
            throws InvalidArgumentException, InvalidCommandException, IOException {
        for (int i = 0; i < 5; i++) {
            Command addLinkCommand = linkCommandParser.parseCommand(
                    "add \"test\" \"Saturday\" \"00:00\" \"2\" \"https://zoom.us/test\"");
            CommandResult addLinkResult = addLinkCommand.execute(moduleManager);
            assertTrue(addLinkResult.isOk());
        }

        Command timetableCommand = mainCommandParser.parseCommand("timetable");
        CommandResult timetableResult = timetableCommand.execute(moduleManager);
        assertTrue(timetableResult.isOk());

        for (int i = 0; i < 5; i++) {
            Command addLinkCommand = linkCommandParser.parseCommand(
                    "add \"test\" \"Friday\" \"00:00\" \"3\" \"https://zoom.us/test\"");
            CommandResult addLinkResult = addLinkCommand.execute(moduleManager);
            assertTrue(addLinkResult.isOk());
        }

        Command timetableCommand1 = mainCommandParser.parseCommand("timetable");
        CommandResult timetableResult1 = timetableCommand1.execute(moduleManager);
        assertTrue(timetableResult1.isOk());

        for (int i = 0; i < 5; i++) {
            Command addLinkCommand = linkCommandParser.parseCommand(
                    "add \"test\" \"Sunday\" \"00:00\" \"5\" \"https://zoom.us/test\"");
            CommandResult addLinkResult = addLinkCommand.execute(moduleManager);
            assertTrue(addLinkResult.isOk());
        }

        Command timetableCommand2 = mainCommandParser.parseCommand("timetable");
        CommandResult timetableResult2 = timetableCommand2.execute(moduleManager);
        assertTrue(timetableResult2.isOk());
    }

    @Test
    void execute_viewDaily_success()
            throws InvalidArgumentException, InvalidCommandException, IOException {
        for (int i = 0; i < 5; i++) {
            Command addLinkCommand = linkCommandParser.parseCommand(
                    "add \"test\" \"Tuesday\" \"00:00\" \"4\" \"https://zoom.us/test\"");
            CommandResult addLinkResult = addLinkCommand.execute(moduleManager);
            assertTrue(addLinkResult.isOk());
        }

        for (int i = 0; i < 5; i++) {
            Command addLinkCommand = linkCommandParser.parseCommand(
                    "add \"test\" \"Wednesday\" \"00:00\" \"3\" \"https://zoom.us/test\"");
            CommandResult addLinkResult = addLinkCommand.execute(moduleManager);
            assertTrue(addLinkResult.isOk());
        }

        for (int i = 0; i < 5; i++) {
            Command addLinkCommand = linkCommandParser.parseCommand(
                    "add \"test\" \"Thursday\" \"00:00\" \"2\" \"https://zoom.us/test\"");
            CommandResult addLinkResult = addLinkCommand.execute(moduleManager);
            assertTrue(addLinkResult.isOk());
        }

        Command timetableCommand = mainCommandParser.parseCommand("timetable Saturday");
        CommandResult timetableResult = timetableCommand.execute(moduleManager);
        assertTrue(timetableResult.isOk());

        Command timetableCommand1 = mainCommandParser.parseCommand("timetable Saturday");
        CommandResult timetableResult1 = timetableCommand1.execute(moduleManager);
        assertTrue(timetableResult1.isOk());

        Command timetableCommand2 = mainCommandParser.parseCommand("timetable Saturday");
        CommandResult timetableResult2 = timetableCommand2.execute(moduleManager);
        assertTrue(timetableResult2.isOk());

        Command timetableCommand3 = mainCommandParser.parseCommand("timetable Saturday");
        CommandResult timetableResult3 = timetableCommand3.execute(moduleManager);
        assertTrue(timetableResult3.isOk());
    }

    @Test
    void execute_viewDaily_exceptionThrown()
            throws InvalidArgumentException, InvalidCommandException, IOException {
        for (int i = 0; i < 5; i++) {
            Command addLinkCommand = linkCommandParser.parseCommand(
                    "add \"test\" \"Saturday\" \"00:00\" \"4\" \"https://zoom.us/test\"");
            CommandResult addLinkResult = addLinkCommand.execute(moduleManager);
            assertTrue(addLinkResult.isOk());
        }

        assertThrows(InvalidArgumentException.class, () -> mainCommandParser.parseCommand("timetable -1"));
        assertThrows(InvalidArgumentException.class, () -> mainCommandParser.parseCommand("timetable ."));
        assertThrows(InvalidArgumentException.class, () -> mainCommandParser.parseCommand("timetable today"));
        assertThrows(InvalidArgumentException.class, () -> mainCommandParser.parseCommand("timetable mondayyy"));
        assertThrows(InvalidArgumentException.class, () -> mainCommandParser.parseCommand("timetable yesterday"));
    }
}