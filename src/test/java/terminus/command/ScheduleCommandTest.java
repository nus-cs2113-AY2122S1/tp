package terminus.command;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.IOException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import terminus.content.Link;
import terminus.exception.InvalidArgumentException;
import terminus.exception.InvalidCommandException;
import terminus.module.ModuleManager;
import terminus.parser.LinkCommandParser;
import terminus.parser.MainCommandParser;

public class ScheduleCommandTest {

    private MainCommandParser commandParser;
    private ModuleManager moduleManager;

    private String tempModule = "test";

    @BeforeEach
    void setUp() {
        commandParser = MainCommandParser.getInstance();
        moduleManager = new ModuleManager();
        moduleManager.addModule(tempModule);
    }

    @Test
    void execute_linkAdvance_success()
            throws InvalidArgumentException, InvalidCommandException, IOException {
        Command mainCommand = commandParser.parseCommand("go " + tempModule + " schedule");
        CommandResult changeResult = mainCommand.execute(moduleManager);
        assertTrue(changeResult.isOk());
        assertTrue(changeResult.getNewCommandParser() instanceof LinkCommandParser);
        mainCommand = commandParser.parseCommand("go " + tempModule + " schedule add \"test\" \"Thursday\" \"00:00\" "
                + "\"3\" \"https://zoom.us\"");
        changeResult = mainCommand.execute(moduleManager);
        assertTrue(changeResult.isOk());
        assertEquals(1, moduleManager.getModule(tempModule).getContentManager(Link.class).getTotalContents());
        mainCommand = commandParser.parseCommand("go " + tempModule + " schedule view");
        changeResult = mainCommand.execute(moduleManager);
        assertTrue(changeResult.isOk());
    }

    @Test
    void execute_linkAdvance_throwsException() {
        assertThrows(InvalidCommandException.class,
            () -> commandParser.parseCommand("go " + tempModule + " schedule -1").execute(moduleManager));
        assertThrows(InvalidArgumentException.class,
            () -> commandParser.parseCommand(
                            "go " + tempModule + " schedule add \"test\" \"Thursday\" \"00:00\" \"2\" \"test.com\"")
                    .execute(moduleManager));
        assertThrows(InvalidArgumentException.class,
            () -> commandParser.parseCommand("go " + tempModule + " schedule delete -1")
                    .execute(moduleManager));

    }
}
