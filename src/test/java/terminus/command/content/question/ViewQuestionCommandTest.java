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

public class ViewQuestionCommandTest {

    private QuestionCommandParser commandParser;
    private ModuleManager moduleManager;

    private String tempModule = "test";

    Class<Question> type = Question.class;

    @BeforeEach
    void setUp() {
        this.moduleManager = new ModuleManager();
        moduleManager.setModule(tempModule);
        this.commandParser = QuestionCommandParser.getInstance();
        this.commandParser.setModuleName(tempModule);
    }

    @Test
    void execute_viewAll_success()
        throws InvalidCommandException, InvalidArgumentException, IOException {
        for (int i = 0; i < 5; i++) {
            Command addCommand = commandParser.parseCommand("add \"test\" \"test" + i + "\"");
            CommandResult addResult = addCommand.execute(moduleManager);
            assertTrue(addResult.isOk());
        }
        assertEquals(5, moduleManager.getModule(tempModule).getContentManager(type).getTotalContents());

        Command viewCommand = commandParser.parseCommand("view");
        CommandResult viewResult = viewCommand.execute(moduleManager);
        assertTrue(viewResult.isOk());
    }

    @Test
    void execute_viewOne_success()
        throws InvalidCommandException, InvalidArgumentException, IOException {
        for (int i = 0; i < 5; i++) {
            Command addCommand = commandParser.parseCommand("add \"test\" \"test" + i + "\"");
            CommandResult addResult = addCommand.execute(moduleManager);
            assertTrue(addResult.isOk());
        }
        assertEquals(5, moduleManager.getModule(tempModule).getContentManager(type).getTotalContents());

        Command viewCommand = commandParser.parseCommand("view 1");
        CommandResult viewResult = viewCommand.execute(moduleManager);
        assertTrue(viewResult.isOk());
    }

    @Test
    void execute_viewOne_exceptionThrown()
        throws InvalidCommandException, InvalidArgumentException, IOException {
        for (int i = 0; i < 5; i++) {
            Command addCommand = commandParser.parseCommand("add \"test\" \"test" + i + "\"");
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
