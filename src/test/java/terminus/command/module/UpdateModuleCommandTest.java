package terminus.command.module;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.io.IOException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import terminus.TestFilePath;
import terminus.command.Command;
import terminus.command.CommandResult;
import terminus.exception.InvalidArgumentException;
import terminus.exception.InvalidCommandException;
import terminus.module.ModuleManager;
import terminus.parser.ModuleCommandParser;
import terminus.storage.ModuleStorage;
import terminus.ui.Ui;

public class UpdateModuleCommandTest {

    private ModuleCommandParser commandParser;
    private ModuleManager moduleManager;
    private Ui ui;
    private ModuleStorage moduleStorage;
    private String tempModule = "test";

    @BeforeEach
    void setUp() {
        this.moduleStorage = ModuleStorage.getInstance();
        this.moduleStorage.init(TestFilePath.SAVE_FILE);
        this.moduleManager = new ModuleManager();
        this.commandParser = ModuleCommandParser.getInstance();
        this.ui = new Ui();
        moduleManager.addModule(tempModule);
    }

    @Test
    void execute_updateModule_success() throws InvalidArgumentException, InvalidCommandException, IOException {
        Command cmd = commandParser.parseCommand("update 1 \"test2\"");
        CommandResult cmdResult = cmd.execute(ui, moduleManager);
        assertNull(moduleManager.getModule(tempModule));
        assertNotNull(moduleManager.getModule("test2"));
        Command cmd2 = commandParser.parseCommand("update 1 \"CS2106\"");
        CommandResult cmdResult2 = cmd.execute(ui, moduleManager);
        assertNull(moduleManager.getModule("test2"));
        assertNotNull(moduleManager.getModule("CS2106"));
    }

    @Test
    void execute_updateModule_throwException() throws InvalidArgumentException, InvalidCommandException, IOException {
        assertThrows(InvalidArgumentException.class,
            () -> commandParser.parseCommand("update 100 \"asdasd\"").execute(ui,
                moduleManager));
        moduleManager.addModule("test2");
        assertThrows(InvalidArgumentException.class,
            () -> commandParser.parseCommand("update 1 \"test2\"").execute(ui,
                moduleManager));
    }
}
