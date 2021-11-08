package terminus.timetable;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

import java.io.IOException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import terminus.command.Command;
import terminus.exception.InvalidArgumentException;
import terminus.exception.InvalidCommandException;
import terminus.module.ModuleManager;
import terminus.parser.LinkCommandParser;
import terminus.ui.Ui;

class TimetableTest {
    private ModuleManager moduleManager;
    private Ui ui;

    private String tempModule = "test";

    @BeforeEach
    void setUp() {
        this.moduleManager = new ModuleManager();
        moduleManager.addModule(tempModule);
    }

    @Test
    void execute_getDailySchedule_success() throws InvalidArgumentException, InvalidCommandException, IOException {
        Timetable timetable = new Timetable(moduleManager);

        LinkCommandParser linkCommandParser = LinkCommandParser.getInstance();
        linkCommandParser.setModuleName(tempModule);

        Command addLinkCommand = linkCommandParser.parseCommand("add \"test\" \"Monday\" \"00:00\" \"2\" \"https://zoom.us/test\"");
        addLinkCommand.execute(moduleManager);
        assertNotNull(timetable.getDailySchedule("Monday"));

        Command addLinkCommand1 = linkCommandParser.parseCommand("add \"test\" \"Thursday\" \"00:00\" \"2\" \"https://zoom.us/test\"");
        addLinkCommand1.execute(moduleManager);
        assertNotNull(timetable.getDailySchedule("Thursday"));

        Command addLinkCommand2 = linkCommandParser.parseCommand("add \"test\" \"Friday\" \"00:00\" \"2\" \"https://zoom.us/test\"");
        addLinkCommand2.execute(moduleManager);
        assertNotNull(timetable.getDailySchedule("Friday"));
    }

    @Test
    void execute_getDailySchedule_fail() throws InvalidArgumentException, InvalidCommandException, IOException {
        LinkCommandParser linkCommandParser = LinkCommandParser.getInstance();
        linkCommandParser.setModuleName(tempModule);

        Command addLinkCommand = linkCommandParser.parseCommand("add \"test\" \"Monday\" \"00:00\" \"2\" \"https://zoom.us/test\"");
        addLinkCommand.execute(moduleManager);

        Command addLinkCommand1 = linkCommandParser.parseCommand("add \"test\" \"Thursday\" \"00:00\" \"2\" \"https://zoom.us/test\"");
        addLinkCommand1.execute(moduleManager);

        Command addLinkCommand2 = linkCommandParser.parseCommand("add \"test\" \"Friday\" \"00:00\" \"2\" \"https://zoom.us/test\"");
        addLinkCommand2.execute(moduleManager);

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
        addLinkCommand.execute(moduleManager);
        assertNotNull(timetable.getWeeklySchedule());

        Command addLinkCommand1 = linkCommandParser.parseCommand("add \"test\" \"Thursday\" \"00:00\" \"2\" \"https://zoom.us/test\"");
        addLinkCommand1.execute(moduleManager);
        assertNotNull(timetable.getWeeklySchedule());

        Command addLinkCommand2 = linkCommandParser.parseCommand("add \"test\" \"Friday\" \"00:00\" \"2\" \"https://zoom.us/test\"");
        addLinkCommand2.execute(moduleManager);
        assertNotNull(timetable.getWeeklySchedule());
    }

    @Test
    void execute_checkEmptySchedule_success() {
        String schedule = null;
        String day1 = "Friday";
        String day2 = null;
        Timetable timetable = new Timetable(moduleManager);
        assertNotNull(timetable.checkEmptySchedule(schedule, day1));
        assertNotNull(timetable.checkEmptySchedule(schedule, day2));
    }
}