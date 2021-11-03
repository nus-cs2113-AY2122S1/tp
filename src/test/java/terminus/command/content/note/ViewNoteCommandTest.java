package terminus.command.content.note;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.IOException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import terminus.command.Command;
import terminus.command.CommandResult;
import terminus.common.Messages;
import terminus.common.TestUtils;
import terminus.content.Note;
import terminus.exception.InvalidArgumentException;
import terminus.exception.InvalidCommandException;
import terminus.module.ModuleManager;
import terminus.parser.NoteCommandParser;

public class ViewNoteCommandTest {

    Class<Note> type = Note.class;
    private NoteCommandParser commandParser;
    private ModuleManager moduleManager;
    private String tempModule = "test";

    @BeforeEach
    void setUp() throws IOException {
        this.moduleManager = new ModuleManager();
        moduleManager.addModule(tempModule);
        this.commandParser = NoteCommandParser.getInstance();
        this.commandParser.setModuleName(tempModule);
    }

    @Test
    void execute_viewNone_success() throws InvalidArgumentException, InvalidCommandException, IOException {
        Command viewCommand = commandParser.parseCommand("view");
        CommandResult viewResult = viewCommand.execute(moduleManager);
        assertEquals(TestUtils.generateCommandOutputString(viewResult.getMessage()),
            Messages.EMPTY_CONTENT_LIST_MESSAGE.trim());
    }

    @Test
    void execute_viewAll_success()
        throws InvalidCommandException, InvalidArgumentException, IOException {
        for (int i = 0; i < 5; i++) {
            Command addCommand = commandParser.parseCommand("add \"test" + i + "\" \"test" + i + "\"");
            CommandResult addResult = addCommand.execute(moduleManager);
            assertTrue(addResult.isOk());
        }
        assertEquals(5, moduleManager.getModule(tempModule).getContentManager(type).getTotalContents());
        
        String stringBuilder = Messages.CONTENT_MESSAGE_HEADER
            + moduleManager.getModule(tempModule).getContentManager(type).listAllContents()
            + Messages.CONTENT_MESSAGE_FOOTER;
        Command viewCommand = commandParser.parseCommand("view");
        CommandResult viewResult = viewCommand.execute(moduleManager);
        assertEquals(stringBuilder, TestUtils.generateCommandOutputString(viewResult.getMessage()));
    }

    @Test
    void execute_viewOne_success()
        throws InvalidCommandException, InvalidArgumentException, IOException {
        for (int i = 0; i < 5; i++) {
            Command addCommand = commandParser.parseCommand("add \"test" + i + "\" \"test" + i + "\"");
            CommandResult addResult = addCommand.execute(moduleManager);
            assertTrue(addResult.isOk());
        }
        assertEquals(5, moduleManager.getModule(tempModule).getContentManager(type).getTotalContents());

        int testId = 1;

        Command viewCommand = commandParser.parseCommand("view " + testId);
        CommandResult viewResult = viewCommand.execute(moduleManager);
        assertEquals(moduleManager.getModule(tempModule).getContentManager(type).getContentData(testId).trim(),
            TestUtils.generateCommandOutputString(viewResult.getMessage()));
    }

    @Test
    void execute_viewOne_exceptionThrown()
        throws InvalidCommandException, InvalidArgumentException, IOException {
        for (int i = 0; i < 5; i++) {
            Command addCommand = commandParser.parseCommand("add \"test" + i + "\" \"test" + i + "\"");
            CommandResult addResult = addCommand.execute(moduleManager);
            assertTrue(addResult.isOk());
        }
        assertEquals(5, moduleManager.getModule(tempModule).getContentManager(type).getTotalContents());

        assertThrows(InvalidArgumentException.class, () -> commandParser.parseCommand("view a"));
        assertThrows(InvalidArgumentException.class,
            () -> commandParser.parseCommand("view -1").execute(moduleManager));
        assertThrows(InvalidArgumentException.class,
            () -> commandParser.parseCommand("view 6").execute(moduleManager));
    }
}
