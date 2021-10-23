package terminus.command.module;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import com.itextpdf.text.DocumentException;
import java.io.IOException;
import org.junit.jupiter.api.AfterAll;
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

public class AddModuleCommandTest {

    private ModuleCommandParser commandParser;
    private ModuleManager moduleManager;
    private Ui ui;
    private ModuleStorage moduleStorage;

    @AfterAll
    static void reset() throws IOException {
        ModuleStorage moduleStorage = ModuleStorage.getInstance();
        moduleStorage.cleanAfterDeleteModule("test");
    }

    @BeforeEach
    void setUp() {
        this.moduleStorage = ModuleStorage.getInstance();
        this.moduleStorage.init(TestFilePath.SAVE_FILE);
        this.moduleManager = new ModuleManager();
        this.commandParser = ModuleCommandParser.getInstance();
        this.ui = new Ui();
    }

    @Test
    void execute_addModule_success()
            throws InvalidArgumentException, InvalidCommandException, IOException {
        Command cmd = commandParser.parseCommand("add \"test\"");
        CommandResult cmdResult = cmd.execute(ui, moduleManager);
        assertTrue(cmdResult.isOk());
        assertNotNull(moduleManager.getModule("test"));
    }

    @Test
    void execute_addModule_throwsException() throws InvalidArgumentException, InvalidCommandException {

        assertThrows(InvalidArgumentException.class,
            () -> commandParser.parseCommand("add \"test\" \"test2\"").execute(ui,
                    moduleManager));
        assertThrows(InvalidArgumentException.class,
            () -> commandParser.parseCommand("add   ").execute(ui,
                    moduleManager));
    }
}
