package terminus.command.content.question;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.IOException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import terminus.command.Command;
import terminus.command.CommandResult;
import terminus.exception.InvalidArgumentException;
import terminus.exception.InvalidCommandException;
import terminus.module.ModuleManager;
import terminus.parser.ModuleWorkspaceCommandParser;
import terminus.parser.QuestionCommandParser;

public class BackQuestionCommandTest {

    private QuestionCommandParser commandParser;
    private ModuleManager moduleManager;

    private String tempModule = "test";

    @BeforeEach
    void setUp() {
        this.commandParser = QuestionCommandParser.getInstance();
        this.commandParser.setModuleName(tempModule);
        this.moduleManager = new ModuleManager();
        moduleManager.addModule(tempModule);
    }

    @Test
    void execute_success() throws InvalidCommandException, InvalidArgumentException, IOException {
        Command backCommand = commandParser.parseCommand("back");
        CommandResult backResult = backCommand.execute(moduleManager);
        assertTrue(backResult.getNewCommandParser() instanceof ModuleWorkspaceCommandParser);
    }
}
