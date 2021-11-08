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

public class DeleteQuestionCommandTest {

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
        for (int i = 0; i < 5; i++) {
            Command addCommand = commandParser.parseCommand("add \"test\" \"test" + i + "\"");
            CommandResult addResult = addCommand.execute(moduleManager);
            assertTrue(addResult.isOk());
        }

        assertEquals(5, moduleManager.getModule(tempModule).getContentManager(type).getTotalContents());

        Command deleteCommand = commandParser.parseCommand("delete 1");
        CommandResult deleteResult = deleteCommand.execute(moduleManager);
        assertTrue(deleteResult.isOk());
        assertEquals(4, moduleManager.getModule(tempModule).getContentManager(type).getTotalContents());
        for (int i = 2; i < 4; i++) {
            deleteCommand = commandParser.parseCommand("delete " + i);
            deleteResult = deleteCommand.execute(moduleManager);
            assertTrue(deleteResult.isOk());
        }
        assertEquals(2, moduleManager.getModule(tempModule).getContentManager(type).getTotalContents());
    }

    @Test
    void execute_throwsException() throws InvalidCommandException, InvalidArgumentException {
        Command deleteCommand = commandParser.parseCommand("delete 100");
        assertThrows(InvalidArgumentException.class, () -> deleteCommand.execute(moduleManager));
    }
}
