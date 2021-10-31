package terminus.command.module;

import static org.junit.jupiter.api.Assertions.assertNotNull;
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

public class AddModuleCommandTest {

    private ModuleCommandParser commandParser;
    private ModuleManager moduleManager;

    @BeforeEach
    void setUp() {
        this.moduleManager = new ModuleManager();
        this.commandParser = ModuleCommandParser.getInstance();
    }

    @Test
    void execute_addModule_success()
            throws InvalidArgumentException, InvalidCommandException, IOException {
        Command cmd = commandParser.parseCommand("add \"test\"");
        CommandResult cmdResult = cmd.execute(moduleManager);
        assertTrue(cmdResult.isOk());
        assertNotNull(moduleManager.getModule("test"));
    }

    @Test
    void execute_addModule_throwsException() {
        assertThrows(InvalidArgumentException.class,
            () -> commandParser.parseCommand("add \"test\" \"test2\"").execute(moduleManager));
        assertThrows(InvalidArgumentException.class,
            () -> commandParser.parseCommand("add   ").execute(moduleManager));
        assertThrows(InvalidArgumentException.class,
            () -> commandParser.parseCommand("add   \"??**??** \"").execute(moduleManager));
    }
}
