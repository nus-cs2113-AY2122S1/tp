package terminus.command.module;

import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import terminus.command.Command;
import terminus.command.CommandResult;
import terminus.exception.InvalidArgumentException;
import terminus.exception.InvalidCommandException;
import terminus.module.ModuleManager;
import terminus.parser.ModuleCommandParser;
import terminus.ui.Ui;

public class DeleteModuleCommandTest {

    private ModuleCommandParser commandParser;
    private ModuleManager moduleManager;
    private Ui ui;

    private static final String tempModule = "test";

    @BeforeEach
    void setUp() {
        this.moduleManager = new ModuleManager();
        this.commandParser = ModuleCommandParser.getInstance();
        moduleManager.setModule(tempModule);
        this.ui = new Ui();
    }

    @Test
    void execute_deleteModule_success() throws InvalidArgumentException, InvalidCommandException {
        Command cmd = commandParser.parseCommand("delete 1");
        CommandResult cmdResult = cmd.execute(ui, moduleManager);
        assertTrue(cmdResult.isOk());
        assertNull(moduleManager.getModule(tempModule));
    }

    @Test
    void execute_deleteModule_throwsException() throws InvalidArgumentException, InvalidCommandException {

        assertThrows(InvalidArgumentException.class,
            () -> commandParser.parseCommand("delete -1").execute(ui,
                    moduleManager));
        assertThrows(InvalidArgumentException.class,
            () -> commandParser.parseCommand("delete").execute(ui,
                    moduleManager));
        assertThrows(InvalidArgumentException.class,
            () -> commandParser.parseCommand("delete 100").execute(ui,
                    moduleManager));
    }
}
