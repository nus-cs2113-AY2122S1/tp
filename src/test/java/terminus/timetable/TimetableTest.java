package terminus.timetable;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import terminus.command.Command;
import terminus.exception.InvalidArgumentException;
import terminus.exception.InvalidCommandException;
import terminus.module.ModuleManager;
import terminus.parser.LinkCommandParser;
import terminus.ui.Ui;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

class TimetableTest {
    private ModuleManager moduleManager;
    private Ui ui;

    private String tempModule = "test";

    @BeforeEach
    void setUp() {
        this.moduleManager = new ModuleManager();
        moduleManager.setModule(tempModule);
        this.ui = new Ui();
    }

    @Test
    void execute_getDailySchedule_success() throws InvalidArgumentException, InvalidCommandException, IOException {
        Timetable timetable = new Timetable(moduleManager);

        LinkCommandParser linkCommandParser = LinkCommandParser.getInstance();
        linkCommandParser.setModuleName(tempModule);

        Command addLinkCommand = linkCommandParser.parseCommand("add \"test\" \"Monday\" \"00:00\" \"2\" \"https://zoom.us/test\"");
        addLinkCommand.execute(ui, moduleManager);
        assertNotNull(timetable.getDailySchedule("Monday"));

        Command addLinkCommand1 = linkCommandParser.parseCommand("add \"test\" \"Thursday\" \"00:00\" \"2\" \"https://zoom.us/test\"");
        addLinkCommand1.execute(ui, moduleManager);
        assertNotNull(timetable.getDailySchedule("Thursday"));

        Command addLinkCommand2 = linkCommandParser.parseCommand("add \"test\" \"Friday\" \"00:00\" \"2\" \"https://zoom.us/test\"");
        addLinkCommand2.execute(ui, moduleManager);
        assertNotNull(timetable.getDailySchedule("Friday"));
    }

    @Test
    void execute_getDailySchedule_fail() throws InvalidArgumentException, InvalidCommandException, IOException {
        LinkCommandParser linkCommandParser = LinkCommandParser.getInstance();
        linkCommandParser.setModuleName(tempModule);

        Command addLinkCommand = linkCommandParser.parseCommand("add \"test\" \"Monday\" \"00:00\" \"2\" \"https://zoom.us/test\"");
        addLinkCommand.execute(ui, moduleManager);

        Command addLinkCommand1 = linkCommandParser.parseCommand("add \"test\" \"Thursday\" \"00:00\" \"2\" \"https://zoom.us/test\"");
        addLinkCommand1.execute(ui, moduleManager);

        Command addLinkCommand2 = linkCommandParser.parseCommand("add \"test\" \"Friday\" \"00:00\" \"2\" \"https://zoom.us/test\"");
        addLinkCommand2.execute(ui, moduleManager);

        Timetable timetable = new Timetable(moduleManager);
        assertNull(timetable.getDailySchedule("Tuesday"));
        assertNull(timetable.getDailySchedule("Sunday"));
        assertNull(timetable.getDailySchedule("Saturday"));
    }

    @Test
    void execute_getWeeklySchedule_success() throws InvalidArgumentException, InvalidCommandException, IOException {
        Timetable timetable = new Timetable(moduleManager);

        LinkCommandParser linkCommandParser = LinkCommandParser.getInstance();
        linkCommandParser.setModuleName(tempModule);

        assertNull(timetable.getWeeklySchedule());

        Command addLinkCommand = linkCommandParser.parseCommand("add \"test\" \"Monday\" \"00:00\" \"2\" \"https://zoom.us/test\"");
        addLinkCommand.execute(ui, moduleManager);
        assertNotNull(timetable.getWeeklySchedule());

        Command addLinkCommand1 = linkCommandParser.parseCommand("add \"test\" \"Thursday\" \"00:00\" \"2\" \"https://zoom.us/test\"");
        addLinkCommand1.execute(ui, moduleManager);
        assertNotNull(timetable.getWeeklySchedule());

        Command addLinkCommand2 = linkCommandParser.parseCommand("add \"test\" \"Friday\" \"00:00\" \"2\" \"https://zoom.us/test\"");
        addLinkCommand2.execute(ui, moduleManager);
        assertNotNull(timetable.getWeeklySchedule());
    }
}