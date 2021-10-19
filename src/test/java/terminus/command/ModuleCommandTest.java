package terminus.command;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import terminus.exception.InvalidArgumentException;
import terminus.exception.InvalidCommandException;
import terminus.module.ModuleManager;
import terminus.parser.MainCommandParser;
import terminus.parser.ModuleCommandParser;
import terminus.ui.Ui;

public class ModuleCommandTest {

    private MainCommandParser commandParser;
    private Ui ui;

    private ModuleManager moduleManager;

    private String tempModule = "test";

    @BeforeEach
    void setUp() {
        commandParser = MainCommandParser.getInstance();
        moduleManager = new ModuleManager();
        ui = new Ui();
    }

    @Test
    void execute_module_success() throws InvalidArgumentException, InvalidCommandException {
        Command cmd = commandParser.parseCommand("module");
        CommandResult cmdResult = cmd.execute(ui, moduleManager);
        assertTrue(cmdResult.isOk());
        assertTrue(cmdResult.getAdditionalData() instanceof ModuleCommandParser);
        cmd = commandParser.parseCommand("module add \"test\"");
        cmdResult = cmd.execute(ui, moduleManager);
        assertTrue(cmdResult.isOk());
        assertEquals(1, moduleManager.getAllModules().length);
    }

    @Test
    void execute_module_throwsException() throws InvalidArgumentException, InvalidCommandException {
        assertThrows(InvalidArgumentException.class, () -> commandParser.parseCommand("module add").execute(ui,
                moduleManager));
        assertThrows(InvalidArgumentException.class, () -> commandParser.parseCommand("module delete asd").execute(ui,
                moduleManager));
        assertThrows(InvalidCommandException.class, () -> commandParser.parseCommand("module asdsasd").execute(ui,
                moduleManager));
    }
}
