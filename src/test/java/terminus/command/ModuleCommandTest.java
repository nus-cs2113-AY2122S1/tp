package terminus.command;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.IOException;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import terminus.TestFilePath;
import terminus.exception.InvalidArgumentException;
import terminus.exception.InvalidCommandException;
import terminus.module.ModuleManager;
import terminus.parser.MainCommandParser;
import terminus.parser.ModuleCommandParser;
import terminus.storage.ModuleStorage;

public class ModuleCommandTest {

    private MainCommandParser commandParser;
    private ModuleStorage moduleStorage;
    private ModuleManager moduleManager;

    private String tempModule = "test";

    @BeforeEach
    void setUp() {
        this.moduleStorage = ModuleStorage.getInstance();
        this.moduleStorage.init(TestFilePath.SAVE_FILE);
        commandParser = MainCommandParser.getInstance();
        moduleManager = new ModuleManager();
    }

    @AfterAll
    static void reset() throws IOException {
        ModuleStorage moduleStorage = ModuleStorage.getInstance();
        moduleStorage.cleanAfterDeleteModule("test");
    }

    @Test
    void execute_module_success() throws InvalidArgumentException, InvalidCommandException, IOException {
        Command cmd = commandParser.parseCommand("module");
        CommandResult cmdResult = cmd.execute(moduleManager);
        assertTrue(cmdResult.isOk());
        assertTrue(cmdResult.getNewCommandParser() instanceof ModuleCommandParser);
        cmd = commandParser.parseCommand("module add \"test\"");
        cmdResult = cmd.execute(moduleManager);
        assertTrue(cmdResult.isOk());
        assertEquals(1, moduleManager.getAllModules().length);
    }

    @Test
    void execute_module_throwsException() {
        assertThrows(InvalidArgumentException.class, () -> commandParser.parseCommand("module add")
            .execute(moduleManager));
        assertThrows(InvalidArgumentException.class, () -> commandParser.parseCommand("module delete asd")
            .execute(moduleManager));
        assertThrows(InvalidCommandException.class, () -> commandParser.parseCommand("module asdsasd")
            .execute(moduleManager));
    }
}
