package terminus.command.content.question;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.IOException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import terminus.command.Command;
import terminus.command.CommandResult;
import terminus.content.Question;
import terminus.exception.InvalidArgumentException;
import terminus.exception.InvalidCommandException;
import terminus.module.ModuleManager;
import terminus.parser.QuestionCommandParser;

public class AddQuestionCommandTest {

    private final String tempModule = "test";
    Class<Question> type = Question.class;
    private QuestionCommandParser commandParser;
    private ModuleManager moduleManager;

    @BeforeEach
    void setUp() {
        this.moduleManager = new ModuleManager();
        moduleManager.addModule(tempModule);
        this.commandParser = QuestionCommandParser.getInstance();
        this.commandParser.setModuleName(tempModule);
    }

    @Test
    void execute_success() throws InvalidCommandException, InvalidArgumentException, IOException {
        Command addCommand = commandParser.parseCommand("add \"test\" \"test1\"");
        CommandResult addResult = addCommand.execute(moduleManager);
        assertTrue(addResult.isOk());
        assertEquals(1, moduleManager.getModule(tempModule).getContentManager(type).getTotalContents());
        assertTrue(moduleManager.getModule(tempModule).getContentManager(type).getContentData(1).contains("test"));
        assertTrue(moduleManager.getModule(tempModule).getContentManager(type).getContentData(1).contains("test1"));
        for (int i = 0; i < 5; i++) {
            addCommand = commandParser.parseCommand("add \"test\" \"test" + i + "\"");
            addResult = addCommand.execute(moduleManager);
            assertTrue(addResult.isOk());
        }
        assertEquals(6, moduleManager.getModule(tempModule).getContentManager(type).getTotalContents());
    }

    @Test
    void execute_invalidArguments_exceptionThrown() {
        assertThrows(InvalidArgumentException.class,
            () -> commandParser.parseCommand("add \" \"test\"").execute(moduleManager));
        assertThrows(InvalidArgumentException.class,
            () -> commandParser.parseCommand("add \"t\" \"\"").execute(moduleManager));
        assertThrows(InvalidArgumentException.class,
            () -> commandParser.parseCommand("add \"\" \"test\"").execute(moduleManager));
        assertThrows(InvalidArgumentException.class,
            () -> commandParser.parseCommand("add \"test\" \"test\"\"\"").execute(moduleManager));
        assertThrows(InvalidArgumentException.class,
            () -> commandParser.parseCommand("add \" \" \" \"").execute(moduleManager));
    }
}
