package terminus.command.content.question;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import com.itextpdf.text.DocumentException;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import terminus.command.Command;
import terminus.command.CommandResult;
import terminus.exception.InvalidArgumentException;
import terminus.exception.InvalidCommandException;
import terminus.module.ModuleManager;
import terminus.parser.QuestionCommandParser;
import terminus.ui.Ui;

public class TestCommandTest {

    private final String tempModule = "test";
    private QuestionCommandParser commandParser;
    private ModuleManager moduleManager;
    private Ui ui;

    @BeforeEach
    void setUp() {
        this.moduleManager = new ModuleManager();
        moduleManager.setModule(tempModule);
        this.commandParser = QuestionCommandParser.getInstance();
        this.commandParser.setModuleName(tempModule);
        this.ui = new Ui();
    }

    @Test
    void execute_noQuestions_exceptionThrown() throws InvalidArgumentException, InvalidCommandException {
        Command command = commandParser.parseCommand("test");
        assertThrows(InvalidCommandException.class, () -> command.execute(ui, moduleManager));
    }

    @Test
    void execute_success() throws InvalidArgumentException, InvalidCommandException, IOException {
        for (int i = 0; i < 4; i++) {
            Command addCommand = commandParser.parseCommand("add \"test\" \"test" + i + "\"");
            CommandResult addResult = addCommand.execute(ui, moduleManager);
            assertTrue(addResult.isOk());
        }
        Command command = commandParser.parseCommand("test");
        String input = String.format("%s%s2%s%s%se%s", System.lineSeparator(), System.lineSeparator(),
            System.lineSeparator(), System.lineSeparator(), System.lineSeparator(), System.lineSeparator());
        InputStream in = new ByteArrayInputStream(input.getBytes());
        this.ui = new Ui(in);
        assertTrue(command.execute(ui, moduleManager).isOk());
    }
}
