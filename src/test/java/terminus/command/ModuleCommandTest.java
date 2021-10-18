package terminus.command;

import org.junit.jupiter.api.BeforeEach;
import terminus.module.ModuleManager;
import terminus.parser.MainCommandParser;
import terminus.parser.ModuleCommandParser;
import terminus.ui.Ui;

public class ModuleCommandTest {
    private ModuleCommandParser commandParser;
    private Ui ui;

    private ModuleManager moduleManager;

    private String tempModule = "test";

    @BeforeEach
    void setUp() {
        commandParser = ModuleCommandParser.getInstance();
        moduleManager = new ModuleManager();
        ui = new Ui();
    }
}
