package terminus.command.module;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.IOException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import terminus.command.Command;
import terminus.command.CommandResult;
import terminus.common.Messages;
import terminus.common.TestUtils;
import terminus.exception.InvalidArgumentException;
import terminus.exception.InvalidCommandException;
import terminus.module.ModuleManager;
import terminus.parser.ModuleCommandParser;

public class ViewModuleCommandTest {

    private static final String tempModule = "test";
    private ModuleCommandParser commandParser;
    private ModuleManager moduleManager;

    @BeforeEach
    void setUp() {
        this.moduleManager = new ModuleManager();
        this.commandParser = ModuleCommandParser.getInstance();
    }

    @Test
    void execute_viewModule_noModules_success() throws InvalidArgumentException, InvalidCommandException, IOException {
        Command cmd = commandParser.parseCommand("view");
        CommandResult cmdResult = cmd.execute(moduleManager);
        assertEquals(Messages.MESSAGE_RESPONSE_NO_MODULES,
            TestUtils.generateCommandOutputString(cmdResult.getMessage()));
    }

    @Test
    void execute_viewModule_success() throws InvalidArgumentException, InvalidCommandException, IOException {
        moduleManager.addModule(tempModule);
        Command cmd = commandParser.parseCommand("view");
        CommandResult cmdResult = cmd.execute(moduleManager);
        assertTrue(cmdResult.isOk());
    }
}
