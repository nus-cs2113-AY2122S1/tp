package terminus.command;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import terminus.content.Link;
import terminus.content.Note;
import terminus.exception.InvalidArgumentException;
import terminus.exception.InvalidCommandException;
import terminus.module.ModuleManager;
import terminus.parser.LinkCommandParser;
import terminus.parser.MainCommandParser;
import terminus.parser.ModuleWorkspaceCommandParser;
import terminus.parser.NoteCommandParser;
import terminus.ui.Ui;

public class GoCommandTest {

    private MainCommandParser commandParser;
    private ModuleManager moduleManager;
    private Ui ui;

    private String tempModule = "test";

    @BeforeEach
    void setUp() {
        this.commandParser = MainCommandParser.getInstance();
        this.moduleManager = new ModuleManager();
        moduleManager.setModule(tempModule);
        this.ui = new Ui();
    }

    @Test
    void execute_go_success() throws InvalidArgumentException, InvalidCommandException {
        Command cmd = commandParser.parseCommand("go " + tempModule);
        CommandResult cmdResult = cmd.execute(ui, moduleManager);
        assertTrue(cmdResult.isOk());
        assertTrue(cmdResult.getAdditionalData() instanceof ModuleWorkspaceCommandParser);
    }

    @Test
    void execute_go_throwsException() throws InvalidArgumentException, InvalidCommandException {
        Command cmd = commandParser.parseCommand("go not_a_test");
        assertThrows(InvalidArgumentException.class, () -> cmd.execute(ui, moduleManager));
    }

    @Test
    void execute_goAdvance_success() throws InvalidArgumentException, InvalidCommandException {
        Command cmd = commandParser.parseCommand("go " + tempModule + " note");
        CommandResult cmdResult = cmd.execute(ui, moduleManager);
        assertTrue(cmdResult.isOk());
        assertTrue(cmdResult.getAdditionalData() instanceof NoteCommandParser);
        cmd = commandParser.parseCommand("go " + tempModule + " note add \"test\" \"test1\"");
        cmdResult = cmd.execute(ui, moduleManager);
        assertTrue(cmdResult.isOk());
        assertEquals(1, moduleManager.getModule(tempModule).getContentManager(Note.class).getTotalContents());
        cmd = commandParser.parseCommand("go " + tempModule + " note view");
        cmdResult = cmd.execute(ui, moduleManager);
        assertTrue(cmdResult.isOk());
        cmd = commandParser.parseCommand("go " + tempModule + " note delete 1");
        cmdResult = cmd.execute(ui, moduleManager);
        assertTrue(cmdResult.isOk());
        assertEquals(0, moduleManager.getModule(tempModule).getContentManager(Note.class).getTotalContents());
        cmd = commandParser.parseCommand("go " + tempModule + " schedule");
        cmdResult = cmd.execute(ui, moduleManager);
        assertTrue(cmdResult.isOk());
        assertTrue(cmdResult.getAdditionalData() instanceof LinkCommandParser);
        cmd = commandParser.parseCommand("go " + tempModule + " schedule add \"test\" \"Thursday\" \"00:00\" "
                + "\"https://zoom.us\"");
        cmdResult = cmd.execute(ui, moduleManager);
        assertTrue(cmdResult.isOk());
        assertEquals(1, moduleManager.getModule(tempModule).getContentManager(Link.class).getTotalContents());
        cmd = commandParser.parseCommand("go " + tempModule + " schedule view");
        cmdResult = cmd.execute(ui, moduleManager);
        assertTrue(cmdResult.isOk());
        cmd = commandParser.parseCommand("go " + tempModule + " schedule delete 1");
        cmdResult = cmd.execute(ui, moduleManager);
        assertTrue(cmdResult.isOk());
        assertEquals(0, moduleManager.getModule(tempModule).getContentManager(Link.class).getTotalContents());
    }

    @Test
    void execute_goAdvance_throwException() throws InvalidArgumentException, InvalidCommandException {
        assertThrows(InvalidCommandException.class,
            () -> commandParser.parseCommand("go " + tempModule + " note abcbd").execute(ui, moduleManager));
        assertThrows(InvalidCommandException.class,
            () -> commandParser.parseCommand("go " + tempModule + " schedule abcbd").execute(ui, moduleManager));
        assertThrows(InvalidArgumentException.class,
            () -> commandParser.parseCommand("go " + tempModule + " schedule delete -1").execute(ui,
                        moduleManager));
        assertThrows(InvalidArgumentException.class,
            () -> commandParser.parseCommand("go " + tempModule + " note delete -1").execute(ui, moduleManager));
        assertThrows(InvalidArgumentException.class,
            () -> commandParser.parseCommand("go " + tempModule + " note view 100").execute(ui, moduleManager));

    }
}
