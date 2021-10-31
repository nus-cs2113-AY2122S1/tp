package terminus.command.module;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.io.IOException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import terminus.command.Command;
import terminus.command.CommandResult;
import terminus.exception.InvalidArgumentException;
import terminus.exception.InvalidCommandException;
import terminus.module.ModuleManager;
import terminus.parser.ModuleCommandParser;

public class UpdateModuleCommandTest {

    private ModuleCommandParser commandParser;
    private ModuleManager moduleManager;
    private String tempModule = "test";

    @BeforeEach
    void setUp() {
        this.moduleManager = new ModuleManager();
        this.commandParser = ModuleCommandParser.getInstance();
        moduleManager.addModule(tempModule);
    }


    @Test
    void execute_updateModule_success() throws InvalidArgumentException, InvalidCommandException, IOException {
        Command cmd = commandParser.parseCommand("update 1 \"test2\"");
        CommandResult cmdResult = cmd.execute(moduleManager);
        assertNull(moduleManager.getModule(tempModule));
        assertNotNull(moduleManager.getModule("test2"));
        Command cmd2 = commandParser.parseCommand("update 1 \"CS2106\"");
        CommandResult cmdResult2 = cmd2.execute(moduleManager);
        assertNull(moduleManager.getModule("test2"));
        assertNotNull(moduleManager.getModule("CS2106"));
    }

    @Test
    void execute_updateModule_throwException() throws InvalidArgumentException, InvalidCommandException, IOException {
        assertThrows(InvalidArgumentException.class,
            () -> commandParser.parseCommand("update 100 \"asdasd\"").execute(moduleManager));
        moduleManager.addModule("test2");
        assertThrows(InvalidArgumentException.class,
            () -> commandParser.parseCommand("update 1 \"test2\"").execute(moduleManager));
        assertThrows(InvalidArgumentException.class,
            () -> commandParser.parseCommand("update 1 \"???\"").execute(moduleManager));
    }
}
