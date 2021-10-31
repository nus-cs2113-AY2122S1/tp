package terminus.command.module;

import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.IOException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import terminus.command.Command;
import terminus.command.CommandResult;
import terminus.exception.InvalidArgumentException;
import terminus.exception.InvalidCommandException;
import terminus.module.ModuleManager;
import terminus.parser.ModuleCommandParser;

public class DeleteModuleCommandTest {

    private static final String tempModule = "test";
    private ModuleCommandParser commandParser;
    private ModuleManager moduleManager;

    @BeforeEach
    void setUp() throws IOException {
        this.moduleManager = new ModuleManager();
        this.commandParser = ModuleCommandParser.getInstance();
        moduleManager.addModule(tempModule);
    }

    @Test
    void execute_deleteModule_success()
            throws InvalidArgumentException, InvalidCommandException, IOException {
        Command cmd = commandParser.parseCommand("delete 1");
        CommandResult cmdResult = cmd.execute(moduleManager);
        assertTrue(cmdResult.isOk());
        assertNull(moduleManager.getModule(tempModule));
    }

    @Test
    void execute_deleteModule_throwsException() {
        assertThrows(InvalidArgumentException.class,
            () -> commandParser.parseCommand("delete -1").execute(moduleManager));
        assertThrows(InvalidArgumentException.class,
            () -> commandParser.parseCommand("delete").execute(moduleManager));
        assertThrows(InvalidArgumentException.class,
            () -> commandParser.parseCommand("delete 100").execute(moduleManager));
    }
}
