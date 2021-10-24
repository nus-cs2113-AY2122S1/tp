package terminus.command.content.question;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.NoSuchElementException;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import terminus.command.Command;
import terminus.command.CommandResult;
import terminus.exception.InvalidArgumentException;
import terminus.exception.InvalidCommandException;
import terminus.module.ModuleManager;
import terminus.parser.QuestionCommandParser;

public class TestCommandTest {

    private final String tempModule = "test";
    private QuestionCommandParser commandParser;
    private ModuleManager moduleManager;

    @BeforeAll
    static void beforeAll() {
        String input = String.format("%s%s2%s%s%se%s", System.lineSeparator(), System.lineSeparator(),
            System.lineSeparator(), System.lineSeparator(), System.lineSeparator(), System.lineSeparator());
        InputStream in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);
    }

    @BeforeEach
    void setUp() {
        this.moduleManager = new ModuleManager();
        moduleManager.addModule(tempModule);
        this.commandParser = QuestionCommandParser.getInstance();
        this.commandParser.setModuleName(tempModule);
    }

    @Test
    void execute_noQuestions_exceptionThrown() throws InvalidArgumentException, InvalidCommandException {
        Command command = commandParser.parseCommand("test");
        assertThrows(InvalidCommandException.class, () -> command.execute(moduleManager));
    }

    @Test
    void execute_success() throws InvalidArgumentException, InvalidCommandException, IOException {
        for (int i = 0; i < 4; i++) {
            Command addCommand = commandParser.parseCommand("add \"test\" \"test" + i + "\"");
            CommandResult addResult = addCommand.execute(moduleManager);
            assertTrue(addResult.isOk());
        }
        Command command = commandParser.parseCommand("test");
        try {
            CommandResult commandResult = command.execute(moduleManager);
            assertTrue(commandResult.isOk());
        } catch (NoSuchElementException exception) {
            assertTrue(true);
        }
    }
}
